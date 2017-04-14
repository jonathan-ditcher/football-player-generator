package com.ditcherj.generator;

import com.ditcherj.generator.dto.Nationality;
import com.ditcherj.generator.dto.Position;
import com.ditcherj.generator.dto.TemplatePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan Ditcher
 * Date: 01/08/13
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class PlayerUtils {

    private static final Logger logger = LoggerFactory.getLogger(PlayerUtils.class);
/*
    public static void setPlayerMentalAttributes(Player player, MentalAttributes mentalAttributes){
        player.putAttribute(PlayerAttributes.consistency, mentalAttributes.getConsistency());
        player.putAttribute(PlayerAttributes.dirtiness, mentalAttributes.getDirtiness());
        player.putAttribute(PlayerAttributes.importantMatches, mentalAttributes.getImportantMatches());
        player.putAttribute(PlayerAttributes.injuryProneness, mentalAttributes.getInjuryProneness());
        player.putAttribute(PlayerAttributes.versatility, mentalAttributes.getVersatility());
        player.putAttribute(PlayerAttributes.adaptability, mentalAttributes.getAdaptability());
        player.putAttribute(PlayerAttributes.ambition, mentalAttributes.getAmbition());
        player.putAttribute(PlayerAttributes.loyalty, mentalAttributes.getLoyalty());
        player.putAttribute(PlayerAttributes.pressure, mentalAttributes.getPressure());
        player.putAttribute(PlayerAttributes.professional, mentalAttributes.getProfessional());
        player.putAttribute(PlayerAttributes.sportsmanship, mentalAttributes.getSportsmanship());
        player.putAttribute(PlayerAttributes.temperament, mentalAttributes.getTemperament());
        player.putAttribute(PlayerAttributes.controversy, mentalAttributes.getControversy());
    }

    public static void setPlayerGoalKeepingAttributes(Player player, GoalKeepingAttributes goalKeepingAttributes){
        player.putAttribute(PlayerAttributes.aerialAbility, goalKeepingAttributes.getAerialAbility());
        player.putAttribute(PlayerAttributes.commandOfArea, goalKeepingAttributes.getCommandOfArea());
        player.putAttribute(PlayerAttributes.communication, goalKeepingAttributes.getCommunication());
        player.putAttribute(PlayerAttributes.eccentricity, goalKeepingAttributes.getEccentricity());
        player.putAttribute(PlayerAttributes.handling, goalKeepingAttributes.getHandling());
        player.putAttribute(PlayerAttributes.kicking, goalKeepingAttributes.getKicking());
        player.putAttribute(PlayerAttributes.oneOnOnes, goalKeepingAttributes.getOneOnOnes());
        player.putAttribute(PlayerAttributes.reflexes, goalKeepingAttributes.getReflexes());
        player.putAttribute(PlayerAttributes.rushingOut, goalKeepingAttributes.getRushingOut());
        player.putAttribute(PlayerAttributes.tendencyToPunch, goalKeepingAttributes.getTendencyToPunch());
        player.putAttribute(PlayerAttributes.throwing, goalKeepingAttributes.getThrowing());
    }

    public static ProgressionStats fetchProgressionStats(Player player){
        ProgressionStats progressionStats = new ProgressionStats();

        progressionStats.setCorners(player.getDoubleAttribute(PlayerAttributes.corners) * 100);
        progressionStats.setCrossing(player.getDoubleAttribute(PlayerAttributes.crossing) * 100);
        progressionStats.setDribbling(player.getDoubleAttribute(PlayerAttributes.dribbling) * 100);
        progressionStats.setFinishing(player.getDoubleAttribute(PlayerAttributes.finishing) * 100);
        progressionStats.setFirst_touch(player.getDoubleAttribute(PlayerAttributes.firstTouch) * 100);
        progressionStats.setFree_kicks(player.getDoubleAttribute(PlayerAttributes.freekicks) * 100);
        progressionStats.setHeading(player.getDoubleAttribute(PlayerAttributes.heading) * 100);
        progressionStats.setLong_shots(player.getDoubleAttribute(PlayerAttributes.longShots) * 100);
        progressionStats.setLong_throws(player.getDoubleAttribute(PlayerAttributes.longthrows) * 100);
        progressionStats.setMarking(player.getDoubleAttribute(PlayerAttributes.marking) * 100);
        progressionStats.setPassing(player.getDoubleAttribute(PlayerAttributes.passing) * 100);
        progressionStats.setPenalty_taking(player.getDoubleAttribute(PlayerAttributes.penaltyTaking) * 100);
        progressionStats.setTackling(player.getDoubleAttribute(PlayerAttributes.tackling) * 100);
        progressionStats.setTechnique(player.getDoubleAttribute(PlayerAttributes.technique) * 100);
        progressionStats.setAggression(player.getDoubleAttribute(PlayerAttributes.aggression) * 100);
        progressionStats.setAnticipation(player.getDoubleAttribute(PlayerAttributes.anticipation) * 100);
        progressionStats.setBravery(player.getDoubleAttribute(PlayerAttributes.bravery) * 100);
        progressionStats.setComposure(player.getDoubleAttribute(PlayerAttributes.composure) * 100);
        progressionStats.setConcentration(player.getDoubleAttribute(PlayerAttributes.concentration) * 100);
        progressionStats.setCreativity(player.getDoubleAttribute(PlayerAttributes.creativity) * 100);
        progressionStats.setDecisions(player.getDoubleAttribute(PlayerAttributes.decisions) * 100);
        progressionStats.setDetermination(player.getDoubleAttribute(PlayerAttributes.determination) * 100);
        progressionStats.setFlair(player.getDoubleAttribute(PlayerAttributes.flair) * 100);
        progressionStats.setInfluence(player.getDoubleAttribute(PlayerAttributes.influence) * 100);
        progressionStats.setOff_the_ball(player.getDoubleAttribute(PlayerAttributes.offTheBall) * 100);
        progressionStats.setPositioning(player.getDoubleAttribute(PlayerAttributes.positioning) * 100);
        progressionStats.setTeamwork(player.getDoubleAttribute(PlayerAttributes.teamwork) * 100);
        progressionStats.setWork_rate(player.getDoubleAttribute(PlayerAttributes.workrate) * 100);
        progressionStats.setAcceleration(player.getDoubleAttribute(PlayerAttributes.acceleration) * 100);
        progressionStats.setAgility(player.getDoubleAttribute(PlayerAttributes.agility) * 100);
        progressionStats.setBalance(player.getDoubleAttribute(PlayerAttributes.balance) * 100);
        progressionStats.setJumping(player.getDoubleAttribute(PlayerAttributes.jumping) * 100);
        progressionStats.setNatural_fitness(player.getDoubleAttribute(PlayerAttributes.naturalFitness) * 100);
        progressionStats.setPace(player.getDoubleAttribute(PlayerAttributes.pace) * 100);
        progressionStats.setStamina(player.getDoubleAttribute(PlayerAttributes.stamina) * 100);
        progressionStats.setStrength(player.getDoubleAttribute(PlayerAttributes.strength) * 100);

        return progressionStats;
    }
 */
    public static Template getTemplate(List<Template> templates, Nationality nationality){
        Random rand = new Random();
        Template template1 = templates.get(rand.nextInt(templates.size()));
        if(template1.getRegion().name().toLowerCase().equals(nationality.getRegion().toLowerCase()))
            return template1;

        return templates
                .stream()
                .filter(t -> t.getPosition().equals(t.getPosition().equals(template1.getPosition()) && t.getRegion().name().equalsIgnoreCase(nationality.getRegion())))
                .findFirst()
                .orElse(template1);
    }

    public static Template findSecondTemplate(List<Template> templates, Template template){
        List<Weighting> possiblePositions = new ArrayList<>();

        possiblePositions.add(new Weighting(template.getPosition().name(), 100));
        switch (template.getPosition()){
            case GOALKEEPER:{
                break;
            }
            case CENTER_BACK:{
                possiblePositions.add(new Weighting(TemplatePosition.FULL_BACK.name(), 50));
                possiblePositions.add(new Weighting(TemplatePosition.DEFENSIVE_MIDFIELDER.name(), 5));
                possiblePositions.add(new Weighting(TemplatePosition.CENTRAL_MIDFIELD.name(), 7));
                possiblePositions.add(new Weighting(TemplatePosition.FORWARD.name(), 10));
                break;
            }
            case FULL_BACK:{
                possiblePositions.add(new Weighting(TemplatePosition.CENTER_BACK.name(), 40));
                possiblePositions.add(new Weighting(TemplatePosition.WIDE_PLAYER.name(), 10));
                break;
            }
            case DEFENSIVE_MIDFIELDER:{
                possiblePositions.add(new Weighting(TemplatePosition.CENTRAL_MIDFIELD.name(), 40));
                possiblePositions.add(new Weighting(TemplatePosition.CENTER_BACK.name(), 20));
                break;
            }
            case CENTRAL_MIDFIELD:{
                possiblePositions.add(new Weighting(TemplatePosition.DEFENSIVE_MIDFIELDER.name(), 30));
                possiblePositions.add(new Weighting(TemplatePosition.WIDE_PLAYER.name(), 40));
                possiblePositions.add(new Weighting(TemplatePosition.FORWARD.name(), 7));
                break;
            }
            case WIDE_PLAYER:{
                possiblePositions.add(new Weighting(TemplatePosition.CENTRAL_MIDFIELD.name(), 30));
                possiblePositions.add(new Weighting(TemplatePosition.FULL_BACK.name(), 10));
                possiblePositions.add(new Weighting(TemplatePosition.FORWARD.name(), 7));
                break;
            }
            case FORWARD:{
                //possiblePositions.add(new Weighting("AMC", 7));
                possiblePositions.add(new Weighting(TemplatePosition.WIDE_PLAYER.name(), 7));
                possiblePositions.add(new Weighting(TemplatePosition.CENTRAL_MIDFIELD.name(), 7));
                possiblePositions.add(new Weighting(TemplatePosition.CENTER_BACK.name(), 10));
                break;
            }
        }

        String poss = weightedRandom(possiblePositions);
        TemplatePosition position = TemplatePosition.valueOf(poss);

        return templates
                .stream()
                .filter(t -> t.getPosition().equals(position))
                .findFirst()
                .orElse(null);
    }

    public static Position generatePosition(Template template1, Template template2, int leftFoot, int rightFoot){
        Random rand = new Random();

        int gk = 0;
        int sw = 0;
        int dr = 0;
        int dl = 0;
        int dc = 0;
        int wbr = 0;
        int wbl = 0;
        int dm = 0;
        int ml = 0;
        int mc = 0;
        int mr = 0;
        int aml = 0;
        int amc = 0;
        int amr = 0;
        int st = 0;

        switch (template1.getPosition()){
            case GOALKEEPER:{
                gk = 20;
                break;
            }
            case CENTER_BACK:{
                dc = 20;
                if(rand.nextInt(100) > 80)
                    sw = 10 + rand.nextInt(9);

                switch (template2.getPosition()){
                    case FULL_BACK:{
                        if(rightFoot > 15)
                            dr = 10 + rand.nextInt(9);
                        if(leftFoot > 15)
                            dl = 10 + rand.nextInt(9);
                        break;
                    }
                    case DEFENSIVE_MIDFIELDER:{
                        dm = 10 + rand.nextInt(9);
                        break;
                    }
                    case CENTRAL_MIDFIELD:{
                        mc = 5 + rand.nextInt(14);
                        break;
                    }
                    case FORWARD:{
                        mc = 5 + rand.nextInt(14);
                        break;
                    }
                }
                break;
            }
            case FULL_BACK:{
                if(rightFoot > 15)
                    dr = 20;
                if(leftFoot > 15)
                    dl = 20;

                if(template1.getType().name().toLowerCase().contains("att") || template1.getType().name().toLowerCase().contains("versatile") || template1.getType().name().toLowerCase().contains("direct")){
                    if(rightFoot > 15)
                        wbr = 10 + rand.nextInt(9);
                    if(leftFoot > 15)
                        wbl = 10 + rand.nextInt(9);
                }

                switch (template2.getPosition()){
                    case CENTER_BACK:{
                        dc = 10 + rand.nextInt(9);
                        break;
                    }
                    case WIDE_PLAYER:{
                        if(rightFoot > 15)
                            wbr = 10 + rand.nextInt(9);
                        else if(leftFoot > 15)
                            wbl = 10 + rand.nextInt(9);
                        break;
                    }
                }
                break;
            }
            case DEFENSIVE_MIDFIELDER:{
                dm = 20;
                mc = 6 + rand.nextInt(8);
                switch (template2.getPosition()){
                    case CENTER_BACK:{
                        dc = 7 + rand.nextInt(12);
                        break;
                    }
                    case CENTRAL_MIDFIELD:{
                        mc = 11 + rand.nextInt(8);
                        break;
                    }
                }
                break;
            }
            case CENTRAL_MIDFIELD:{
                mc = 20;

                if(template1.getType().name().toLowerCase().contains("attacking") || template1.getType().name().toLowerCase().contains("play_maker")){
                    amc = 10 + rand.nextInt(9);
                }

                switch (template2.getPosition()){
                    case DEFENSIVE_MIDFIELDER:{
                        dm = 7 + rand.nextInt(12);
                        break;
                    }
                    case WIDE_PLAYER:{
                        if(rightFoot > 15)
                            mr = 10 + rand.nextInt(9);
                        if(leftFoot > 15)
                            ml = 10 + rand.nextInt(9);

                        if(template1.getType().name().toLowerCase().contains("attacking") || template1.getType().name().toLowerCase().contains("play_maker")){
                            if(rightFoot > 15)
                                amr = mr - rand.nextInt(3);
                            if(leftFoot > 15)
                                aml = ml - rand.nextInt(3);
                        }

                        break;
                    }
                    case FORWARD:{
                        st = 7 + rand.nextInt(12);
                        break;
                    }
                }
                break;
            }
            case WIDE_PLAYER:{
                if(rightFoot > 18)
                    mr = 20;
                if(leftFoot > 18)
                    ml = 20;

                if(template1.getType().name().toLowerCase().contains("winger")){
                    if(rightFoot > 15)
                        amr = 16 + rand.nextInt(4);
                    if(leftFoot > 15)
                        aml = 16 + rand.nextInt(4);
                }

                switch (template2.getPosition()){
                    case CENTRAL_MIDFIELD:{
                        mc = 7 + rand.nextInt(12);
                        break;
                    }
                    case FULL_BACK:{
                        if(rightFoot > 15)
                            wbr = 10 + rand.nextInt(9);
                        if(leftFoot > 15)
                            wbl = 10 + rand.nextInt(9);
                        break;
                    }
                    case FORWARD:{
                        st = 7 + rand.nextInt(12);
                        break;
                    }
                }
                break;
            }
            case FORWARD:{
                st = 20;
                switch (template2.getPosition()){
                    case CENTRAL_MIDFIELD:{
                        mc = 7 + rand.nextInt(13);
                        amc = mc + rand.nextInt(20 - mc);
                        break;
                    }
                    case WIDE_PLAYER:{
                        if(rightFoot > 15)
                            amr = 10 + rand.nextInt(9);
                        if(leftFoot > 15)
                            aml = 10 + rand.nextInt(9);
                        break;
                    }
                    case CENTER_BACK:{
                        dc = 1 + rand.nextInt(19);
                        break;
                    }
                }
                break;
            }
        }

        Position position = new Position(leftFoot, rightFoot, gk, sw,
                dr, dl, dc, wbr, wbl, dm, ml, mc,
                mr, aml, amc, amr, st);
        return position;
    }

    public static class Weighting {

        String value;
        int weighting;

        public Weighting(String v, int w) {
            this.value = v;
            this.weighting = w;
        }
    }

    public static String weightedRandom(List<Weighting> weightingOptions) {

        //determine sum of all weightings
        int total = 0;
        for (Weighting w : weightingOptions) {
            total += w.weighting;
        }

        //select a random value between 0 and our total
        int random = new Random().nextInt(total);

        //loop thru our weightings until we arrive at the correct one
        int current = 0;
        for (Weighting w : weightingOptions) {
            current += w.weighting;
            if (random < current)
                return w.value;
        }

        //shouldn't happen.
        return null;
    }

}
