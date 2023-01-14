package com.ndz.wheatmall.config;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

import io.reactivex.rxjava3.annotations.Nullable;

/**
 * 实现jackson对于LocalDate、LocalDateTime、LocalTime类型数据的格式化
 * 此类为全局默认配置，如类还想自定义则用@JsonFormat注解
 *
 * 实现：
 *   1、无论前端传时间戳，yyyy-MM-dd 还是 yyyy-MM-dd HH:mm:ss都转换后端为LocalDateTime对象接受，并且后端格式化为yyyy-MM-dd HH:mm:ss
 *   2、后端返回统一时间戳LocalDateTime对象给前端，并统一格式化为yyyy-MM-dd HH:mm:ss
 */
@Slf4j
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
            String valueStr = jsonParser.getValueAsString();
            if (StrUtil.isEmpty(valueStr)) return null;
            if (valueStr.matches("\\d{13}")) {
                // 毫秒级，13位时间戳
                return LocalDateTime.ofEpochSecond(Long.parseLong(valueStr) / 1000, 0, ZoneOffset.ofHours(8));
            } else if (valueStr.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
                // 标准年月日 时分秒 YYYY-MM-dd HH:mm:ss
                return LocalDateTime.parse(valueStr, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
            } else if(valueStr.matches("^\\d{4}-\\d{2}-\\d{2}")){
                // 只有年月日 YYYY-MM-dd
                return LocalDateTime.parse(valueStr +" 00:00:00", DateTimeFormatter.ofPattern(DATETIME_PATTERN));
            } else  {
                return null;
            }
        }
    }




}
