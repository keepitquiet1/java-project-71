package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

public class App {
    @Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.0.1",
            description = "Compares two configuration files and shows a difference.")
    static class GenDiff implements Callable<Integer> {

        @Parameters(index = "0", description = "path to first file")
        private File filepath1;
        @Parameters(index = "1", description = "path to second file")
        private File filepath2;

        @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
        private String format = "SHA-256";

        @Override
        public Integer call() throws Exception { // your business logic goes here...
            //byte[] fileContents = Files.readAllBytes(file.toPath());
            //byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
            //System.out.printf("%0" + (digest.length * 2) + "x%n", new BigInteger(1, digest));
            return 0;
        }

    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new GenDiff()).execute(args);
        System.exit(exitCode);
    }

}