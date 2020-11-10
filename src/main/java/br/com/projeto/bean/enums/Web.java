package br.com.projeto.bean.enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import br.com.projeto.bean.interfaces.WebApplication;

public enum Web implements WebApplication {

    CHROME {
        @Override
        public WebDriver getDriver() {
            System.setProperty("webdriver.chrome.driver","chromedriver.exe");
            return new ChromeDriver();
        }
    }
}
