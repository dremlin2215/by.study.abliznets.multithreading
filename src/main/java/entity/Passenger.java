package entity;

import java.util.Objects;

public class Passenger {
    private static int counter = 1;
    private int id;

    public Passenger() {
        this.id = counter++;
    }

    public int getId() {
        return id;
    }

    public boolean isTargetLocation() {
        int i = (int) Math.round(Math.random());
        return i == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passenger passenger = (Passenger) o;
        return id == passenger.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}