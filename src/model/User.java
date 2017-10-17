package model;

public class User {
    private final String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public String toString() {
        return this.userName;
    }
}