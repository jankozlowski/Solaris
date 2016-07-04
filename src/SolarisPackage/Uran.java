package SolarisPackage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Uran extends Planeta {

    public Uran() {
        super.setInklinacja(0.77343);
        super.setAsc(74.0954);
        super.setPerychelium(175.6807);
        super.setDystans(19.30181);
        super.setRuch(0.01162295);
        super.setEkscentrycznosc(0.0428959);
        super.setLongitude(303.18967);
    }
    
    public Uran(int kat, int au) {
        super.setInklinacja(0.77343);
        super.setAsc(74.0954);
        super.setPerychelium(175.6807);
        super.setDystans(19.30181);
        super.setRuch(0.01162295);
        super.setEkscentrycznosc(0.0428959);
        super.setLongitude(303.18967);
        super.setKat(kat);
        super.setAu(au);
    }
            
    
        public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(14, 43, 207));
        int rad = (int)(super.getAu()*10);
        g2.draw(new Ellipse2D.Double(500 - rad, 320 - rad, rad * 2, rad * 2));
        g2.setColor(new Color(170, 240, 240));
        double x = rad * Math.cos(super.getKat()) + 500;
        double y = rad * Math.sin(super.getKat()) + 320;
        g2.fillOval((int) x, (int) y, 10, 10);

    }
}