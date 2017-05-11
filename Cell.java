package core1.Wire;

/**
 * Created by Maksim Kapralov on 4/6/2017.
 */
public class Cell {
    private int x;
    private int y;
    private int state;

    public Cell(int x, int y, int state)
    {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getState()
    {
        return state;
    }

}
