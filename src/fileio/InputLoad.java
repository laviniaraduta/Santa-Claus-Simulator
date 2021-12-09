package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class InputLoad {
    private String inputPath;

    public InputLoad(String inputPath) {
        this.inputPath = inputPath;
    }

    public GameDataInput readInput() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(inputPath), GameDataInput.class);
    }
}
