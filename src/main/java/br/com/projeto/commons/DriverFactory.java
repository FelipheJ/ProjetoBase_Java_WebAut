package br.com.projeto.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class DriverFactory {

    public static WebDriver getDriver(String browserName) {
        WebDriver webDriver;
        switch (browserName.toUpperCase()) {
            case "CHROME":
                webDriver = new ChromeDriver();
                break;
            case "IE":
                webDriver = new InternetExplorerDriver();
                break;
            case "FIREFOX":
                webDriver = new FirefoxDriver();
                break;
            case "SAFARI":
                webDriver = new SafariDriver();
                break;
            case "OPERA":
                webDriver = new OperaDriver();
                break;
            case "EDGE":
                webDriver = new EdgeDriver();
                break;
            default:
                throw new WebDriverException("This browser isn't implemented.");
        }
        return webDriver;
    }

    public static WebDriver getDriver(String browserName, MutableCapabilities capabilities) {
        WebDriver webDriver;
        switch (browserName.toUpperCase()) {
            case "CHROME":
                webDriver = new ChromeDriver((ChromeOptions) capabilities);
                break;
            case "IE":
                webDriver = new InternetExplorerDriver((InternetExplorerOptions) capabilities);
                break;
            case "FIREFOX":
                webDriver = new FirefoxDriver((FirefoxOptions) capabilities);
                break;
            case "SAFARI":
                webDriver = new SafariDriver((SafariOptions) capabilities);
                break;
            case "OPERA":
                webDriver = new OperaDriver((OperaOptions) capabilities);
                break;
            case "EDGE":
                webDriver = new EdgeDriver((EdgeOptions) capabilities);
                break;
            default:
                throw new WebDriverException("This browser isn't implemented.");
        }
        return webDriver;
    }
}
