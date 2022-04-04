import javax.imageio.ImageIO;
import javax.print.attribute.standard.RequestingUserName;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.geom.*;
import java.awt.*;

public class TravellingSalesManGUI extends JFrame {

    private JButton Losowo = new JButton("Losowy");
    private JButton genetyczny = new JButton("Genetyczny");
    private JButton wyzarzanie = new JButton("Wyżarzanie");
    private JButton zachlanny = new JButton("Zachlanny");
    private JPanel mapPanel;
    private JLabel mapLabel;
    private Image map;
    private boolean paintcheck = false;
    private boolean geneticCheck = false;
    private boolean annealing = false;
    private boolean greedyCheck = false;
    private int index = 0;
    private int glownyIndex;
    private TravellingSalesManRandom random = new TravellingSalesManRandom("Waszyngton");
    private GeneticTravellingSalesman genetic = new GeneticTravellingSalesman();
    private SimulatedAnnealingTSM anneal = new SimulatedAnnealingTSM(100, 2000, (float) 0.985);
    private TravellingSalesmanGreedy greedy = new TravellingSalesmanGreedy();


    public JPanel panel1 = new JPanel(){
        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(map, 0, 0, null);
            g2.setColor(Color.RED);
            if (paintcheck) {
                for (int i = 0; i < random.getFinalLIst().toArray().length - 1; i++) {
                    g2.drawLine(random.getFinalLIst().get(i).getX(), random.getFinalLIst().get(i).getY(),
                            random.getFinalLIst().get(i + 1).getX(), random.getFinalLIst().get(i + 1).getY());
                    g2.setColor(Color.BLACK);

                    g2.drawString(String.valueOf(i + 1), ((random.getFinalLIst().get(i).getX()) + random.getFinalLIst().get(i + 1).getX()) / 2,
                            ((random.getFinalLIst().get(i).getY()) + random.getFinalLIst().get(i + 1).getY()) / 2);
                    g2.setColor(Color.RED);
                }
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(random.getWayLenght()), 100, 10);
                g2.setColor(Color.RED);
            }

            if(annealing){
                for(int i = 0; i < 15; i++){
                    g2.drawLine(((City) anneal.getFinalList().get(index).get(i)).getX(),
                            ((City) anneal.getFinalList().get(index).get(i)).getY(),
                            ((City) anneal.getFinalList().get(index).get(i + 1)).getX(),
                            ((City) anneal.getFinalList().get(index).get(i + 1)).getY());
                }
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(anneal.getListOfLenghts().get(index)), 50, 10);
                g2.setColor(Color.RED);

                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(anneal.getListOfTemperatures().get(index)), 100, 10);
                g2.setColor(Color.RED);
            }

            if (geneticCheck) {
                for (int j = 0; j < genetic.getFinalList().get(index).toArray().length - 2; j++) {
                    g2.drawLine(((City) genetic.getFinalList().get(index).get(j)).getX(),
                            ((City) genetic.getFinalList().get(index).get(j)).getY(),
                            ((City) genetic.getFinalList().get(index).get(j + 1)).getX(),
                            ((City) genetic.getFinalList().get(index).get(j + 1)).getY());
                }
                g2.setColor(Color.BLACK);
                if(genetic.getFinalList().get(index).toArray().length == 17)
                g2.drawString(genetic.getFinalList().get(index).get(16).toString(), 50, 10);
                g2.setColor(Color.RED);

            }
            if(greedyCheck){
                for(int k=0;k < greedy.getFinalList().toArray().length - 1;k++){
                    g2.drawLine(greedy.getFinalList().get(k).getX(),
                            greedy.getFinalList().get(k).getY(),
                            greedy.getFinalList().get(k+1).getX(),
                            greedy.getFinalList().get(k+1).getY());
                }
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(greedy.getTotalDist()), 50, 10);
                g2.setColor(Color.RED);
            }
        }
    };

    public TravellingSalesManGUI() {
        ImageIcon mapa = new ImageIcon("PROJEKT/map/mapka.png");
        map = mapa.getImage();
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getX() + " " + e.getY());
            }
        });

        zachlanny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greedy.greedyAlgorithm();
                greedyCheck = true;
                panel1.repaint();
            }
        });
        genetyczny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genetic.geneticAlgorithm();
                panel1.repaint();
                geneticCheck = true;
                Runnable paintController = new Runnable() {
                    @Override
                    public void run() {
                        for (int k = 0; k < genetic.getFinalList().toArray().length; k++) {
                            index = k;
                            try {
                                Thread.sleep(0,10);
                                panel1.repaint();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                Thread paintThread = new Thread(paintController);
                paintThread.start();
            }

        });
        Losowo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                random.way();
                paintcheck = true;
                panel1.repaint();
            }
        });
        wyzarzanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annealing = true;
                anneal.algorithm();
                panel1.repaint();
                Runnable paintController = new Runnable() {
                    @Override
                    public void run() {
                        for (int k = 0; k < anneal.getFinalList().toArray().length; k++) {
                            index = k;
                            try {
                                Thread.sleep(50);
                                panel1.repaint();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                Thread paintThread = new Thread(paintController);
                paintThread.start();
            }
        });
        panel1.add(Losowo);
        panel1.add(genetyczny);
        panel1.add(wyzarzanie);
        panel1.add(zachlanny);

    }

    public static void main(String[] args) throws IOException {
        TravellingSalesManGUI frame = new TravellingSalesManGUI();
        frame.setContentPane(new TravellingSalesManGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(798, 570);
    }
}

