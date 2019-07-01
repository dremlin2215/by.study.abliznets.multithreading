package entity;

import action.BusstopAction;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Bus extends Thread {
    private final int Capacity;
    private List<Passenger> passengersInBus;
    private Route route;

    public Bus(List<Passenger> passengersInBus, int capacity) {
        this.passengersInBus = passengersInBus;
        this.Capacity = capacity;
    }

    public List<Passenger> getPassengersInBus() {

        return passengersInBus;
    }

    public int getCapacity() {
        return Capacity;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void run() {

        for (Busstop busstop : route.getBusStops()) {
            try {
                BusstopAction.INSTANCE.exchangePassengersWithBus(this, busstop);
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                //TODO: Log message
            }
        }
        System.out.println("Bus number " + route.getNumber() + " has finished movement!");
    }

    public void addPassengers(List<Passenger> passengers) {
        passengersInBus.addAll(passengers);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "Capacity=" + Capacity +
                ", passengersInBus=" + passengersInBus.size() +
                ", route=" + route +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bus bus = (Bus) o;
        return Capacity == bus.Capacity &&
                Objects.equals(passengersInBus, bus.passengersInBus) &&
                Objects.equals(route, bus.route);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Capacity, passengersInBus, route);
    }
}
