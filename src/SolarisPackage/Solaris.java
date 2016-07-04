package SolarisPackage;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Solaris implements Runnable, ActionListener, ChangeListener {

    private JFrame okno, oProgramieOkno, oknoPorady, oknoInformacji;
    private JLayeredPane uklad;
    private JMenuItem nowyMenuItem, aktualnyMenuItem, exitMenuItem, oProgramieMenuItem, poradyMenuItem;
    private JCheckBoxMenuItem ksiezycMenuItem;
    private JButton zamknij, start, koniec, wewnatrz, nazewnatrz;
    private JTextField rok, miesiac, dzien, godzina, minuta;
    private JRadioButton year, month, day, hour, minute;
    private JLabel wartosc;
    private JSlider szybkosc;
    private boolean end, orbit1;
    private Planeta Merc, Wen, Ziem, Mar, Jo, Sat, Ura, Nep;
    private Ksiezyc ksiaze;
    private Timer licznik;
    private back wszechswiat;
    private DecimalFormat dFormat = new DecimalFormat("00");
    

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Solaris());

    }

    public class back extends JPanel {

        private BufferedImage stars;

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            try {
                stars = ImageIO.read((getClass().getResource("/Images/stars.jpg")));
            } catch (IOException ex) {
                System.out.println(ex);
            }

            g.drawImage(stars, 0, 0, null);

            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(207, 91, 15));



            if (orbit1 == true) {
                g2.fillOval(485, 305, 30, 30);
                Merc.draw(g);
                Wen.draw(g);
                Ziem.draw(g);
                Mar.draw(g);
            } else {
                g2.fillOval(490, 310, 20, 20);
                Jo.draw(g);
                Sat.draw(g);
                Ura.draw(g);
                Nep.draw(g);
            }
        }
    }

    /*class sluchaczDokumentow implements DocumentListener {

     public void insertUpdate(DocumentEvent e) {
     }

     public void removeUpdate(DocumentEvent e) {
     }

     public void changedUpdate(DocumentEvent e) {
     }
     }*/
    
    public void run() {

        okno = new JFrame("Uklad sloneczny");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu plikMenu = new JMenu("Plik");
        plikMenu.setMnemonic(KeyEvent.VK_P);

        JMenu ksiezycMenu = new JMenu("Ksiezyc");
        ksiezycMenu.setMnemonic(KeyEvent.VK_K);

        JMenu pomocMenu = new JMenu("Pomoc");
        pomocMenu.setMnemonic(KeyEvent.VK_H);

        nowyMenuItem = new JMenuItem("Nowy", KeyEvent.VK_N);
        nowyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        nowyMenuItem.addActionListener(this);
        plikMenu.add(nowyMenuItem);

        aktualnyMenuItem = new JMenuItem("Aktualny czas", KeyEvent.VK_A);
        aktualnyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        aktualnyMenuItem.addActionListener(this);
        plikMenu.add(aktualnyMenuItem);

        exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK | ActionEvent.CTRL_MASK));
        exitMenuItem.addActionListener(this);
        plikMenu.add(exitMenuItem);

        poradyMenuItem = new JMenuItem("Porady", KeyEvent.VK_R);
        poradyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        poradyMenuItem.addActionListener(this);
        pomocMenu.add(poradyMenuItem);

        oProgramieMenuItem = new JMenuItem("O Programie", KeyEvent.VK_F1);
        oProgramieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
        oProgramieMenuItem.addActionListener(this);
        pomocMenu.add(oProgramieMenuItem);

        ksiezycMenuItem = new JCheckBoxMenuItem("Wlacz/Wylacz Ksiezyc");
        ksiezycMenuItem.addActionListener(this);
        ksiezycMenuItem.setState(true);
        ksiezycMenu.add(ksiezycMenuItem);

        menuBar.add(plikMenu);
        menuBar.add(ksiezycMenu);
        menuBar.add(pomocMenu);
        okno.setJMenuBar(menuBar);

        end = true;
        orbit1 = true;

        uklad = new JLayeredPane();
        wszechswiat = new back();
        wszechswiat.setBounds(150, 0, 1050, 720);
        uklad.add(wszechswiat, new Integer(0));

        rok = new JTextField("");
        //  rok.getDocument().addDocumentListener(new sluchaczDokumentow());
        miesiac = new JTextField("");
        //  miesiac.getDocument().addDocumentListener(new sluchaczDokumentow());
        dzien = new JTextField("");
        //  dzien.getDocument().addDocumentListener(new sluchaczDokumentow());
        godzina = new JTextField("");
        minuta = new JTextField("");
        aktualny();

        start = new JButton("Start");
        start.addActionListener(this);
        koniec = new JButton("Koniec");
        koniec.addActionListener(this);

        wewnatrz = new JButton("Uklad wewnetrzny");
        wewnatrz.addActionListener(this);
        nazewnatrz = new JButton("Uklad zewnetrzny");
        nazewnatrz.addActionListener(this);

        year = new JRadioButton("Rok");
        month = new JRadioButton("Miesiac");
        day = new JRadioButton("Dzien");
        hour = new JRadioButton("Godzina");
        minute = new JRadioButton("Minuta");
        day.setSelected(true);

        wartosc = new JLabel("0.001");

        ButtonGroup group = new ButtonGroup();
        group.add(year);
        group.add(month);
        group.add(day);
        group.add(hour);
        group.add(minute);

        szybkosc = new JSlider(1, 3000);
        szybkosc.setValue(1);
        szybkosc.addChangeListener(this);

        okno.setLayout(null);

        JLabel data = new JLabel("Data:");
        okno.add(data);
        data.setBounds(20, 5, 50, 15);
        okno.add(rok);
        rok.setBounds(80, 25, 45, 25);
        okno.add(miesiac);
        miesiac.setBounds(50, 25, 25, 25);
        okno.add(dzien);
        dzien.setBounds(20, 25, 25, 25);
        okno.add(godzina);
        godzina.setBounds(20, 60, 25, 25);
        okno.add(minuta);
        minuta.setBounds(50, 60, 25, 25);

        okno.add(start);
        start.setBounds(20, 100, 80, 25);
        okno.add(koniec);
        koniec.setBounds(20, 130, 80, 25);

        JLabel predkosc = new JLabel("Predkosc animacji:");
        predkosc.setBounds(20, 160, 150, 25);
        okno.add(predkosc);

        okno.add(year);
        year.setBounds(20, 185, 80, 25);
        okno.add(month);
        month.setBounds(20, 210, 80, 25);
        okno.add(day);
        day.setBounds(20, 235, 80, 25);
        okno.add(hour);
        hour.setBounds(20, 260, 80, 25);
        okno.add(minute);
        minute.setBounds(20, 285, 80, 25);

        okno.add(szybkosc);
        szybkosc.setBounds(0, 320, 150, 25);
        okno.add(wartosc);
        wartosc.setBounds(60, 350, 60, 25);

        okno.add(wewnatrz);
        wewnatrz.setBounds(5, 400, 140, 50);
        okno.add(nazewnatrz);
        nazewnatrz.setBounds(5, 460, 140, 50);

        Merc = new Merkury();
        Wen = new Wenus();
        Ziem = new Ziemia();
        Mar = new Mars();
        Jo = new Jowisz();
        Sat = new Saturn();
        Ura = new Uran();
        Nep = new Neptun();

        planetyWewnetrzne();
        ksiaze = new Ksiezyc(Integer.parseInt(dzien.getText()), Integer.parseInt(miesiac.getText()), Integer.parseInt(rok.getText()));

        okno.add(uklad);
        uklad.setBounds(0, 0, 1050, 720);

        okno.setSize(1024, 720);
        okno.setResizable( false );
        okno.setVisible(true);

    }

    public double[] orbita(Planeta x) {
        double inklinacja, asc, perychelium, ruch, longitude;
        double Anomalia, PrawdziwaAnomalia, Long, Radius, Phi, au, stopnie2;
        double[] ret = new double[2];

        inklinacja = x.getInklinacja() * Math.PI / 180;
        asc = x.getAsc() * Math.PI / 180;
        perychelium = x.getPerychelium() * Math.PI / 180;
        ruch = x.getRuch() * Math.PI / 180;
        longitude = x.getLongitude() * Math.PI / 180;

        Anomalia = (ruch * julianski() + longitude - perychelium) % (2 * Math.PI);
        PrawdziwaAnomalia = Anomalia + (2 * x.getEkscentrycznosc() - Math.pow(x.getEkscentrycznosc(), (3 / 4)) + 5 / 96 * Math.pow(x.getEkscentrycznosc(), 5)) * Math.sin(Anomalia)
                + (5 * Math.pow(x.getEkscentrycznosc(), 2 / 4) - ((11 / 24) * Math.pow(x.getEkscentrycznosc(), 4))) * Math.sin(2 * Anomalia)
                + (13 * Math.pow(x.getEkscentrycznosc(), 3 / 12) - ((43 / 64) * Math.pow(x.getEkscentrycznosc(), 5))) * Math.sin(3 * Anomalia)
                + 103 / 96 * Math.pow(x.getEkscentrycznosc(), 4) * Math.sin(4 * Anomalia)
                + 1097 / 960 * Math.pow(x.getEkscentrycznosc(), 5) * Math.sin(5 * Anomalia);
        Long = (PrawdziwaAnomalia + perychelium) % (2 * Math.PI);
        Radius = x.getDystans() * (1 - Math.pow(x.getEkscentrycznosc(), 2)) / (1 + x.getEkscentrycznosc() * Math.cos(Anomalia));
        Phi = Math.asin(Math.sin(Long - asc) * Math.sin(inklinacja));
        stopnie2 = 180 / Math.PI * Phi;
        au = Radius * Math.cos(Phi);
        ret[0] = -Anomalia;
        ret[1] = au;
        return ret;

    }

    public double julianski() {
        int day = Integer.parseInt(dzien.getText());
        int month = Integer.parseInt(miesiac.getText());
        int year = Integer.parseInt(rok.getText());
        int hour = Integer.parseInt(godzina.getText());
        int minute = Integer.parseInt(minuta.getText());

        double juli = 2450680.5;
        double wylicz = 367 * year - Math.floor(7 * (year + Math.floor((month + 9) / 12)) / 4) + Math.floor(275 * month / 9) + day - 730531.5 + (hour + minute / 60) / 60;
        double wynik = wylicz - (juli - 2451545);
        return wynik;
    }

    public void bledy() {

        int day = Integer.parseInt(dzien.getText());
        int month = Integer.parseInt(miesiac.getText());
        int year = Integer.parseInt(rok.getText());
        int hour = Integer.parseInt(godzina.getText());
        int minute = Integer.parseInt(minuta.getText());

        int[] liczba_dni = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (year % 4 == 0) {
            liczba_dni[1] = 29;
        }

        if (year < -4713) {
            throw new IllegalArgumentException("Za wczesny rok");
        }

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Zły miesiac");
        }

        if (day < 1 || day > liczba_dni[month - 1]) {
            throw new IllegalArgumentException("Zły dzien");
        }

        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Zła godzina");
        }

        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Zła minuta");
        }

    }
    
    public void stateChanged(ChangeEvent e) {
        double liczba = szybkosc.getValue();
        wartosc.setText(Double.toString(liczba / 1000));
    }

    public void ustawPlanete(Planeta x) {
        double[] dane = orbita(x);
        x.setKat(dane[0]);
        x.setAu(dane[1]);
    }

    public void planetyWewnetrzne() {
        ustawPlanete(Merc);
        ustawPlanete(Wen);
        ustawPlanete(Ziem);
        ustawPlanete(Mar);
    }

    public void planetyZewnetrzne() {
        ustawPlanete(Jo);
        ustawPlanete(Sat);
        ustawPlanete(Ura);
        ustawPlanete(Nep);
    }

    public void kalendarz() {

        int dayk = Integer.parseInt(dzien.getText());
        int monthk = Integer.parseInt(miesiac.getText());
        int yeark = Integer.parseInt(rok.getText());
        int hourk = Integer.parseInt(godzina.getText());
        int minutek = Integer.parseInt(minuta.getText());

        if (minute.isSelected()) {
            minutek++;
        } else if (hour.isSelected()) {
            hourk++;
        } else if (day.isSelected()) {
            dayk++;
        } else if (month.isSelected()) {
            monthk++;
        }

        if (minutek > 60) {
            minutek = 0;
            hourk++;
            godzina.setText(dFormat.format(hourk));
        }
        minuta.setText(dFormat.format(minutek));

        if (hourk > 23) {
            hourk = 0;
            dayk++;
            dzien.setText(dFormat.format(dayk));
        }
        godzina.setText(dFormat.format(hourk));

        int liczbaDni;
        if (monthk == 1 || monthk == 3 || monthk == 5 || monthk == 7 || monthk == 8 || monthk == 10 || monthk == 12) {
            liczbaDni = 31;
        } else if (monthk == 4 || monthk == 6 || monthk == 9 || monthk == 11) {
            liczbaDni = 30;
        } else {
            if (yeark % 4 == 0) {
                liczbaDni = 29;
            } else {
                liczbaDni = 28;
            }
        }
        if (dayk > liczbaDni) {

            dayk = 1;
            dzien.setText(dFormat.format(dayk));
            monthk++;
            miesiac.setText(dFormat.format(monthk));
        }
        dzien.setText(dFormat.format(dayk));
        if (monthk > 12) {
            monthk = 1;
            yeark++;
            rok.setText(dFormat.format(yeark));
        }
        miesiac.setText(dFormat.format(monthk));

        if (year.isSelected()) {
            yeark++;
            rok.setText(dFormat.format(yeark));
        }

        if (!year.isSelected() && !month.isSelected() && !day.isSelected() && !hour.isSelected() && !minute.isSelected()) {
            throw new IllegalStateException();
        }

    }

    public void nowy() {
        rok.setText(" ");
        miesiac.setText(" ");
        dzien.setText(" ");
        godzina.setText(" ");
        minuta.setText(" ");
    }

    public void aktualny() {
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        rok.setText(dFormat.format(year));
        miesiac.setText(dFormat.format(month + 1));
        dzien.setText(dFormat.format(day));
        godzina.setText(dFormat.format(hour));
        minuta.setText(dFormat.format(minute));
    }

    public void oProgramie() {
        oProgramieOkno = new JFrame("O Programie");
        oProgramieOkno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        oProgramieOkno.setLayout(new BoxLayout(oProgramieOkno.getContentPane(), BoxLayout.Y_AXIS));

        Font Czcionka = new Font("Arial", Font.PLAIN, 16);

        JTextField nazwa = new JTextField("Solaris 1.0");
        nazwa.setEditable(false);
        nazwa.setOpaque(false);
        nazwa.setHorizontalAlignment(JTextField.CENTER);
        nazwa.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        JTextArea informacje = new JTextArea("\nProgram pozwala uzytkownikowi na symulacje ziemskiego ukladu slonecznego uwzgledniajacy stosunek wielkosci orbit i czasu obrotu planety, jak także umozliwia zobrazowanie fazy ksiezyca dla wybranej daty.");
        informacje.setEditable(false);
        informacje.setLineWrap(true);
        informacje.setOpaque(false);
        informacje.setWrapStyleWord(true);

        JTextArea tworca = new JTextArea("Autor: Jan Kozlowski\n\nTelefon: 514393032\n\nE-mail: jankozlowsk@gmail.com");
        tworca.setEditable(false);
        tworca.setLineWrap(true);
        tworca.setOpaque(false);
        tworca.setWrapStyleWord(true);

        zamknij = new JButton("Zamknij");
        zamknij.addActionListener(this);

        nazwa.setFont(new Font("Arial", Font.BOLD, 32));
        informacje.setFont(Czcionka);
        tworca.setFont(Czcionka);
        oProgramieOkno.add(nazwa);
        oProgramieOkno.add(informacje);
        oProgramieOkno.add(tworca);
        oProgramieOkno.add(zamknij);

        oProgramieOkno.setSize(700, 350);
        oProgramieOkno.setVisible(true);
    }

    public void porady() {

        ArrayList<String> madrosci = new ArrayList<String>();
        madrosci.add("Odwazni nie zyja wiecznie, ostrozni nie zyja wcale");
        madrosci.add("Gdybym tak byl, gdzie chcialbym byc. To bylbym tam, gdzie nie mna mnie. Lecz tu, gdzie jestem, musze byc. Nie moge byc zas tam, gdzie chce");
        madrosci.add("Wiedze mozemy przejac od innych, ale madrosci musimy nauczyc sie sami");
        madrosci.add("Nic na tym swiecie nie stoi Ci na prszeszkodzie, poza Twoim wlasnym przekonaniem, ze cos Ci przeszkadza");
        madrosci.add("Idz swoja droga, a ludzie niech mowia, co chca");
        madrosci.add("Nikogo nie mozesz zmusic do tego aby cie kochal, ale to co mozesz zrobic to byc kims kogo warto kochac");
        madrosci.add("Jesli jedna osoba cos potrafi, kazda moze sie tego nauczyc");
        madrosci.add("Nigdy nie pozwol, by szkola stanela na drodze Twojej edukacji");
        madrosci.add("W gruncie rzeczy wazniejsze jest aby miec cel niz zeby go osiagnac");
        madrosci.add("Odpierwiastkuj sie ode mnie bo jak ci przyparabole to ci potega z niawiasow wypadnie");
        madrosci.add("Co dostane za pieniadze? Zdrowie? Nie tylko lekarzy. Wiare? Nie tylko ksiezy. Milosc? Nie tylko chetne kobiety. Przyjazn? Nie tylko towarzystwo. Dom? Nie tylko budynek");
        madrosci.add("People shouldn't be affraid of their goverment it's the goerment that should be afraid of it's people");
        madrosci.add("Ci ktorzy odrzuczaja wolnosc dla bezpieczenstwa, nie zasluguja ani na jedno ani na drugie");
        madrosci.add("Might makes right\n");
        madrosci.add("Usmiech kosztuje mniej niz elektrycznosc i daje wiecej swiatla");
        madrosci.add("Lepszy w wolnosci kesek byle jaki niz w niewoli przysmaki");
        madrosci.add("Gdy jezyk potrafi juz tylko narzekac i krytykowac, oznacza to, ze chore jest serce");
        madrosci.add("Szacunkiem dla prszeszlosci jest praca w przyszlosci");
        madrosci.add("Nie idz za tlumem bo nigdzie nie dojdziesz");
        madrosci.add("Drogi wcale nie musza dokads prowadzic. Musza sie tylko gdzies zaczynac");

        Random r = new Random();
        int x = r.nextInt(20);

        JOptionPane.showMessageDialog(oknoPorady, madrosci.get(x), "Porada", JOptionPane.INFORMATION_MESSAGE);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == nowyMenuItem) {
            nowy();
        } else if (source == aktualnyMenuItem) {
            aktualny();
        } else if (source == exitMenuItem) {
            System.exit(0);
        } else if (source == oProgramieMenuItem) {
            oProgramie();
        } else if (source == poradyMenuItem) {
            porady();
        } else if (source == zamknij) {
            oProgramieOkno.dispose();
        } else if (source == ksiezycMenuItem) {
            if (ksiezycMenuItem.getState() == true) {
                ksiaze = new Ksiezyc(Integer.parseInt(dzien.getText()), Integer.parseInt(miesiac.getText()), Integer.parseInt(rok.getText()));
            } else {
                ksiaze.zamknij();
            }
        } else if (source == start) {
            try {
                bledy();
                end = false;
                licznik = new Timer(szybkosc.getValue(), this);
                licznik.start();

            } catch (IllegalArgumentException l) {
                JOptionPane.showMessageDialog(oknoInformacji, "Podales bledna date popraw swoj formularz", "Blad", JOptionPane.WARNING_MESSAGE);
            }

        } else if (source == koniec) {
            end = true;
        } else if (source == licznik) {
            if (end == false) {
                if (orbit1 == true) {
                    planetyWewnetrzne();
                } else {
                    planetyZewnetrzne();
                }
                kalendarz();
                wszechswiat.repaint();
                ksiaze.set(Integer.parseInt(dzien.getText()), Integer.parseInt(miesiac.getText()), Integer.parseInt(rok.getText()));
                ksiaze.repaint();

            } else if (end == true) {
                licznik.stop();
            }
        } else if (source == wewnatrz) {
            orbit1 = true;
            planetyWewnetrzne();
            wszechswiat.repaint();
        } else if (source == nazewnatrz) {
            orbit1 = false;
            planetyZewnetrzne();
            wszechswiat.repaint();
        }

    }
}
