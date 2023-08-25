package com.crypto.project.helpers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class TimestampDeserializer extends JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(jsonParser.getValueAsLong());
        return new Timestamp(calendar.getTimeInMillis());
    }
}
