package hexlet.code.formatters;

import hexlet.code.Value;

import java.util.Map;

public class Stylish {

    public static String stylishFormatter(Map<String, Value> mapOfDiff) {

        StringBuilder str = new StringBuilder();

        str.append("{\n");

        for (Map.Entry<String, Value> element : mapOfDiff.entrySet()) {

            String status = element.getValue().getStatus();
            String key = element.getKey();
            var value1 = element.getValue().getValue();
            var value2 = element.getValue().getNewValue();

            switch (status) {
                case "deleted" -> str.append("- ").append(key).append(": ").append(value1).append("\n");
                case "added" -> str.append("+ ").append(key).append(": ").append(value2).append("\n");
                case "unchanged" -> str.append("  ").append(key).append(": ").append(value1).append("\n");
                case "changed" -> str.append("- ").append(key).append(": ").append(value1).
                        append("\n").append("+ ").append(key).append(": ").append(value2).append("\n");
                default -> {
                    return "Something went wrong for input: " + element.getValue();
                }
            }
        }

        str.append("}");
        return str.toString();
    }
}
