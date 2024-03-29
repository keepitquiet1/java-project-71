package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        Path absolutePath1 = getAbsolutePath(filePath1);
        Path absolutePath2 = getAbsolutePath(filePath2);

        String file1Extension = getFormat(String.valueOf(absolutePath1));
        String file2Extension = getFormat(String.valueOf(absolutePath2));

        String contentOfFile1 = Files.readString(absolutePath1);
        String contentOfFile2 = Files.readString(absolutePath2);

        ObjectMapper mapper1 = Parser.mapperFactory(file1Extension);
        ObjectMapper mapper2 = Parser.mapperFactory(file2Extension);

        Map<String, Object> map1 = Parser.getData(contentOfFile1, mapper1);
        Map<String, Object> map2 = Parser.getData(contentOfFile2, mapper2);

        Map<String, Value> mapOfDiff = DifferenceFinder.findDifference(map1, map2);

        if (mapOfDiff.isEmpty()) {
            return "The files are empty!";
        }

        String result = Formatter.format(mapOfDiff, formatName);
        return result;
    }

    public static Path getAbsolutePath(String path) {
        String loweredPath = path.toLowerCase();
        Path absolutePath = Paths.get(loweredPath).toAbsolutePath().normalize();
        return absolutePath;
    }

    private static String getFormat(String filePath) {

        int index = filePath.lastIndexOf('.');

        return index > 0 ? filePath.substring(index + 1) : "";

    }

}
