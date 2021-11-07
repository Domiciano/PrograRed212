package model;

public class Venue {

 private int id;
 private int cityID;
 private String name;

    public Venue() {
    }

    public Venue(int id, int cityID, String name) {
        this.id = id;
        this.cityID = cityID;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCity() {
        return cityID;
    }

    public void setCity(int city) {
        this.cityID = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
