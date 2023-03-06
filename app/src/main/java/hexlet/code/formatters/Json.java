package hexlet.code.formatters;


import java.util.Map;

public class Json {

    /*public static String jsonFormatter(Map<String, String> mapOfDiff) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(mapOfDiff);
    }*/

    public static String jsonFormatter(Map<String, String> mapOfDiff,
                                       Map<String, Object> map1, Map<String, Object> map2) {

        StringBuilder result = new StringBuilder();

        result.append("{");
        result.append("\n");

        for (Map.Entry<String, String> element : mapOfDiff.entrySet()) {

            switch (element.getValue()) {
                case "deleted" -> result.append("\"" + element.getKey() + "=" + map1.get(element.getKey()) + "\"" + ": "
                        + "\"" + element.getValue() + "\"" + "," + "\n");
                case "added" -> result.append("\"" + element.getKey() + "=" + map2.get(element.getKey()) + "\"" + ": "
                        + "\"" + element.getValue() + "\"" + "," + "\n");
                case "unchanged" -> result.append("\"" + element.getKey() + "=" + map1.get(element.getKey())
                        + "\"" + ": " + "\"" + element.getValue() + "\"" + "," + "\n");
                case "changed" -> result.append("\"" + element.getKey() + "=" + map1.get(element.getKey()) + "\"" + ": "
                        + "\"deleted\"" + "," + "\n" + "\"" + element.getKey() + "="
                        + map2.get(element.getKey()) + "\"" + ": " + "\"added\"" + "," + "\n");
                default -> {
                    return "Something went wrong!";
                }
            }
        }

        var resultLength = result.length();
        result.delete(resultLength - 2, resultLength - 1);
        result.append("}" + "\n");

        return result.toString();
    }
}
