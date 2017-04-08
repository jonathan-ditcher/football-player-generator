package com.ditcherj.generator.dto;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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

    public Position() {
        List<Position> positionList = new LinkedList();
        Collections.sort(positionList, new Comparator<Position>() {
            public int compare(Position o1, Position o2) {
                return 0;
            }
        });
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
        int largest = this.dr;
        if(this.dl > largest) {
            largest = this.dl;
        }

        if(this.dc > largest) {
            largest = this.dc;
        }

        return largest;
    }

    public int getWingBack() {
        int largest = this.wbr;
        if(this.wbl > largest) {
            largest = this.wbl;
        }

        return largest;
    }

    public int getMidfielder() {
        int largest = this.mr;
        if(this.ml > largest) {
            largest = this.ml;
        }

        if(this.mc > largest) {
            largest = this.mc;
        }

        return largest;
    }

    public int getAttackingMidfielder() {
        int largest = this.amr;
        if(this.aml > largest) {
            largest = this.aml;
        }

        if(this.amc > largest) {
            largest = this.amc;
        }

        return largest;
    }

    public int getRight() {
        int largest = this.dr;
        if(this.wbr > largest) {
            largest = this.wbr;
        }

        if(this.mr > largest) {
            largest = this.mr;
        }

        if(this.amr > largest) {
            largest = this.amr;
        }

        return largest;
    }

    public int getLeft() {
        int largest = this.dl;
        if(this.wbl > largest) {
            largest = this.wbl;
        }

        if(this.ml > largest) {
            largest = this.ml;
        }

        if(this.aml > largest) {
            largest = this.aml;
        }

        return largest;
    }

    public int getCentre() {
        int largest = this.dc;
        if(this.dm > largest) {
            largest = this.dm;
        }

        if(this.mc > largest) {
            largest = this.mc;
        }

        if(this.amc > largest) {
            largest = this.amc;
        }

        return largest;
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        if(this.gk > 15) {
            sb.append("/GK");
        }

        if(this.getDefender() > 15) {
            sb.append("/D");
        }

        if(this.getWingBack() > 15) {
            sb.append("/WB");
        }

        if(this.dm > 15) {
            sb.append("/DM");
        }

        if(this.getMidfielder() > 15) {
            sb.append("/M");
        }

        if(this.getAttackingMidfielder() > 15) {
            sb.append("/AM");
        }

        if(this.st > 15) {
            sb.append("/S");
        }

        sb.append(" ");
        if(this.getRight() >= 15) {
            sb.append("R");
        }

        if(this.getLeft() >= 15) {
            sb.append("L");
        }

        if(this.getCentre() >= 15) {
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

    public enum Positions {
        GK("Goalkeeper"),
        SW("Sweeper"),
        DR("Defender (Right)"),
        DL("Defender (Left)"),
        DC("Defender (Center)"),
        WBR("Wing Back (Right)"),
        WBL("Wing Back (Left)"),
        DM("Defensive Midfielder"),
        ML("Midfielder (Left)"),
        MC("Midfielder (Center)"),
        MR("Midfielder (Right)"),
        AML("Attacking Midfielder (Left)"),
        AMC("Attacking Midfielder (Center)"),
        AMR("Attacking Midfielder (Right)"),
        ST("Striker");

        public String label;

        private Positions(String label) {
            this.label = label;
        }
    }
}
