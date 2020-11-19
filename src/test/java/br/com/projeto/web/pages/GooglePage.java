package br.com.projeto.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GooglePage {

    public GooglePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q")
    private WebElement searchBar;

    @FindAll({ @FindBy(xpath = "//div[@class='rc']") })
    private List<WebElement> resultList;

    // Getters
    public WebElement getSearchBar() {
        return searchBar;
    }

    public List<WebElement> getResultList() {
        return resultList;
    }
}
