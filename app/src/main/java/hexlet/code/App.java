package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Command(name = "app", mixinStandardHelpOptions = true, version = "app 0.0.1",
        description = "Compare 2 JSONs.")
public class App implements Callable<Integer> {
    @Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.0.1",
            description = "Compares two configuration files and shows a difference.")

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "SHA-256";

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        String test1 = fileToString(filepath1);
        String test2 = fileToString(filepath2);

        String lines1[] = test1.split("\\n");
        String lines2[] = test2.split("\\n");

        Map<String, String> map1 = getMap(lines1);
        Map<String, String> map2 = getMap(lines2);


        Map<String, String> resultMap = compareJson(map1, map2);

        printSortedMap(resultMap);
        return 0;
    }


    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    private static String fileToString(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }

    private static Map<String, String> getMap(String[] file1) {
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