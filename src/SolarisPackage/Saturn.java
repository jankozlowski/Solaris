package SolarisPackage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Saturn extends Planeta {

    public Saturn() {
        super.setInklinacja(2.48524);
        super.setAsc(113.6358);
        super.setPerychelium(88.863);
        super.setDystans(9.5719);
        super.setRuch(0.03328656);
        super.setEkscentrycznosc(0.0531651);
        super.setLongitude(20.95759);
    }
    
        public Saturn(int kat, int au) {
        super.setInklinacja(2.48524);
        super.setAsc(113.6358);
        super.setPerychelium(88.863);
        super.setDystans(9.5719);
        super.setRuch(0.03328656);
        super.setEkscentrycznosc(0.0531651);
        super.setLongitude(20.95759);
        super.setKat(kat);
        super.setAu(au);
    }
            
    
        public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(14, 43, 207));
        int rad = (int)(super.getAu()*10);
        g2.draw(new Ellipse2D.Double(500 - rad, 320 - rad, rad * 2, rad * 2));
        g2.setColor(new Color(245, 245, 245));
        double x = rad * Math.cos(super.getKat()) + 500;
        double y = rad * Math.sin(super.getKat()) + 320;
        g2.fillOval((int) x, (int) y, 10, 10);

    }
    
}