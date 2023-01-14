package com.ndz.wheatmall.config;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.reactivex.rxjava3.annotations.Nullable;

/**
 * 实现jackson对于LocalDate、LocalDateTime、LocalTime类型数据的格式化
 * 此类为全局默认配置，如类还想自定义则用@JsonFormat注解
 */
@Configuration
public class JacksonLocalDateTimeConfig {
    
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 适配自定义序列化和反序列化策略
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // 序列化给前端不用时间戳， 如果返回前端也要时间戳则自己实现
            // LocalDateTime
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
            // 如有其他类型，以下还可添加
        };
    }

    /**
     * 序列化: LocalDateTime（后端） -> 时间戳（前端）
     */
    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializers)
                throws IOException {
            if (localDateTime != null) {
                String dateTime = localDateTime.format(DateTimeFormatter.ofPattern(DATETIME_PATTERN));
                jsonGenerator.writeString(dateTime);
                // 时间戳格式输出
//              jsonGenerator.writeNumber(localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());

            }
        }
    }

    /**
     * 反序列化: 时间戳（前端） -> LocalDateTime（后端）
     */
    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            long timestamp = jsonParser.getValueAsLong();
            if (timestamp > 0) {
                // 毫秒级，13位时间戳
                return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
            } else {
                return null;
            }
        }
    }

    /**
     * 自定义配置类方式可以对日期进行统一的格式化处理，（前端）13位时间戳 -> （后端）自定义格式化
     * 目前只配对了LocalDateTime， 其他继续写注入Formatter
     */
    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<>() {
            @Nullable
            @Override
            public LocalDateTime parse(@Nullable String text, @Nullable Locale locale) {
                if (!StringUtils.hasText(text)) return null;
                return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
            }

            @Nullable
            @Override
            public String print(@Nullable LocalDateTime localDateTime, @Nullable Locale locale) {
                if (Objects.isNull(localDateTime)) return null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
                return formatter.format(localDateTime);
            }

        };
    }


}
