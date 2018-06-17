package example.controllers;

import example.person.Algo;
import example.person.Person;
import example.person.PersonRepository;
import example.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;


@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Validator validator;

    @GetMapping("/person")
    public ModelAndView getPersonForm() {
        return new ModelAndView("addPerson","person",new Person("",""));
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

    @PostMapping("/person")
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
            return "addPerson";
        }

    }

}
