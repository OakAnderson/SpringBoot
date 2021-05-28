package br.com.erudio.restwithspringbootudemy.service;

import br.com.erudio.restwithspringbootudemy.exception.UnsuportedMathOperationException;

public class MathValidation {

    public static void verifyNumber(String number) {
        if (isNotNumeric(number)) {
            throw new UnsuportedMathOperationException("Please set a numeric value.");
        }
    }

    protected static boolean isNotNumeric(String strNumber) {
        return !isNumeric(strNumber);
    }

    protected static boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;

        String number = strNumber.replaceAll(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
