package parser;

import Reader.FileReader;
import entity.Busstop;
import entity.Route;
import entity.Schedule;
import exception.EmptyFileException;
import exception.NoSuchFileException;
import generator.EntityGenerator;
import generator.PassengerListGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public enum RouteParser {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(RouteParser.class);
    private static final String ROUTE_PATTERN = "ROUTE.+";
    private static final String BUSSTOP_PATTERN = "BUSSTOP.+";

    public void readAndCreateRoutes() {
        String stringFromReader = "";
        try {
            stringFromReader = FileReader.INSTANCE.readFromFile(EntityGenerator.INSTANCE.pathToSchedule);
        } catch (EmptyFileException e) {
        LOGGER.info("EmptyFileException", e);
        } catch (NoSuchFileException e) {
            LOGGER.info("NoSuchFileException", e);
        } catch (IOException e) {
            LOGGER.info("IOException", e);
        }

        String[] entityStrings = stringFromReader.split("\\n");
        String routeString = entityStrings[0];
        int counter = 1;
        List<String> busstops = new LinkedList<>();

        while (counter < entityStrings.length) {


            if (entityStrings[counter].matches(BUSSTOP_PATTERN)) {
                busstops.add(entityStrings[counter]);
            } else if (entityStrings[counter].matches(ROUTE_PATTERN)) {
                Route finalRoute = createRoute(routeString, busstops);
                Schedule.INSTANCE.addRoute(finalRoute);
                routeString = entityStrings[counter];
                busstops = new LinkedList<>();
            }
            if (counter == entityStrings.length - 1) {
                Route finalRoute = createRoute(routeString, busstops);
                Schedule.INSTANCE.addRoute(finalRoute);
            }

            counter++;
        }
        LOGGER.info("Schedule restored from file and written");
    }

    private Route createRoute(String route, List<String> busstops) {
        List<Busstop> busstopsList = new LinkedList<>();
        for (String busstopString : busstops) {
            Busstop busstop = createBusstop(busstopString);
            busstopsList.add(busstop);
        }
        String routeNumber = route.split("\\s")[1];
        return new Route(routeNumber, busstopsList);
    }

    private Busstop createBusstop(String busstop) {
        String[] busstopParameters = busstop.split("\\s");
        return new Busstop(busstopParameters[1],
                Integer.parseInt(busstopParameters[2]),
                PassengerListGenerator.INSTANCE.generatePassenger(Integer.parseInt(busstopParameters[3])));
    }
}
