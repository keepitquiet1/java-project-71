package hexlet.code.formatters;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;


public class Plain {

    public static String plainFormatter(Map<String, String> mapOfDiff, Map<String,
            Object> map1, Map<String, Object> map2)  {

        StringBuilder str = new StringBuilder();

        for (Map.Entry<String, String> element : mapOfDiff.entrySet()) {

            var value1 = map1.get(element.getKey());
            var value2 = map2.get(element.getKey());

            var valueResult1 = prepareValues(value1);
            var valueResult2 = prepareValues(value2);

            String forTest = element.getValue();

            switch (forTest) {
                case "deleted" -> str.append("Property " + "'" + element.getKey() + "'" + " was removed" + "\n");
                case "added" -> str.append("Property " + "'" + element.getKey() + "'" + " was added with value: "
                        + valueResult2 + "\n");
                case "changed" -> str.append("Property " + "'" + element.getKey() + "'" + " was updated. " + "From "
                        + valueResult1 + " to " + valueResult2 + "\n");
                case "unchanged" -> {
                    break;
                }
                default -> {
                    return  "Something went wrong! Could not create a resulting string.";
                }
            }
        }
        return str.toString();
    }

    private static String prepareValues(Object value) {

        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Map || value instanceof Array || value instanceof List) {
            return "[complex value]";
        }
        return String.valueOf(value);
    }
}
