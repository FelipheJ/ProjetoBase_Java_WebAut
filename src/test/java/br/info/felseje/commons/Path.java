package br.info.felseje.commons;

import io.cucumber.java.Status;
import br.info.felseje.exceptions.PathNotImplementedException;

/**
 * Path utils.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class Path {

    private static final String IE_PATH;
    private static final String EDGE_PATH;
    private static final String OPERA_PATH;
    private static final String SAFARI_PATH;
    private static final String CHROME_PATH;
    private static final String FIREFOX_PATH;

    static {
        IE_PATH = "drivers" + separator() + "iedriver_390_x64.exe";
        EDGE_PATH = "drivers" + separator() + "edgedriver_89_x64.exe";
        SAFARI_PATH = separator() + "usr" + separator() + "bin" + separator() + "safaridriver";
        OPERA_PATH = "drivers" + separator() + "operadriver_86_x64.exe";
        FIREFOX_PATH = "drivers" + separator() + "geckodriver_0280_x64.exe";
        CHROME_PATH = "drivers" + separator() + "chromedriver_87_x32.exe";
    }

    /**
     * Obtains the path of specified browser (if it is already implemented).
     * @param browserName name of browser.
     * @return browserPath.
     */
    public static String get(String browserName) {
        switch (browserName.toUpperCase()) {
            case "CHROME":
                return CHROME_PATH;
            case "IE":
                return IE_PATH;
            case "FIREFOX":
                return FIREFOX_PATH;
            case "SAFARI":
                return SAFARI_PATH;
            case "OPERA":
                return OPERA_PATH;
            case "EDGE":
                return EDGE_PATH;
            default:
                throw new PathNotImplementedException("This browserName hasn't yet been implemented.");
        }
    }

    /**
     *
     * @return the file separator of the current OS.
     */
    private static String separator() {
        return System.getProperty("file.separator");
    }

    public static String getEvidencePath() {
        return System.getProperty("user.dir") + separator() + "evidence" + separator();
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
}
