package br.com.erudio.restwithspringbootudemy.controller;

import br.com.erudio.restwithspringbootudemy.service.MathConverter;
import br.com.erudio.restwithspringbootudemy.service.MathSolver;
import br.com.erudio.restwithspringbootudemy.service.MathValidation;
import br.com.erudio.restwithspringbootudemy.exception.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public double sum(@PathVariable String numberOne, @PathVariable String numberTwo)
            throws UnsuportedMathOperationException {
        MathSolver solver = new MathSolver(numberOne, numberTwo);

        return solver.sum();
    }

    @GetMapping("/subtraction/{numberOne}/{numberTwo}")
    public double sub(@PathVariable String numberOne, @PathVariable String numberTwo) throws UnsuportedMathOperationException {
        MathSolver solver = new MathSolver(numberOne, numberTwo);

        return solver.subtraction();
    }

    @GetMapping("/multiplication/{numberOne}/{numberTwo}")
    public double times(@PathVariable String numberOne, @PathVariable String numberTwo) throws UnsuportedMathOperationException {
        MathSolver solver = new MathSolver(numberOne, numberTwo);

        return solver.multiplication();
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public double division(@PathVariable String numberOne, @PathVariable String numberTwo) throws UnsuportedMathOperationException {
        MathSolver solver = new MathSolver(numberOne, numberTwo);

        return solver.division();
    }

    @GetMapping("/mean/{numberOne}/{numberTwo}")
    public double mean(@PathVariable String numberOne, @PathVariable String numberTwo) throws UnsuportedMathOperationException {
        MathSolver solver = new MathSolver(numberOne, numberTwo);

        return solver.mean();
    }

    @GetMapping("/sqrt/{number}")
    public double sqrt(@PathVariable String number) throws UnsuportedMathOperationException {
        MathValidation.verifyNumber(number);

        return Math.sqrt(MathConverter.convertToDouble(number));
    }

}
