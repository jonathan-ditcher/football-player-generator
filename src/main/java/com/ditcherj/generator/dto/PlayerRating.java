package com.ditcherj.generator.dto;

import java.io.Serializable;

public class PlayerRating implements Serializable {

    protected Long id;
    protected Long clubId;
    protected String name;
    protected int goals;
    protected Double rating;
    protected int apps;
    protected Integer passes;
    protected Integer successfulPasses;
    protected Integer shots;
    protected Integer successfulShots;
    protected Integer tackles;
    protected int mom;

    public PlayerRating() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() {
        return this.goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getApps() {
        return this.apps;
    }

    public void setApps(int apps) {
        this.apps = apps;
    }

    public Integer getPasses() {
        return this.passes;
    }

    public void setPasses(Integer passes) {
        this.passes = passes;
    }

    public Integer getSuccessfulPasses() {
        return this.successfulPasses;
    }

    public void setSuccessfulPasses(Integer successfulPasses) {
        this.successfulPasses = successfulPasses;
    }

    public Integer getShots() {
        return this.shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    public Integer getSuccessfulShots() {
        return this.successfulShots;
    }

    public void setSuccessfulShots(Integer successfulShots) {
        this.successfulShots = successfulShots;
    }

    public Integer getTackles() {
        return this.tackles;
    }

    public void setTackles(Integer tackles) {
        this.tackles = tackles;
    }

    public Long getClubId() {
        return this.clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public String getPassesPercent() {
        if(this.passes.intValue() != 0) {
            double percent = (double)this.successfulPasses.intValue() / (double)this.passes.intValue() * 100.0D;
            return (int)percent + "%";
        } else {
            return "0%";
        }
    }

    public String getShotsPercent() {
        if(this.passes.intValue() != 0) {
            double percent = (double)this.successfulShots.intValue() / (double)this.shots.intValue() * 100.0D;
            return (int)percent + "%";
        } else {
            return "0%";
        }
    }

    public int getMom() {
        return this.mom;
    }

    public void setMom(int mom) {
        this.mom = mom;
    }

    @Override
    public String toString() {
        return "PlayerRating{" +
                "id=" + id +
                ", clubId=" + clubId +
                ", name='" + name + '\'' +
                ", goals=" + goals +
                ", rating=" + rating +
                ", apps=" + apps +
                ", passes=" + passes +
                ", successfulPasses=" + successfulPasses +
                ", shots=" + shots +
                ", successfulShots=" + successfulShots +
                ", tackles=" + tackles +
                ", mom=" + mom +
                '}';
    }
}
