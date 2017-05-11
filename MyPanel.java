package core1.Wire.GUI;

import core1.Wire.Board;
import core1.Wire.ReadData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Maksim Kapralov on 4/8/2017.
 */

public class MyPanel extends JPanel {

    private Board board;
    public MyPanel()
    {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.board = new Board();
        addMouseListener(new MyMouseEvents(this));
    }
    public MyPanel(File file) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.board = new Board(getDataFromFile(file));
    }

    public Dimension getPreferredSize() {
        return new Dimension(501,501);
    }

    public void refresh()
    {
            board.refresh();
    }

    public void createBoard()
    {
        board.create();
    }

    public boolean hasElectron()
    {return board.hasElectron();}
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = board.getSize();
        ArrayList<Point> conductorPoints = board.getConductorPoints();
        ArrayList<Point> headPoints = board.getHeadPoints();
        ArrayList<Point> tailPoints = board.getTailPoints();
        int length = getWidth() / size;
        int height = getHeight() / size;
        setBackground(Color.black);
        g.setColor(Color.gray);
        int pointX = 1;
        int pointY = 1;
        for(int i = 0; i <= size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                g.drawRect(pointX, pointY, length, height);
                pointX += length;
            }
            pointY += height;
            pointX = 0;
        }


        fillCells(g, conductorPoints, length, height, Color.yellow);
        fillCells(g, headPoints, length, height, Color.blue);
        fillCells(g, tailPoints, length, height, Color.red);

    }

    private void fillCells(Graphics g, ArrayList<Point> points, int length, int height, Color color)
    {
        g.setColor(color);
        for(int i = 0; i < points.size(); i++)
        {
            Point p = points.get(i);
            g.fillRect((int)p.getX() * length + 1, (int)p.getY() * height + 1, length - 1, height - 1);
        }
    }

    public static Properties getDataFromFile(File file)
    {
        ReadData readData = new ReadData(file);
        readData.readData();
        return readData.getData();
    }

    public void addPoint(int x, int y, String type)
    {
        Point p = new Point(x / (this.getWidth() / board.getSize()), y / (this.getHeight() / board.getSize()));
        if("Head".equals(type))
            board.addHeadPoint(p);
        else if("Tail".equals(type))
            board.addTailPoint(p);
        else
            board.addConductorPoint(p);
        repaint();
    }
}

class MyMouseEvents implements MouseListener
{
    MyPanel panel;
    public MyMouseEvents(MyPanel panel)
    {
        super();
        this.panel = panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(e.isMetaDown())
            panel.addPoint(x, y, "Head");
        else if(e.isAltDown())
            panel.addPoint(x, y, "Tail");
        else
            panel.addPoint(x, y, "Conductor");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
