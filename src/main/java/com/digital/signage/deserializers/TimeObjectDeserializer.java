package com.digital.signage.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.sql.Time;

/**
 * @author -Ravi Kumar created on 12/27/2022 11:17 PM
 * @project - Digital Sign-edge
 */
public class TimeObjectDeserializer extends StdDeserializer<Time> {

    public TimeObjectDeserializer() {
        super(Time.class);
    }

    protected TimeObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    protected TimeObjectDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected TimeObjectDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Time deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String val = p.getValueAsString();
        if (val != null) {
            val = val.trim();
            if (val.length() == 5) {
                val = val + ":00";
            }
            return Time.valueOf(val);
        }
        return null;
    }
}

