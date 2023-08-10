package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BasePage {
    protected final WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    /**
     * Ждет пока элемент будет содержать текст, котоый подходит под регулярное условие
     * @param regex регулярное выражение
     * @param by локатор элемента
     */
    public void waitFotTextMatches(String regex, By by){
        Pattern pattern = Pattern.compile(regex);
        wait.until(ExpectedConditions.textMatches(by, pattern));
    }

    /**
     * Ждет появления элементов на странице, в списке ищет элемент который содержит нужный текст
     * @param text текст для поиска
     * @param by локатор для коллекции элементов
     * @return элемент с нужным текстом
     */
    public WebElement waitForTextPresentedInList(String text, By by){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElements(by).stream() //перебираем коллекцию элементов
                .filter(x->x.getText().contains(text)) //ищем элемент который содержит искомый текст
                .findFirst() //берем первый элемент найденный, если не найдено то кидаем ошибку
                .orElseThrow(()-> new NoSuchElementException("Город не найден: " + text));
    }

    /**
     * Ждет пока элемент появится на странице
     * @param by локатор элемента
     */
    public void waitForElementVisible(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Ждет пока элемент исчезнет со страницы
     * @param by локатор элемента
     */
    public void waitForElementDisappear(By by){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Метод который найдет элемент на странице, получит текст и оставит только цифры
     * @param by локатор элемента
     * @return числовое значение из текста
     */
    public Integer getDigitsFromTextElement(By by){
        String textDigits = driver.findElement(by).getText();
        return getDigitFromText(textDigits);
    }

    /**
     * Метод который найдет элемент на странице, получит текст и оставит только цифры
     * @param element веб элемент
     * @return числовое значение из текста
     */
    public Integer getDigitsFromTextElement(WebElement element){
        String textDigits = element.getText();
        return getDigitFromText(textDigits);
    }

    /**
     * Удаляет числа из строки
     * @param text строка для удаления всего, что не является цифрой
     * @return
     */

    private Integer getDigitFromText(String text){
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    /**
     * Получает список из числовых значений из видимых вебэлементов
     * @param by локатор к коллекции элементов
     * @return
     */
    public List<Integer> getDigitList(By by){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElements(by).stream() //перебираем все элементы
                .filter(WebElement::isDisplayed) //берем только видимые элементы, еще можно записать так x->x.isDisplayed()
                .map(this::getDigitsFromTextElement) //из каждого элемента достаем текст и оставляем только цифры
                .collect(Collectors.toList()); //собираем все в новый список
    }
}
