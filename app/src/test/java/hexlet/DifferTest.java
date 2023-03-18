package hexlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DifferTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static String pathJSON1 = "src/test/resources/fixtures/nested.json";
    private static String path1 = "src/test/resources/fixtures/nested";
    private static String path2 = "src/test/resources/fixtures/nested2";
    private static String pathJSON2 = "src/test/resources/fixtures/nested2.json";
    private static String pathYML1 = "src/test/resources/fixtures/nested.yml";
    private static String pathYML2 = "src/test/resources/fixtures/nested2.yml";

    @BeforeEach
    public void beforeEach() throws Exception {

        resultJson = readMock("res.json").replaceAll("\r", "");
        resultPlain = readMock("res-plain.txt").replaceAll("\r", "");
        resultStylish = readMock("res-stylish.txt").replaceAll("\r", "");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = (path1 + "." + format).toString();
        String filePath2 = (path2 + "." + format).toString();

        assertEquals(resultStylish, Differ.generate(filePath1, filePath2));

        assertEquals(resultStylish, Differ.generate(filePath1, filePath2, "stylish"));

        assertEquals(resultPlain, Differ.generate(filePath1, filePath2, "plain"));

        assertEquals(resultJson, Differ.generate(filePath1, filePath2, "json"));

    }

    private static String readMock(String fileName) throws Exception {
        Path filePath = getMockPath(fileName);
        return Files.readString(filePath);
    }

    private static Path getMockPath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

}
