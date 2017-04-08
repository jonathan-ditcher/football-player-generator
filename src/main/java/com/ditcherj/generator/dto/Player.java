package com.ditcherj.generator.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan Ditcher
 * Date: 12/10/13
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public class Player implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Player.class);

    private Long id;
    private String uuid;
    private int age;
    private String nationality;
    private String name;
    private Position position;
    private Integer seasonPATarget;
    private double seasonPAAge;
    private Integer primaryTemplateId;
    private double initialAbility;
    private double annualCurrentAbility;
    private double potentialAbility;
    private double currentAbility;
    private Map<PlayerAttribute, Double> attributes = new HashMap<>();

    public Player(Long id){
        this.id = id;
    }

    public Player(
            long id,
            int age,
            String nationality,
            double aerialAbility,
            double commandOfArea,
            double communication,
            double eccentricity,
            double handling,
            double kicking,
            double oneOnOnes,
            double reflexes,
            double rushingOut,
            double tendencyToPunch,
            double throwing,
            double corners,
            double crossing,
            double dribbling,
            double finishing,
            double firstTouch,
            double freekicks,
            double heading,
            double longShots,
            double longthrows,
            double marking,
            double passing,
            double penaltyTaking,
            double tackling,
            double technique,
            double aggression,
            double anticipation,
            double bravery,
            double composure,
            double concentration,
            double creativity,
            double decisions,
            double determination,
            double flair,
            double influence,
            double offTheBall,
            double positioning,
            double teamwork,
            double workrate,
            double acceleration,
            double agility,
            double balance,
            double jumping,
            int leftFoot,
            double naturalFitness,
            double pace,
            int rightFoot,
            double stamina,
            double strength,
            double consistency,
            double dirtiness,
            double importantMatches,
            double injuryProneness,
            double versatility,
            double adaptability,
            double ambition,
            double loyalty,
            double pressure,
            double professional,
            double sportsmanship,
            double temperament,
            double controversy) {
        this(id);
        this.age = age;
        this.nationality = nationality;

        this.putAttribute(PlayerAttribute.aerialAbility, aerialAbility);
        this.putAttribute(PlayerAttribute.commandOfArea, commandOfArea);
        this.putAttribute(PlayerAttribute.communication, communication);
        this.putAttribute(PlayerAttribute.eccentricity, eccentricity);
        this.putAttribute(PlayerAttribute.handling, handling);
        this.putAttribute(PlayerAttribute.kicking, kicking);
        this.putAttribute(PlayerAttribute.oneOnOnes, oneOnOnes);
        this.putAttribute(PlayerAttribute.reflexes, reflexes);
        this.putAttribute(PlayerAttribute.rushingOut, rushingOut);
        this.putAttribute(PlayerAttribute.tendencyToPunch, tendencyToPunch);
        this.putAttribute(PlayerAttribute.throwing, throwing);
        this.putAttribute(PlayerAttribute.corners, corners);
        this.putAttribute(PlayerAttribute.crossing, crossing);
        this.putAttribute(PlayerAttribute.dribbling, dribbling);
        this.putAttribute(PlayerAttribute.finishing, finishing);
        this.putAttribute(PlayerAttribute.firstTouch, firstTouch);
        this.putAttribute(PlayerAttribute.freekicks, freekicks);
        this.putAttribute(PlayerAttribute.heading, heading);
        this.putAttribute(PlayerAttribute.longShots, longShots);
        this.putAttribute(PlayerAttribute.longthrows, longthrows);
        this.putAttribute(PlayerAttribute.marking, marking);
        this.putAttribute(PlayerAttribute.passing, passing);
        this.putAttribute(PlayerAttribute.penaltyTaking, penaltyTaking);
        this.putAttribute(PlayerAttribute.tackling, tackling);
        this.putAttribute(PlayerAttribute.technique, technique);
        this.putAttribute(PlayerAttribute.aggression, aggression);
        this.putAttribute(PlayerAttribute.anticipation, anticipation);
        this.putAttribute(PlayerAttribute.bravery, bravery);
        this.putAttribute(PlayerAttribute.composure, composure);
        this.putAttribute(PlayerAttribute.concentration, concentration);
        this.putAttribute(PlayerAttribute.creativity, creativity);
        this.putAttribute(PlayerAttribute.decisions, decisions);
        this.putAttribute(PlayerAttribute.determination, determination);
        this.putAttribute(PlayerAttribute.flair, flair);
        this.putAttribute(PlayerAttribute.influence, influence);
        this.putAttribute(PlayerAttribute.offTheBall, offTheBall);
        this.putAttribute(PlayerAttribute.positioning, positioning);
        this.putAttribute(PlayerAttribute.teamwork, teamwork);
        this.putAttribute(PlayerAttribute.workrate, workrate);
        this.putAttribute(PlayerAttribute.acceleration, acceleration);
        this.putAttribute(PlayerAttribute.agility, agility);
        this.putAttribute(PlayerAttribute.balance, balance);
        this.putAttribute(PlayerAttribute.jumping, jumping);
        this.putAttribute(PlayerAttribute.naturalFitness, naturalFitness);
        this.putAttribute(PlayerAttribute.pace, pace);
        this.putAttribute(PlayerAttribute.stamina, stamina);
        this.putAttribute(PlayerAttribute.strength, strength);
        this.putAttribute(PlayerAttribute.consistency, consistency);
        this.putAttribute(PlayerAttribute.dirtiness, dirtiness);
        this.putAttribute(PlayerAttribute.importantMatches, importantMatches);
        this.putAttribute(PlayerAttribute.injuryProneness, injuryProneness);
        this.putAttribute(PlayerAttribute.versatility, versatility);
        this.putAttribute(PlayerAttribute.adaptability, adaptability);
        this.putAttribute(PlayerAttribute.ambition, ambition);
        this.putAttribute(PlayerAttribute.loyalty, loyalty);
        this.putAttribute(PlayerAttribute.pressure, pressure);
        this.putAttribute(PlayerAttribute.professional, professional);
        this.putAttribute(PlayerAttribute.sportsmanship, sportsmanship);
        this.putAttribute(PlayerAttribute.temperament, temperament);
        this.putAttribute(PlayerAttribute.controversy, controversy);
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

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntegerAttribute(PlayerAttribute key){
        Object val = this.getAttribute(key);

        if(val instanceof Double)
            return ((Double) val).intValue();

        return (Integer)val;
    }


    public int getDisplayAttribute(PlayerAttribute key){
        return this.getIntegerAttribute(key) / 100;
    }

    public double getDoubleAttribute(PlayerAttribute key){
        return (double)this.getIntegerAttribute(key) / 100.0;
    }

    public int getGoalKeepingRating(){
        if(this.getId() != null){
            double rating =
                            this.getDoubleAttribute(PlayerAttribute.commandOfArea) +
                            this.getDoubleAttribute(PlayerAttribute.communication) +
                            this.getDoubleAttribute(PlayerAttribute.eccentricity)  +
                            this.getDoubleAttribute(PlayerAttribute.handling)      +
                            this.getDoubleAttribute(PlayerAttribute.kicking)       +
                            this.getDoubleAttribute(PlayerAttribute.oneOnOnes)     +
                            this.getDoubleAttribute(PlayerAttribute.reflexes)      +
                            this.getDoubleAttribute(PlayerAttribute.rushingOut)    +
                            this.getDoubleAttribute(PlayerAttribute.tendencyToPunch);
            return (int) (rating / 9);
        }
        return 1;
    }

    public int getDefendingRating(){
        if(this.getId() != null){
            double rating =
                    this.getDoubleAttribute(PlayerAttribute.heading) +
                            this.getDoubleAttribute(PlayerAttribute.tackling) + +
                            this.getDoubleAttribute(PlayerAttribute.longthrows) +  +
                            this.getDoubleAttribute(PlayerAttribute.marking);
            return (int) rating / 4;
        }
        return 1;
    }

    public int getPlaymakingRating(){
        if(this.getId() != null){
            double rating =
                    this.getDoubleAttribute(PlayerAttribute.passing) +
                            this.getDoubleAttribute(PlayerAttribute.agility) +
                            this.getDoubleAttribute(PlayerAttribute.creativity);
            return (int) (rating / 3);
        }
        return 1;
    }

    public int getGoalscoringRating(){
        if(this.getId() != null){
            double rating = this.getDoubleAttribute(PlayerAttribute.finishing);
            return (int) rating / 1;
        }
        return 1;
    }

    public int getCharacterRating(){
        if(this.getId() != null){
            double rating = this.getDoubleAttribute(PlayerAttribute.professional);
            return (int) rating / 1;
        }
        return 1;
    }

    public int getPhysicalRating(){
        if(this.getId() != null){
            double rating = this.getDoubleAttribute(PlayerAttribute.strength);
            return (int) rating / 1;
        }
        return 1;
    }

    public int getSetPiecesRating(){
        if(this.getId() != null){
            double rating = this.getDoubleAttribute(PlayerAttribute.crossing);
            return (int) rating / 1;
        }
        return 1;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getSeasonPATarget() {
        return seasonPATarget;
    }

    public void setSeasonPATarget(Integer seasonPATarget) {
        this.seasonPATarget = seasonPATarget;
    }

    public double getSeasonPAAge() {
        return seasonPAAge;
    }

    public void setSeasonPAAge(double seasonPAAge) {
        this.seasonPAAge = seasonPAAge;
    }

    public Integer getPrimaryTemplateId() {
        return primaryTemplateId;
    }

    public void setPrimaryTemplateId(Integer primaryTemplateId) {
        this.primaryTemplateId = primaryTemplateId;
    }

    public void putAttribute(PlayerAttribute key, Double attribute) {
        this.attributes.put(key, attribute);
    }

    public Double getAttribute(PlayerAttribute key) {
        return this.attributes.get(key);
    }

    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    public double getCurrentAbility() {
        return this.currentAbility;
    }

    public void setCurrentAbility(double currentAbility) {
        this.currentAbility = currentAbility;
    }

    public double getPotentialAbility() {
        return this.potentialAbility;
    }

    public void setPotentialAbility(double potentialAbility) {
        this.potentialAbility = potentialAbility;
    }

    public int getInitialAbility() {
        return (int) this.initialAbility;
    }

    public void setInitialAbility(int initialAbility) {
        this.initialAbility = initialAbility;
    }

    public double getAnnualCurrentAbility() {
        return this.annualCurrentAbility;
    }

    public void setAnnualCurrentAbility(double annualCurrentAbility) {
        this.annualCurrentAbility = annualCurrentAbility;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", seasonPATarget=" + seasonPATarget +
                ", seasonPAAge=" + seasonPAAge +
                ", primaryTemplateId=" + primaryTemplateId +
                ", initialAbility=" + initialAbility +
                ", annualCurrentAbility=" + annualCurrentAbility +
                ", potentialAbility=" + potentialAbility +
                ", currentAbility=" + currentAbility +
                ", attributes=" + attributes +
                '}';
    }
}
