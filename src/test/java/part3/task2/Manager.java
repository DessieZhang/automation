package part3.task2;

public class Manager {
    private int id;
    private String name;

    public Manager(int id, String name) {
        super();
        this.id = id;
        this.name = name;
        }

    @Override
    public String toString() {
        return "Name = " + name + ",ID = " + id;
    }
}
