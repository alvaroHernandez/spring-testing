package example.validator;

/**
 * Created by ralmeida on 11/6/18.
 */
public class LastNumberValidator {


    public boolean validate(String invalidDocument) {
        String lastDigitDocument = invalidDocument.substring(invalidDocument.length() - 1, invalidDocument.length());
        String firstNineDigits = invalidDocument.substring(0, invalidDocument.length() - 1);
        String[] documentDigits = firstNineDigits.split("");
        Integer sumDigits = 0;

        for (int i = 0; i <= firstNineDigits.length() - 1; i++) {
            if (i % 2 == 0) {
                sumDigits = sumDigits + GetMultiplicationResultEvenPosition(documentDigits[i]);
            } else {
                sumDigits = sumDigits + GetMultiplicationResultOddPosition(documentDigits[i]);
            }
        }
        Integer lastDigit = calculateLastDigit(sumDigits);
        if (lastDigit == Integer.parseInt(lastDigitDocument)) {
            return true;
        }
        return false;
    }

    private Integer GetMultiplicationResultEvenPosition(String digit) {
        Integer multiplicationResult = Integer.parseInt(digit) * 2;
        if (multiplicationResult >= 10) multiplicationResult = multiplicationResult - 9;
        return multiplicationResult;
    }

    private Integer GetMultiplicationResultOddPosition(String digit) {
        Integer multiplicationResult = Integer.parseInt(digit) * 1;
        if (multiplicationResult >= 10) multiplicationResult = multiplicationResult - 9;
        return multiplicationResult;
    }

    private int calculateLastDigit(int SumOfDigits) {
        if (SumOfDigits % 10 == 0) return 0;
        int ten = SumOfDigits - (SumOfDigits % 10) + 10;
        return ten - SumOfDigits;
    }
}
