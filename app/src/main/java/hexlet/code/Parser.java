package hexlet.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {
    public static String fileToString(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        return stringBuilder.toString();
    }

    public static Map<String, String> getMap(String[] file1) {
        Map<String, String> result = new HashMap<>();
        for (String s : file1) {
            if (!s.startsWith("{") && !s.startsWith("}")) {
                result.put(s.split(": ")[0].replaceAll("[\" ]", ""), s.split(":")[1].replaceAll("[\r,\" ]", ""));
            }
        }
        return result;
    }

    public static Map<String, String> compareJson(Map<String, String> json1, Map<String,
            String> json2) {
        Map<String, String> result = new HashMap<>();
        for (String key : json1.keySet()) {
            if (json2.containsKey(key)) {
                if (!json1.get(key).equals(json2.get(key))) {
                    result.put("-" + key, json1.get(key));
                    result.put("+" + key, json2.get(key));
                } else {
                    result.put(key, json1.get(key));
                }
            } else {
                result.put("-" + key, json1.get(key));
            }
        }
        for (String key : json2.keySet()) {
            if (!json1.containsKey(key)) {
                result.put("+" + key, json2.get(key));
            }
        }
        return result;
    }

    public static void printSortedMap(Map<String, String> map) {

        Map<String, String> sortedMap = map.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().replaceAll("[+-]", "")))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));
        System.out.println("{");
        for (Map.Entry e : sortedMap.entrySet()) {
            System.out.println(e);
        }
        System.out.println("}");

    }
}
