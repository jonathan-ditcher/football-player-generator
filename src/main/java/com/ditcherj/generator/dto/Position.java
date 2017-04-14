package com.ditcherj.generator.dto;

import java.io.Serializable;

public class Position implements Serializable {

    private int leftFoot;
    private int rightFoot;
    private int gk;
    private int sw;
    private int dr;
    private int dl;
    private int dc;
    private int wbr;
    private int wbl;
    private int dm;
    private int ml;
    private int mc;
    private int mr;
    private int aml;
    private int amc;
    private int amr;
    private int st;

    public Position() {
    }

    public Position(int leftFoot, int rightFoot, int gk, int sw, int dr, int dl, int dc, int wbr, int wbl, int dm, int ml, int mc, int mr, int aml, int amc, int amr, int st) {
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
        this.gk = gk;
        this.sw = sw;
        this.dr = dr;
        this.dl = dl;
        this.dc = dc;
        this.wbr = wbr;
        this.wbl = wbl;
        this.dm = dm;
        this.ml = ml;
        this.mc = mc;
        this.mr = mr;
        this.aml = aml;
        this.amc = amc;
        this.amr = amr;
        this.st = st;
    }

    public int getLeftFoot() {
        return this.leftFoot;
    }

    public void setLeftFoot(int leftFoot) {
        this.leftFoot = leftFoot;
    }

    public int getRightFoot() {
        return this.rightFoot;
    }

    public void setRightFoot(int rightFoot) {
        this.rightFoot = rightFoot;
    }

    public int getGk() {
        return this.gk;
    }

    public void setGk(int gk) {
        this.gk = gk;
    }

    public int getSw() {
        return this.sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public int getDr() {
        return this.dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }

    public int getDl() {
        return this.dl;
    }

    public void setDl(int dl) {
        this.dl = dl;
    }

    public int getDc() {
        return this.dc;
    }

    public void setDc(int dc) {
        this.dc = dc;
    }

    public int getWbr() {
        return this.wbr;
    }

    public void setWbr(int wbr) {
        this.wbr = wbr;
    }

    public int getWbl() {
        return this.wbl;
    }

    public void setWbl(int wbl) {
        this.wbl = wbl;
    }

    public int getDm() {
        return this.dm;
    }

    public void setDm(int dm) {
        this.dm = dm;
    }

    public int getMl() {
        return this.ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }

    public int getMc() {
        return this.mc;
    }

    public void setMc(int mc) {
        this.mc = mc;
    }

    public int getMr() {
        return this.mr;
    }

    public void setMr(int mr) {
        this.mr = mr;
    }

    public int getAml() {
        return this.aml;
    }

    public void setAml(int aml) {
        this.aml = aml;
    }

    public int getAmc() {
        return this.amc;
    }

    public void setAmc(int amc) {
        this.amc = amc;
    }

    public int getAmr() {
        return this.amr;
    }

    public void setAmr(int amr) {
        this.amr = amr;
    }

    public int getSt() {
        return this.st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public int getDefender() {
        return Math.max(Math.max(this.dr, this.dl), this.dc);
    }

    public int getWingBack() {
        return Math.max(this.wbr, this.wbl);
    }

    public int getMidfielder() {
        return Math.max(Math.max(this.mr, this.ml), this.mc);
    }

    public int getAttackingMidfielder() {
        return Math.max(Math.max(this.amr, this.aml), this.amc);
    }

    public int getRight() {
        return Math.max(Math.max(Math.max(this.dr, this.wbr), this.mr), this.amr);
    }

    public int getLeft() {
        return Math.max(Math.max(Math.max(this.dl, this.wbl), this.ml), this.aml);
    }

    public int getCentre() {
        return Math.max(Math.max(Math.max(this.dc, this.dm), this.mc), this.amc);
    }

    public String getDescription() {
        final int threshold = 15;

        StringBuilder sb = new StringBuilder();
        if(this.gk > threshold) {
            sb.append("/GK");
        }

        if(this.getDefender() > threshold) {
            sb.append("/D");
        }

        if(this.getWingBack() > threshold) {
            sb.append("/WB");
        }

        if(this.dm > threshold) {
            sb.append("/DM");
        }

        if(this.getMidfielder() > threshold) {
            sb.append("/M");
        }

        if(this.getAttackingMidfielder() > threshold) {
            sb.append("/AM");
        }

        if(this.st > threshold) {
            sb.append("/S");
        }

        sb.append(" ");
        if(this.getRight() >= threshold) {
            sb.append("R");
        }

        if(this.getLeft() >= threshold) {
            sb.append("L");
        }

        if(this.getCentre() >= threshold) {
            sb.append("C");
        }

        sb.deleteCharAt(0);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Position{" +
                "leftFoot=" + leftFoot +
                ", rightFoot=" + rightFoot +
                ", gk=" + gk +
                ", sw=" + sw +
                ", dr=" + dr +
                ", dl=" + dl +
                ", dc=" + dc +
                ", wbr=" + wbr +
                ", wbl=" + wbl +
                ", dm=" + dm +
                ", ml=" + ml +
                ", mc=" + mc +
                ", mr=" + mr +
                ", aml=" + aml +
                ", amc=" + amc +
                ", amr=" + amr +
                ", st=" + st +
                '}';
    }
}
