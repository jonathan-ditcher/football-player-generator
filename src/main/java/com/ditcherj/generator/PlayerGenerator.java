package com.ditcherj.generator;

import com.ditcherj.generator.dto.*;
import org.apache.commons.math3.distribution.BetaDistribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * User: Jonathan
 * Date: 18/07/13
 * Time: 18:25
 */
public class PlayerGenerator {

    private static final Logger logger = LoggerFactory.getLogger(PlayerGenerator.class);

    private static final double PA_ALPHA = 10.0;
    private static final double PA_BETA = 6.0;
    private static final double FEET_ALPHA = 2.0;
    private static final double FEET_BETA = 3.0;
    protected static final Integer MAX_ATTRIBUTE_VALUE = 20;

    private Map<String, Nationality> nationalities;
    private BetaDistribution paBeta;
    private BetaDistribution feetBeta;
    private Random rand;
    private Map<Integer, Double> agesMap;
    private Map<Integer, Double> upperPaAgesMap;

    public PlayerGenerator() {
        this.nationalities = SimpleDataCache.getInstance().getNationalities().getNationalities();
        this.paBeta = new BetaDistribution(PA_ALPHA, PA_BETA);
        this.feetBeta = new BetaDistribution(FEET_ALPHA, FEET_BETA);
        this.rand = new Random();

        this.agesMap = new HashMap<>();
        this.agesMap.put(14, 0.2);
        this.agesMap.put(15, 0.224);
        this.agesMap.put(16, 0.229);
        this.agesMap.put(17, 0.2);
        this.agesMap.put(18, 0.2);
        this.agesMap.put(19, 0.247);
        this.agesMap.put(20, 0.2727);
        this.agesMap.put(21, 0.2917);
        this.agesMap.put(22, 0.375);
        this.agesMap.put(23, 0.4714);
        this.agesMap.put(24, 0.561);
        this.agesMap.put(25, 0.672);
        this.agesMap.put(26, 0.6615);
        this.agesMap.put(27, 0.7087);
        this.agesMap.put(28, 0.8);
        this.agesMap.put(29, 0.8);
        this.agesMap.put(30, 0.8);
        this.agesMap.put(31, 0.8);
        this.agesMap.put(32, 0.8);
        this.agesMap.put(33, 0.85);
        this.agesMap.put(34, 0.85);
        this.agesMap.put(35, 0.9);
        this.agesMap.put(36, 0.9);

        this.upperPaAgesMap = new HashMap<>();
        this.upperPaAgesMap.put(14, 0.3);
        this.upperPaAgesMap.put(15, 0.33);
        this.upperPaAgesMap.put(16, 0.4);
        this.upperPaAgesMap.put(17, 0.55);
        this.upperPaAgesMap.put(18, 0.6);
        this.upperPaAgesMap.put(19, 0.75);
        this.upperPaAgesMap.put(20, 0.8);
        this.upperPaAgesMap.put(34, 0.85);
        this.upperPaAgesMap.put(35, 0.9);
        this.upperPaAgesMap.put(36, 0.9);
    }

    public List<Player> generatePlayers(int numToGenerate ) {
        double maxAge = 40;
        double minAge = 15;
        BetaDistribution beta = new BetaDistribution(2, 4);
        return this.generatePlayers(numToGenerate, beta, minAge, maxAge);
    }

    public List<Player> generatePlayers(int numToGenerate, BetaDistribution beta, double minAge, double maxAge) {
        List<Player> players = new LinkedList<>();

        double x;
        double b;
        for (int i = 0; i < numToGenerate; i++) {
            x = Math.random();
            b = beta.inverseCumulativeProbability(x);

            int age = Double.valueOf((b-0d)/(1d-0d) * (maxAge-minAge) + minAge).intValue();

            Nationality nationality = this.getNationality();
            Template template1 = PlayerUtils.getTemplate(SimpleDataCache.getInstance().getTemplates(), nationality);
            Template template2 = PlayerUtils.findSecondTemplate(SimpleDataCache.getInstance().getTemplates(), template1);

            double pa = this.generatePotentialAbility();
            double ia = this.generateInitialAbility(pa, age);

            try {
                Player player = this.generatePlayer(template1, template2, nationality, ia, ia, pa);
                player.setAge(age);
                players.add(player);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return players;
    }

    public Player generatePlayer(Template template1, Template template2, Nationality nationality, double ability, double initalAbility, double potentialAbility) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        logger.trace("template1[{}] template2[{}]", template1, template2);
        Player player = new Player(null);
        player.setNationality(nationality);

        double pa_adjustment = Math.pow((ability / 600.0), 0.5);

        template1.getAttributes().sort(Comparator.comparing(TemplateAttribute::getPlayerAttribute));
        template2.getAttributes().sort(Comparator.comparing(TemplateAttribute::getPlayerAttribute));

        for(int i=0; i<template1.getAttributes().size(); i++){
            TemplateAttribute attribute1 = template1.getAttributes().get(i);
            TemplateAttribute attribute2 = template2.getAttributes().get(i);

            double x = Math.random();
            double weighting = ((double)attribute1.getValue())*x + ((double)attribute2.getValue())*(1-x);

            double pa_weighting = weighting * pa_adjustment;

            int newVal = this.getClosestValue((int)Math.round(pa_weighting)) * 100;

            PlayerAttribute attribute = attribute1.getPlayerAttribute();
            //logger.trace("attr[{}] name[{}]", attribute, attribute1.getPlayerAttribute());

            player.putAttribute(attribute, (double) newVal);
        }

        player.setPotentialAbility(potentialAbility);
        player.setInitialAbility((int) initalAbility);
        player.setCurrentAbility(initalAbility);

        int rightFoot = this.rand.nextInt(3627) > 1000 ? MAX_ATTRIBUTE_VALUE : 0;
        int leftFoot = rightFoot != MAX_ATTRIBUTE_VALUE ? MAX_ATTRIBUTE_VALUE : 0;
        if(rightFoot == MAX_ATTRIBUTE_VALUE)
            leftFoot = (int) (this.feetBeta.inverseCumulativeProbability(Math.random()) * MAX_ATTRIBUTE_VALUE);
        else if(leftFoot == MAX_ATTRIBUTE_VALUE)
            rightFoot = (int) (this.feetBeta.inverseCumulativeProbability(Math.random()) * MAX_ATTRIBUTE_VALUE);

        //PlayerUtils.setPlayerMentalAttributes(player, mentalAttributes);
       // PlayerUtils.setPlayerGoalKeepingAttributes(player, goalKeepingAttributes);

        player.setPrimaryTemplateId(template1.getId());
        player.setPosition(PlayerUtils.generatePosition(template1, template2, leftFoot, rightFoot));

        double M = 1.0;
        double C = 0;

        Double season_pa_target = Progression.calcYearProgression(initalAbility, potentialAbility, player.getCurrentAbility(), player.getAge(), M, C);
        player.setSeasonPATarget(season_pa_target.intValue());

        return player;
    }

    private int getClosestValue(int combined_target){
        int start = (combined_target - 1) * MAX_ATTRIBUTE_VALUE;
        int end = start + MAX_ATTRIBUTE_VALUE;

        TreeMap<Double, Integer> map = new TreeMap<>();

        int counter = 0;
        for(int j=0; j< BetaShape.BETA_SHAPES.length; j++){
            BetaShape betaShape = BetaShape.BETA_SHAPES[j];
            for(int i=1; i<=MAX_ATTRIBUTE_VALUE; i++){
                BetaDistribution beta = new BetaDistribution(betaShape.getAlpha(), betaShape.getBeta());
                double lower = beta.cumulativeProbability(((double)i/MAX_ATTRIBUTE_VALUE.doubleValue())-0.05);
                double upper = beta.cumulativeProbability((double)i/MAX_ATTRIBUTE_VALUE.doubleValue());
                double discrete = upper - lower;
                double cdf = upper;
                double cdf_lower = cdf - discrete;

                if(counter >= start && counter <= end)
                    map.put(cdf_lower, i);

                counter++;
            }
        }

        double x = Math.random();
        try{
            double above = map.ceilingKey(x);
            double below = map.floorKey(x);

            Double key = x - below > above - x ? above : below;
            return map.get(key);
        } catch (Exception e){
            e.printStackTrace();
        }

        return combined_target;
    }

    public Nationality getNationality(){
        logger.trace("");

        List<NationWeighting> nationWeightings = new LinkedList<>(SimpleDataCache.getInstance().getNationWeightings().getNationalities().values());
        List<Weighting> weightings = new LinkedList<>();
        for(int i=0; i<nationWeightings.size(); i++){
            NationWeighting weighting = nationWeightings.get(i);
            weightings.add(new Weighting(i, weighting.getWeighting()));
        }

        NationWeighting weighting = nationWeightings.get(Weighting.weightedRandom(weightings));
        return this.nationalities.get(weighting.getIsoCode());
    }

    public double generatePotentialAbility(){
        return this.paBeta.inverseCumulativeProbability(Math.random()) * 1000.0;
    }

    public static double generateInitialAbility(double pa){
        return pa * 0.35;
    }

    // @TODO improve random - should be distributed.....
    public double generateInitialAbility(double pa, int age){

        //@TODO add upper level and lower level

        Double lowerMultiplier = this.agesMap.get(age);
        if(lowerMultiplier == null)
            lowerMultiplier = 0.9;

        Double upperMultiplier = this.upperPaAgesMap.get(age);
        if(upperMultiplier == null)
            upperMultiplier = 1.0;

        int lower = (int) (lowerMultiplier * 100.0);
        int upper = (int) (upperMultiplier * 100.0);

        if(upper <= lower)
            upper = lower + 1;

        //logger.trace("age["+age+"] lower["+lower+"] lowerMultiplier["+lowerMultiplier+"] upper["+upper+"]");
        Random rand = new Random();
        int num = rand.nextInt(upper - lower) + lower;

        return pa * ((double)num / 100.0);
    }
}
