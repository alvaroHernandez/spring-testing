package example.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ralmeida on 11/6/18.
 */
public class LastNumberStructureValidatorTest {

    String _documentNumber;
    LastNumberValidator _lastNumberValidator;

    @Before
    public void setUp() {
        _documentNumber = "1713825394";
        _lastNumberValidator = new LastNumberValidator();

    }

    @Test
    public void ShouldReturnFalseIfTheLastNumberIsWrong() {
        _documentNumber = "1716779634";
        boolean result = _lastNumberValidator.validate(_documentNumber);
        assertFalse(result);

    }

    @Test
    public void ShouldReturnTrueIfTheLastNumberIsOk() {
        boolean result = _lastNumberValidator.validate(_documentNumber);
        assertTrue(result);

    }

    @Test
    public void ShouldReturnTrueIfTheLastNumberIsOkAndIsZero() {
        _documentNumber = "1716779630";
        boolean result = _lastNumberValidator.validate(_documentNumber);
        assertTrue(result);

    }


}