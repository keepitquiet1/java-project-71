package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> getData(String contentOfFile, ObjectMapper mapper) throws Exception {

        return mapper.readValue(contentOfFile, Map.class);
    }

}
