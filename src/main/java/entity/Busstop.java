package entity;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Busstop {
    private Semaphore semaphore;
    private String name;
    private List<Passenger> passengersOnStop;
    private Lock busstopLock = new ReentrantLock(true);

    public Busstop(String name, int capacityOfTransport, List<Passenger> passengersOnStop) {
        this.name = name;
        this.semaphore = new Semaphore(capacityOfTransport, true);
        this.passengersOnStop = passengersOnStop;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public Lock getBusstopLock() {
        return busstopLock;
    }

    public String getName() {
        return name;
    }

    public List<Passenger> getPassengersOnStop() {
        return passengersOnStop;
    }

    public void addPassengers(List<Passenger> passengers) {
        passengersOnStop.addAll(passengers);
    }

    @Override
    public String toString() {
        //For generation use semaphore.availablePermits() because this method only used for entity generator class when the class not in use.
        return "BUSSTOP " + name + " " + semaphore.availablePermits() + " " + passengersOnStop.size();
    }
}
