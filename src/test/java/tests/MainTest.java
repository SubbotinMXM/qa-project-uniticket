package tests;

import org.junit.Assert;
import org.junit.Test;
import tests.pages.HomePage;
import tests.pages.SearchResultPage;
import java.util.List;

public class MainTest extends BaseTest {

    /**
     * Название теста отражает суть
     */
    @Test
    public void testFlightDatesFilterShouldBeApplied(){

        //тестовые данные
        int expectedArrival = 31;
        int expectedDeparture = 21;

        HomePage homePage = new HomePage(driver);

        //основные действия на странице
        SearchResultPage searchResultPage = homePage
                .chooseDepartureCity("Москва")
                .chooseArrivalCity("Ханой")
                .chooseDateOfDeparture(expectedDeparture)
                .chooseDateOfComingBack(expectedArrival)
                .clickSearchButton();

        searchResultPage.waitForPage();

        //сбор актуальных данных
        int actualArrivalDate = searchResultPage.getArrivalDate();
        int actualDepartureDate = searchResultPage.getDepartureDate();

        //проверка что ожидаемые данные и актуальные совпадают
        Assert.assertEquals(expectedArrival, actualArrivalDate);
        Assert.assertEquals(expectedDeparture, actualDepartureDate);

        searchResultPage.waitAllResultsTicket();

        //сбор актуальных данных
        List<Integer> actualArrivalTicketsDate = searchResultPage.getAllArrivalDays();
        List<Integer> actualDepartureTicketsDate = searchResultPage.getAllDepartureDays();

        //проверка что ожидаемые данные и актуальные совпадают (все элементы в списке содержат ожидаемую дату отправки)
        boolean ticketsArrival = actualArrivalTicketsDate.stream().allMatch(x->x.equals(expectedArrival));
        boolean ticketsDeparture = actualDepartureTicketsDate.stream().allMatch(x->x.equals(expectedDeparture));

        Assert.assertTrue("Билеты отправки не содержат нужный фильтр",ticketsArrival);
        Assert.assertTrue("Билеты прибытия не содержат нужный фильтр", ticketsDeparture);
    }
}

