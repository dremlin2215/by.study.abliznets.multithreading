package action;

import entity.Bus;
import entity.Busstop;
import entity.Passenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public enum BusstopAction {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(BusstopAction.class);

    public void exchangePassengersWithBus(Bus bus, Busstop busstop) throws InterruptedException {
        LOGGER.info("Bus " + bus.getRoute().getNumber() + " reached busstop " + busstop.getName());
        busstop.getSemaphore().acquire();
        LOGGER.info("Bus " + bus.getRoute().getNumber() + " opened the doors");
        try {
            busstop.getBusstopLock().lock();

            TimeUnit.MICROSECONDS.sleep(250);
            LOGGER.info("Bus " + bus.getRoute().getNumber() + " started exchange with busstop." +
                    " There are " + busstop.getPassengersOnStop().size() + " people on busstop " + busstop.getName() +
                    " and " + bus.getPassengersInBus().size() + " in bus before exchange");
            //passengers leaving bus adding free space
            List<Passenger> passengersGoingToLeaveBus = BusAction.INSTANCE.passengersToLeaveBus(bus);
            LOGGER.info(passengersGoingToLeaveBus.size() + " passengers want to exit from bus number " +
                    bus.getRoute().getNumber());

            TimeUnit.SECONDS.sleep(2);
            busstop.addPassengers(passengersGoingToLeaveBus);

            LOGGER.info(passengersGoingToLeaveBus.size() + " passengers left the bus number " +
                    bus.getRoute().getNumber() + ". There are " + busstop.getPassengersOnStop().size() +
                    " people on busstop " + busstop.getName() + " and " + bus.getPassengersInBus().size() +
                    " in bus before entering");
            TimeUnit.MILLISECONDS.sleep(passengersGoingToLeaveBus.size() * 50);

            //passengers entering bus
            List<Passenger> passengersGoingToLeaveBusstop = passengersToLeaveBusstop(busstop, bus);

            LOGGER.info(passengersGoingToLeaveBusstop.size() + " passengers want to enter the bus number " +
                    bus.getRoute().getNumber());

            bus.addPassengers(passengersGoingToLeaveBusstop);

            LOGGER.info(passengersGoingToLeaveBusstop.size() + " passengers entered the bus number " +
                    bus.getRoute().getNumber() + ". The doors are closing. The bus number " +
                    bus.getRoute().getNumber() + " is departing.");

            TimeUnit.MILLISECONDS.sleep(passengersGoingToLeaveBus.size() * 50);

        } catch (Exception e) {
            busstop.getBusstopLock().unlock();
            busstop.getSemaphore().release();
        }

    }

    public List<Passenger> passengersToLeaveBusstop(Busstop busstop, Bus bus) {
        List<Passenger> passengersToLeaveBusstop = new LinkedList<>();
        List<Passenger> passengersOnBusstop = busstop.getPassengersOnStop();
        Passenger passenger;
        int counter = 0;

        while ((bus.getCapacity() >= bus.getPassengersInBus().size() + passengersToLeaveBusstop.size())
                && counter < passengersOnBusstop.size()) {
            passenger = passengersOnBusstop.get(counter);
            if (passenger.isTargetLocation()) {
                passengersToLeaveBusstop.add(passenger);
            }
            counter++;
        }
        passengersOnBusstop.removeAll(passengersToLeaveBusstop);
        return passengersToLeaveBusstop;
    }
}
