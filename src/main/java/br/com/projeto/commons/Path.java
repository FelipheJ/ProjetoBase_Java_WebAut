package br.com.projeto.commons;

import br.com.projeto.exceptions.PathNotImplementedException;

public class Path {

    private static final String IE_PATH = "iedriver.exe";
    private static final String EDGE_PATH = "edgedriver.exe";
    private static final String SAFARI_PATH = "safaridriver";
    private static final String OPERA_PATH = "operadriver.exe";
    private static final String FIREFOX_PATH = "geckodriver.exe";
    private static final String CHROME_PATH = "chromedriver.exe";

    public static String get(String path) {
        switch (path.toUpperCase()) {
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
                throw new PathNotImplementedException("This path hasn't yet been implemented.");
        }
    }
}
