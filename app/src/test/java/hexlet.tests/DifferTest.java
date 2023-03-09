package hexlet.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static String pathJSON1 = "src/test/resources/mockData/nested.json";
    private static String pathJSON2 = "src/test/resources/mockData/nested2.json";
    ;
    private static String pathYML1 = "src/test/resources/mockData/nested.yml";
    ;
    private static String pathYML2 = "src/test/resources/mockData/nested2.yml";
    ;

    @BeforeEach
    public void beforeEach() throws Exception {

        resultJson = readMock("res.json");
        resultPlain = readMock("res-plain.txt");
        resultStylish = readMock("res-stylish.txt");
    }

    private static String readMock(String fileName) throws Exception {
        Path filePath = getMockPath(fileName);
        return Files.readString(filePath);
    }

    private static Path getMockPath(String fileName) {
        return Paths.get("src", "test", "resources", "mockData", fileName)
                .toAbsolutePath().normalize();
    }

    @Test
    public void createJSON() throws Exception {

        String actual1 = Differ.generate(pathJSON1, pathJSON2);
        String actual2 = Differ.generate(pathJSON1, pathJSON2, "stylish");
        String actual3 = Differ.generate(pathJSON1, pathJSON2, "plain");
        String actual4 = Differ.generate(pathJSON1, pathJSON2, "json");

        String expected12 = resultStylish;
        String expected3 = resultPlain;
        String expected4 = resultJson;

        assertEquals(expected12, actual1);
        assertEquals(expected12, actual2);
        assertEquals(expected3, actual3);
        assertEquals(expected4, actual4);
    }

    @Test
    public void createYML() throws Exception {

        var actual1 = Differ.generate(pathYML1, pathYML2, "stylish");
        var actual2 = Differ.generate(pathYML1, pathYML2, "stylish");
        var actual3 = Differ.generate(pathYML1, pathYML2, "plain");
        var actual4 = Differ.generate(pathYML1, pathYML2, "json");

        var expected12 = resultStylish;
        var expected3 = resultPlain;
        var expected4 = resultJson;

        assertEquals(expected12, actual1);
        assertEquals(expected12, actual2);
        assertEquals(expected3, actual3);
        assertEquals(expected4, actual4);

    }

}
