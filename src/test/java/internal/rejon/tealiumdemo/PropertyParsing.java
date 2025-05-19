package internal.rejon.tealiumdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyParsing {
    static Properties properties = new Properties();

    public static Properties getProperties() throws IOException {
        properties.load(new FileInputStream(System.getProperty("user.dir") + "/site.properties"));
        return properties;
    }
}
