package example.validator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by ralmeida on 15/6/18.
 */
@Component
public class Validator {

    public boolean validate(String docNumber) {
        StructureValidator structureValidator = new StructureValidator();
        LastNumberValidator lastNumberValidator = new LastNumberValidator();

        return (structureValidator.validate(docNumber) && lastNumberValidator.validate(docNumber));
    }
}
