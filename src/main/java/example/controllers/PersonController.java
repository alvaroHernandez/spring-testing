package example.controllers;

import example.person.Person;
import example.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/person/{document}")
    public String getPerson(@PathVariable final String document, Model model, HttpServletResponse response) {
        Optional<Person> foundPerson = personRepository.findByDocument(document);
        if (foundPerson.isPresent()) {
            model.addAttribute("person", foundPerson.get());
            return "personDetails";
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("error", "Person with a Document = " + document + " was not found");
            return "notFound";
        }
    }

}
