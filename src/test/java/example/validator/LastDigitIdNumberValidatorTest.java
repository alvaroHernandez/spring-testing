package example.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ralmeida on 11/6/18.
 */
public class LastDigitIdNumberValidatorTest {

    LastDigitIdNumberValidator lastDigitIdNumberValidator;

    @Test
    public void ShouldReturnFalseIfTheLastNumberIsWrong() {
        lastDigitIdNumberValidator = new LastDigitIdNumberValidator("1716779634");
        boolean result = lastDigitIdNumberValidator.validate();
        assertFalse(result);

    }

    @Test
    public void ShouldReturnTrueIfTheLastNumberIsOk() {
        lastDigitIdNumberValidator = new LastDigitIdNumberValidator("1710034065");
        boolean result = lastDigitIdNumberValidator.validate();
        assertTrue(result);

    }

    @Test
    public void ShouldReturnTrueIfTheLastNumberIsOkAndIsZero() {
        lastDigitIdNumberValidator = new LastDigitIdNumberValidator("1716779630");
        boolean result = lastDigitIdNumberValidator.validate();
        assertTrue(result);

    }


}