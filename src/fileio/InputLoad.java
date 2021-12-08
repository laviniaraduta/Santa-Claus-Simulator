package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.GameData;

import java.io.File;
import java.io.IOException;

public class InputLoad {
    private String inputPath;

    public InputLoad(String inputPath) {
        this.inputPath = inputPath;
    }

    public GameData readInput() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(inputPath), GameData.class);
    }
}
