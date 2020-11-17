package br.com.projeto.evidence.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class EvidenceUtils {

    protected static Properties properties;

    public static Properties loadProperties(String fileName) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(fileName));
        return properties;
    }

    public static boolean createEvidenceDirectory(String directory) {
        boolean exists;
        File dir, subDir;
        String nDir = "";
        String folderName[] = { "Passed", "Failed" };
        try {
            if (!(dir = new File(directory)).exists()) {
                dir.mkdir();
            }
            for (int i = 0; i < 2; i++) {
                nDir = dir.getAbsolutePath()
                        + System.getProperty("file.separator")
                        + folderName[i];
                if (!(subDir = new File(nDir)).exists()) {
                    subDir.mkdir();
                }
            }
            exists = true;
        } catch (SecurityException securityException) {
            exists = false;
            securityException.printStackTrace(System.err);
        }
        return exists;
    }
}
