package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public enum Schedule {
    INSTANCE;

    private Lock lock = new ReentrantLock();
    private List<Route> routes = new ArrayList<>();

    public void addRoute(Route route) {
        routes.add(route);
    }

    public int getSize() {
        return routes.size();
    }

    public Route getRoute() {
        Route route = null;
        lock.lock();
        if (getSize() > 0) {
            route = routes.get(0);
            routes.remove(route);
        }
        lock.unlock();
        return route;
    }
}
