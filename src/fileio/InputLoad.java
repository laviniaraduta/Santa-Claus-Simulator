package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public final class InputLoad {
    private String inputPath;

    public InputLoad(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * @return object containing all the input data read
     * @throws IOException
     */
    public GameDataInput readInput() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(inputPath), GameDataInput.class);
    }
}
