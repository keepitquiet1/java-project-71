package hexlet.code.formatters;


import java.util.Iterator;
import java.util.Map;

public class Json {

    /*public static String jsonFormatter(Map<String, String> mapOfDiff) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(mapOfDiff);
    }*/

    public static String jsonFormatter(Map<String, String> mapOfDiff) {

        StringBuilder result = new StringBuilder();

        result.append("{");
        result.append("\n");

        Iterator<Map.Entry<String, String>> iterator = mapOfDiff.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            result.append("  \"" + entry.getKey() + "\": \"" + entry.getValue() + "\""
                    + (iterator.hasNext() ? ",\n" : "\n"));
        }

        result.append("}" + "\n");

        return result.toString();
    }
}
