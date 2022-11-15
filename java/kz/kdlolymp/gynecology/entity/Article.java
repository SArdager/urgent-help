package kz.kdlolymp.gynecology.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="articles")

public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private int id;
    @Column(name="text", length = 2000)
    private String text;
    @Column(name="place_name")
    private String placeName;
    @Column(name="lang")
    private String lang;

    public Article() {}

    public int getId() {return id;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}

    public String getPlaceName() {return placeName;}

    public void setPlaceName(String placeName) {this.placeName = placeName;}

    public String getLang() {return lang;}

    public void setLang(String lang) {this.lang = lang;}
}
