package SolarisPackage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Merkury extends Planeta {

    public Merkury() {
        super.setInklinacja(7.00507);
        super.setAsc(48.3339);
        super.setPerychelium(77.454);
        super.setDystans(0.3870978);
        super.setRuch(4.092353);
        super.setEkscentrycznosc(0.2056324);
        super.setLongitude(314.42369);
        super.setKat(2);
        super.setKat(0.40);
    }
     
    public Merkury(int kat, int au) {
        super.setInklinacja(7.00507);
        super.setAsc(48.3339);
        super.setPerychelium(77.454);
        super.setDystans(0.3870978);
        super.setRuch(4.092353);
        super.setEkscentrycznosc(0.2056324);
        super.setLongitude(314.42369);
        super.setKat(kat);
        super.setAu(au);
    }
    
    public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(14, 43, 207));
        int rad = (int)(super.getAu()*200);
        g2.draw(new Ellipse2D.Double(500 - rad, 320 - rad, rad * 2, rad * 2));
        g2.setColor(new Color(184, 156, 132));
        double x = rad * Math.cos(super.getKat()) + 500;
        double y = rad * Math.sin(super.getKat()) + 320;
        g2.fillOval((int) x, (int) y, 10, 10);

    }
}
