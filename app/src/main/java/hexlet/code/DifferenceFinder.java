package hexlet.code;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DifferenceFinder {

    public static Map<String, Value> findDifference(Map<String, Object> firstMap, Map<String, Object> secondMap) {

        Map<String, Value> result = new LinkedHashMap<>();

        Set<String> keySet = new TreeSet<>(firstMap.keySet());
        keySet.addAll(secondMap.keySet());

        for (String key : keySet) {
            if (!firstMap.containsKey(key)) {
                result.put(key, new Value("added", null, secondMap.get(key)));
            } else if (!secondMap.containsKey(key)) {
                result.put(key, new Value("deleted", firstMap.get(key)));
            } else if (Objects.equals(firstMap.get(key), secondMap.get(key))) {
                result.put(key, new Value("unchanged", firstMap.get(key)));
            } else if (!Objects.equals(firstMap.get(key), secondMap.get(key))) {
                result.put(key, new Value("changed", firstMap.get(key), secondMap.get(key)));
            }
        }

        return result;
    }
}
