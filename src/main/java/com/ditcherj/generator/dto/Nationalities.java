package com.ditcherj.generator.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by Jonathan Ditcher on 31/03/2017.
 */
@XmlRootElement
public class Nationalities {

    private Map<String, Nationality> nationalities;

    public Nationalities() {
    }

    public Map<String, Nationality> getNationalities() {
        return nationalities;
    }

    public void setNationalities(Map<String, Nationality> nationalities) {
        this.nationalities = nationalities;
    }

    @Override
    public String toString() {
        return "Nationalities{" +
                "nationalities=" + nationalities +
                '}';
    }
}
