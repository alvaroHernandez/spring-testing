package example.validator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ralmeida on 11/6/18.
 */
public class LastNumberValidatorTest {

    @Test
    public void ShouldReturnFalseIfTheLastNumberisWrong() {
        String invalidDocument = "1716779631";
        LastNumberValidator lastNumberValidator = new LastNumberValidator();
        boolean result = lastNumberValidator.validate(invalidDocument);
        assertFalse(result);

    }
}