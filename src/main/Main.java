package main;

import checker.Checker;
import common.Constants;
import fileio.GameDataInput;
import fileio.InputLoad;
import fileio.Output;
import fileio.Writer;
import simulation.Simulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

//        Checker checker = new Checker();
//        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            InputLoad inputLoader = new InputLoad(file.getPath());
            GameDataInput game = inputLoader.readInput();
            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                System.out.println("created");
            }
            Output output = Simulation.applyRound(game);
            Writer writer = new Writer(filepath);
            writer.writeToFile(output);
        }


//        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
//        Checkstyle test = new Checkstyle();
//        test.testCheckstyle();
        Checker.calculateScore();
    }
}
