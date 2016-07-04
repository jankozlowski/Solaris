package SolarisPackage;


import java.awt.Graphics;
import javax.swing.JPanel;


abstract public class Planeta extends JPanel {

    private double inklinacja, asc, perychelium, dystans, ruch, ekscentrycznosc, longitude,kat,au;

    public double getInklinacja() {
        return inklinacja;
    }

    public double getAsc() {
        return asc;
    }

    public double getPerychelium() {
        return perychelium;
    }

    public double getDystans() {
        return dystans;
    }

    public double getRuch() {
        return ruch;
    }

    public double getEkscentrycznosc() {
        return ekscentrycznosc;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getKat() {
        return kat;
    }

        public double getAu() {
        return au;
    }

    public void setInklinacja(double ink) {
        inklinacja=ink;
    }
    public void setAsc(double asc) {
        this.asc=asc;
    }

    public void setPerychelium(double per) {
        perychelium=per;
    }

    public void setDystans(double dys) {
        dystans=dys;
    }

    public void setRuch(double ruch) {
        this.ruch=ruch;
    }

    public void setEkscentrycznosc(double eks) {
        ekscentrycznosc=eks;
    }

    public void setLongitude(double lon) {
        longitude=lon;
    }
    
    public void setKat(double kat) {
        this.kat=kat;
    }
        public void setAu(double au) {
        this.au=au;
    }
    abstract void draw(Graphics g);
}
