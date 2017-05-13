package com.ditcherj.generator.dto;

/**
 * Created by Jonathan Ditcher on 13/05/2017.
 */
public class Name {

    private String first;
    private String last;
    private String nation;

    public Name() {
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getFullName() {
        return first.trim() + " " + last.trim();
    }

    @Override
    public String toString() {
        return "Name{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", nation='" + nation + '\'' +
                '}';
    }
}
