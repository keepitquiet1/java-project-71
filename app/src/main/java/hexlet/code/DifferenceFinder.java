package hexlet.code;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DifferenceFinder {

    public static Map<String, String> findDifference(Map<String, Object> firstMap, Map<String, Object> secondMap) {

        Map<String, String> result = new LinkedHashMap<>();

        Set<String> keySet = new TreeSet<>(firstMap.keySet());
        keySet.addAll(secondMap.keySet());

        for (String key : keySet) {
            if (!firstMap.containsKey(key)) {
                result.put(key, "added");
            } else if (!secondMap.containsKey(key)) {
                result.put(key, "deleted");
            } else if (Objects.equals(firstMap.get(key), secondMap.get(key))) {
                result.put(key, "unchanged");
            } else if (!Objects.equals(firstMap.get(key), secondMap.get(key))) {
                result.put(key, "changed");
            }
        }

        return result;
    }
}