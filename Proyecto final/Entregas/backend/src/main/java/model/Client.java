package model;

public class Client {

    private String natId;
    private int id;
    private int age;
    private String name;
    private String lastname;
    private float weight;
    private float length;
    private int statusID;
    private int membershipID;

    public Client(String natId,int id, int age, String name, String lastname, float weight, float length, int statusID, int membershipID) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.lastname = lastname;
        this.weight = weight;
        this.length = length;
        this.statusID = statusID;
        this.membershipID = membershipID;
        this.natId = natId;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNatId() {
        return natId;
    }

    public void setNatId(String natId) {
        this.natId = natId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }
}
