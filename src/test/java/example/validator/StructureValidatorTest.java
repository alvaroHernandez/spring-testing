package example.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ralmeida on 6/6/18.
 */
public class StructureValidatorTest {

    StructureValidator _structureValidator;
    private String idNumber;

    @Before
    public void setUp() {
        _structureValidator = new StructureValidator();

    }

    @Test
    public void ShouldReturnFalseIfTheFirstTwoNumbersAreGreaterThan24() throws Exception {
        idNumber = "3016779630";
        boolean isValid = _structureValidator.validate(idNumber);
        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseIfTheFirstTwoNumbersAreLessThanOne() throws Exception {
        idNumber = "0016779630";
        boolean isValid = _structureValidator.validate(idNumber);
        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseIfTheThirdNumberIsGreaterThanSix() throws Exception {
        idNumber = "1791231230";
        boolean isValid = _structureValidator.validate(idNumber);
        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfTheThirdNumberIsEqualToSix() throws Exception {
        idNumber = "1761231230";
        boolean isValid = _structureValidator.validate(idNumber);
        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfIdNumberHasLessThan10Digits() throws Exception {
        idNumber = "171123";
        boolean isValid = _structureValidator.validate(idNumber);
        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfIdNumberMoreThan10Digits() throws Exception {
        idNumber = "171123111111111";
        boolean isValid = _structureValidator.validate(idNumber);
        assertFalse(isValid);
    }

}
