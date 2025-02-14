package org.estg.ipp.pt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Enums.Type;
import org.estg.ipp.pt.Enums.UnitType;
import org.estg.ipp.pt.Models.Flow;
import org.estg.ipp.pt.Models.Process;
import org.estg.ipp.pt.Models.ProcessManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelingTest {

    /**
     * Test factory para testar casos de teste ECP e BVA da funcionalidade modelling para processos Secundários
     *
     * @return stream de testes executados
     */
    @TestFactory
    Stream<DynamicTest> dynamicTestsForSecondaryDataModelingBVAAndECP() {
        int MAX_SIZE = 50;
        List<String> maxSizeList = IntStream.range(0, MAX_SIZE).mapToObj(i -> "Cutting,INPUT,Material,10,KG,0.1").toList();
        List<String> moreThanMaxSizeList = IntStream.range(0, MAX_SIZE + 1).mapToObj(i -> "Cutting,INPUT,Material,10,KG,0.1").toList();

        List<List<String>> data = List.of(
                List.of("Cutting,INPUT,Material,10,KG,0.1"),
                maxSizeList,
                moreThanMaxSizeList,
                List.of(),
                List.of("Cutting,INPUT,Material,10,KG,0.1", "Packaging,OUTPUT,Waste,10,KG,0.1"),
                List.of("example", "example"),
                List.of("Cutting,INPUT,Material,10,KG,0.1", "Packaging,OUTPUT,Waste,10,KG,0.1"),
                List.of("EXAMPLE,INPUT,Not Material,10,KG,0.1")
        );

        List<String> testNames = List.of("BVA_SD_MODELING_01", "BVA_SD_MODELING_02", "BVA_SD_MODELING_03", "BVA_SD_MODELING_04",
                "ECP_SD_MODELING_01", "ECP_SD_MODELING_02", "ECP_SD_MODELING_03", "ECP_SD_MODELING_04");

        List<Boolean> testResult = List.of(true, true, false, false, true, false, true, false);

        List<Integer> secondaryProcessesLength = List.of(1, 1, 0, 0, 2, 0, 0, 0);

        List<Integer> flowCount = List.of(1, MAX_SIZE, 0, 0, 2, 0, 0, 0);


        ProcessManager processManager = new ProcessManager();

        return IntStream.range(0, data.size()).mapToObj(index -> DynamicTest.dynamicTest(testNames.get(index) + ": " + data.get(index).toString(), () -> {
            try {
                processManager.clearProcesses();
                processManager.setSecondary(true);

                if (index == 6) {
                    processManager.setSecondary(false);
                }

                boolean result = processManager.modeling(data.get(index));

                Assertions.assertEquals(testResult.get(index), result, "Test failed for (" + testNames.get(index) + "): " + data.get(index));

                if (result) {
                    if (data.get(index).size() != MAX_SIZE) {
                        Assertions.assertEquals(secondaryProcessesLength.get(index), processManager.getSecondaryProcesses().size(), "Test failed for (" + testNames.get(index) + "): " + data.get(index));
                    }
                    int count = 0;
                    for (Process process : processManager.getSecondaryProcesses()) {
                        count += process.getOutputs().size() + process.getInputs().size();
                    }

                    assertEquals(flowCount.get(index), count, "Test failed for (" + testNames.get(index) + "): " + data.get(index));
                }
            } catch (Exception e) {
                throw new AssertionError("Exception occurred for test case (" + testNames.get(index) + "): " + data.get(index) + ": " + e.getMessage(), e);
            }
        }));
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsForModelingECPExceptionCases() {
        List<String> testNames = List.of(
                "ECP_MODELING_01",
                "ECP_MODELING_02",
                "ECP_MODELING_03",
                "ECP_MODELING_04"
        );

        List<Executable> actions = List.of(
                () -> {
                    ProcessManager processManager = new ProcessManager();
                    processManager.modeling(List.of("Cutting,INPUT,Material,10,KG,0.1", "Packaging,OUTPUT,Waste,10,KG,0.1"), "exemplo");
                },
                () -> {
                    ProcessManager processManager = new ProcessManager();
                    processManager.modeling();
                },
                () -> {
                    ProcessManager processManager = new ProcessManager();
                    processManager.modeling(List.of(0, 1));
                },
                () -> {
                    ProcessManager processManager = new ProcessManager();
                    processManager.modeling("example");
                }
        );

        return IntStream.range(0, testNames.size())
                .mapToObj(index -> DynamicTest.dynamicTest(
                        testNames.get(index),
                        () -> Assertions.assertThrows(
                                IllegalArgumentException.class,
                                actions.get(index),
                                "Test failed for (" + testNames.get(index) + ")"
                        )
                ));
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsForPrimaryDataModelingBVAAndECP() {
        int MAX_SIZE = 51;
        List<String> maxSizeList = IntStream.range(0, MAX_SIZE).mapToObj(i -> "Cutting,INPUT,Material,10,KG,0.1").toList();
        List<String> moreThanMaxSizeList = IntStream.range(0, MAX_SIZE + 1).mapToObj(i -> "Cutting,INPUT,Material,10,KG,0.1").toList();

        List<String> secondaryData = List.of("Cutting,INPUT,Material;Leather,10.0,KG,0.1", "Cutting,INPUT,Energy,5,kWh,0.1",
                "Cutting,OUTPUT,Emission;Leather,17.79220902,kgCO2eq,0.3", "Cutting,OUTPUT,Emission;Air,0.5,KGCO2TONELADAKM,0.1",
                "Cutting,INPUT,Transport;Air,9073.58,km,0.1", "Energy,OUTPUT,Emission;biomass,0.105,GCO2EQKWH,0.1",
                "Stitching,INPUT,Material;Leather,10,KG,0.1", "Stitching,INPUT,Energy,5.0,kWh,0.1",
                "Stitching,OUTPUT,Emission;Leather,17.79220902,kgCO2eq,0.3", "Stitching,OUTPUT,Emission;Air,0.5,KGCO2TONELADAKM,0.1",
                "Stitching,INPUT,Transport;Air,9073.58,km,0.1", "Assembly,INPUT,Material;Leather,10.0,KG,0.1",
                "Assembly,INPUT,Energy,5.0,kWh,0.1", "Assembly,OUTPUT,Emission;Leather,17.79220902,kgCO2eq,0.3",
                "Assembly,OUTPUT,Emission;Air,0.5,KGCO2TONELADAKM,0.1", "Assembly,INPUT,Transport;Air,9073.58,km,0.1",
                "Packaging,INPUT,Material;Leather,10.0,KG,0.1", "Packaging,INPUT,Energy,5.0,kWh,0.1",
                "Packaging,OUTPUT,Emission;Leather,17.79220902,kgCO2eq,0.3", "Packaging,OUTPUT,Emission;Air,0.5,KGCO2TONELADAKM,0.1",
                "Packaging,INPUT,Transport;Air,9073.58,km,0.1"
        );

        List<List<String>> data = List.of(
                List.of("Cutting,INPUT,Material;Leather,10,KG,0.1","Cutting,OUTPUT,EMISSION;Leather,2.0,KGCO2EQ,0.2"),
                maxSizeList,
                moreThanMaxSizeList,
                List.of(),
                List.of("Cutting,INPUT,Material;Leather,10,KG,0.1","Cutting,OUTPUT,EMISSION;Leather,2.0,KGCO2EQ,0.2"),
                List.of("example", "example"),
                List.of("Cutting,INPUT,Material;Leather,10,KG,0.1","Cutting,OUTPUT,EMISSION;Leather,2.0,KGCO2EQ,0.2"),
                List.of("EXAMPLE,INPUT,Not Material,10,KG,0.1"),
                List.of("Cutting,INPUT,Material;Leather","Cutting,output,EMISSION;Leather,3.0,KGCO2EQ,2.0"),
                List.of("Cutting,INPUT,Material;Leather,10,KG,0.1")
        );

        List<String> testNames = List.of("BVA_PD_MODELING_01", "BVA_PD_MODELING_02", "BVA_PD_MODELING_03", "BVA_PD_MODELING_04",
                "ECP_PD_MODELING_01", "ECP_PD_MODELING_02", "ECP_PD_MODELING_03", "ECP_PD_MODELING_04", "ECP_PD_MODELING_05", "ECP_PD_MODELING_06");

        List<Boolean> testResult = List.of(true, false, false, false, true, false, true, false, true, true);

        List<Integer> primaryProcessesLength = List.of(2,2, 0, 0, 2, 0, 0, 0, 2, 2);

        List<Integer> flowCount = List.of(3, MAX_SIZE, 0, 0, 3, 0, 0, 0, 3, 3);

        ProcessManager processManager = new ProcessManager();

        return IntStream.range(0, data.size()).mapToObj(index -> DynamicTest.dynamicTest(testNames.get(index) + ": " + data.get(index).toString(), () -> {
            try {
                processManager.clearProcesses();

                processManager.modeling(secondaryData);

                if (index == 6) {
                    processManager.setSecondary(true);
                }

                boolean result = processManager.modeling(data.get(index));

                Assertions.assertEquals(testResult.get(index), result, "Test failed for (" + testNames.get(index) + "): " + data.get(index));

                if (result) {
                    if (data.get(index).size() != MAX_SIZE) {
                        Assertions.assertEquals(primaryProcessesLength.get(index), processManager.getPrimaryProcesses().size(), "Test failed for (" + testNames.get(index) + "): " + data.get(index));
                    }
                    int count = 0;
                    for (Process process : processManager.getPrimaryProcesses()) {
                        count += process.getOutputs().size() + process.getInputs().size();
                    }

                    assertEquals(flowCount.get(index), count, "Test failed for (" + testNames.get(index) + "): " + data.get(index));

                    if (index == 8) {
                        Process process = processManager.getPrimaryProcesses().get(0);

                        Flow materialFlow = process.getInputs().get(0);

                        assertEquals(10.0, materialFlow.getValue(), "Test failed for (" + testNames.get(index) + "): " + data.get(index));
                        assertEquals(UnitType.KG, materialFlow.getUnit(), "Test failed for (" + testNames.get(index) + "): " + data.get(index));
                        assertEquals(0.1, materialFlow.getLossRate(), "Test failed for (" + testNames.get(index) + "): " + data.get(index));
                    }
                }
            } catch (Exception e) {
                throw new AssertionError("Exception occurred for test case (" + testNames.get(index) + "): " + data.get(index) + ": " + e.getMessage(), e);
            }
        }));
    }
}