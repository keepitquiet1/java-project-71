package hexlet.code.formatters;


import java.util.Map;

public class Stylish {

    public static String stylishFormatter(Map<String, String> mapOfDiff, Map<String,
            Object> map1, Map<String, Object> map2) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Map.Entry<String, String> element : mapOfDiff.entrySet()) {
            switch (element.getValue()) {
                case "deleted" ->
                        stringBuilder.append("- " + element.getKey() + ": " + map1.get(element.getKey()) + "\n");
                case "added" ->
                        stringBuilder.append("+ " + element.getKey() + ": " + map2.get(element.getKey()) + "\n");
                case "unchanged" ->
                        stringBuilder.append("  " + element.getKey() + ": " + map1.get(element.getKey()) + "\n");
                case "changed" ->
                        stringBuilder.append("- " + element.getKey() + ": " + map1.get(element.getKey()) + "\n"
                                + "+ " + element.getKey() + ": " + map2.get(element.getKey()) + "\n");
                default -> {
                    return "Something went wrong!";
                }
            }
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }
}
