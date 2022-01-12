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

import static common.Constants.FILE_EXTENSION;
import static common.Constants.TESTS_NUMBER;
import static common.Constants.TESTS_PATH;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        for (int i = 1; i <= TESTS_NUMBER; i++) {
            File inputFile = new File(TESTS_PATH + "test" + i + FILE_EXTENSION);
            InputLoad inputLoader = new InputLoad(inputFile.getAbsolutePath());
            try {
                GameDataInput game = inputLoader.readInput();
                String filepath = Constants.OUT_PATH + i + FILE_EXTENSION;
                File out = new File(filepath);
                out.createNewFile();
                Output output = Simulation.applyRound(game);
                Writer writer = new Writer(filepath);
                writer.writeToFile(output);
            } catch (Exception exception) {
                System.out.println("error");
            }
        }
        Checker.calculateScore();
    }
}
