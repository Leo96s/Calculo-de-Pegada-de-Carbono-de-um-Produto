package org.estg.ipp.pt;

import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Models.Flow;
import org.estg.ipp.pt.Models.FlowClassifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClassifyTest {

    FlowClassifier flowClassifier;

    /**
     * Set up da instância de FlowClassifier para a execução dos testes
     */
    @BeforeEach
    void setUp() {
        flowClassifier = new FlowClassifier();
    }

    /**
     * Test factory para testar casos de teste ECP válidos da funcionalidade classify
     *
     * @return stream de testes executados
     */
    @TestFactory
    Stream<DynamicTest> validDynamicTestsForClassifyECP() {
        List<List<String>> data = List.of(
                List.of("Cutting", "INPUT", "Material", "10", "KG", "0.1"),
                List.of("Stitching", "INPUT", "Transport", "125", "KM", "0.0"),
                List.of("Assembling", "INPUT", "Energy", "15", "KWH", "0.1"),
                List.of("Packaging", "OUTPUT", "Waste", "10", "KG", "0.1"),
                List.of("Cutting", "OUTPUT", "Emission", "10", "KGCO2EQ", "0.0")
        );

        List<String> testNames = List.of("ECP_MATERIALFLOW_1", "ECP_TRANSPORTFLOW_1", "ECP_ENERGYFLOW_1", "ECP_WASTEFLOW_1",
                "ECP_EMISSIONFLOW_1");

        List<FlowCategory> outputs = List.of(FlowCategory.MATERIAL, FlowCategory.TRANSPORT, FlowCategory.ENERGY,
                FlowCategory.WASTE, FlowCategory.EMISSION);

        return IntStream.range(0, data.size())
                .mapToObj(index -> DynamicTest.dynamicTest(
                        testNames.get(index) + ": " + data.get(index).toString(),
                        () -> {
                            String[] line = data.get(index).toArray(new String[0]);
                            try {
                                Flow returned = flowClassifier.classifyFlow(line);
                                assertEquals(outputs.get(index), returned.getCategory(),
                                        "Test failed for(" + testNames.get(index) + "): " + Arrays.toString(line));
                            } catch (Exception e) {
                                throw new AssertionError(
                                        "Exception occurred for test case (" + testNames.get(index) + "): " +
                                                Arrays.toString(line) + ": " + e.getMessage(), e);
                            }
                        }
                ));
    }

    /**
     * Test factory para testar casos de teste ECP inválidos da funcionalidade classify
     *
     * @return stream de testes executados
     */
    @TestFactory
    Stream<DynamicTest> invalidDynamicTestsForClassifyECP() {
        List<List<String>> data = List.of(
                List.of("Cutting", "INPUT", "Material", "-1", "KG", "0.0"),
                List.of("Stitching", "INPUT", "Transport", "125", "KG", "0.0"),
                List.of("Assembling", "INPUT", "Energy", "15", "KWH", "-1"),
                List.of("Packaging", "OUTPUT", "Waste", "-10", "KG", "0.1"),
                List.of("Cutting", "OUTPUT", "Emission", "10", "KWH", "0.0")
        );

        List<String> testNames = List.of("ECP_MATERIALFLOW_2", "ECP_TRANSPORTFLOW_2", "ECP_ENERGYFLOW_2", "ECP_WASTEFLOW_2",
                "ECP_EMISSIONFLOW_2");

        return IntStream.range(0, data.size())
                .mapToObj(index -> DynamicTest.dynamicTest(
                        testNames.get(index) + ": " + data.get(index).toString(),
                        () -> {
                            String[] line = data.get(index).toArray(new String[0]);
                            try {
                                assertNull(flowClassifier.classifyFlow(line), "Test failed for(" + testNames.get(index) + "): " + Arrays.toString(line));
                            } catch (Exception e) {
                                throw new AssertionError(
                                        "Exception occurred for test case (" + testNames.get(index) + "): " +
                                                Arrays.toString(line) + ": " + e.getMessage(), e);
                            }
                        }
                ));
    }

    /**
     * Test factory para testar casos de teste BVA válidos da funcionalidade classify
     *
     * @return stream de testes executados
     */
    @TestFactory
    Stream<DynamicTest> validDynamicTestsForClassifyBVA() {
        List<List<String>> data = List.of(
                List.of("Cutting", "OUTPUT", "Waste", "0.0", "KG", "0"),
                List.of("Stitching", "INPUT", "Energy", String.valueOf(Double.MAX_VALUE), "KWH", "1.0")
        );

        List<String> testNames = List.of("BVA_FLOW_01", "BVA_FLOW_02", "BVA_FLOW_03", "BVA_FLOW_04", "BVA_FLOW_05",
                "BVA_FLOW_06", "BVA_FLOW_07", "BVA_FLOW_08", "BVA_FLOW_09", "BVA_FLOW_10");

        List<Class<?>> outputs = List.of(Flow.class, Flow.class);

        return IntStream.range(0, data.size())
                .mapToObj(index -> DynamicTest.dynamicTest(
                        testNames.get(index) + ": " + data.get(index).toString(),
                        () -> {
                            String[] line = data.get(index).toArray(new String[0]);
                            try {
                                Flow result = flowClassifier.classifyFlow(line);

                                Assertions.assertInstanceOf(outputs.get(index), result,
                                        "Test failed for(" + testNames.get(index) + "): " + Arrays.toString(line));
                            } catch (Exception e) {
                                throw new AssertionError(
                                        "Exception occurred for test case (" + testNames.get(index) + "): " +
                                                Arrays.toString(line) + ": " + e.getMessage(), e);
                            }
                        }
                ));
    }

    /**
     * Test factory para testar casos de teste BVA inválidos da funcionalidade classify
     *
     * @return stream de testes executados
     */
    @TestFactory
    Stream<DynamicTest> invalidDynamicTestsForClassifyBVA() {
        List<List<String>> data = List.of(
                List.of("qweew", "INPUT", "Energy", "0", "KG", "0.0"),
                List.of("Assembly", "OUTPUT", "jahdj", "0", "KGCO2EQ", "1.0"),
                List.of("Cutting", "AHSQW", "Energy", "0", "KM", "1.0"),
                List.of("Cutting", "OUTPUT", "Waste", "-2", "KG", "1.0"),
                List.of("Stitching", "INPUT", "Transport", "50.0", "T", "0.0"),
                List.of("Assembly", "OUTPUT", "Transport", "12", "KG", "1.5"),
                List.of("Cutting", "INPUT", "1000", "KG", "0.5"),
                List.of("Cutting", "SO2", "OUTPUT", "15", "KGCO2EQ", "1.2", "oqwe")
        );

        List<String> testNames = List.of("BVA_FLOW_03", "BVA_FLOW_04", "BVA_FLOW_05",
                "BVA_FLOW_06", "BVA_FLOW_07", "BVA_FLOW_08", "BVA_FLOW_09", "BVA_FLOW_10");

        return IntStream.range(0, data.size())
                .mapToObj(index -> DynamicTest.dynamicTest(
                        testNames.get(index) + ": " + data.get(index).toString(),
                        () -> {
                            String[] line = data.get(index).toArray(new String[0]);
                            try {
                                Assertions.assertNull(flowClassifier.classifyFlow(line), "Test failed for (" + testNames.get(index) + "): " + Arrays.toString(line));
                            } catch (Exception e) {
                                throw new AssertionError(
                                        "Exception occurred for test case (" + testNames.get(index) + "): " +
                                                Arrays.toString(line) + ": " + e.getMessage(), e);
                            }
                        }
                ));
    }
}

