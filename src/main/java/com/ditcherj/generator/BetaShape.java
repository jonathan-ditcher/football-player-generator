package com.ditcherj.generator;

import org.apache.commons.math3.distribution.BetaDistribution;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan Ditcher
 * Date: 20/07/13
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public class BetaShape {

    public static BetaShape[] BETA_SHAPES = new BetaShape[]{
            new BetaShape(1, 1.4, 8.6),
            new BetaShape(2, 2, 10),
            new BetaShape(3, 2.8, 11.2),
            new BetaShape(4, 3.8, 12.2),
            new BetaShape(5, 5, 13),
            new BetaShape(6, 6.2, 13.133333),
            new BetaShape(7, 7.5, 13.071429),
            new BetaShape(8, 8.8, 12.7),
            new BetaShape(9, 10.1, 12.122222),
            new BetaShape(10, 11.2, 11.2),
            new BetaShape(11, 12.122, 10.1),
            new BetaShape(12, 12.7, 8.8),
            new BetaShape(13, 13.071, 7.5),
            new BetaShape(14, 13.133, 6.2),
            new BetaShape(15, 13, 5),
            new BetaShape(16, 12.2, 3.8),
            new BetaShape(17, 11.2, 2.8),
            new BetaShape(18, 10, 2),
            new BetaShape(19, 8.6, 1.4),
            new BetaShape(20, 7, 1),
    };

    private int attribute_index;
    private double alpha;
    private double beta;

    public BetaShape(int attribute_index, double alpha, double beta) {
        this.attribute_index = attribute_index;
        this.alpha = alpha;
        this.beta = beta;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getMode() {
        return this.attribute_index / 20.0;
    }

    public double getMean() {
        return this.alpha/(this.alpha + this.beta);
    }

    public double getMedian() {
        return new BetaDistribution(this.alpha, this.beta).inverseCumulativeProbability(0.5);
    }
    public double getVariance() {
        return this.alpha*this.beta/(Math.pow((this.alpha+this.beta),2)*(this.alpha+this.beta+1));
    }

    public double getSkewness() {
        return (2*(this.beta-this.alpha)*Math.sqrt(this.alpha + this.beta + 1))/((this.alpha+this.beta+2)*Math.sqrt(this.alpha * this.beta));
    }

    public double getKurtosis_excess() {
        return 6*(Math.pow(this.alpha,3)-(Math.pow(this.alpha, 2))*(2*this.beta-1)+(Math.pow(this.beta,2))*(this.beta+1)-(2*this.alpha*this.beta)*(this.beta+2))/(this.alpha*this.beta*(this.alpha+this.beta+2)*(this.alpha+this.beta+3));
    }

    @Override
    public String toString() {
        return "com.ditcherj.generator.BetaShape{" +
                "attribute_index=" + attribute_index +
                ", alpha=" + alpha +
                ", beta=" + beta +
                ", mean=" + getMean() +
                ", mode=" + getMode() +
                ", median=" + getMedian() +
                ", variance=" + getVariance() +
                ", skewness=" + getSkewness() +
                ", kurtosis_excess=" + getKurtosis_excess() +
                '}';
    }

    public static void main(String[] args) throws Exception {
        for(BetaShape betaShape : BETA_SHAPES){
            for(int i=1; i<=20; i++){
                BetaDistribution beta = new BetaDistribution(betaShape.getAlpha(), betaShape.getBeta());
                double lower = beta.cumulativeProbability(((double)i/20.0)-0.05);
                double upper = beta.cumulativeProbability((double)i/20.0);
                double discrete = upper - lower;
                double cdf = upper;

                System.out.println(betaShape.toString() + " lower=" + lower + ", upper=" +upper);
            }
        }
    }
}
