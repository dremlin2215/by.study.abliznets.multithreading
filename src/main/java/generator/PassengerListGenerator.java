package generator;

import entity.Passenger;

import java.util.LinkedList;
import java.util.List;

public enum PassengerListGenerator {
    INSTANCE;

    public List<Passenger> generatePassenger(int number) {
        List<Passenger> passengers = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            passengers.add(new Passenger());
        }
        return passengers;
    }
}
