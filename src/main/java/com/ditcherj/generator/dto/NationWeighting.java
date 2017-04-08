package com.ditcherj.generator.dto;

public class NationWeighting {

    private String isoCode;
    private String name;
    private int weighting;

    public NationWeighting(String isoCode, String name, int weighting) {
        this.isoCode = isoCode;
        this.name = name;
        this.weighting = weighting;
    }

    public NationWeighting() {
    }

    public String getIsoCode() {
        return this.isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeighting() {
        return this.weighting;
    }

    public void setWeighting(int weighting) {
        this.weighting = weighting;
    }

    @Override
    public String toString() {
        return "NationWeighting{" +
                "isoCode='" + isoCode + '\'' +
                ", name='" + name + '\'' +
                ", weighting=" + weighting +
                '}';
    }
}
