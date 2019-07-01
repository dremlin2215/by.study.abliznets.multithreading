import entity.Bus;
import entity.Schedule;
import generator.EntityGenerator;
import parser.BusParser;
import parser.RouteParser;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    /**
     *
     * Program is working not fully as intended due to additional requirement just before deadline.
     * Routes with the same name are not the same objects.
     */

    public static void main(String[] args) {

        //Order is important - number of buses is gotten through the size() method of Schedule class.
        EntityGenerator.INSTANCE.generateAndWriteRoutes();
        RouteParser.INSTANCE.readAndCreateRoutes();
        EntityGenerator.INSTANCE.generateAndWriteBuses();


        List<Bus> buses = BusParser.INSTANCE.readAndCreateBuses();
        for (Bus bus: buses){
            bus.setRoute(Schedule.INSTANCE.getRoute());
        }
        List<Thread> threads = new ArrayList<>();
        for (Bus bus : buses){
            threads.add(new Thread(bus));
        }
        for (Thread thread: threads){
            thread.start();
        }
    }
}