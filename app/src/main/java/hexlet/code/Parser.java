package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> getData(String contentOfFile, ObjectMapper mapper) throws Exception {

        return mapper.readValue(contentOfFile, Map.class);
    }

    public static ObjectMapper mapperFactory(String extension) throws Exception {

        switch (extension) {
            case "json" -> {
                return new ObjectMapper();
            }
            case "yml", "yaml" -> {
                return new YAMLMapper();
            }
            default -> throw new Exception("Unsupported file extension!");
        }
    }
}
