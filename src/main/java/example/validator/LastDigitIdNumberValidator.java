package example.validator;

/**
 * Created by ralmeida on 11/6/18.
 */
public class LastDigitIdNumberValidator {

    public static final int FACTOR_EVEN_POSITIONS = 2;
    public static final int FACTOR_ODD_POSITIONS = 1;
    public static final int DIGIT_CONVERSION_VALUE = 9;
    private Integer sumDigits = 0;
    private String lastDigitCharacter;
    private String firstNineCharacters;

    LastDigitIdNumberValidator(String idNumber) {
        lastDigitCharacter = idNumber.substring(idNumber.length() - 1, idNumber.length());
        firstNineCharacters = idNumber.substring(0, idNumber.length() - 1);
    }

    public boolean validate() {
        calculateLastDigitTimeFactorAdditionValue(firstNineCharacters);
        if (getLastDigitVerifier() == Integer.parseInt(lastDigitCharacter)) return true;
        return false;
    }

    private void calculateLastDigitTimeFactorAdditionValue(String firstNineCharacters) {
        String[] firstNineCharactersSplit = firstNineCharacters.split("");

        for (int i = 0; i <= firstNineCharacters.length() - 1; i++) {

            sumDigits = sumDigits + getFactorTimesDigit(firstNineCharactersSplit[i], calculateFactor(i));
        }
    }

    private int calculateFactor(int position) {
        return position % 2 == 0 ? FACTOR_EVEN_POSITIONS : FACTOR_ODD_POSITIONS;
    }

    private int getFactorTimesDigit(String digit, int factor) {
        Integer digitTimesFactor = Integer.parseInt(digit) * factor;
        if (digitTimesFactor >= 10) digitTimesFactor = digitTimesFactor - DIGIT_CONVERSION_VALUE;
        return digitTimesFactor;
    }

    private int getLastDigitVerifier() {
        if (sumDigits % 10 == 0) return 0;
        int nextDozen = sumDigits - (sumDigits % 10) + 10;
        return nextDozen - sumDigits;
    }
}
