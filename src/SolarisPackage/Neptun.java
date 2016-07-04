package SolarisPackage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Neptun extends Planeta {

    public Neptun() {
        super.setInklinacja(1.7681);
        super.setAsc(131.7925);
        super.setPerychelium(7.206);
        super.setDystans(30.26664);
        super.setRuch(0.005919282);
        super.setEkscentrycznosc(0.0102981);
        super.setLongitude(299.8641);
    }
    
        public Neptun(int kat, int au) {
        super.setInklinacja(1.7681);
        super.setAsc(131.7925);
        super.setPerychelium(7.206);
        super.setDystans(30.26664);
        super.setRuch(0.005919282);
        super.setEkscentrycznosc(0.0102981);
        super.setLongitude(299.8641);
        super.setKat(kat);
        super.setAu(au);
    }
            
    
        public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(14, 43, 207));
        int rad = (int)(super.getAu()*10);
        g2.draw(new Ellipse2D.Double(500 - rad, 320 - rad, rad * 2, rad * 2));
        g2.setColor(new Color(63, 16, 181));
        double x = rad * Math.cos(super.getKat()) + 500;
        double y = rad * Math.sin(super.getKat()) + 320;
        g2.fillOval((int) x, (int) y, 10, 10);

    }
}