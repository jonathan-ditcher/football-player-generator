package com.ditcherj.generator.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by Jonathan Ditcher on 31/03/2017.
 */
@XmlRootElement
public class NationWeightings {

    private Map<String, NationWeighting> nationalities;

    public NationWeightings() {
    }

    public Map<String, NationWeighting> getNationalities() {
        return nationalities;
    }

    public void setNationalities(Map<String, NationWeighting> nationalities) {
        this.nationalities = nationalities;
    }

    @Override
    public String toString() {
        return "NationWeightings{" +
                "nationalities=" + nationalities +
                '}';
    }
}
