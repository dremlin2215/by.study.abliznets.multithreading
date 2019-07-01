package generator;

import Writer.Writer;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public enum EntityGenerator {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(EntityGenerator.class);
    public final String pathToSchedule = "src/main/resources/schedule.txt";
    public final String pathToBuses = "src/main/resources/bus.txt";

    public void generateAndWriteRoutes() {
        Writer.INSTANCE.writeStringsToFile(generateRoutes(), pathToSchedule);
        LOGGER.info(pathToSchedule + " file generated");
    }

    public void generateAndWriteBuses() {
        Writer.INSTANCE.writeStringsToFile(generateBuses(), pathToBuses);
        LOGGER.info(pathToBuses + " file generated");
    }

    public List<String> generateBuses() {
        int numberOfBuses = Schedule.INSTANCE.getSize();
        List<String> buses = new LinkedList<>();
        for (int i = 0; i < numberOfBuses; i++) {
            int capacity = (int) (50 + Math.round(Math.random() * 35));
            int numberOfPassengers = (int) (Math.round(Math.random() * capacity));
            buses.add("BUS " + capacity + " " + numberOfPassengers + "\n");
        }
        return buses;
    }

    public List<String> generateRoutes() {
        int numberOfRoutes = 3 + (int) Math.round(Math.random() * 5);

        List<String> routes = new LinkedList<>();

        for (int i = 0; i < numberOfRoutes; i++) {
            int routePrefix = (int) Math.round(Math.random() * 100);
            char routePostfix = (char) (65 + Math.round(Math.random() * 25));
            String routeName = "" + routePostfix + routePrefix;
            int numberOfBusstops = 4 + (int) Math.round(Math.random() * 4);

            Route route = new Route(routeName, generateBusttops(numberOfBusstops));
            routes.add(route.toString());
        }
        return routes;
    }

    private List<Busstop> generateBusttops(int number) {
        List<Busstop> busstopList = new LinkedList<>();
        List<String> busstopNames = new LinkedList<>();
        busstopNames.addAll(BusstopNameEnum.INSTANCE.getNames());

        for (int i = 0; i < number; i++) {
            int capacityOfTransport = (int) (3 + Math.round(Math.random() * 2));
            List<Passenger> passengers =
                    PassengerListGenerator.INSTANCE.generatePassenger((int) (15 + Math.random() * 35));
            String name = busstopNames.get((int) (Math.random() * busstopNames.size()));
            busstopNames.remove(name);
            Busstop busstop = new Busstop(name, capacityOfTransport, passengers);
            busstopList.add(busstop);
        }
        return busstopList;
    }
}
