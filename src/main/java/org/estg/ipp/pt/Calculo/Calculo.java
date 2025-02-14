package org.estg.ipp.pt.Calculo;

import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Interfaces.Calculo.Resultado;
import org.estg.ipp.pt.Interfaces.Models.ProcessInterface;
import org.estg.ipp.pt.Models.Flow;
import org.estg.ipp.pt.Models.Process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A classe {@code Calculo} implementa a interface {@link Resultado} e é responsável por calcular o impacto
 * ambiental e o valor do PCF (Product Carbon Footprint) para uma lista de processos.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Calcular a ineficiência de fluxos e os impactos relacionados à energia.</li>
 *   <li>Verificar se todos os processos necessários estão presentes antes do cálculo.</li>
 *   <li>Realizar os cálculos detalhados por processo e armazenar os resultados específicos.</li>
 * </ul>
 *
 * <p><b>Requisitos:</b></p>
 * <ul>
 *   <li>A lista de processos não pode estar vazia.</li>
 *   <li>Todos os processos obrigatórios (energy, cutting, stitching, assembling, package) devem estar presentes.</li>
 *   <li>Fluxos de entrada e saída são avaliados para o impacto ambiental.</li>
 * </ul>
 *
 * <p><b>Principais Classes e Métodos:</b></p>
 * <ul>
 *   <li>{@link #calculateImpact(Flow, Flow)}: Calcula o impacto de um fluxo com base em seu fluxo de emissão.</li>
 *   <li>{@link #calculatePCFvalue(List)}: Calcula o valor do PCF para uma lista de processos.</li>
 *   <li>{@link #getResultado()}: Devolve o valor agregado do impacto ambiental.</li>
 *   <li>{@link #getResultadoPerProcess()}: Devolve o impacto ambiental detalhado por processo.</li>
 * </ul>
 */
public class Calculo implements Resultado {

    private CalculoFlowInefficiency calculoFlowInefficiency;

    private final HashMap<String,Double> resultadoPerProcess;

    private double resultado;

    private CalculoEnergy calculoEnergy;


    /**
     * Construtor padrão da classe {@code Calculo}.
     *
     * <p>Inicializa as instâncias necessárias para os cálculos da ineficiência de fluxo e impacto de energia,
     * além de configurar as estruturas para armazenar os resultados.</p>
     */
    public Calculo() {
        this.calculoFlowInefficiency = new CalculoFlowInefficiency();
        this.resultado = 0;
        this.calculoEnergy = new CalculoEnergy();
        this.resultadoPerProcess = new HashMap<>();
    }

    /**
     * Calcula o impacto do Flow
     *
     * @param flow O fluxo cujo impacto será calculado.
     * @param emissionFlow O fluxo de emissão associado ao fluxo principal.
     * @return o valor do impact do flow
     */
    @Override
    public double calculateImpact(Flow flow, Flow emissionFlow) {
        return calculoFlowInefficiency.calculateImpact(flow,emissionFlow);
    }

    /**
     * Calcula o valor da ineficiência do fluxo
     *
     * @param flow O fluxo cuja ineficiência será calculada.
     * @return o valor da ineficiência do fluxo
     */
    @Override
    public double calculateFlowInefficiency(Flow flow) {
        return calculoFlowInefficiency.calculateFlowInefficiency(flow);
    }

    /**
     * Calcula o valor do PCF
     *
     * @param processes Uma lista de objetos {@link ProcessInterface} que representam os processos a serem considerados no cálculo.
     * @return valor do cálculo do PCF
     */
    @Override
    public double calculatePCFvalue(List<ProcessInterface> processes) {
        if(processes.isEmpty()){
            throw new IllegalArgumentException("Processes cannot be empty");
        }

        ProcessInterface energyProcess = getSecondaryEnergyProcess(processes);
        List<String> requiredProcesses = Arrays.asList("energy", "cutting", "stitching", "assembling", "package");
        List<String> presentProcesses = new ArrayList<>();

        if(energyProcess == null) {
            throw new IllegalArgumentException("There is no energy process");
        }

        Flow EnergyImpact = calculoEnergy.calculateEnergyImpact(energyProcess.getOutputs());


        for (ProcessInterface process : processes) {
            if (!presentProcesses.contains(process.getProcessName().toLowerCase())) {
                presentProcesses.add(process.getProcessName().toLowerCase());
            }
        }

        for (String requiredProcess : requiredProcesses) {
            if (!presentProcesses.contains(requiredProcess)) {
                throw new IllegalArgumentException("Processo não encontrado: " + requiredProcess);
            }
        }

        for(ProcessInterface process : processes) {
            double perProcess = 0;
            for(Flow inputFlow : process.getInputs()) {
                if(inputFlow.getCategory() == FlowCategory.ENERGY){
                    double calculateImpactEnergy = calculateImpact(inputFlow,EnergyImpact);
                    perProcess += calculateImpactEnergy;
                    resultado += calculateImpactEnergy;
                }
                Flow emissionFlow = getEmissionFlow(inputFlow,process);
                double calculateImpact = calculateImpact(inputFlow,emissionFlow);
                perProcess += calculateImpact;
                resultado += calculateImpact;
            }
            resultadoPerProcess.put(process.getProcessName().toLowerCase(),perProcess);
        }
        return resultado;
    }

    /**
     * Obtém o fluxo de emissão correspondente a um fluxo de entrada e processo fornecidos.
     *
     * @param flow    O fluxo de entrada a ser analisado.
     * @param process O processo associado ao fluxo de entrada.
     * @return O fluxo de emissão correspondente ou {@code null} se não encontrado.
     */
    private Flow getEmissionFlow(Flow flow, ProcessInterface process) {

        for (Flow output : process.getOutputs()) {
            if(output.getName().equals(flow.getName())) {
                return output;
            }
        }

        return null;
    }

    /**
     * Obtém o processo de energia secundário da lista de processos fornecida.
     *
     * @param processes A lista de processos para análise.
     * @return O processo de energia secundário ou {@code null} se não encontrado.
     */
    private ProcessInterface getSecondaryEnergyProcess(List<ProcessInterface> processes){
        for(ProcessInterface process : processes){
            if(process.getProcessName().equalsIgnoreCase("energy")){
                return process;
            }
        }
        return null;
    }

    /**
     * Devolve o valor total calculado do impacto ambiental.
     *
     * @return O impacto total como um valor {@code double}.
     */
    public double getResultado() {
        return resultado;
    }

    /**
     * Devolve um mapeamento detalhado do impacto ambiental por processo.
     *
     * <p>A chave do mapa é o identificador único ou o nome de cada processo,
     * enquanto o valor é o impacto correspondente como um {@code double}.</p>
     *
     * @return Um {@code HashMap<String, Double>} que contém o impacto por processo.
     */
    public HashMap<String,Double> getResultadoPerProcess() {
        return resultadoPerProcess;
    }
}
