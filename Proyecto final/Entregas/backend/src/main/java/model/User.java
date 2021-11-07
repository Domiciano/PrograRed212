package model;

public class User {
    private int id;
    private String name;
    private String lastName;
    private String password;
    private int venuesBuddyID;
    private int roleBuddyID;

    public User() {
    }

    public User(int id, String name, String lastName, String password, int venuesBuddyID, int roleBuddyID) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.venuesBuddyID = venuesBuddyID;
        this.roleBuddyID = roleBuddyID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVenuesBuddyID() {
        return venuesBuddyID;
    }

    public void setVenuesBuddyID(int venuesBuddyID) {
        this.venuesBuddyID = venuesBuddyID;
    }

    public int getRoleBuddyID() {
        return roleBuddyID;
    }

    public void setRoleBuddyID(int roleBuddyID) {
        this.roleBuddyID = roleBuddyID;
    }
}
