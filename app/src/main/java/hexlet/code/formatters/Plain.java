package hexlet.code.formatters;

import hexlet.code.Value;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;


public class Plain {

    public static String plainFormatter(Map<String, Value> mapOfDiff) {

        StringBuilder str = new StringBuilder();

        for (Map.Entry<String, Value> element : mapOfDiff.entrySet()) {

            String status = element.getValue().getStatus();
            String key = element.getKey();
            var value1 = element.getValue().getValue();
            var value2 = element.getValue().getNewValue();

            var valueResult1 = prepareValues(value1);
            var valueResult2 = prepareValues(value2);

            switch (status) {
                case "deleted" -> str.append("Property " + "'").append(key).append("'").append(" was removed")
                        .append("\n");
                case "added" -> str.append("Property " + "'").append(key).append("'").append(" was added with value: ")
                        .append(valueResult2).append("\n");
                case "changed" -> str.append("Property " + "'").append(key).append("'").append(" was updated. ")
                        .append("From ").append(valueResult1).append(" to ").append(valueResult2).append("\n");
                case "unchanged" -> { }
                default -> {
                    return "Something went wrong for input: " + element.getValue();
                }
            }
        }
        return str.toString().trim();
    }

    private static String prepareValues(Object value) {

        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Map || value instanceof Array || value instanceof List) {
            return "[complex value]";
        } else if (value == null) {
            return "null";
        }
        return String.valueOf(value);
    }
}
