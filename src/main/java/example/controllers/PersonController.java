package example.controllers;

import example.person.Person;
import example.person.PersonRepository;
import example.person.PersonRequest;
import example.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
public class PersonController {

    private final PersonRepository personRepository;

    private final Validator validator;

    @Autowired
    public PersonController(final PersonRepository personRepository,final Validator validator) {
        this.personRepository = personRepository;
        this.validator = validator;
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

    @PostMapping("/person/")
    public String savePerson(@RequestBody PersonRequest body, Model model, HttpServletResponse response)
    {
        System.out.println("xxxx");

        if(this.validator.validate("")){
            this.personRepository.save(new Person(body.getFirtName(), body.getDocument()));
            response.setStatus(HttpServletResponse.SC_CREATED);
            System.out.println("=================");
            return "notFound";
        }
        else
        {
            System.out.println("AAAAA");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "invalidDocument";
        }

    }

}
