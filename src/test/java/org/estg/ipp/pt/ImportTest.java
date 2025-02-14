package org.estg.ipp.pt;

import org.estg.ipp.pt.Import.Import;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class ImportTest {
    private final Import csvImporter = new Import();

    @Test
    void testECP_01() {
        boolean result = csvImporter.ImportCSV("./Import/primaryData.csv");
        assertTrue(result, "Expected non-empty list for a valid primaryData.csv");
    }

    @Test
    void testECP_02() {
        boolean result = csvImporter.ImportCSV("C:/Users/example/Desktop/secondaryData.csv");
        assertFalse(result, "Expected empty list for an invalid secondaryData.csv");
    }

    @Test
    void testECP_03() {
        boolean result = csvImporter.ImportCSV("./Import/primaryData.csv");
        assertTrue(result, "Expected valid lines from primaryData.csv containing primary data");
    }

    @Test
    void testECP_04() {
        boolean result = csvImporter.ImportCSV("./Import/secondaryData.csv");
        assertTrue(result, "Expected valid lines from secondaryData.csv containing secondary data");
    }

    @Test
    void testECP_05() {
        boolean result = csvImporter.ImportCSV("./Import/invalidData.csv");
        assertFalse(result, "Expected empty list for invalidData.csv");
    }

    @Test
    void testECP_06() {
        boolean result = csvImporter.ImportCSV("3");
        assertFalse(result, "Expected empty list when path is numeric");
    }

    @Test
    void testECP_07() {
        boolean result = csvImporter.ImportCSV("./Import/secondaryData.cvs");
        assertFalse(result, "Expected empty list for misspelled file extension .cvs");
    }

    @Test
    void testECP_08() {
        boolean result = csvImporter.ImportCSV("./Import/nonexistent.csv");
        assertFalse(result, "Expected empty list when file does not exist");
    }

    @Test
    void testECP_09() {
        boolean result = csvImporter.ImportCSV("/protected/primaryData.csv");
        assertFalse(result, "Expected empty list when file cannot be accessed");
    }

    @Test
    void testECP_10() {
        boolean result = csvImporter.ImportCSV("string");
        assertFalse(result, "Expected empty list for an invalid file name");
    }

    @Test
    void testECP_11() {
        String longPath = "a".repeat(256) + ".csv";
        boolean result = csvImporter.ImportCSV(longPath);
        assertFalse(result, "Expected empty list for a path exceeding 255 characters");
    }

    @Test
    void testBVA_01() {
        boolean result = csvImporter.ImportCSV("./Import/primaryData.csv");
        assertTrue(result, "Expected valid lines for a correctly formatted primaryData.csv");
    }

    @Test
    void testBVA_02() {
        boolean result = csvImporter.ImportCSV("./Import/secondaryData.csv");
        assertTrue(result, "Expected valid lines for a path ending with 'secondaryData'");
    }

    @Test
    void testBVA_03() {
        boolean result = csvImporter.ImportCSV("");
        assertFalse(result, "Expected empty list for an empty string as the path");
    }
}