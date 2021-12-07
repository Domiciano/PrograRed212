package model;

public class LogUser {

    private int id;
    private String name;
    private String lastName;
    private int venuesBuddyID;
    private int roleBuddyID;

    public LogUser() {
    }

    public LogUser(int id, String name, String lastName, int venuesBuddyID, int roleBuddyID) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
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
