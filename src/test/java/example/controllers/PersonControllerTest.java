package example.controllers;

import com.github.javafaker.Faker;
import example.person.Person;
import example.person.PersonRepository;
import example.utils.builders.PersonBuilder;
import example.validator.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static example.utils.builders.PersonBuilder.DOCUMENT_LENGTH;
import static example.utils.builders.PersonBuilder.newPerson;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {
    Faker faker = new Faker();

    @Autowired
    private MockMvc serviceMock;
    private Validator validator;

    @MockBean
    private PersonRepository personRepository;

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
    public void shouldReturn201WhenPersonIsInserted() throws Exception {
        Person person = newPerson().build();
        given(validator.validate(any())).willReturn(true);
        given(personRepository.save(person)).willReturn(person);
        serviceMock
                .perform(post("/person/").content(""))
                .andExpect(status().isCreated());
    }

    //TODO: mock and verify person repository not being called
    @Test
    public void shouldNotInsertPersonWhenDocumentIsInvalid() throws Exception {
        Person person = newPerson().build();
        given(validator.validate(any())).willReturn(false);
        serviceMock
                .perform(post("/person/").content(""));
    }

    @Test
    public void shouldReturnBadRequestWhenInsertingPersonWithInvalidDocument() throws Exception {
        given(validator.validate(any())).willReturn(false);
        serviceMock
                .perform(post("/person/").content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnErrorMessageWhenInsertingPersonWithInvalidDocument() throws Exception {
        Person person = newPerson().build();
        given(validator.validate(person.getDocument())).willReturn(false);
        serviceMock
                .perform(post("/person/").content(""))
                .andExpect(model().attribute("error", is("Invalid document number, person was not saved")));
    }
}