package com.ditcherj.generator.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Nationality implements Serializable {

    private String isoCode;
    private String name;
    private String adjective;
    private String region;

    public Nationality() {
    }

    public Nationality(String isoCode, String name, String adjective, String region) {
        this.isoCode = isoCode;
        this.name = name;
        this.adjective = adjective;
        this.region = region;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return this.isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getAdjective() {
        return this.adjective;
    }

    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String toString() {
        return "com.ditcherj.generator.dto.Nationality{isoCode='" + this.isoCode + '\'' + ", name='" + this.name + '\'' + ", adjective='" + this.adjective + '\'' + ", region='" + this.region + '\'' + '}';
    }
}
