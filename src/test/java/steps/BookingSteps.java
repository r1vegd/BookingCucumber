package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class BookingSteps {
    String hotelName;
    public static final By HOTEL_SEARCH_INPUT = By.xpath("//input[@id='ss']");
    public static final By SEARCH_BUTTON = By.xpath("//button[@type='submit']");
    public static final By FIRST_HOTEL_TITLE_SEARCH_RESULT = By.xpath("(//div[@data-testid='title'])[1]");
    public static final By FIRST_HOTEL_RATING_SEARCH_RESULT = By.xpath("(//div[@data-testid='review-score'])[1]/div[@aria-label]");

    WebDriver driver;
    public static final String BASE_URL = "https://www.booking.com/searchresults.en-gb.html";


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After()
    public void tearDown() {
        driver.quit();
    }

    @Given("hotel name {string}")
    public void hotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @When("user does search")
    public void userDoesSearch() {
        driver.get(BASE_URL);
        driver.findElement(HOTEL_SEARCH_INPUT).click();
        driver.findElement(HOTEL_SEARCH_INPUT).sendKeys(hotelName);
        driver.findElement(SEARCH_BUTTON).click();

    }

    @Then("{string} is the first search result on the page")
    public void hotelTitle(String hotelTitle) {
        Assert.assertEquals(driver.findElement(FIRST_HOTEL_TITLE_SEARCH_RESULT).getText(), hotelTitle, "Searched hotel name does not match");
    }

    @And("rating should be {string}")
    public void ratingShouldBe(String rating) {
        Assert.assertEquals(driver.findElement(FIRST_HOTEL_RATING_SEARCH_RESULT).getText(), rating, "Searched hotel rating does not match");
    }

}
