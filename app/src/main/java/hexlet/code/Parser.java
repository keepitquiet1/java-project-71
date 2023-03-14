package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> getData(String data, ObjectMapper mapper) throws Exception {

        return mapper.readValue(data, Map.class);
    }

    public static ObjectMapper mapperFactory(String dataFormat) throws Exception {

        switch (dataFormat) {
            case "json" -> {
                return new ObjectMapper();
            }
            case "yml", "yaml" -> {
                return new YAMLMapper();
            }
            default -> throw new Exception("Unsupported type!");
        }
    }

}
