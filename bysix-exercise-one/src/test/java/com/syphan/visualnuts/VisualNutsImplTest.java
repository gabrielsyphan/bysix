package com.syphan.visualnuts;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VisualNutsImplTest {

    private final VisualNutsImpl visualNuts = new VisualNutsImpl();
    private PrintStream originalOut;
    private ByteArrayOutputStream outputStream;

    @BeforeAll
    public void setUp() {
        originalOut = System.out;

        // Create a stream to hold the output
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterAll
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testHighNumberSuccess() {
        // Call the method
        assertDoesNotThrow(() -> visualNuts.checkNumbers(500));

        // Get the output frin the ByteArrayOutputStream
        String output = outputStream.toString();

        // Count the occurrences of each word
        int countVisualNuts = countOcurrence(output, "\nVisual Nuts\n");
        assertEquals(33, countVisualNuts);

        int countNuts = countOcurrence(output, "\nNuts\n");
        assertEquals(67, countNuts);

        int countVisual = countOcurrence(output, "\nVisual\n");
        assertEquals(133, countVisual);
    }

    @Test
    public void testNegativeNumberFailure() {
        assertThrows(IllegalArgumentException.class, () -> visualNuts.checkNumbers(-300));
    }

    @Test
    public void testZeroNumberFailure() {
        assertThrows(IllegalArgumentException.class, () -> visualNuts.checkNumbers(0));
    }

    private int countOcurrence(String texto, String palavra) {
        int count = 0;
        Pattern pattern = Pattern.compile(palavra);
        Matcher matcher = pattern.matcher(texto);
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}