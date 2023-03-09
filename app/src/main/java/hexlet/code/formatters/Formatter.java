package hexlet.code.formatters;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, String> mapOfDiff,
                                Map<String, Object> map1,
                                Map<String, Object> map2,
                                String style) throws JsonProcessingException {

        String result;

        switch (style) {
            case "stylish" -> {
                result = Stylish.stylishFormatter(mapOfDiff, map1, map2);
            }
            case "plain" -> {
                result = Plain.plainFormatter(mapOfDiff, map1, map2);
            }
            case "json" -> {
                result = Json.jsonFormatter(mapOfDiff);
            }
            default -> throw new RuntimeException("Unsupported style: " + style);
        }
        return result;
    }
}
