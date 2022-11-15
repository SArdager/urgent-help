package kz.kdlolymp.gynecology.entity;

import java.io.Serializable;

public class RegionVisits implements Serializable {
    private String regionName;
    private int visitsCount;

    public RegionVisits() {}

    public String getRegionName() {return regionName;}

    public void setRegionName(String regionName) {this.regionName = regionName;}

    public int getVisitsCount() {return visitsCount;}

    public void setVisitsCount(int visitsCount) {this.visitsCount = visitsCount;}
}
