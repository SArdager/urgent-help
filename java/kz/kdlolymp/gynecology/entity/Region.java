package kz.kdlolymp.gynecology.entity;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="regions")
public class Region implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="region_id")
    private int id;
    @Column(name="region_name")
    private String regionName;
    @Column(name="region_kz_name")
    private String regionKzName;

    public Region() {}

    public Region(String regionName) {
        this.regionName = regionName;
    }

    public int getId() {return id;}

    public String getRegionName() {return regionName;}

    public void setRegionName(String regionName) {this.regionName = regionName;}

    public String getRegionKzName() {return regionKzName;}

    public void setRegionKzName(String regionKzName) {this.regionKzName = regionKzName;}
}
