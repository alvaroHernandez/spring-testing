package example.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by ralmeida on 21/6/18.
 */
public class ValidatorTest {

    private final Validator validator = new Validator();

    @Test
    public void ShouldReturnTrue() throws Exception {
        boolean isValid = validator.validate("1716779630");
        assertTrue(isValid);

    }

    @Test
    public void ShouldReturnFalseWhenIdProvidedIsNotRight() throws Exception {
        boolean isValid = validator.validate("1716779635");
        assertFalse(isValid);

    }

    @Test
    public void ShouldReturnFalseWhenIdStructureIsNotRight() throws Exception {
        boolean isValid = validator.validate("171677");
        assertFalse(isValid);

    }
}