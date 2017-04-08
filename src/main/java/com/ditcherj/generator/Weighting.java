package com.ditcherj.generator;

import java.util.List;
import java.util.Random;

public class Weighting {

    int value;
    int weighting;

    public Weighting(int value, int weighting) {
        this.value = value;
        this.weighting = weighting;
    }

    public static int weightedRandom(List<Weighting> weightingOptions){
        int total = 0;
        for(Weighting weighting : weightingOptions)
            total += weighting.weighting;

        int random = (new Random()).nextInt(total);
        int current = 0;
        for(Weighting weighting : weightingOptions) {
            current += weighting.weighting;
            if(random < current)
                return weighting.value;
        }

        return -1;
    }
}
