package core1.Wire;

import java.awt.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Maksim Kapralov on 4/6/2017.
 */
public class Board {
    private Cell[][] board;
    private int size;
    private ArrayList<Point> conductorPoints;
    private ArrayList<Point> headPoints;
    private ArrayList<Point> tailPoints;
    public Board()
    {
        this.size = 15;
        board = new Cell[size][size];
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                board[i][j] = new Empty(j, i);
            }
        }
        conductorPoints = new ArrayList<>();
        headPoints = new ArrayList<>();
        tailPoints = new ArrayList<>();
    }
    public Board(Properties data)
    {
        size = Integer.parseInt((String)data.get("Size"));
        board = new Cell[size][size];
        conductorPoints = new ArrayList<>();
        headPoints = new ArrayList<>();
        tailPoints = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                board[i][j] = new Empty(j, i);
            }
        }
        String[] conductor = ((String)data.get("Conductor")).split(" ");
        for(int i = 0; i < conductor.length; i += 2)
        {
            conductorPoints.add(new Point(Integer.parseInt(conductor[i]), Integer.parseInt(conductor[i + 1])));
        }

        String[] electronHead = ((String)data.get("ElectronHead")).split(" ");
        for(int i = 0; i < electronHead.length; i += 2)
        {
            headPoints.add(new Point(Integer.parseInt(electronHead[i]), Integer.parseInt(electronHead[i + 1])));
        }

        String[] electronTail = ((String) data.get("ElectronTail")).split(" ");
        for(int i = 0; i < electronTail.length; i += 2)
        {
            tailPoints.add(new Point(Integer.parseInt(electronTail[i]), Integer.parseInt(electronTail[i + 1])));
        }
    }

    public ArrayList<Point> getConductorPoints()
    {
        return conductorPoints;
    }

    public ArrayList<Point> getHeadPoints()
    {
        return headPoints;
    }

    public int getSize()
    {
        return size;
    }
    public boolean hasElectron()
    {
        if(headPoints.size() == 0 || tailPoints.size() == 0)
            return false;
        return true;
    }

    public ArrayList<Point> getTailPoints()
    {
        return tailPoints;
    }
    public void printBoard()
    {
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
                System.out.print(board[i][j].getState());
            System.out.println();
        }

    }

    private void changeState()
    {
        Cell[][] newBoard = newBoard();
        for(int i = 0; i < conductorPoints.size(); i++)
        {
            Point p = conductorPoints.get(i);
            if(board[p.y][p.x] instanceof ElectronHead)
                newBoard[p.y][p.x] = new ElectronTail(p.x, p.y);
            else if(board[p.y][p.x] instanceof ElectronTail)
                newBoard[p.y][p.x] = new Conductor(p.x, p.y);
            else
            {
                int num = checkConductor(p.x, p.y);
                if(num == 1 || num == 2)
                    newBoard[p.y][p.x] = new ElectronHead(p.x, p.y);
                else
                    newBoard[p.y][p.x] = new Conductor(p.x, p.y);
            }
        }
        board = newBoard;
    }

    public int checkConductor(int x, int y)
    {
        int num = 0;
        for(int i = x - 1; i <= x + 1; i++)
        {
            if(i < 0 || i > size - 1) continue;
            for(int j = y - 1; j <= y + 1; j++)
            {
                if(y < 0 || y > size - 1 || (x == i && y == j)) continue;
                if(board[j][i] instanceof ElectronHead)
                    num++;
            }
        }

        return num;
    }

    private Cell[][] newBoard()
    {
        Cell[][] newBoard = new Cell[size][size];
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
                newBoard[i][j] = new Empty(j, i);
        }
        return newBoard;
    }

    private void addConductor(int x, int y)
    {
        board[y][x] = new Conductor(x, y);
    }

    private void addElectronHead(int x, int y)
    {
        board[y][x] = new ElectronHead(x, y);
    }

    private void addElectronTail(int x, int y)
    {
        board[y][x] = new ElectronTail(x, y);
    }

    public void create()
    {
        for(int i = 0; i < conductorPoints.size(); i++)
        {
            addConductor((int)conductorPoints.get(i).getX(), (int)conductorPoints.get(i).getY());
        }

        for(int i = 0; i < headPoints.size(); i++)
        {
            addElectronHead((int)headPoints.get(i).getX(), (int)headPoints.get(i).getY());
        }

        for(int i = 0; i < tailPoints.size(); i++)
        {
            addElectronTail((int)tailPoints.get(i).getX(), (int)tailPoints.get(i).getY());
        }
    }

    public void refresh()
    {
        changeState();
        ArrayList<Point> elHead = new ArrayList<>();
        ArrayList<Point> elTail = new ArrayList<>();
        for(int i = 0; i < conductorPoints.size(); i++)
        {
            Point p = conductorPoints.get(i);
            if(board[p.y][p.x] instanceof ElectronHead)
                elHead.add(p);
            else if(board[p.y][p.x] instanceof ElectronTail)
                elTail.add(p);
        }
        headPoints = elHead;
        tailPoints = elTail;
    }
    public void addConductorPoint(Point p)
    {
        this.conductorPoints.add(p);
    }
    public void addHeadPoint(Point p){this.headPoints.add(p);}
    public void addTailPoint(Point p){this.tailPoints.add(p);}
}
