package example.e2e;

import example.person.Person;
import example.utils.builders.PersonBuilder;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdminJourneyTest {

    private WebDriver driver;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void adminJourney(){
        Person newPerson = PersonBuilder.newPerson().withValidDocument().build();
        driver.navigate().to("http://localhost:8080/person");

        WebElement addPersonFormPageBody = driver.findElement(By.tagName("body"));
        assertThat(addPersonFormPageBody.getText(), containsString("Insert Person"));

        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys(newPerson.getFirstName());
        WebElement documentNumberInput = driver.findElement(By.id("document"));
        documentNumberInput.sendKeys(newPerson.getDocument());

        driver.findElement(By.id("submitButton")).click();
        WebElement personDetailsPageBody = driver.findElement(By.tagName("body"));
        assertThat(personDetailsPageBody.getText(), containsString(newPerson.getFirstName()));
        assertThat(personDetailsPageBody.getText(), containsString(newPerson.getDocument()));
    }
}

