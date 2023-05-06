package Session4.Lab5.App4;

public class Car {
    private static int nextId = 1;
    private final int id;

    public Car() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }
}