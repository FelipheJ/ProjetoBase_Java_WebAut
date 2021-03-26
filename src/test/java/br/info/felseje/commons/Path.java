package br.info.felseje.commons;

import java.io.IOException;

import br.info.felseje.exceptions.PathException;
import io.cucumber.java.Status;
import br.info.felseje.exceptions.PathNotFoundException;
import br.info.felseje.exceptions.PathNotImplementedException;

import static br.info.felseje.commons.Utils.FileUtils.*;

/**
 * Path utils.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class Path {

    private static final String TOP_IMAGE_PATH;
    private static final String PROPERTIES_FILE;

    static {
        TOP_IMAGE_PATH = "config" + separator() + "evidence" + separator() + "top-image.png";
        PROPERTIES_FILE = userdir() + separator() + "config" + separator() + "webdrivers.properties";
    }

    /**
     *
     * @return the path to current directory.
     */
    private static String userdir() { return System.getProperty("user.dir"); }

    /**
     *
     * @return the file separator of the current OS.
     */
    private static String separator() {
        return System.getProperty("file.separator");
    }

    /**
     * Obtains the path of specified browser (if it is already implemented).
     * @param browserName name of browser.
     * @return browserPath.
     */
    public static String getBrowserPath(String browserName) {
        try {
            switch (browserName.toUpperCase()) {
                case "CHROME":
                    return getProperty(PROPERTIES_FILE, "chrome.path");
                case "IE":
                    return getProperty(PROPERTIES_FILE, "ie.path");
                case "FIREFOX":
                    return getProperty(PROPERTIES_FILE, "firefox.path");
                case "SAFARI":
                    return getProperty(PROPERTIES_FILE, "safari.path");
                case "OPERA":
                    return getProperty(PROPERTIES_FILE, "opera.path");
                case "EDGE":
                    return getProperty(PROPERTIES_FILE, "edge.path");
                default:
                    throw new PathNotImplementedException("This browserName hasn't yet been implemented.");
            }
        } catch (IOException ioEx) {
            throw new PathException("Did you set up the webdriver.properties file?", ioEx);
        }
    }

    public static String getEvidencePath() {
        return userdir() + separator() + "evidence" + separator();
    }

    public static String getEvidencePath(Status status) {
        String folderName;
        if (status == Status.PASSED) {
            folderName = "Passed";
        } else if (status == Status.FAILED) {
            folderName = "Failed";
        } else throw new IllegalArgumentException("Not recognized status " + status.name());
        return System.getProperty("user.dir") + separator() + "evidence" + separator() + folderName + separator();
    }

    public static String getTopImagePath() {
        return TOP_IMAGE_PATH;
    }
}
