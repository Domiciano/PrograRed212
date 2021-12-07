package model;

public class UserCard {
    private User user;
    private String venueName;
    private String cityName;

    public UserCard() {}

    public UserCard(User user, String venueName, String cityName) {
        this.user = user;
        this.venueName = venueName;
        this.cityName = cityName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
