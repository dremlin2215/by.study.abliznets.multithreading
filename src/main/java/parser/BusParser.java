package parser;

import Reader.FileReader;
import entity.Bus;
import exception.EmptyFileException;
import exception.NoSuchFileException;
import generator.EntityGenerator;
import generator.PassengerListGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public enum BusParser {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(BusParser.class);
    private static final String BUS_PATTERN = "BUS.+";

    public List<Bus> readAndCreateBuses() {
        String stringFromReader = "";
        try {
            stringFromReader = FileReader.INSTANCE.readFromFile(EntityGenerator.INSTANCE.pathToBuses);
        } catch (EmptyFileException e) {
            LOGGER.info("EmptyFileException", e);
        } catch (NoSuchFileException e) {
            LOGGER.info("NoSuchFileException", e);
        } catch (IOException e) {
            LOGGER.info("IOException", e);
        }
        String[] entityStrings = stringFromReader.split("\\n");
        List<Bus> busesList = new LinkedList<>();
        for (String string : entityStrings) {
            if (string.matches(BUS_PATTERN)) {
                busesList.add(createBus(string));
            }
        }
        LOGGER.info("Bus restored from file");
        return busesList;
    }

    private Bus createBus(String busString) {
        String[] busParameters = busString.split("\\s");

        return new Bus(PassengerListGenerator.INSTANCE.generatePassenger(Integer.parseInt(busParameters[2])),
                Integer.parseInt(busParameters[1]));
    }

}
