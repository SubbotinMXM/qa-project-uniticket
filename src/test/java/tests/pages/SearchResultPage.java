package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class SearchResultPage extends BasePage {
    private By dateOfDeparture = By.xpath("//li[@class='price--current']//span[@class='prices__date'][1]");
    private By dateOfArrival = By.xpath("//li[@class='price--current']//span[@class='prices__date'][2]");
    private By priceSelected = By.xpath("//li[@class='price--current']//span[@class='prices__price currency_font currency_font--rub']");
    private By loadText = By.xpath("//span[@class='countdown-title__text']");
    private By listOfDepartureDates = By.xpath("//div[@class='ticket-details']//div[@class='ticket-action-airline-container']//following::span[@class='flight-brief-date__day'][1]");
    private By listOfArrivalDates = By.xpath("//div[@class='ticket-action-airline-container']//following::div[@class='flight-brief-departure'][2]//span[@class='flight-brief-date__day']");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Получает список с датами прилета из всех найденных билетов
     * @return
     */
    public List<Integer> getAllArrivalDays(){
        return getDigitList(listOfArrivalDates);
    }

    /**
     * Получает список с датами вылета из всех найденных билетов
     * @return
     */
    public List<Integer> getAllDepartureDays(){
        return getDigitList(listOfDepartureDates);
    }

    public Integer getDepartureDate() {
        return getDigitsFromTextElement(dateOfDeparture);
    }

    public Integer getArrivalDate() {
        return getDigitsFromTextElement(dateOfArrival);
    }

    /**
     * Ждет появления нужных дат и цены в верхнем блоке с текущей и соседними датами
     */
    public void waitForPage() {
        waitForElementVisible(dateOfDeparture);
        waitFotTextMatches("\\d+", priceSelected);
    }

    /**
     * Ждет когда поиск билетов будет прекращен
     */
    public void waitAllResultsTicket(){
        waitForElementDisappear(loadText);
    }
}
