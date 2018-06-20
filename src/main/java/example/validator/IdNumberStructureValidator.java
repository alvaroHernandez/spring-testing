package example.validator;

/**
 * Created by ralmeida on 6/6/18.
 */
public class IdNumberStructureValidator {

    public static final int MAX_PROVINCE_INDEX = 24;
    public static final int MIN_PROVINCE_INDEX = 1;
    public static final int MAX_THIRD_NUMBER = 6;
    public static final int MAX_LENGTH = 10;
    private String _idNumber;

    IdNumberStructureValidator(String idNumber)
    {
        _idNumber = idNumber;
    }

    public boolean validate() {
        return (hasTenNumbers() && firstTwoNumbersAreBetweenZeroAnd24() && thirdNumberIsLessThanSix());
    }

    private boolean hasTenNumbers() {
        return _idNumber.length() == MAX_LENGTH;
    }

    private boolean thirdNumberIsLessThanSix() {
        Integer thirdNumber = Integer.parseInt(_idNumber.substring(2, 3));
        return thirdNumber < MAX_THIRD_NUMBER;
    }

    private boolean firstTwoNumbersAreBetweenZeroAnd24() {
        Integer twoFirstNumbers = Integer.parseInt(_idNumber.substring(0, 2));
        return twoFirstNumbers <= MAX_PROVINCE_INDEX && twoFirstNumbers >= MIN_PROVINCE_INDEX;
    }

}
