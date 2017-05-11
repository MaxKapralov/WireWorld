package core1.Wire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Maksim Kapralov on 4/6/2017.
 */
public class ReadData {
    private Properties properties;
    FileInputStream stream;

    public ReadData(File file)
    {
        try
        {
            stream = new FileInputStream(file);
        }catch(FileNotFoundException e)
        {
            System.err.println(e.getMessage());
            System.exit(2);
        }
        properties = new Properties();
    }

    public void readData()
    {
        try {
            properties.load(stream);
            stream.close();
        }catch(IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public Properties getData()
    {
        return properties;
    }
}
