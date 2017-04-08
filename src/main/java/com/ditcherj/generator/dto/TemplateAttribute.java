package com.ditcherj.generator.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan Ditcher
 * Date: 20/07/13
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class TemplateAttribute {

    private PlayerAttribute playerAttribute;
    private int value;
    private int importance;
    private Double weighting;

    public TemplateAttribute() {
    }

    public TemplateAttribute(PlayerAttribute playerAttribute, int value, int importance) {
        this.playerAttribute = playerAttribute;
        this.value = value;
        this.importance = importance;
    }

    public PlayerAttribute getPlayerAttribute() {
        return playerAttribute;
    }

    public void setPlayerAttribute(PlayerAttribute playerAttribute) {
        this.playerAttribute = playerAttribute;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public Double getWeighting() {
        return weighting;
    }

    public void setWeighting(Double weighting) {
        this.weighting = weighting;
    }

    @Override
    public String toString() {
        return "TemplateAttribute{" +
                "playerAttribute='" + playerAttribute + '\'' +
                ", value=" + value +
                ", importance=" + importance +
                ", weighting=" + weighting +
                '}';
    }
}
