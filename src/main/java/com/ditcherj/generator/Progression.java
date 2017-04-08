package com.ditcherj.generator;

import com.ditcherj.generator.dto.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jose
 * Date: 21/07/13
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
public class Progression {

    public static final int A_I = 14; //initial age
    public static final int A_M = 33; // PA target age

    public Progression() {
    }

    public static Player calcDayProgression(Player player, double age, double M, double C, Template template){
        int totaldays = 28;

        System.out.println((age % 1));
        if (age % 1 < 0.000000000001 || age % 1 > 0.99) {
            player.setAnnualCurrentAbility(player.getCurrentAbility());
            System.out.println("RESET: " +player.getCurrentAbility());
            //age = Math.floor(age);
        }
        /*
        double ia = playerProgression.getIa();
        double pa = playerProgression.getPa();
        double ca = playerProgression.getCa();

        double f_a_c = Math.pow(M, (A_I + A_M)/2.0-age) + C;

        double f_i = 0;
        for(int i=A_I + 1; i<=A_M; i++){
            f_i = f_i + Math.pow(M, (A_I + A_M)/2.0-i) + C;
        }
        double ability = ca + (((pa - ia)*(f_a_c/f_i)) / 28);

        try {
            clacattribs(ability - playerProgression.getCa(), playerProgression, template);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        playerProgression.setCa(ability);

        return playerProgression;
        */
        double ia = player.getInitialAbility();
        double pa = player.getPotentialAbility();
        double ca = player.getAnnualCurrentAbility();

        double f_a_c = Math.pow(M, (A_I + A_M)/2.0-Math.ceil(age)) + C;

        double f_i = 0;
        for(int i=A_I + 1; i<=A_M; i++){
            f_i = f_i + Math.pow(M, (A_I + A_M)/2.0-i) + C;
        }
        double ability = ca + ((pa - ia)*(f_a_c/f_i));

        double x1 = Math.floor(age);
        double y1 = player.getAnnualCurrentAbility();

        double x2 = Math.ceil(age);
        double y2 = ability;

        /*
        d = sqrt((x2-x1)^2 + (y2 - y1)^2) #distance
                r = n / d #segment ratio

        x3 = r * x2 + (1 - r) * x1 #find point that divides the segment
        y3 = r * y2 + (1 - r) * y1 #into the ratio (1-r):r
           */
      //  double distance = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2 - y1, 2));
      //  double r = age / distance;

        double m = (y2 - y1) / (x2 - x1);

        /*
        y2 - y1 = m(x2 - x1)
         */

        ability = (m * (age- x1)) + y1;
        System.out.println("age["+age+"] ability["+ability+"] ca["+ca+"] x1["+x1+"] y1["+y1+"] x2["+x2+"] y2["+y2+"]");
        try {
            calcAttributes(ability - player.getCurrentAbility(), player, template);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        player.setCurrentAbility(ability);

        return player;
    }

    public static Double calcYearProgression(double ia, double pa, double ca, int age, double M, double C){

        double f_a_c = Math.pow(M, (A_I + A_M)/2.0-age) + C;

        double f_i = 0;
        for(int i=A_I + 1; i<=A_M; i++){
            f_i = f_i + Math.pow(M, (A_I + A_M)/2.0-i) + C;
        }
        double ability = ca + ((pa - ia)*(f_a_c/f_i));

        return ability;
    }

    public static Player calcYearProgression(Player player, int age, double M, double C, Template template){

        double ia = player.getInitialAbility();
        double pa = player.getPotentialAbility();
        double ca = player.getCurrentAbility();

        double f_a_c = Math.pow(M, (A_I + A_M)/2.0-age) + C;

        double f_i = 0;
        for(int i=A_I + 1; i<=A_M; i++){
            f_i = f_i + Math.pow(M, (A_I + A_M)/2.0-i) + C;
        }
        double ability = ca + ((pa - ia)*(f_a_c/f_i));

        try {
            calcAttributes(ability - player.getCurrentAbility(), player, template);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        player.setCurrentAbility(ability);

        return player;
    }

    public static void calcAttributes(double pa_adjustmetn, Player player, Template template) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
       // System.out.println(pa_adjustmetn);
        double pa_distributable = pa_adjustmetn * 0.7;

        // for every +1 PA, 1 attib can increase by 0.7
        // weight

        //iterate ovewr pa_distributable, choose weighted attribute and aad random amoun from 0-1 untill all has been allocted;

        List<Weighting> weightings = new LinkedList<Weighting>();
        for(int i=0; i<template.getAttributes().size(); i++){
            TemplateAttribute attribute = template.getAttributes().get(i);
            weightings.add(new Weighting(i, 35-attribute.getImportance()));
        }

        Map<TemplateAttribute, Double> attributes = new HashMap<TemplateAttribute, Double>();

        boolean neg = false;
        if(pa_distributable < 0){
            pa_distributable = -pa_distributable;
            neg = true;
        }

        while(pa_distributable > 0){
            TemplateAttribute attribute = template.getAttributes().get(Weighting.weightedRandom(weightings));
            double x = Math.random();

            if(pa_adjustmetn - x == 0)
                x = pa_adjustmetn;

            Double value = player.getDoubleAttribute(attribute.getPlayerAttribute());
            if(value + x > 20)
                x = 20 - x;

            if(neg){
                value = value - x;
            }
            else{
                value = value + x;
            }

            if(value > 20){
                value = 20.0;
                x = 0;
            }
            else if(value < 0){
                value = 0.0;
                x = 0;
            }
            attributes.put(attribute, value);
            pa_distributable = pa_distributable - x;
        }

        attributes.keySet().forEach(k -> player.putAttribute(k.getPlayerAttribute(), attributes.get(k) * 100));
    }

}
