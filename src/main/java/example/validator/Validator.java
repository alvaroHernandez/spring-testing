package example.validator;

import org.springframework.stereotype.Component;

/**
 * Created by ralmeida on 15/6/18.
 */
@Component
public class Validator {

    public boolean validate(String idNumber) {
        IdNumberStructureValidator IdNumberStructureValidator = new IdNumberStructureValidator(idNumber);
        LastDigitIdNumberValidator lastDigitIdNumberValidator = new LastDigitIdNumberValidator(idNumber);

        return (IdNumberStructureValidator.validate() && lastDigitIdNumberValidator.validate());
    }
}
