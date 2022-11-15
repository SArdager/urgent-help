package kz.kdlolymp.gynecology.entity;

import java.io.Serializable;

public class UserVisits implements Serializable {

    private int regionId;
    private User user;
    private int total;

    public UserVisits() {
    }

    public int getRegionId() {return regionId;}

    public void setRegionId(int regionId) {this.regionId = regionId;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public int getTotal() {return total;}

    public void setTotal(int total) {this.total = total;}
}
