package example.controllers;

import example.person.PersonRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

public class PersonControllerTest {
    @Autowired
    private MockMvc serviceMock;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void shouldReturn200WhenPersonIsFound() {
    }

    @Test
    public void shouldReturn404WhenPersonIsNotFound(){

    }

    @Test
    public void shouldReturnPersonNameAndRucWhenPersonIsFound() {
    }

    @Test
    public void shouldReturnMessageWithNotFoundRuc() {
    }

    @Test
    public void shouldReturn203WhenRucIsInvalid(){

    }

    @Test
    public void shouldNotInsertPersonWhenRucIsInvalid() {

    }
}