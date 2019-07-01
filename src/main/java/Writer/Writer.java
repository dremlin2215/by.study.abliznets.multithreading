package Writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public enum Writer {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(Writer.class);

    public void writeStringsToFile(List<String> strings, String pathToFile) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (String string : strings) {
                writer.write(string);
            }
            writer.close();

        } catch (IOException e) {
            LOGGER.warn("IO Exception \n", e);
        }
    }
}
