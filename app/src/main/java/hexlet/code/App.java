package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Map;
import java.util.concurrent.Callable;

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
    public Integer call() throws Exception {
        String test1 = Parser.fileToString(filepath1);
        String test2 = Parser.fileToString(filepath2);

        Map<String, String> map1 = Parser.getMap(test1.split("\\n"));
        Map<String, String> map2 = Parser.getMap(test2.split("\\n"));

        Map<String, String> resultMap = Parser.compareJson(map1, map2);

        Parser.printSortedMap(resultMap);

        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

