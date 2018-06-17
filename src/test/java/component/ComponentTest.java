package component;

import example.ExampleApplication;
import example.person.Person;
import example.person.PersonRepository;
import example.utils.builders.PersonBuilder;
import example.validator.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ExampleApplication.class})
@WebAppConfiguration
public class ComponentTest {
    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Validator validator;

    private MockMvc mvcService;

    @Before
    public void beforeAll() {
        mvcService = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @After
    public void tearDown() throws Exception {
        personRepository.deleteAll();
    }

    @Test
    public void shouldInsertNewPerson() throws Exception {
        Person personToBeSaved = PersonBuilder.newPerson().withValidDocument().build();
        mvcService.perform(
                post("/person/")
                        .flashAttr("person", new Person(personToBeSaved.getFirstName(), personToBeSaved.getDocument())));

        Person savedPerson = personRepository.findByDocument(personToBeSaved.getDocument()).get();
        assertThat(savedPerson.getFirstName(), is(personToBeSaved.getFirstName()));
        assertThat(savedPerson.getDocument(), is(personToBeSaved.getDocument()));
    }
}
