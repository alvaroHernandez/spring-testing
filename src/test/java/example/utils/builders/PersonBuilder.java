package example.utils.builders;

import com.github.javafaker.Faker;
import example.person.Person;

public class PersonBuilder {
    public static final int DOCUMENT_LENGTH = 10;

    Faker faker = new Faker();
    private String firstName = faker.name().firstName();
    private String document = faker.number().digits(DOCUMENT_LENGTH) ;

    private PersonBuilder(){}

    public static PersonBuilder newPerson(){
        return new PersonBuilder();
    }

    public PersonBuilder WithFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder WithDocument(String document) {
        this.document = document;
        return this;
    }

    public Person build() {
        return new Person(firstName,document);
    }
}