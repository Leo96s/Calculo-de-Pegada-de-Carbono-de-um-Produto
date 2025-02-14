package org.estg.ipp.pt.Interfaces.Models;

import org.estg.ipp.pt.Models.Process;

import java.util.List;

/**
 * A interface {@code ProcessModeling} define os métodos essenciais para a modelagem e
 * gestão de processos, incluindo processos primários e secundários.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Permitir a modelagem de processos a partir de dados brutos.</li>
 *   <li>Obter listas separadas de processos primários e secundários.</li>
 *   <li>Reinicializar o estado atual dos processos modelados.</li>
 * </ul>
 *
 * <p>Esta interface serve como uma base para implementar funcionalidades mais específicas
 * relacionadas à manipulação de processos.</p>
 *
 * @see Process
 */
public interface ProcessModeling {
    /**
     * Realiza a modelagem de processos com base nos dados fornecidos.
     *
     * @param data Lista de strings que contêm os dados necessários para a modelagem do processo.
     * @return true se a modelagem foi bem-sucedida, caso contrário, false.
     */
    boolean modeling(List<String> data);

    /**
     * Recupera a lista de processos primários modelados.
     *
     * @return Uma lista de objetos {@link Process} que representa os processos primários.
     */
    List<Process> getPrimaryProcesses();

    /**
     * Recupera a lista de processos secundários modelados.
     *
     * @return Uma lista de objetos {@link Process} que representa os processos secundários.
     */
    List<Process> getSecondaryProcesses();

    /**
     * Limpa todos os processos modelados (primários e secundários).
     */
    void clearProcesses();
}
