package com.ditcherj.generator;

import com.ditcherj.generator.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jonathan Ditcher on 31/03/2017.
 */
public class SimpleDataCache {

    private static final Logger logger = LoggerFactory.getLogger(SimpleDataCache.class);
    private static final SimpleDataCache instance = new SimpleDataCache();

    private Nationalities nationalities = new Nationalities();
    private NationWeightings nationWeightings = new NationWeightings();
    private final List<Template> templates = new LinkedList<>();
    private Names names = new Names();

    private SimpleDataCache() {
        this.buildAllData();
    }

    public static SimpleDataCache getInstance() {
        return instance;
    }

    private void buildAllData() {
        this.buildNationalities();
        this.buildNationWeightings();
        this.buildTemplates();
        this.buildNames();
    }

    private void buildNationalities() {
        logger.trace("");

        synchronized (this.nationalities) {
            try {
                JAXBContext context = JAXBContext.newInstance(Nationalities.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("nationalities.xml");

                this.nationalities = (Nationalities)unmarshaller.unmarshal(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void buildNationWeightings() {
        logger.trace("");

        synchronized (this.nationWeightings) {
            try {
                JAXBContext context = JAXBContext.newInstance(NationWeightings.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("weightings.xml");

                this.nationWeightings = (NationWeightings)unmarshaller.unmarshal(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void buildTemplates() {
        logger.trace("");

        synchronized (this.templates) {

            this.templates.clear();

            try {
                JAXBContext context = JAXBContext.newInstance(Template.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

                File file = new File(classLoader.getResource("players").getFile());

                File[] files = file.listFiles(pathname -> pathname.getName().endsWith(".xml"));
                for(File template : files)
                    this.templates.add((Template)unmarshaller.unmarshal(template));

            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    private void buildNames() {
        logger.trace("");

        synchronized (this.names) {
            try {
                JAXBContext context = JAXBContext.newInstance(Names.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("names.xml");

                this.names = (Names)unmarshaller.unmarshal(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Nationalities getNationalities() {
        synchronized (this.nationalities){
            return nationalities;
        }
    }

    public NationWeightings getNationWeightings() {
        synchronized (this.nationWeightings){
            return nationWeightings;
        }
    }

    public List<Template> getTemplates() {
        synchronized (this.templates){
            return templates;
        }
    }

    public Names getNames() {
        synchronized (this.names){
            return names;
        }
    }

    public Template getTemplate(TemplatePosition position, TemplateRegion region, TemplateType type) {
        synchronized (this.templates){
            return this.templates.stream()
                    .filter(t -> t.getPosition().equals(position) && t.getRegion().equals(region) && t.getType().equals(type))
                    .findFirst()
                    .orElse(null);
        }
    }
}
