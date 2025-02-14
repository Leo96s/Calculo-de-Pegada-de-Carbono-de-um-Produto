package org.estg.ipp.pt.Models;

import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Enums.Type;
import org.estg.ipp.pt.Interfaces.Models.ProcessModeling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A classe {@code ProcessManager} é responsável pela gestão de processos, podendo
 * distinguir entre dados primários ({@code primaryData}) e dados secundários
 * ({@code secondaryData}). Essa classe permite a modelagem de processos, a classificação de
 * fluxos ({@code Flow}) e a adição de informações em falta baseadas em dados secundários.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Modelagem de processos com base em dados fornecidos.</li>
 *   <li>Gestão separada de processos primários e secundários.</li>
 *   <li>Classificação de fluxos em entradas e saídas.</li>
 *   <li>Preenchimento de informações em falta em fluxos.</li>
 * </ul>
 *
 * <p><b>Estrutura Interna:</b></p>
 * <ul>
 *   <li>{@code primaryData}: Lista de processos primários.</li>
 *   <li>{@code secondaryData}: Lista de processos secundários.</li>
 *   <li>{@code classifier}: Instância de {@link FlowClassifier} para classificar fluxos.</li>
 *   <li>{@code isSecondary}: Indica se o próximo conjunto de dados a ser modelado é secundário.</li>
 * </ul>
 *
 * <p>Essa classe implementa a interface {@link ProcessModeling}, que define os métodos para
 * a gestão e modelagem de processos.</p>
 *
 * @see FlowClassifier
 * @see Process
 */
public class ProcessManager implements ProcessModeling {
    static List<Process> secondaryData;
    private boolean isSecondary = true;
    List<Process> primaryData;
    FlowClassifier classifier;

    public ProcessManager() {
        this.primaryData = new ArrayList<>();
        this.secondaryData = new ArrayList<>();
        this.classifier = new FlowClassifier();
    }

    /**
     * Realiza a modelagem dos processos com base nos dados fornecidos.
     *
     * <p>Se os dados forem secundários, eles serão adicionados a {@code secondaryData}. Caso contrário,
     * serão adicionados a {@code primaryData}. Também define o estado de
     * {@code isSecondary} após processar os dados secundários.</p>
     *
     * @param data Lista de strings que representam os dados dos processos.
     * @return {@code true} se a modelagem for bem-sucedida; {@code false} caso contrário.
     */
    @Override
    public boolean modeling(List<String> data) {
        if (data == null || data.isEmpty() || data.size() > 50) {
            return false;
        }

        try {
            List<Process> targetData = isSecondary ? secondaryData : primaryData;
            boolean isSuccess = model(data, targetData);
            if (isSuccess && isSecondary) {
                isSecondary = false;
            }
            return isSuccess;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean modeling(Object... objects) throws IllegalArgumentException {
        if (objects.length != 1) {
            throw new IllegalArgumentException("The input must be a single object");
        }

        if (!(objects[0] instanceof List<?> list)) {
            throw new IllegalArgumentException("The input must be a list");
        }

        for (Object obj : list) {
            if (!(obj instanceof String)) {
                throw new IllegalArgumentException("All elements in the list must be a string");
            }
        }

        return false;
    }

    /**
     * Devolve a lista de processos secundários.
     *
     * @return Lista de {@link Process} secundários.
     */
    @Override
    public List<Process> getSecondaryProcesses() {
        return secondaryData;
    }

    /**
     * Devolve a lista de processos primários.
     *
     * @return Lista de {@link Process} primários.
     */
    @Override
    public List<Process> getPrimaryProcesses() {
        return primaryData;
    }

    /**
     * Limpa todas as listas de processos primários e secundários e
     * limpa o estado de {@code isSecondary}.
     */
    @Override
    public void clearProcesses() {
        primaryData.clear();
        secondaryData.clear();
        isSecondary = true;
    }

    /**
     * Divide uma string de dados do processo em componentes separados com base em vírgulas
     *
     * @param data String de dados a ser dividida.
     * @return Array de strings divididas.
     */
    private String[] splitData(String data) {
        String[] split = data.split(",");

        return split;
    }

    /**
     * Modela os dados numa lista de processos.
     *
     * <p>Para cada string de dados de processos, classifica o processo correspondente
     * e adiciona-o à lista de processos.</p>
     *
     * @param Data      Lista de strings que representa os dados.
     * @param processes Lista de processos onde os dados serão adicionados.
     * @return {@code true} se a modelagem for bem-sucedida; {@code false} caso contrário.
     */
    private boolean model(List<String> Data, List<Process> processes) {
        boolean existEnergy = false;
        for (String data : Data) {
            boolean result = classifyProcess(data, processes);

            if (!result) {
                return false;
            }
        }
        if (!isSecondary) {
            for (Process process : processes) {
                addMissingEmissions(process);
                if(process.getProcessName().equalsIgnoreCase("energy")){
                    existEnergy = true;
                }
            }
            if(!existEnergy) {
                addMissingEnergies(secondaryData, processes);
            }
        }

        return true;
    }

    /**
     * Classifica um único processo com base nos dados fornecidos.
     *
     * <p>Se o processo já existir, adiciona o fluxo correspondente. Caso contrário,
     * cria um novo processo e adiciona-o à lista.</p>
     *
     * @param data      String que representa os dados do processo.
     * @param processes Lista de processos onde o novo processo será adicionado.
     * @return {@code true} se a classificação for bem-sucedida; {@code false} caso contrário.
     */
    private boolean classifyProcess(String data, List<Process> processes) {
        try {
            String[] dataSplit = splitData(data);
            String processName = dataSplit[0];

            Flow flow = classifier.classifyFlow(dataSplit);

            Process process = getOrCreateProcess(processName, processes);
            addFlowToExistingProcess(process, flow);

            if (!isSecondary) {
                addMissingInfo(flow, process);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém um processo existente na lista ou cria um caso ele não exista.
     *
     * <p>Se o processo com o nome especificado já estiver presente na lista, ele é devolvido.
     * Caso contrário, um novo processo é criado, adicionado à lista e devolvido.</p>
     *
     * @param processName Nome do processo a ser procurado ou criado.
     * @param processes   Lista onde o processo será buscado ou adicionado.
     * @return O processo existente ou o novo processo criado.
     */
    private Process getOrCreateProcess(String processName, List<Process> processes) {
        Process existingProcess = getExistingProcess(processName, processes);
        if (existingProcess != null) {
            return existingProcess;
        }

        Process newProcess = new Process(processName, new ArrayList<>(), new ArrayList<>());
        processes.add(newProcess);
        return newProcess;
    }

    /**
     * Verifica se um processo com o nome especificado já existe na lista.
     *
     * @param processName Nome do processo a ser procurado.
     * @param processes   Lista de processos onde a busca será realizada.
     * @return O processo existente, se encontrado; caso contrário, {@code null}.
     */
    private Process getExistingProcess(String processName, List<Process> processes) {
        for (Process process : processes) {
            if (process.getProcessName().equals(processName)) {
                return process;
            }
        }
        return null;
    }

    /**
     * Adiciona um fluxo ao processo existente e classifica-o como entrada ou saída.
     *
     * <p>Se o tipo do fluxo for {@code INPUT}, ele é adicionado à lista de entradas do processo.
     * Caso contrário, é adicionado à lista de saídas.</p>
     *
     * @param process Processo ao qual o fluxo será adicionado.
     * @param flow    Fluxo a ser adicionado.
     */
    private void addFlowToExistingProcess(Process process, Flow flow) {
        if (flow.getType() == Type.INPUT) {
            process.addInput(flow);
        } else {
            process.addOutput(flow);
        }
    }

    /**
     * Obtém a lista de fluxos secundários associados ao processo e ao tipo de fluxo especificado.
     *
     * <p>Se o fluxo for uma entrada, devolve os fluxos de entrada do processo secundário correspondente.
     * Caso contrário, devolve os fluxos de saída.</p>
     *
     * @param processName Nome do processo a ser procurado.
     * @param flow        Fluxo usado para determinar se entradas ou saídas devem ser devolvidas.
     * @return Lista de fluxos secundários correspondentes.
     */
    private List<Flow> getSecondaryFlows(String processName, Flow flow) {
        Process secondaryProcess = new Process();
        List<Flow> list;
        for (Process process : secondaryData) {
            if (process.getProcessName().equals(processName)) {
                secondaryProcess = process;
            }
        }

        if (flow.getType().equals(Type.INPUT)) {
            list = secondaryProcess.getInputs();
        } else {
            list = secondaryProcess.getOutputs();
        }
        return list;
    }

    /**
     * Preenche informações em falta de um fluxo com base nos dados secundários.
     *
     * <p>Se o fluxo tiver valores em falta (ex.: valor, unidade), eles serão copiados
     * dos fluxos correspondentes dos processos secundários.</p>
     *
     * @param flow    Fluxo a ser atualizado.
     * @param process Processo ao qual o fluxo pertence.
     */
    private void addMissingInfo(Flow flow, Process process) {
        List<Flow> secondaryFlowData = getSecondaryFlows(process.getProcessName(), flow);

        if (flow.getValue() == 0.0) {
            for (Flow secFlow : secondaryFlowData) {
                if (secFlow.getName().equals(flow.getName())) {
                    flow.setValue(secFlow.getValue());
                    flow.setUnit(secFlow.getUnit());
                    flow.setLossRate(secFlow.getLossRate());

                    break;
                }
            }
        }
    }

    /**
     * Adiciona emissões ausentes ao processo primário, com base nos dados secundários.
     *
     * <p>Se o processo primário contiver entradas sem emissões correspondentes,
     * elas serão buscadas nos dados dos processos secundários.</p>
     *
     * @param primaryProcess Processo primário a ser atualizado.
     */
    private void addMissingEmissions(Process primaryProcess) {
        for (Flow input : primaryProcess.getInputs()) {
            if (!hasEmission(primaryProcess, input)) {
                addEmissionFromSecondary(primaryProcess, input);
            }
        }
    }

    /**
     * Adiciona emissões de energia ausentes ao processo primário, com base nos dados secundários.
     *
     * <p>Se o processo primário não contiver um processo de energia este será adicionado.</p>
     *
     * @param primaryProcesses Processos primários a ser atualizados.
     * @param secondaryProcesses Processo secundário a ser utilizado.
     */
    private void addMissingEnergies(List<Process> secondaryProcesses, List<Process> primaryProcesses) {
        for (Process secondaryProcess : secondaryProcesses) {
            if(secondaryProcess.getProcessName().equalsIgnoreCase("energy")) {
                primaryProcesses.add(secondaryProcess);
                return;
            }
        }
    }

    /**
     * Verifica se um fluxo de emissão já está associado a uma entrada no processo.
     *
     * <p>Compara os fluxos de saída do processo e verifica se há um fluxo com categoria
     * {@code EMISSION} e com o mesmo nome da entrada fornecida.</p>
     *
     * @param process Processo onde a verificação será feita.
     * @param input   Fluxo de entrada a ser comparado.
     * @return {@code true} se uma emissão correspondente existir; {@code false} caso contrário.
     */
    private boolean hasEmission(Process process, Flow input) {
        for (Flow output : process.getOutputs()) {
            if (output.getCategory() == FlowCategory.EMISSION && output.getName().equals(input.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adiciona emissões ao processo com base nos dados de processos secundários.
     *
     * <p>Para cada entrada no processo, busca fluxos de emissão correspondentes nos dados
     * secundários e os adiciona às saídas do processo primário.</p>
     *
     * @param process Processo primário ao qual as emissões serão adicionadas.
     * @param input   Fluxo de entrada para o qual as emissões serão buscadas.
     */
    private void addEmissionFromSecondary(Process process, Flow input) {
        for (Process secondaryProcess : secondaryData) {
            if (secondaryProcess.getProcessName().equals(process.getProcessName())) {
                for (Flow secondaryFlow : secondaryProcess.getOutputs()) {
                    if (secondaryFlow.getName().equals(input.getName()) && secondaryFlow.getCategory() == FlowCategory.EMISSION) {
                        process.addOutput(secondaryFlow);

                        break;
                    }
                }
            }
        }

    }

    /**
     * Devolve o valor de {@code isSecondary}
     *
     * @return {@code true} se o {@code ProcessManager} está configurado para receber dados secundários; {@code false} caso contrário.
     */
    public boolean isSecondary() {
        return isSecondary;
    }

    /**
     * Define o valor de {@code isSecondary}
     *
     * @param secondary boolean que indica se os processos modelados correspondem a valores secundários ou não
     */
    public void setSecondary(boolean secondary) {
        isSecondary = secondary;
    }
}
