package SolarisPackage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Jowisz extends Planeta {

    public Jowisz() {
        super.setInklinacja(1.30463);
        super.setAsc(100.4713);
        super.setPerychelium(15.6978);
        super.setDystans(5.202597);
        super.setRuch(0.08309618);
        super.setEkscentrycznosc(0.0484646);
        super.setLongitude(322.55983);
    }
    
    public Jowisz(int kat, int au) {
        super.setInklinacja(1.30463);
        super.setAsc(100.4713);
        super.setPerychelium(15.6978);
        super.setDystans(5.202597);
        super.setRuch(0.08309618);
        super.setEkscentrycznosc(0.0484646);
        super.setLongitude(322.55983);
        super.setKat(kat);
        super.setAu(au);
    }
            
    
        public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(14, 43, 207));
        int rad = (int)(super.getAu()*10);
        g2.draw(new Ellipse2D.Double(500 - rad, 320 - rad, rad * 2, rad * 2));
        g2.setColor(new Color(232, 161, 30));
        double x = rad * Math.cos(super.getKat()) + 500;
        double y = rad * Math.sin(super.getKat()) + 320;
        g2.fillOval((int) x, (int) y, 10, 10);

    }

}