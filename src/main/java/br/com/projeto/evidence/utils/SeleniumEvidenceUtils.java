package br.com.projeto.evidence.utils;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class SeleniumEvidenceUtils {

    protected static Properties properties;

    public static Properties loadProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("init.properties"));
        return properties;
    }

}
