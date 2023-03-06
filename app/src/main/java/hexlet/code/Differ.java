package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {

        Path fullPath1 = getFullPath(filePath1);
        Path fullPath2 = getFullPath(filePath2);

        String file1Extension = getFileExtension(String.valueOf(fullPath1));
        String file2Extension = getFileExtension(String.valueOf(fullPath2));

        String contentOfFile1 = Files.readString(fullPath1);
        String contentOfFile2 = Files.readString(fullPath2);

        ObjectMapper mapper1 = mapperFactory(file1Extension);
        ObjectMapper mapper2 = mapperFactory(file2Extension);

        Map<String, Object> map1 = Parser.getData(contentOfFile1, mapper1);
        Map<String, Object> map2 = Parser.getData(contentOfFile2, mapper2);

        Map<String, String> mapOfDiff = DifferenceFinder.findDifference(map1, map2);

        if (mapOfDiff.isEmpty()) {
            return "The files are empty!";
        }

        String result = Formatter.format(mapOfDiff, map1, map2, formatName);

        return result;
    }

    public static Path getFullPath(String path) {

        String loweredPath = path.toLowerCase();

        Path fullPath = Paths.get(loweredPath).toAbsolutePath().normalize();

        return fullPath;
    }

    private static ObjectMapper mapperFactory(String extension) throws Exception {

        switch (extension) {
            case ".json" -> {
                return new ObjectMapper();
            }
            case ".yml" -> {
                return new YAMLMapper();
            }
            default -> throw new Exception("Unsupported file extension!");
        }
    }

    private static String getFileExtension(String path) {

        return path.substring(path.indexOf("."));

    }
}
