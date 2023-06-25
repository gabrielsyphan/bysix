package com.syphan.visualnuts;

import java.util.logging.Logger;

public class VisualNutsImpl implements VisualNuts {

    private final Logger logger = Logger.getLogger(VisualNutsImpl.class.getName());

    @Override
    public void checkNumbers(int number) throws IllegalArgumentException {
        logger.info("Checking number: " + number);

        checkIfValid(number);

        for(int i = 1; i <= number; i++) {
            if(checkIfDivisibleByThreeAndFive(i)) {
                System.out.println("Visual Nuts");
            } else if(checkIfDivisibleByFive(i)) {
                System.out.println("Nuts");
            } else if(checkIfDivisibleByThree(i)) {
                System.out.println("Visual");
            } else {
                System.out.println(i);
            }
        }
    }

    private void checkIfValid(int number) throws IllegalArgumentException {
        if(checkIfIsNegative(number)) {
            throw new IllegalArgumentException("Number cannot be negative");
        } else if(checkIfIsZero(number)) {
            throw new IllegalArgumentException("Number cannot be zero");
        }
    }

    private boolean checkIfDivisibleByThree(int number) {
        return number % 3 == 0;
    }

    private boolean checkIfDivisibleByFive(int number) {
        return number % 5 == 0;
    }

    private boolean checkIfDivisibleByThreeAndFive(int number) {
        return number % 3 == 0 && number % 5 == 0;
    }

    private boolean checkIfIsNegative(int number) {
        return number < 0;
    }

    private boolean checkIfIsZero(int number) {
        return number == 0;
    }
}
