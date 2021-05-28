package br.com.erudio.restwithspringbootudemy.service;

public class MathConverter {

    public static double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;

        String number = strNumber.replaceAll(",", ".");
        if( MathValidation.isNumeric(number) ) return Double.parseDouble(number);

        return 0D;
    }

}
