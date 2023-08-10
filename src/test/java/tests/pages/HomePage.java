package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By departureField = By.xpath("//input[@placeholder='Откуда']");
    private By arrivalField = By.xpath("//input[@placeholder='Куда']");
    private By dateOfDepartureField = By.xpath("//input[@placeholder='Туда']");
    private String exactDateOfDepartureButton = "//span[text()='%d']";
    private By dateOfComingBackField = By.xpath("//input[@placeholder='Обратно']");
    private String exactDateOfComingBackButton = "//div[@class='return_date field active']//span[text()='%d']";
    private By searchButton = By.xpath("//div[@class='search_btn']");
    private By listOfDestinationCity = By.xpath("//div[@class='destination field active']//div[@class='city']");
    private By listOfDepartureCity = By.xpath("//div[@class='origin field active']//div[@class='city']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage chooseDepartureCity(String city) {
        driver.findElement(departureField).clear();
        driver.findElement(departureField).sendKeys(city);
        driver.findElement(departureField).click();
        waitForTextPresentedInList(city, listOfDepartureCity).click();
        return this;
    }

    public HomePage chooseArrivalCity(String city) {
        driver.findElement(arrivalField).click();
        driver.findElement(arrivalField).sendKeys(city);
        driver.findElement(arrivalField).click();
        waitForTextPresentedInList(city, listOfDestinationCity).click();
        return this;
    }

    public HomePage chooseDateOfDeparture(int day) {
        driver.findElement(dateOfDepartureField).click();
        By date = By.xpath(String.format(exactDateOfDepartureButton, day));
        driver.findElement(date).click();
        return this;
    }

    public HomePage chooseDateOfComingBack(int day) {
        driver.findElement(dateOfComingBackField).click();
        By date = By.xpath(String.format(exactDateOfComingBackButton, day));
        driver.findElement(date).click();
        return this;
    }

    public SearchResultPage clickSearchButton() {
        driver.findElement(searchButton).click();
        return new SearchResultPage(driver);
    }
}
