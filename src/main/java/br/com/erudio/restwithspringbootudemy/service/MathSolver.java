package br.com.erudio.restwithspringbootudemy.service;

import br.com.erudio.restwithspringbootudemy.exception.UnsuportedMathOperationException;

public class MathSolver {

    private double numberOne;
    private double numberTwo;

    public MathSolver(String numberOne, String numberTwo) throws UnsuportedMathOperationException {
        MathValidation.verifyNumber(numberOne);
        MathValidation.verifyNumber(numberTwo);


        this.numberOne = MathConverter.convertToDouble(numberOne);
        this.numberTwo = MathConverter.convertToDouble(numberTwo);
    }

    public MathSolver(Double numberOne, double numberTwo) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
    }

    public double sum () {
        return numberOne + numberTwo;
    }

    public double subtraction () {
        return numberOne - numberTwo;
    }

    public double multiplication () {
        return numberOne * numberTwo;
    }

    public double division () {
        return numberOne / numberTwo;
    }

    public double mean () {
        return (numberOne + numberTwo) / 2;
    }

    public double getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(double numberOne) {
        this.numberOne = numberOne;
    }

    public double getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(double numberTwo) {
        this.numberTwo = numberTwo;
    }
}
