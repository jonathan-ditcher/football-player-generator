package com.ditcherj.generator.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by Jonathan Ditcher on 13/05/2017.
 */
@XmlRootElement
public class Names {

    private Map<String, Name[]> names;

    public Names() {
    }

    public Map<String, Name[]> getNames() {
        return names;
    }

    public void setNames(Map<String, Name[]> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "Names{" +
                "names=" + names +
                '}';
    }

}
