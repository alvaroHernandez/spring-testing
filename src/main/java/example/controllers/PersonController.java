package example.controllers;

import example.person.Person;
import example.person.PersonRepository;
import example.person.PersonRequest;
import example.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Validator validator;


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

    @PostMapping("/person/")
    public String savePerson(@ModelAttribute("person")Person person, Model model, HttpServletResponse response)
    {
        if(this.validator.validate(person.getDocument())){
            Person savedPerson = this.personRepository.save(new Person(person.getFirstName(), person.getDocument()));
            response.setStatus(HttpServletResponse.SC_CREATED);
            model.addAttribute("person", savedPerson);
            return "personDetails";
        }
        else
        {
            model.addAttribute("error", "Invalid document number, person was not saved");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "newPerson";
        }

    }

}
