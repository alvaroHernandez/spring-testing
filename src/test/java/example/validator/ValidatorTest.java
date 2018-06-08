package example.validator;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ralmeida on 6/6/18.
 */
public class ValidatorTest {
    @Test
    public void ShouldReturnFalseIfTheFirstTwoNumbersAreGreaterThan24() throws Exception {
        String idNumber = "3016779630";
        Validator validator = new Validator();

        boolean isValid = validator.validate(idNumber);

        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseIfTheFirstTwoNumbersAreLessThanOne() throws Exception {
        String idNumber = "0016779630";
        Validator validator = new Validator();

        boolean isValid = validator.validate(idNumber);

        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseIfTheThirdNumberIsGreaterThanSix() throws Exception {
        String idNumber = "1791231230";
        Validator validator = new Validator();

        boolean isValid = validator.validate(idNumber);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfTheThirdNumberIsEqualToSix() throws Exception {
        String idNumber = "1761231230";
        Validator validator = new Validator();

        boolean isValid = validator.validate(idNumber);

        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseIfIdNumberHasLessThan10Digits() throws Exception {
        String idNumber = "171123";
        Validator validator = new Validator();

        boolean isValid = validator.validate(idNumber);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfIdNumberMoreThan10Digits() throws Exception {
        String idNumber = "171123111111111";
        Validator validator = new Validator();

        boolean isValid = validator.validate(idNumber);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnTrueWhenTheLastNumberIsValid() throws Exception {
        String idNumber = "0103612578";
        Validator validator = new Validator();

        boolean isValid = validator.validate(idNumber);

        assertTrue(isValid);

    }
}