package org.mockserver.client.serialization.serializers.body;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Charsets;
import org.junit.Test;
import org.mockserver.client.serialization.ObjectMapperFactory;
import org.mockserver.client.serialization.model.StringBodyDTO;
import org.mockserver.model.StringBody;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockserver.model.Not.not;

public class StringBodyDTOSerializerTest {

    @Test
    public void shouldSerializeStringBodyDTO() throws JsonProcessingException {
        assertThat(ObjectMapperFactory.createObjectMapper().writeValueAsString(new StringBodyDTO(new StringBody("string_body"))),
                is("\"string_body\""));
    }

    @Test
    public void shouldSerializeStringBodyDTOWithCharset() throws JsonProcessingException {
        assertThat(ObjectMapperFactory.createObjectMapper().writeValueAsString(new StringBodyDTO(new StringBody("string_body", Charsets.UTF_16))),
                is("{\"charset\":\"UTF-16\",\"type\":\"STRING\",\"string\":\"string_body\"}"));
    }

    @Test
    public void shouldSerializeStringBodyDTOWithNot() throws JsonProcessingException {
        assertThat(ObjectMapperFactory.createObjectMapper().writeValueAsString(new StringBodyDTO(not(new StringBody("string_body")))),
                is("{\"not\":true,\"type\":\"STRING\",\"string\":\"string_body\"}"));
    }

    @Test
    public void shouldSerializeStringBodyDTOWithCharsetAndNot() throws JsonProcessingException {
        assertThat(ObjectMapperFactory.createObjectMapper().writeValueAsString(new StringBodyDTO(not(new StringBody("string_body", Charsets.UTF_16)))),
                is("{\"not\":true,\"charset\":\"UTF-16\",\"type\":\"STRING\",\"string\":\"string_body\"}"));
    }

}