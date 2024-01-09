package com.digital.signage.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.sql.Time;

/**
 * @author -Ravi Kumar created on 12/27/2022 11:17 PM
 * @project - Digital Sign-edge
 */
public class TimeObjectSerializer extends StdSerializer<Time> {

    public TimeObjectSerializer() {
        super(Time.class);
    }

    public TimeObjectSerializer(Class<Time> t) {
        super(t);
    }

    public TimeObjectSerializer(JavaType type) {
        super(type);
    }

    public TimeObjectSerializer(Class<Time> t, boolean dummy) {
        super(t, dummy);
    }

    public TimeObjectSerializer(StdSerializer<Time> src) {
        super(src);
    }

    @Override
    public void serialize(Time value, JsonGenerator generator, SerializerProvider provider)
            throws IOException {
        if (!ObjectUtils.isEmpty(value)) {
            String format = value.toString();
            generator.writeString(format.length() == 8 ? format : format + ":00");
        }
    }
}
