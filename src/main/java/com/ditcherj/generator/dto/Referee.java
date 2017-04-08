package com.ditcherj.generator.dto;

import java.io.Serializable;

/**
 * Created by Jonathan Ditcher on 08/04/2017.
 */
public class Referee implements Serializable {

    private Long id;
    private String uuid;
    private String name;
    private String nationality;
    private int age;
    private Style style;
    private int redCards;
    private int yellowCards;

    public Referee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    @Override
    public String toString() {
        return "Referee{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                ", style=" + style +
                ", redCards=" + redCards +
                ", yellowCards=" + yellowCards +
                '}';
    }

    public enum Style {
        LENIANT,
        STRICT;
    }
}
