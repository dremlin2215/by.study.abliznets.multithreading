package entity;

import java.util.List;
import java.util.Objects;

public class Route {
    private String number;
    private List<Busstop> busStops;

    public Route(String number, List<Busstop> busStops) {
        this.number = number;
        this.busStops = busStops;
    }

    public String getNumber() {

        return number;
    }

    public List<Busstop> getBusStops() {
        return busStops;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Route route = (Route) o;
        return number.equals(route.number) &&
                Objects.equals(busStops, route.busStops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, busStops);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ROUTE ").append(number).append(":");
        for (Busstop busstop : busStops) {
            stringBuilder.append("\n" + busstop);
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
