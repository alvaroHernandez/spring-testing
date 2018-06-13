package example.component;

import com.github.javafaker.Faker;
import example.person.Person;
import example.person.PersonRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
//TODO : change to defined port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComponentTest {
    Faker faker = new Faker();

    @LocalServerPort
    private int port;
    private String serviceUrl = String.format("http://localhost:%s/hello", port);

    @Autowired
    private PersonRepository personRepository;


    @After
    public void tearDown() throws Exception {
        personRepository.deleteAll();
    }

    @Test
    public void shouldInsertNewPerson() throws Exception {
        Person personToBeInserted = new Person(faker.name().fullName(),faker.idNumber().ssnValid());
        //TODO: fix post body
        given()
                .body("formDataPlaceholder")
                .when()
                .post(serviceUrl, new HashMap<String, String>())
                .then()
                .statusCode(is(201));
        Optional person = personRepository.findByDocument(personToBeInserted.getFirstName());
        assertThat(person, hasProperty("firstName", equalTo(personToBeInserted.getFirstName())));
    }

    @Test
    public void shouldReturnPersonWithGivenRuc() throws Exception {
        Person storedPerson = new Person(faker.name().fullName(),faker.idNumber().ssnValid());
        personRepository.save(storedPerson);

        when()
                .get(serviceUrl)
                .then()
                .statusCode(is(200))
                .body(containsString(storedPerson.getFirstName()))
                .and()
                .body(containsString(storedPerson.getDocument()));
    }
}
