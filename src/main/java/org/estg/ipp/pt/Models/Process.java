package org.estg.ipp.pt.Models;

import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Enums.Type;
import org.estg.ipp.pt.Interfaces.Models.ProcessInterface;

import java.io.Serializable;
import java.util.List;

/**
 * A classe {@code Process} representa um processo com fluxos de entrada e saída, e permite
 * cálculos como a pegada de carbono e a gestão dos seus fluxos.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Gestão dos fluxos de entrada e saída associados ao processo.</li>
 *   <li>Cálculo da pegada de carbono com base em fluxos de emissão.</li>
 *   <li>Adição dinâmica de fluxos de entrada e saída.</li>
 * </ul>
 *
 * <p><b>Estrutura Interna:</b></p>
 * <ul>
 *   <li>{@code processName}: Nome do processo.</li>
 *   <li>{@code inputs}: Lista de fluxos de entrada do processo.</li>
 *   <li>{@code outputs}: Lista de fluxos de saída do processo.</li>
 * </ul>
 *
 * <p>Essa classe implementa a interface {@link ProcessInterface}, que define métodos para
 * manipular e gerir os processos e os seus fluxos.</p>
 *
 * @see Flow
 * @see FlowCategory
 * @see Type
 */
public class Process implements ProcessInterface {
    String processName;
    List<Flow> inputs;
    List<Flow> outputs;

    /**
     * Construtor para inicializar um processo com o seu nome, entradas e saídas.
     *
     * @param processName Nome do processo.
     * @param input Lista de fluxos de entrada.
     * @param output Lista de fluxos de saída.
     */
    public Process(String processName, List<Flow> input, List<Flow> output) {
        this.processName = processName;
        this.inputs = input;
        this.outputs = output;
    }

    /**
     * Construtor padrão que inicializa um processo vazio.
     */
    public Process() {}

    //getters e setters
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public List<Flow> getInputs() {
        return inputs;
    }

    public void setInputs(List<Flow> inputs) {
        this.inputs = inputs;
    }

    public List<Flow> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Flow> outputs) {
        this.outputs = outputs;
    }

    public void addInput(Flow flow) {
        this.inputs.add(flow);
    }
    public void addOutput(Flow flow) {
        this.outputs.add(flow);
    }

    @Override
    public String toString() {
        return "Process{" +
                "processName='" + processName + '\'' +
                ", input=" + inputs +
                ", output=" + outputs +
                '}';
    }
}
