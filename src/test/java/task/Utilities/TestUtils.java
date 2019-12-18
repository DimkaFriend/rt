package task.Utilities;


import java.io.*;
import java.util.Properties;


public class TestUtils {
    public static Properties properties;

    public static void init()
    {
        initPropreties();
    }

    private static void initPropreties()  {

        try (InputStream input = new FileInputStream("src/test/resources/testData.properties")) {

            properties = new Properties();
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
