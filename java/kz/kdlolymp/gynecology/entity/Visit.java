package kz.kdlolymp.gynecology.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="visits")

public class Visit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visit_id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="region_id", nullable = false)
    private Region region;
    @Column(name="visit_date")
    private LocalDateTime visitDate;

    public Visit() {}

    public Long getId() {return id;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public Region getRegion() {return region;}

    public void setRegion(Region region) {this.region = region;}

    public LocalDateTime getVisitDate() {return visitDate;}

    public void setVisitDate(LocalDateTime visitDate) {this.visitDate = visitDate;}
}
