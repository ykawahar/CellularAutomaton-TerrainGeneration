import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MyPanel extends JPanel {

    gol canvaGol;
    int height=50, width=50; //set dimensions here
//    boolean auto = false;
    public Timer t;
    int cellSize = 25;
    boolean cave = false;

    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screensize.height;
    int screenWidth = screensize.width;

    MyPanel() {


        this.cellSize = (Math.min((int)screensize.getWidth()-100,(int)screensize.getHeight()-100)/Math.max(width, height));
        this.setPreferredSize(new Dimension(cellSize*width,cellSize*height));
        this.canvaGol = new gol(height, width);

        animate();
    }

    public void animate(){
        t = new Timer(300, new MoveListener());
    }


    public void paint(Graphics g) {
        Color beach = new Color(254, 255, 188);
        Color grass2 = new Color(0,141,5);
        Color brown = new Color(138, 93, 15);

        Graphics2D g2D = (Graphics2D) g;
        // gol canvaGol = new gol(200, 200);

        if (cave) {
            for (int i = 0; i < canvaGol.getNumRow(); i++) {
                for (int j = 0; j < canvaGol.getNumColumn(); j++) {
                    if (canvaGol.getBoard()[i][j] == 1) {
                        g2D.setPaint(brown);
//                    g2D.fillRect(i*25, j*25, 25, 25);
                        g2D.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    } else if (canvaGol.getBoard()[i][j] == 2) {
                        g2D.setPaint(brown);
//                    g2D.fillRect(i*25, j*25, 25, 25);
                        g2D.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    } else {
                        g2D.setPaint(Color.black);
//                    g2D.fillRect(i*25, j*25, 25, 25);
                        g2D.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    }
                    g2D.setColor(Color.gray);
                    g2D.setStroke(new BasicStroke(1));
//                g2D.drawRect(i*25, j*25, 25, 25);
                    g2D.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
                }
            }
        }
        else {
            for (int i = 0; i < canvaGol.getNumRow(); i++) {
                for (int j = 0; j < canvaGol.getNumColumn(); j++) {
                    if (canvaGol.getBoard()[i][j] == 1) {
                        Random r = new Random();
                        int a = r.nextInt(2);
                        if (a == 1) {
                            g2D.setPaint(Color.green);
                        } else {
                            g2D.setPaint(grass2);
                        }
//                    g2D.fillRect(i*25, j*25, 25, 25);
                        g2D.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);

                    } else if (canvaGol.getBoard()[i][j] == 2) {
                        g2D.setPaint(beach);
//                    g2D.fillRect(i*25, j*25, 25, 25);
                        g2D.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    } else {
                        g2D.setPaint(Color.blue);
//                    g2D.fillRect(i*25, j*25, 25, 25);
                        g2D.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);

                    }
                    g2D.setColor(Color.gray);
                    g2D.setStroke(new BasicStroke(1));
//                g2D.drawRect(i*25, j*25, 25, 25);
                    g2D.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
                }
            }

        }
        //t.start();

        //

        // addMouseListener(new MouseInputAdapter(){
        //     public void mousePressed(MouseEvent e){
        //         g2D.fillOval(30, 30, 30, 70);
                

        //     }
        // });

    }

    /**
     * Resets the board and draws a new one.
     */
    public void reset(){
        gol newBoard = new gol(height,width);
        this.canvaGol = newBoard;
        repaint();
    }

    /**
     * Loads save Data
     * @param filename String of filename
     */
    public void loadData(String filename){
        canvaGol.testBoard(filename + ".txt");

        this.width = canvaGol.getNumColumn();
        this.height = canvaGol.getNumRow();

        repaint();
    }

    public void setMode(){
        cave = !cave;
    }

    public boolean getMode(){
        return cave;
    }

      private class MoveListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!canvaGol.isStable()) {
                    canvaGol.iterate();
                    repaint();
                }
            }
        }
}
