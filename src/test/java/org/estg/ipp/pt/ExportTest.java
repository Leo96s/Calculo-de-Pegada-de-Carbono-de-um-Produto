package org.estg.ipp.pt;

import org.estg.ipp.pt.Export.FilterOptions;
import org.estg.ipp.pt.Export.ResultExporter;
import org.estg.ipp.pt.Interfaces.Export.Export;
import org.estg.ipp.pt.Interfaces.Export.FilterOpt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExportTest {

    private Export csvExporter;

    @BeforeEach
    void setup(){
        csvExporter = new ResultExporter();
        csvExporter.clear();

    }


    /**
     * Teste que recebe 10.2 como um double e espera receber true ao
     * ser criado um ficheiro com o valor
     */
    @Test
    void testECP_01() {
        FilterOpt filter = new FilterOptions(2.0, 4.0, "Transport");

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 5.0);
        map.put("Transport", 3.4);
        map.put("Processing", 3.2);

        boolean result = csvExporter.exportData(map, 11.6, filter);
        assertTrue(result, "Expected the creation of a file PCF.csv");
    }


    @Test
    void testECP_02(){
        ResultExporter r = (ResultExporter)csvExporter;

        assertThrows(IllegalArgumentException.class, () -> {
            r.exportData(10.2);
        }, "Expected exportData to throw IllegalArgumentException for invalid number of inputs");
    }



    @Test
    void testECP_03(){

        ResultExporter r = (ResultExporter)csvExporter;

        FilterOpt filter = new FilterOptions(2.0, 4.0, "Processing");

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 5.0);
        map.put("Transport", 3.4);
        map.put("Processing", 3.2);

        assertThrows(IllegalArgumentException.class, () -> {
            r.exportData(map,"11.6", filter);
        }, "Expected exportData to throw IllegalArgumentException for invalid type of inputs");

    }


    @Test
    void testECP_04(){

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 5.0);
        map.put("Transport", 3.4);
        map.put("Processing", 3.2);

        assertThrows(IllegalArgumentException.class, () -> {
            csvExporter.exportData(map,11.6, null);
        }, "Expected exportData to throw IllegalArgumentException for invalid type of inputs");
    }

    @Test
    void testECP_05(){
        FilterOpt filter = new FilterOptions(2.0, 4.0, null);

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 5.0);
        map.put("Transport", 3.4);
        map.put("Processing", 3.2);

        boolean result = csvExporter.exportData(map, 11.6, filter);
        assertTrue(result, "Expected the creation of a file PCF.csv");
    }


    @Test
    void testECP_06(){
        ResultExporter r = (ResultExporter)csvExporter;

        FilterOpt filter = new FilterOptions(2.0, 4.0, "Processing");

        HashMap<Double, String> map = new HashMap<>();
        map.put(5.0, "Assembly");
        map.put(3.4, "Transport");
        map.put(3.2, "Processing");

        assertThrows(IllegalArgumentException.class, () -> {
            r.exportData(map,11.6, filter);
        }, "Expected exportData to throw IllegalArgumentException for invalid type of inputs");
    }

    @Test
    void testECP_07(){
        ResultExporter r = (ResultExporter)csvExporter;

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 5.0);
        map.put("Transport", 3.4);
        map.put("Processing", 3.2);

        assertThrows(IllegalArgumentException.class, () -> {
            r.exportData(map, 11.6, "test");
        }, "Expected exportData to throw IllegalArgumentException for invalid type of inputs");
    }

    @Test
    void testECP_08(){
        ResultExporter r = (ResultExporter)csvExporter;

        FilterOpt filter = new FilterOptions(2.0, 4.0, null);

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 5.0);
        map.put("Transport", 3.4);
        map.put("Processing", 3.2);

        assertThrows(IllegalArgumentException.class, () -> {
            r.exportData(11.6, map, filter);
        }, "Expected exportData to throw IllegalArgumentException for invalid type of inputs");
    }

    @Test
    void testBVA_01(){
        FilterOpt filter = new FilterOptions(0.0, 0.0, null);

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 0.0);

        boolean result = csvExporter.exportData(map, 0.0, filter);
        assertTrue(result, "Expected the creation of a file PCF.csv");
    }

    @Test
    void testBVA_02(){
        FilterOpt filter = new FilterOptions(Double.MAX_VALUE, Double.MAX_VALUE, "Cutting");

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", Double.MAX_VALUE);
        map.put("Transport", Double.MAX_VALUE);
        map.put("Processing", Double.MAX_VALUE);
        map.put("Cutting",Double.MAX_VALUE);

        boolean result = csvExporter.exportData(map, Double.MAX_VALUE, filter);
        assertTrue(result, "Expected the creation of a file PCF.csv");
    }

    @Test
    void testBVA_03(){
        FilterOpt filter = new FilterOptions(2.0, 4.0, null);

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 5.0);
        map.put("Transport", 3.4);
        map.put("Processing", 3.2);

        assertThrows(IllegalArgumentException.class, () -> {
            csvExporter.exportData(map, -1.0, filter);
        }, "Expected exportData to throw IllegalArgumentException for invalid type of inputs");
    }

    @Test
    void testBVA_04(){
        FilterOpt filter = new FilterOptions(2.0, 4.0, null);

        HashMap<String, Double> map = null;

        boolean result = csvExporter.exportData(map, 0.0, filter);
        assertTrue(result, "Expected the creation of a file PCF.csv");

    }

    @Test
    void testBVA_05(){
        FilterOpt filter = new FilterOptions(-2.0, 4.0, null);

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 5.0);
        map.put("Transport", 3.4);
        map.put("Processing", 3.2);

        assertThrows(IllegalArgumentException.class, () -> {
            csvExporter.exportData(map, 11.6, filter);
        }, "Expected exportData to throw IllegalArgumentException for invalid type of inputs");
    }

    @Test
    void testBVA_06(){
        FilterOpt filter = new FilterOptions(2.0, 4.0, null);

        HashMap<String, Double> map = new HashMap<>();
        map.put("Assembly", 5.0);
        map.put("Transport", 3.4);
        map.put("Processing", 3.2);

        assertThrows(IllegalArgumentException.class, () -> {
            csvExporter.exportData(map, null, filter);
        }, "Expected exportData to throw IllegalArgumentException for invalid type of inputs");
    }
}
