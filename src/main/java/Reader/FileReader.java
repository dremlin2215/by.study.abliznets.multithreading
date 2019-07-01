package Reader;

import exception.EmptyFileException;
import exception.NoSuchFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public enum FileReader {
    INSTANCE;

    public String readFromFile(String path) throws EmptyFileException, NoSuchFileException, IOException {

        try (Stream<String> stringStream = Files.lines(Paths.get(path))) {
            if (isEmpty(path)) {
                throw new EmptyFileException("The file is empty");
            }
            if (!isExist(path)) {
                throw new NoSuchFileException("There is no such file");
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringStream.forEach(s -> stringBuilder.append(s + "\n"));
            return stringBuilder.toString();
        }
    }

    private boolean isEmpty(String path) {

        File file = new File(path);

        return file.length() == 0;
    }

    private boolean isExist(String str) {
        File f = new File(str);
        return (f.exists() && !f.isDirectory());
    }
}
