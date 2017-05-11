package core1.Wire;

import java.io.*;
import java.util.Properties;

/**
 * Created by Maksim Kapralov on 4/6/2017.
 */
public class WriteData {

    public static void main(String[] args)
    {
        Properties pr = new Properties();
        FileOutputStream stream = null;
        String state = null, points = null;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            do {
                System.out.print("State: ");
                state = reader.readLine();
                if("exit".equals(state)) continue;
                System.out.print("Points(x, y): ");
                points = reader.readLine();
                pr.put(state, points);

            } while (!"exit".equals(state));
        }catch(IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        try
        {
            stream = new FileOutputStream("Data");
            pr.store(stream, "WireWorldStates");
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }
}
