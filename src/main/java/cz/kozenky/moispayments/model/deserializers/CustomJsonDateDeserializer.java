package cz.kozenky.moispayments.model.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.Instant;

public class CustomJsonDateDeserializer extends JsonDeserializer<DateTime> {

    @Override
    public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return Instant.parse(p.getText()).toDateTime();
    }
}
