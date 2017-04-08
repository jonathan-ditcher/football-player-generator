package com.ditcherj.generator.dto;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class PlayerStats extends PlayerRating implements Serializable {

    private int season;
    private Integer f1;
    private Integer f2;
    private Integer f3;
    private Integer f4;
    private Integer f5;
    private int yellowCards;
    private int redCards;

    public PlayerStats() {
    }

    public int getSeason() {
        return this.season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public Integer getF1() {
        return this.f1;
    }

    public void setF1(Integer f1) {
        this.f1 = f1;
    }

    public Integer getF2() {
        return this.f2;
    }

    public void setF2(Integer f2) {
        this.f2 = f2;
    }

    public Integer getF3() {
        return this.f3;
    }

    public void setF3(Integer f3) {
        this.f3 = f3;
    }

    public Integer getF4() {
        return this.f4;
    }

    public void setF4(Integer f4) {
        this.f4 = f4;
    }

    public Integer getF5() {
        return this.f5;
    }

    public void setF5(Integer f5) {
        this.f5 = f5;
    }

    public int getYellowCards() {
        return this.yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return this.redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public double getAverageRating_() {
        BigDecimal bd = new BigDecimal(this.rating.doubleValue());
        BigDecimal rounded = bd.setScale(2, 4);
        return rounded.doubleValue();
    }

    public String getFormString() {
        List<Integer> forms = new LinkedList<>();

        if(this.f1 != null)
            forms.add(this.f1);
        if(this.f2 != null)
            forms.add(this.f2);
        if(this.f3 != null)
            forms.add(this.f3);
        if(this.f4 != null)
            forms.add(this.f4);
        if(this.f5 != null)
            forms.add(this.f5);

        return StringUtils.join(forms, " ");
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "season=" + season +
                ", f1=" + f1 +
                ", f2=" + f2 +
                ", f3=" + f3 +
                ", f4=" + f4 +
                ", f5=" + f5 +
                ", yellowCards=" + yellowCards +
                ", redCards=" + redCards +
                "} " + super.toString();
    }
}
