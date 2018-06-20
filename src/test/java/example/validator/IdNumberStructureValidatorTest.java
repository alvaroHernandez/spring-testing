package example.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ralmeida on 6/6/18.
 */
public class IdNumberStructureValidatorTest {

    IdNumberStructureValidator idNumberStructureValidator;

    @Test
    public void ShouldReturnFalseIfTheFirstTwoNumbersAreGreaterThan24() throws Exception {
        idNumberStructureValidator = new IdNumberStructureValidator("3016779630");
        boolean isValid = idNumberStructureValidator.validate();
        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseIfTheFirstTwoNumbersAreLessThanOne() throws Exception {
        idNumberStructureValidator = new IdNumberStructureValidator("0016779630");
        boolean isValid = idNumberStructureValidator.validate();
        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseIfTheThirdNumberIsGreaterThanSix() throws Exception {
        idNumberStructureValidator = new IdNumberStructureValidator("1791231230");
        boolean isValid = idNumberStructureValidator.validate();
        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfTheThirdNumberIsEqualToSix() throws Exception {
        idNumberStructureValidator = new IdNumberStructureValidator("1761231230");
        boolean isValid = idNumberStructureValidator.validate();
        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfIdNumberHasLessThan10Digits() throws Exception {
        idNumberStructureValidator = new IdNumberStructureValidator("171123");
        boolean isValid = idNumberStructureValidator.validate();
        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfIdNumberMoreThan10Digits() throws Exception {
        idNumberStructureValidator = new IdNumberStructureValidator("171123111111111");
        boolean isValid = idNumberStructureValidator.validate();
        assertFalse(isValid);
    }

}
