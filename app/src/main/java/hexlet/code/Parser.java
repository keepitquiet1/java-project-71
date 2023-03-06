package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getData(String contentOfFile, ObjectMapper type) throws Exception {

        Map<String, Object> result = type.readValue(contentOfFile, Map.class);

        return result;
    }
}
