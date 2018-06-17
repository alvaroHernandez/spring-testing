package example.controllers;

import com.github.javafaker.Faker;
import example.person.Person;
import example.person.PersonRepository;
import example.utils.builders.PersonBuilder;
import example.validator.Validator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static example.utils.builders.PersonBuilder.DOCUMENT_LENGTH;
import static example.utils.builders.PersonBuilder.newPerson;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonControllerTest {
    Faker faker = new Faker();

    @Mock
    private Validator validator;

    @MockBean
    private PersonRepository personRepository;

    @InjectMocks
    PersonController personController;
    private MockMvc serviceMock;

    @Before
    public void beforeAll(){
        MockitoAnnotations.initMocks(this);
        serviceMock = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void shouldReturn200WhenPersonIsFound() throws Exception {
        String personId = faker.number().digits(DOCUMENT_LENGTH);
        given(personRepository.findByDocument(personId)).willReturn(Optional.of(PersonBuilder.newPerson().build()));
        serviceMock
                .perform(get("/person/" + personId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404WhenPersonIsNotFound() throws Exception {
        String personId = faker.number().digits(DOCUMENT_LENGTH);
        given(personRepository.findByDocument(personId)).willReturn(Optional.empty());
        serviceMock
                .perform(get("/person/" + personId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnPersonNameAndDocumentWhenPersonIsFound() throws Exception {
        Person person = PersonBuilder.newPerson().build();
        given(personRepository.findByDocument(person.getDocument())).willReturn(Optional.of(person));
        serviceMock
                .perform(get("/person/" + person.getDocument()))
                .andExpect(model().attribute("person", is(person)));
    }

    @Test
    public void shouldReturnErrorMessageWithNotFoundDocument() throws Exception {
        String searchedDocument = faker.number().digit();
        given(personRepository.findByDocument(any())).willReturn(Optional.empty());
        serviceMock
                .perform(get("/person/" + searchedDocument))
                .andExpect(model().attribute("error", is("Person with a Document = " + searchedDocument + " was not found")));
    }

    @Test
    public void shouldInsertPersonWhenDocumentIsValid() throws Exception {
        Person person = newPerson().build();
        when(validator.validate(person.getDocument())).thenReturn(true);
        when(personRepository.save(person)).thenReturn(person);
        serviceMock
                .perform(
                        post("/person")
                        .flashAttr("person", new Person(person.getFirstName(),person.getDocument())))
                .andExpect(model().attribute("person", is(person)));
    }

    @Test
    public void shouldReturn201WhenPersonIsInserted() throws Exception {
        when(validator.validate(any())).thenReturn(true);
        serviceMock
                .perform(post("/person"))
                .andExpect(status().isCreated());
    }

    //TODO: mock and verify person repository not being called
    @Test
    public void shouldNotInsertPersonWhenDocumentIsInvalid() throws Exception {
        Person person = newPerson().build();
        when(validator.validate(any())).thenReturn(false);

        serviceMock
                .perform(post("/person").content(""));
    }

    @Test
    public void shouldReturnBadRequestWhenInsertingPersonWithInvalidDocument() throws Exception {
        given(validator.validate(any())).willReturn(false);
        serviceMock
                .perform(post("/person").content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnErrorMessageWhenInsertingPersonWithInvalidDocument() throws Exception {
        Person person = newPerson().build();
        given(validator.validate(person.getDocument())).willReturn(false);
        serviceMock
                .perform(post("/person"))
                .andExpect(model().attribute("error", is("Invalid document number, person was not saved")));
    }

}