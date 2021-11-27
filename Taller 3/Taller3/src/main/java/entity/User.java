package entity;

public class User {

    private String idNat;
    private String name;



    public User() {
    }

    public User(String idNat, String name) {
        this.idNat = idNat;
        this.name = name;
    }

    public String getIdNat() {
        return idNat;
    }

    public void setIdNat(String idNat) {
        this.idNat = idNat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
