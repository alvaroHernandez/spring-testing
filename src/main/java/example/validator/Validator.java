package example.validator;

/**
 * Created by ralmeida on 6/6/18.
 */
public class Validator {

    public static final int MAX_PROVINCE_INDEX = 24;
    public static final int MIN_PROVINCE_INDEX = 1;
    public static final int MAX_THIRD_NUMBER = 6;
    public static final int MAX_LENGTH = 10;

    public boolean validate(String idNumber) {
        return (hasTenNumbers(idNumber) && firstTwoNumbersAreBetweenZeroAnd24(idNumber) && thirdNumberIsLessThanSix(idNumber));
    }

    private boolean thirdNumberIsLessThanSix(String idNumber) {
        Integer thirdNumber = Integer.parseInt(idNumber.substring(2,3));
        return thirdNumber < MAX_THIRD_NUMBER;
    }

    private boolean firstTwoNumbersAreBetweenZeroAnd24(String idNumber) {
        Integer twofirstNumbers = Integer.parseInt(idNumber.substring(0,2));
        return twofirstNumbers <= MAX_PROVINCE_INDEX && twofirstNumbers >= MIN_PROVINCE_INDEX;
    }

    private boolean hasTenNumbers(String idNumber){
        return idNumber.length() == MAX_LENGTH;
    }
}
