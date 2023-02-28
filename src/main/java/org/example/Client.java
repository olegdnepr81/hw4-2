package org.example;

public class Client {
    private long Id;
    private String Name;

    public Client() {
    }

    public Client(long id, String name) {
        Id = id;
        Name = name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
