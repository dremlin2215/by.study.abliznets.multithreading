package action;

import entity.Bus;
import entity.Passenger;

import java.util.LinkedList;
import java.util.List;

public enum BusAction {

    INSTANCE;

    public List<Passenger> passengersToLeaveBus(Bus bus) {
        List<Passenger> passengersToLeaveBus = new LinkedList<>();
        List<Passenger> passengersInBus = bus.getPassengersInBus();

        for (Passenger passenger : passengersInBus) {
            if (passenger.isTargetLocation()) {
                passengersToLeaveBus.add(passenger);
            }
        }
        passengersInBus.removeAll(passengersToLeaveBus);
        return passengersToLeaveBus;
    }
}
