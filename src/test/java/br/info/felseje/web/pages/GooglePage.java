package br.info.felseje.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {

    public GooglePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /* Campos */
    @FindBy(xpath = "//input[@name='q']")
    private WebElement barraDeBusca;

    /* Resultados: Link */
    @FindBy(xpath = "//cite[contains(text(),'github.com')]")
    private WebElement gitHubLink;

    /* Getters */
    public WebElement getBarraDeBusca() {
        return barraDeBusca;
    }

    public WebElement getGitHubLink() {
        return gitHubLink;
    }
}
