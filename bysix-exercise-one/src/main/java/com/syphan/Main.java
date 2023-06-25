package com.syphan;

import com.syphan.visualnuts.VisualNuts;
import com.syphan.visualnuts.VisualNutsImpl;

public class Main {

    public static void main(String[] args) {
        VisualNuts visualNuts = new VisualNutsImpl();

        // Check numbers from 1 to 100
        visualNuts.checkNumbers(100);
    }
}