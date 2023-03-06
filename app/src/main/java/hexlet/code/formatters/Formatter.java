package hexlet.code.formatters;


import java.util.Map;

import static hexlet.code.formatters.Json.jsonFormatter;
import static hexlet.code.formatters.Plain.plainFormatter;

public class Formatter {

    public static String format(Map<String, String> mapOfDiff, Map<String, Object> map1,
                                Map<String, Object> map2, String style) {

        String result;

        switch (style) {
            case "stylish" -> {
                result = Stylish.stylishFormatter(mapOfDiff, map1, map2);
            }
            case "plain" -> {
                result = plainFormatter(mapOfDiff, map1, map2);
            }
            case "json" -> {
                result = jsonFormatter(mapOfDiff, map1, map2);
            }
            default -> {
                return "Unsupported style!";
            }
        }
        return result;
    }
}
