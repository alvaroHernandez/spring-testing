package example.controllers;

import example.person.Person;
import example.person.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/person/{document}")
    public String getPerson(@PathVariable final String document, Model model, HttpServletResponse response) {
        Optional<Person> foundPerson = personRepository.findByDocument(document);
        if(foundPerson.isPresent()){
            model.addAttribute("person", foundPerson);
            return "personDetails";
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "notFound";
        }
    }

}
