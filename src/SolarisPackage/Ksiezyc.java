package SolarisPackage;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Ksiezyc implements Runnable {

    private int dzien, miesiac, rok;
    private JFrame okienko;
    private Thread thr;
    private boolean czas;
    private JLabel Faza;
    private BufferedImage stars;
    private JLayeredPane uklad;
    private moon mon;

    public Ksiezyc() {
        czas = false;
        thr = new Thread(this);
        thr.start();
    }

    public Ksiezyc(int dzien, int miesiac, int rok) {
        this.dzien = dzien;
        this.miesiac = miesiac;
        this.rok = rok;
        czas = false;
        thr = new Thread(this);
        thr.start();
    }

    public void set(int dzien, int miesiac, int rok) {
        this.dzien = dzien;
        this.miesiac = miesiac;
        this.rok = rok;
    }

    public void zamknij() {   
        okienko.dispose();
    }

    public void zatrzymaj() {  
        czas = true;
    }
    public void repaint(){
        mon.repaint();
    }

    public double dataJulianska() {
        double one, two, three, four, five, six, seven, eight, nine;
        one = (miesiac + 9) / 12;
        two = 4716 + rok + Math.floor(one);
        three = 275 * miesiac / 9;
        four = 7 * two / 4;
        five = 1729279.5 + 367 * rok + Math.floor(three) - Math.floor(four) + dzien;
        six = (two + 83) / 100;
        seven = Math.floor(six);
        eight = 3 * (seven + 1) / 4;
        nine = Math.floor(eight);
        double july = five + 38 - nine;
        return july;
    }

    public double faza() {
        double fazen = ((dataJulianska() / 29.5305902778) - 0.3033);
        double faz = fazen - Math.floor(fazen);
        return faz;
    }

    public void run() {

        okienko = new JFrame();

        uklad = new JLayeredPane();
        Faza = new JLabel(Double.toString(faza()));

        mon = new moon();
        mon.setBounds(0, 0, 250, 250);
        uklad.add(mon, new Integer(0));
        okienko.setTitle("Ksiezyc");
        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = screen.getWidth();
        if(width>=1280){
        okienko.setLocation(1030, 0);
        }
        else{
        okienko.setLocation(155, 50);    
        }
        okienko.setSize(245, 270);
        okienko.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        okienko.add(uklad, BorderLayout.CENTER);
        okienko.setVisible(true);

    }

    public class moon extends JPanel {

        public void paintComponent(Graphics g) {
            String plik = "/Images/moon";
            int number = 0;
            for (double a = 0; a < faza(); a = a + 0.05) {
                number++;
            }

            String num = Integer.toString(number);
            String jpg = ".jpg";
            plik = plik + num + jpg;

            super.paintComponent(g);
            try {
                stars = ImageIO.read(getClass().getResource(plik));
            } catch (IOException ex) {
                System.out.println(ex);
            }

            g.drawImage(stars, 0, 0, null);

        }
    }
}
