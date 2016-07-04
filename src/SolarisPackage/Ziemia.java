package SolarisPackage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Ziemia extends Planeta {

    public Ziemia() {
        super.setInklinacja(0);
        super.setAsc(349.2);
        super.setPerychelium(102.8517);
        super.setDystans(1);
        super.setRuch(0.9855796);
        super.setEkscentrycznosc(0.0166967);
        super.setLongitude(328.40353);
    }
    
            public Ziemia(int kat, int au) {
        super.setInklinacja(0);
        super.setAsc(349.2);
        super.setPerychelium(102.8517);
        super.setDystans(1);
        super.setRuch(0.9855796);
        super.setEkscentrycznosc(0.0166967);
        super.setLongitude(328.40353);
        super.setKat(kat);
        super.setAu(au);
    }
            
    
        public void draw(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(14, 43, 207));
        int rad = (int)(super.getAu()*200);
        g2.draw(new Ellipse2D.Double(500 - rad, 320 - rad, rad * 2, rad * 2));
        g2.setColor(new Color(128, 168, 209));
        double x = rad * Math.cos(super.getKat()) + 500;
        double y = rad * Math.sin(super.getKat()) + 320;
        g2.fillOval((int) x, (int) y, 10, 10);

    }
}


