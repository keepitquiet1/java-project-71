package hexlet.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.Differ;
import hexlet.code.DifferenceFinder;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Map;

public class DifferTest {

    private Map<String, Object> map1;
    private Map<String, Object> map2;
    private final Map<String, Object> emptyMap = Collections.emptyMap();
    private final Map<String, Object> emptyMap2 = Collections.emptyMap();

    private final ObjectMapper mapper1 = new ObjectMapper();
    private final ObjectMapper mapper2 = new YAMLMapper();

    @BeforeEach
    public void beforeEach() {
        map1 = Map.of("timeout", 50, "host", "hexlet.io",
                "proxy", "123.234.53.22", "follow", false);

        map2 = Map.of("timeout", 20, "verbose", true,
                "host", "hexlet.io");
    }

    @Test
    public void getDataTest() throws Exception {

        String content1 = Files.readString(Differ.getFullPath("src/test/resources/test.json"));
        String content2 = Files.readString(Differ.getFullPath("src/test/resources/test2.json"));

        Map<String, Object> actual1 = Parser.getData(content1, mapper1);
        Map<String, Object> actual2 = Parser.getData(content2, mapper2);

        Map<String, Object> expected1 = map1;
        Map<String, Object> expected2 = map2;

        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);

    }

    @Test
    public void findDifferenceTest() {
        Map<String, String> expected2 = Map.of("follow", "deleted", "host", "unchanged", "proxy", "deleted",
                "timeout", "changed", "verbose", "added");
        Map<String, String> expected3 = Map.of("timeout", "added", "verbose", "added", "host", "added");
        Map<String, String> expected4 = Map.of("host", "deleted", "timeout", "deleted", "proxy", "deleted",
                "follow", "deleted");

        Map<String, String> actual1 = DifferenceFinder.findDifference(emptyMap, emptyMap2);
        Map<String, String> actual2 = DifferenceFinder.findDifference(map1, map2);
        Map<String, String> actual3 = DifferenceFinder.findDifference(emptyMap, map2);
        Map<String, String> actual4 = DifferenceFinder.findDifference(map1, emptyMap);

        assertThat(actual1).isEqualTo(emptyMap);
        assertThat(actual2).isEqualTo(expected2);
        assertThat(actual3).isEqualTo(expected3);
        assertThat(actual4).isEqualTo(expected4);
    }

    @Test
    public void generateWithEmptyFile() throws Exception {

        String expected1 = "The files are empty!";
        String actual1 = Differ.generate("src/test/resources/empty.json", "src/test/resources/empty2.json");

        assertThat(actual1).isEqualTo(expected1);

    }

    @Test
    public void generateStylishYml() throws Exception {

        String expected4 = """
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                """;

        String actual4 = Differ.generate("src/test/resources/test.yml", "src/test/resources/test2.yml");

        assertThat(actual4).isEqualTo(expected4);

    }

    @Test
    public void generateStylishJson() throws Exception {

        String expected1 = """
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                """;

        String expected2 = """
                + host: hexlet.io
                + timeout: 20
                + verbose: true
                """;

        String expected3 = """
                - follow: false
                - host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                """;

        String expected4 = """
                  chars1: [a, b, c]
                - chars2: [d, e, f]
                + chars2: false
                - checked: false
                + checked: true
                - default: null
                + default: [value1, value2]
                - id: 45
                + id: null
                - key1: value1
                + key2: value2
                  numbers1: [1, 2, 3, 4]
                - numbers2: [2, 3, 4, 5]
                + numbers2: [22, 33, 44, 55]
                - numbers3: [3, 4, 5]
                + numbers4: [4, 5, 6]
                + obj1: {nestedKey=value, isNested=true}
                - setting1: Some value
                + setting1: Another value
                - setting2: 200
                + setting2: 300
                - setting3: true
                + setting3: none
                """;

        String actual1 = Differ.generate("src/test/resources/test.json", "src/test/resources/test2.json");
        String actual2 = Differ.generate("src/test/resources/empty.json", "src/test/resources/test2.json");
        String actual3 = Differ.generate("src/test/resources/test.json", "src/test/resources/empty.json");
        String actual4 = Differ.generate("src/test/resources/nested.json", "src/test/resources/nested2.json");

        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
        assertThat(actual3).isEqualTo(expected3);
        assertThat(actual4).isEqualTo(expected4);

    }
}
