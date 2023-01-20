package com.ndz.wheatmall.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.ndz.wheatmall.common.enums.BaseEnum;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

/**
 * 枚举类的反序列化，非使用索引不从0开始的状态值枚举
 *
 * @author nidazhong
 * @date 2023/01/20
 */
public class BaseEnumDeserializer extends JsonDeserializer<BaseEnum> {

    @Override
    public BaseEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode value = jsonParser.getCodec().readTree(jsonParser);
        String currentName = jsonParser.currentName();
        Object currentValue = jsonParser.getCurrentValue();
        Class findPropertyType = BeanUtils.findPropertyType(currentName, currentValue.getClass());
        if (findPropertyType.isEnum()) {
            BaseEnum[] enumConstants = (BaseEnum[]) findPropertyType.getEnumConstants();
            for (BaseEnum e : enumConstants) {
                if (e.getCode().equals(value.asInt())) {
                    return e;
                }
            }
        }
        return null;
    }
}
