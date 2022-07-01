package it.bologna.ausl.bdm.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TimeZone;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author andrea
 */
public interface Dumpable {

    public static final String BDM_CLASS_TYPE = "__BDM_CLASS_TYPE__";

    public static ObjectMapper buildCustomObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.setTimeZone(TimeZone.getDefault());
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        return mapper;
    }
    
    public default <T extends Dumpable> String dump() throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JodaModule());
//        mapper.setTimeZone(TimeZone.getDefault());
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        ObjectMapper mapper = buildCustomObjectMapper();
        String writeValueAsString = mapper.writeValueAsString(this);
        return writeValueAsString;
    }

    public static <T extends Dumpable> T load(String json, Class<T> dumpableCass) throws IOException, ClassNotFoundException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JodaModule());
//        mapper.setTimeZone(TimeZone.getDefault());
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
/*
         JsonNode jn = mapper.readTree(json);
         for (JsonNode n : jn.findValues(BDM_CLASS_TYPE)) {
         String className = n.asText();
         Class<?> classz;
         try {
         classz = Class.forName("it.bologna.ausl.bdm.workflows.processes." + className);
         } catch (ClassNotFoundException ex) {
         classz = Class.forName("it.bologna.ausl.bdm.workflows.tasks." + className);
         }
         mapper.registerSubtypes(new NamedType(classz));
         }
         */
        ObjectMapper mapper = buildCustomObjectMapper();
        return mapper.readValue(json, dumpableCass);
    }

    public default <T extends Dumpable> void dumpToOutputStream(OutputStream out) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JodaModule());
//        mapper.setTimeZone(TimeZone.getDefault());
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ObjectMapper mapper = buildCustomObjectMapper();
        mapper.writeValue(out, this);
    }

    public static <T extends Dumpable> T loadFromInputStream(InputStream is, Class<T> dumpableCass) throws IOException, ClassNotFoundException {
        String s = IOUtils.toString(is);
        return load(s, dumpableCass);

    }
}
