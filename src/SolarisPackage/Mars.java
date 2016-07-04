package SolarisPackage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Mars extends Planeta {

    public Mars() {
        super.setInklinacja(1.84992);
        super.setAsc(49.5664);
        super.setPerychelium(336.0882);
        super.setDystans(1.5236365);
        super.setRuch(0.5240613);
        super.setEkscentrycznosc(0.0934231);
        super.setLongitude(262.42784);
    }
    
        public Mars(int kat, int au) {
        super.setInklinacja(1.84992);
        super.setAsc(49.5664);
        super.setPerychelium(336.0882);
        super.setDystans(1.5236365);
        super.setRuch(0.5240613);
        super.setEkscentrycznosc(0.0934231);
        super.setLongitude(262.42784);
        super.setKat(kat);
        super.setAu(au);
    }
            
    
        public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(14, 43, 207));
        int rad = (int)(super.getAu()*200);
        g2.draw(new Ellipse2D.Double(500 - rad, 320 - rad, rad * 2, rad * 2));
        g2.setColor(new Color(194, 14, 32));
        double x = rad * Math.cos(super.getKat()) + 500;
        double y = rad * Math.sin(super.getKat()) + 320;
        g2.fillOval((int) x, (int) y, 10, 10);

    }
}


