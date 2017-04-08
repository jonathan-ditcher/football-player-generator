package com.ditcherj.generator;

import com.ditcherj.generator.dto.TemplateAttribute;
import com.ditcherj.generator.dto.TemplatePosition;
import com.ditcherj.generator.dto.TemplateRegion;
import com.ditcherj.generator.dto.TemplateType;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Template {

    private Integer id;
    private String description;
    private TemplatePosition position;
    private TemplateType type;
    private TemplateRegion region;
    private String nation;
    private List<TemplateAttribute> attributes;

    public Template() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TemplatePosition getPosition() {
        return this.position;
    }

    public void setPosition(TemplatePosition position) {
        this.position = position;
    }

    public TemplateRegion getRegion() {
        return this.region;
    }

    public void setRegion(TemplateRegion region) {
        this.region = region;
    }

    public List<TemplateAttribute> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(List<TemplateAttribute> attributes) {
        this.attributes = attributes;
    }

    public TemplateType getType() {
        return this.type;
    }

    public void setType(TemplateType type) {
        this.type = type;
    }

    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", position=" + position +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", region=" + region +
                ", nation='" + nation + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}

