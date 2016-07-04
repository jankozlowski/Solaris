package SolarisPackage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Wenus extends Planeta {

    public Wenus() {
        super.setInklinacja(3.39472);
        super.setAsc(76.6889);
        super.setPerychelium(131.761);
        super.setDystans(0.7233238);
        super.setRuch(1.602158);
        super.setEkscentrycznosc(0.0067933);
        super.setLongitude(236.94045);
    }
    
        public Wenus(int kat, int au) {
        super.setInklinacja(3.39472);
        super.setAsc(76.6889);
        super.setPerychelium(131.761);
        super.setDystans(0.7233238);
        super.setRuch(1.602158);
        super.setEkscentrycznosc(0.0067933);
        super.setLongitude(236.94045);
        super.setKat(kat);
        super.setAu(au);
    }
            
    
        public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(14, 43, 207));
        int rad = (int)(super.getAu()*200);
        g2.draw(new Ellipse2D.Double(500 - rad, 320 - rad, rad * 2, rad * 2));
        g2.setColor(new Color(245, 232, 220));
        double x = rad * Math.cos(super.getKat()) + 500;
        double y = rad * Math.sin(super.getKat()) + 320;
        g2.fillOval((int) x, (int) y, 10, 10);

    }
}

