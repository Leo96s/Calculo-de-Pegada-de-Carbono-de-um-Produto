package org.estg.ipp.pt;

import org.estg.ipp.pt.Calculo.Calculo;
import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Enums.Type;
import org.estg.ipp.pt.Enums.UnitType;
import org.estg.ipp.pt.Interfaces.Models.ProcessInterface;
import org.estg.ipp.pt.Models.Flow;
import org.estg.ipp.pt.Models.Process;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CalculoTest {
    private Calculo calculo;

    @BeforeEach
    void setUp() {
        calculo = new Calculo();
    }

    @Test
    void testValidCalculo() {
        // Fluxos para energyPortugal
        Flow energyInput = new Flow(FlowCategory.ENERGY, "energia", Type.INPUT, 14, UnitType.KWH, 0.1);
        Flow emissionOutputEnergy = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.6, UnitType.KGCO2EQ, 0);

        // Processo energyPortugal
        Process energyPortugal = new Process("energy",
                List.of(energyInput),
                List.of(emissionOutputEnergy));

        // Fluxos para cutting
        Flow leatherInput = new Flow(FlowCategory.MATERIAL, "leather", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputCutting = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.4, UnitType.KGCO2EQ, 0);

        // Processo cutting
        Process cutting = new Process("cutting",
                List.of(leatherInput),
                List.of(emissionOutputCutting));

        // Fluxos para stitching
        Flow stringInput = new Flow(FlowCategory.MATERIAL, "string", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputStitching = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo stitching
        Process stitching = new Process("stitching",
                List.of(stringInput),
                List.of(emissionOutputStitching));

        // Fluxos para assembling
        Flow glueOutput = new Flow(FlowCategory.WASTE, "glue", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputAssembling = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo assembling
        Process assembling = new Process("assembling",
                List.of(),
                Arrays.asList(glueOutput, emissionOutputAssembling));

        // Fluxos para package
        Flow tapeOutput = new Flow(FlowCategory.WASTE, "tape", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputPackage = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo package
        Process packageProcess = new Process("package",
                List.of(),
                Arrays.asList(tapeOutput, emissionOutputPackage));

        // Lista de processos
        List<ProcessInterface> processes = Arrays.asList(energyPortugal, cutting, stitching, assembling, packageProcess);

        System.out.println(processes);

        // Teste
        double result = calculo.calculatePCFvalue(processes);
        assertEquals(9.24, result, 0.01);
    }

    @Test
    void testInvalidTypeOfProcess() {
        // Teste do cenário "NullCategory"
        List<Object> processesList = Arrays.asList("aditional string");

        List<ProcessInterface> validProcesses = processesList.stream()
                .filter(process -> process instanceof ProcessInterface)
                .map(process -> (ProcessInterface) process)
                .collect(Collectors.toList());

        assertThrows(IllegalArgumentException.class, () -> {
            calculo.calculatePCFvalue(validProcesses);
        });
    }

    @Test
    void testNullList() {
        // Teste do cenário "NullCategory"
        assertThrows(NullPointerException.class, () -> calculo.calculatePCFvalue(null));
    }

    @Test
    void testEmptyList() {
        // Teste do cenário "EmptyList"
        List<ProcessInterface> processes = List.of();

        assertThrows(IllegalArgumentException.class, () -> {
            calculo.calculatePCFvalue(processes);
        });
    }

    @Test
    void testValidCalculoWithNewFlow() {
        // Dados do cenário "ValidCategory" (madeira)
        // Fluxos para energyPortugal
        Flow energyInput = new Flow(FlowCategory.ENERGY, "energia", Type.INPUT, 14, UnitType.KWH, 0.1);
        Flow emissionOutputEnergy = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.6, UnitType.KGCO2EQ, 0);

        // Processo energyPortugal
        Process energyPortugal = new Process("energy",
                List.of(energyInput),
                List.of(emissionOutputEnergy));

        // Fluxos para cutting
        Flow leatherInput = new Flow(FlowCategory.MATERIAL, "leather", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputCutting = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.4, UnitType.KGCO2EQ, 0);

        // Processo cutting
        Process cutting = new Process("cutting",
                List.of(leatherInput),
                List.of(emissionOutputCutting));

        // Fluxos para stitching
        Flow stringInput = new Flow(FlowCategory.MATERIAL, "string", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputStitching = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo stitching
        Process stitching = new Process("stitching",
                List.of(stringInput),
                List.of(emissionOutputStitching));

        // Fluxos para assembling
        Flow glueOutput = new Flow(FlowCategory.WASTE, "glue", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputAssembling = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo assembling
        Process assembling = new Process("assembling",
                List.of(),  // Assembling não possui inputs
                Arrays.asList(glueOutput, emissionOutputAssembling));

        // Fluxos para package
        Flow tapeOutput = new Flow(FlowCategory.WASTE, "tape", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputPackage = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo package
        Process packageProcess = new Process("package",
                List.of(),  // Package não possui inputs
                Arrays.asList(tapeOutput, emissionOutputPackage));

        // Lista de processos
        List<ProcessInterface> processes = Arrays.asList(energyPortugal, cutting, stitching, assembling, packageProcess);


        // Teste
        double result = calculo.calculatePCFvalue(processes);
        assertEquals(9.24, result, 0.01);
    }

    @Test
    void testInvalidCalculoMissingProcessPackage() {
        // Dados do cenário "TransportCategory"
        // Fluxos para energyPortugal
        Flow energyInput = new Flow(FlowCategory.ENERGY, "energia", Type.INPUT, 14, UnitType.KWH, 0.1);
        Flow emissionOutputEnergy = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.6, UnitType.KGCO2EQ, 0);

        // Processo energyPortugal
        Process energyPortugal = new Process("energy",
                List.of(energyInput),
                List.of(emissionOutputEnergy));

        // Fluxos para cutting
        Flow leatherInput = new Flow(FlowCategory.MATERIAL, "leather", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputCutting = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.4, UnitType.KGCO2EQ, 0);

        // Processo cutting
        Process cutting = new Process("cutting",
                List.of(leatherInput),
                List.of(emissionOutputCutting));

        // Fluxos para stitching
        Flow stringInput = new Flow(FlowCategory.MATERIAL, "string", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputStitching = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo stitching
        Process stitching = new Process("stitching",
                List.of(stringInput),
                List.of(emissionOutputStitching));

        // Fluxos para assembling
        Flow glueOutput = new Flow(FlowCategory.WASTE, "glue", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputAssembling = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo assembling
        Process assembling = new Process("assembling",
                List.of(),  // Assembling não possui inputs
                Arrays.asList(glueOutput, emissionOutputAssembling));

        // Lista de processos
        List<ProcessInterface> processes = Arrays.asList(energyPortugal, cutting, stitching, assembling);


        // Teste
        assertThrows(IllegalArgumentException.class, () -> {
            calculo.calculatePCFvalue(processes);
        });
    }

    @Test
    void testInvalidCalculoMissingProcessAssembling() {
        // Dados do cenário "RecyclingCategory"
        // Fluxos para energyPortugal
        Flow energyInput = new Flow(FlowCategory.ENERGY, "energia", Type.INPUT, 14, UnitType.KWH, 0.1);
        Flow emissionOutputEnergy = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.6, UnitType.KGCO2EQ, 0);

        // Processo energyPortugal
        Process energyPortugal = new Process("energy",
                List.of(energyInput),
                List.of(emissionOutputEnergy));

        // Fluxos para cutting
        Flow leatherInput = new Flow(FlowCategory.MATERIAL, "leather", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputCutting = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.4, UnitType.KGCO2EQ, 0);

        // Processo cutting
        Process cutting = new Process("cutting",
                List.of(leatherInput),
                List.of(emissionOutputCutting));

        // Fluxos para stitching
        Flow stringInput = new Flow(FlowCategory.MATERIAL, "string", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputStitching = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo stitching
        Process stitching = new Process("stitching",
                List.of(stringInput),
                List.of(emissionOutputStitching));

        // Fluxos para package
        Flow tapeOutput = new Flow(FlowCategory.WASTE, "tape", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputPackage = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo package
        Process packageProcess = new Process("package",
                List.of(),  // Package não possui inputs
                Arrays.asList(tapeOutput, emissionOutputPackage));

        // Lista de processos
        List<ProcessInterface> processes = Arrays.asList(energyPortugal, cutting, stitching, packageProcess);


        // Teste
        assertThrows(IllegalArgumentException.class, () -> {
            calculo.calculatePCFvalue(processes);
        });
    }

    @Test
    void testInvalidCalculoMissingProcessStitching() {
        // Fluxos para energyPortugal
        Flow energyInput = new Flow(FlowCategory.ENERGY, "energia", Type.INPUT, 14, UnitType.KWH, 0.1);
        Flow emissionOutputEnergy = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.6, UnitType.KGCO2EQ, 0);

        // Processo energyPortugal
        Process energyPortugal = new Process("energy",
                List.of(energyInput),
                List.of(emissionOutputEnergy));

        // Fluxos para cutting
        Flow leatherInput = new Flow(FlowCategory.MATERIAL, "leather", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputCutting = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.4, UnitType.KGCO2EQ, 0);

        // Processo cutting
        Process cutting = new Process("cutting",
                List.of(leatherInput),
                List.of(emissionOutputCutting));

        // Fluxos para assembling
        Flow glueOutput = new Flow(FlowCategory.WASTE, "glue", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputAssembling = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo assembling
        Process assembling = new Process("assembling",
                List.of(),  // Assembling não possui inputs
                Arrays.asList(glueOutput, emissionOutputAssembling));

        // Fluxos para package
        Flow tapeOutput = new Flow(FlowCategory.WASTE, "tape", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputPackage = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo package
        Process packageProcess = new Process("package",
                List.of(),  // Package não possui inputs
                Arrays.asList(tapeOutput, emissionOutputPackage));


        // Lista de processos
        List<ProcessInterface> processes = Arrays.asList(energyPortugal, cutting, assembling, packageProcess);


        // Teste
        assertThrows(IllegalArgumentException.class, () -> {
            calculo.calculatePCFvalue(processes);
        });
    }

    @Test
    void testInvalidCalculoMissingProcessCutting() {
        // Fluxos para energyPortugal
        Flow energyInput = new Flow(FlowCategory.ENERGY, "energia", Type.INPUT, 14, UnitType.KWH, 0.1);
        Flow emissionOutputEnergy = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.6, UnitType.KGCO2EQ, 0);

        // Processo energyPortugal
        Process energyPortugal = new Process("energy",
                List.of(energyInput),
                List.of(emissionOutputEnergy));

        // Fluxos para cutting
        Flow leatherInput = new Flow(FlowCategory.MATERIAL, "leather", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputCutting = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.4, UnitType.KGCO2EQ, 0);

        // Processo cutting
        Process cutting = new Process("cutting",
                List.of(leatherInput),
                List.of(emissionOutputCutting));

        // Fluxos para assembling
        Flow glueOutput = new Flow(FlowCategory.WASTE, "glue", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputAssembling = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo assembling
        Process assembling = new Process("assembling",
                List.of(),  // Assembling não possui inputs
                Arrays.asList(glueOutput, emissionOutputAssembling));

        // Fluxos para package
        Flow tapeOutput = new Flow(FlowCategory.WASTE, "tape", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputPackage = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo package
        Process packageProcess = new Process("package",
                List.of(),  // Package não possui inputs
                Arrays.asList(tapeOutput, emissionOutputPackage));


        // Lista de processos
        List<ProcessInterface> processes = Arrays.asList(energyPortugal, cutting, assembling, packageProcess);


        // Teste
        assertThrows(IllegalArgumentException.class, () -> {
            calculo.calculatePCFvalue(processes);
        });
    }

    @Test
    void testInvalidCalculoWithAditionalInput() {
        // Fluxos para cutting
        Flow leatherInput = new Flow(FlowCategory.MATERIAL, "leather", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputCutting = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.4, UnitType.KGCO2EQ, 0);

        // Processo cutting
        Process cutting = new Process("cutting",
                List.of(leatherInput),
                List.of(emissionOutputCutting));

        // Fluxos para stitching
        Flow stringInput = new Flow(FlowCategory.MATERIAL, "string", Type.INPUT, 0.8, UnitType.KG, 0.2);
        Flow emissionOutputStitching = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo stitching
        Process stitching = new Process("stitching",
                List.of(stringInput),
                List.of(emissionOutputStitching));

        // Fluxos para assembling
        Flow glueOutput = new Flow(FlowCategory.WASTE, "glue", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputAssembling = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo assembling
        Process assembling = new Process("assembling",
                List.of(),  // Assembling não possui inputs
                Arrays.asList(glueOutput, emissionOutputAssembling));

        // Fluxos para package
        Flow tapeOutput = new Flow(FlowCategory.WASTE, "tape", Type.OUTPUT, 0.2, UnitType.KG, 0);
        Flow emissionOutputPackage = new Flow(FlowCategory.EMISSION, "emission", Type.OUTPUT, 0.2, UnitType.KGCO2EQ, 0);

        // Processo package
        Process packageProcess = new Process("package",
                List.of(),  // Package não possui inputs
                Arrays.asList(tapeOutput, emissionOutputPackage));


        // Lista de processos com a string adicional
        List<Object> processesList = Arrays.asList(cutting, stitching, assembling, packageProcess, "aditional string");

        List<ProcessInterface> validProcesses = processesList.stream()
                .filter(process -> process instanceof ProcessInterface)
                .map(process -> (ProcessInterface) process)
                .collect(Collectors.toList());

        // Teste
        assertThrows(IllegalArgumentException.class, () -> {
            calculo.calculatePCFvalue(validProcesses);
        });
    }

}
