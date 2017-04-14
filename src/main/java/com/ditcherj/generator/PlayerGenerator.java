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

    private static final double ALPHA = 10.0;
    private static final double BETA = 6.0;

    private Map<String, Nationality> nationalities;

    public PlayerGenerator() {
        this.nationalities = SimpleDataCache.getInstance().getNationalities().getNationalities();
    }

    public List<Player> generatePlayers(int numToGenerate ) {
        List<Player> players = new LinkedList<>();

        double maxAge = 40;
        double minAge = 15;

        double x;
        double b;
        BetaDistribution beta = new BetaDistribution(2, 4);
        for (int i = 0; i < numToGenerate; i++) {
            x = Math.random();
            b = beta.inverseCumulativeProbability(x);

            int age = Double.valueOf((b-0d)/(1d-0d) * (maxAge-minAge) + minAge).intValue();

            Nationality nationality = this.getNationality();
            Template template1 = PlayerUtils.getTemplate(SimpleDataCache.getInstance().getTemplates(), nationality);
            Template template2 = PlayerUtils.findSecondTemplate(SimpleDataCache.getInstance().getTemplates(), template1);

            double pa = PlayerGenerator.generatePotentialAbility();
            double ia = PlayerGenerator.generateInitialAbility(pa, age);

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
        player.setNationality(nationality.getIsoCode());

        double pa_adjustment = Math.pow((ability / 600.0), 0.5);

        template1.getAttributes().sort((o1, o2) -> o1.getPlayerAttribute().compareTo(o2.getPlayerAttribute()));
        template2.getAttributes().sort((o1, o2) -> o1.getPlayerAttribute().compareTo(o2.getPlayerAttribute()));

        for(int i=0; i<template1.getAttributes().size(); i++){
            TemplateAttribute attribute1 = template1.getAttributes().get(i);
            TemplateAttribute attribute2 = template2.getAttributes().get(i);

            double x = Math.random();
            double weighting = ((double)attribute1.getValue())*x + ((double)attribute2.getValue())*(1-x);

            double pa_weighting = weighting * pa_adjustment;

            int newVal = getClosestValue((int)Math.round(pa_weighting)) * 100;

            PlayerAttribute attribute = attribute1.getPlayerAttribute();
            //logger.trace("attr[{}] name[{}]", attribute, attribute1.getPlayerAttribute());

            player.putAttribute(attribute, (double) newVal);
        }

        player.setPotentialAbility(potentialAbility);
        player.setInitialAbility((int) initalAbility);
        player.setCurrentAbility(initalAbility);

        Random rand = new Random();
        BetaDistribution feetBeta = new BetaDistribution(2, 3);

        int rightFoot = rand.nextInt(3627) > 1000 ? 20 : 0;
        int leftFoot = rightFoot != 20 ? 20 : 0;
        if(rightFoot == 20)
            leftFoot = (int) (feetBeta.inverseCumulativeProbability(Math.random()) * 20);
        else if(leftFoot == 20)
            rightFoot = (int) (feetBeta.inverseCumulativeProbability(Math.random()) * 20);

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
        int start = (combined_target-1)*20;
        int end = start + 20;

        TreeMap<Double, Integer> map = new TreeMap<Double, Integer>();

        int counter =0;
        for(int j=0; j< BetaShape.BETA_SHAPES.length; j++){
            BetaShape betaShape = BetaShape.BETA_SHAPES[j];
            for(int i=1; i<=20; i++){
                BetaDistribution beta = new BetaDistribution(betaShape.getAlpha(), betaShape.getBeta());
                double lower = beta.cumulativeProbability(((double)i/20.0)-0.05);
                double upper = beta.cumulativeProbability((double)i/20.0);
                double discrete = upper - lower;
                double cdf = upper;
                double cdf_lower = cdf - discrete;

                if(counter >= start && counter <= end) {
                    map.put(new Double(cdf_lower), new Integer(i));
                }
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

    public static double generatePotentialAbility(){
        BetaDistribution beta = new BetaDistribution(ALPHA, BETA);
        return beta.inverseCumulativeProbability(Math.random()) * 1000.0;
    }

    public static double generateInitialAbility(double pa){
        return pa * 0.35;
    }

    // @TODO improve random - should be distributed.....
    public static double generateInitialAbility(double pa, int age){

        //@TODO add upper level and lower level
        Map<Integer, Double> agesMap = new HashMap<>();
        agesMap.put(14, 0.2);
        agesMap.put(15, 0.224);
        agesMap.put(16, 0.229);
        agesMap.put(17, 0.2);
        agesMap.put(18, 0.2);
        agesMap.put(19, 0.247);
        agesMap.put(20, 0.2727);
        agesMap.put(21, 0.2917);
        agesMap.put(22, 0.375);
        agesMap.put(23, 0.4714);
        agesMap.put(24, 0.561);
        agesMap.put(25, 0.672);
        agesMap.put(26, 0.6615);
        agesMap.put(27, 0.7087);
        agesMap.put(28, 0.8);
        agesMap.put(29, 0.8);
        agesMap.put(30, 0.8);
        agesMap.put(31, 0.8);
        agesMap.put(32, 0.8);
        agesMap.put(33, 0.85);
        agesMap.put(34, 0.85);
        agesMap.put(35, 0.9);
        agesMap.put(36, 0.9);

        Map<Integer, Double> upperPaAgesMap = new HashMap<>();
        upperPaAgesMap.put(14, 0.3);
        upperPaAgesMap.put(15, 0.33);
        upperPaAgesMap.put(16, 0.4);
        upperPaAgesMap.put(17, 0.55);
        upperPaAgesMap.put(18, 0.6);
        upperPaAgesMap.put(19, 0.75);
        upperPaAgesMap.put(20, 0.8);
        upperPaAgesMap.put(34, 0.85);
        upperPaAgesMap.put(35, 0.9);
        upperPaAgesMap.put(36, 0.9);

        Double lowerMultiplier = agesMap.get(age);
        if(lowerMultiplier == null)
            lowerMultiplier = 0.9;

        Double upperMultiplier = upperPaAgesMap.get(age);
        if(upperMultiplier == null)
            upperMultiplier = 1.0;

        int lower = (int) (lowerMultiplier * 100.0);
        int upper = (int) (upperMultiplier * 100.0);

        if(upper <= lower)
            upper =lower+1;

        //logger.trace("age["+age+"] lower["+lower+"] lowerMultiplier["+lowerMultiplier+"] upper["+upper+"]");
        Random rand = new Random();
        int num = rand.nextInt(upper- lower) + lower;

        return pa * ((double)num / 100.0);
    }
}
