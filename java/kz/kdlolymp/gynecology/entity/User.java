package kz.kdlolymp.gynecology.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Component
@Table(name="users")
public class User implements UserDetails, Serializable {

    private static final String ROLE_PREFIX = "ROLE_";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    @Column(name = "login")
    private String username;
    @Column(name = "password")
    private String password;
    @Transient
    private String passwordConfirm;
    @Column(name = "role")
    private String role;
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "user_surname")
    private String userSurname;
    @Column(name = "user_firstname")
    private String userFirstname;
    @Column(name = "position")
    private String position;
    @Column(name = "email")
    private String email;
    @Column(name = "work_place")
    private String workPlace;

    @Column(name = "region_name")
    private String regionName;
    @Column(name = "region_id")
    private int regionId;
    @Column(name = "is_temporary")
    private boolean isTemporary;
    @Column(name = "lang")
    private String lang;

    public User(){}

    public User(Long id, String username, String role, String userSurname, String userFirstname, String email) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.userSurname = userSurname;
        this.userFirstname = userFirstname;
        this.email = email;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {this.password = password;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public void setEnabled(boolean enabled) {isEnabled = enabled;}

    public String getUserSurname() {return userSurname;}

    public void setUserSurname(String userSurname) {this.userSurname = userSurname;}

    public String getUserFirstname() {return userFirstname;}

    public void setUserFirstname(String userFirstname) {this.userFirstname = userFirstname;}

    public String getPosition() {return position;}

    public void setPosition(String position) {this.position = position;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getWorkPlace() {return workPlace;}

    public void setWorkPlace(String workPlace) {this.workPlace = workPlace;}

    public String getRegionName() {return regionName;}

    public void setRegionName(String regionName) {this.regionName = regionName;}

    public int getRegionId() {return regionId;}

    public void setRegionId(int regionId) {this.regionId = regionId;}

    public boolean isTemporary() {return isTemporary;}

    public void setTemporary(boolean temporary) {isTemporary = temporary;}

    public String getLang() {return lang;}

    public void setLang(String lang) {this.lang = lang;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

}
