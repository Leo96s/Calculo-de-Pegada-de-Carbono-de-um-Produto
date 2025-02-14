package org.estg.ipp.pt.Interfaces.Models;

import org.estg.ipp.pt.Enums.UnitType;
import org.estg.ipp.pt.Models.Flow;

import java.util.List;

/**
 * A interface {@code ProcessInterface} define o contrato para classes que representam
 * um processo no sistema, incluindo os seus fluxos de entrada e saída.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Obter o nome do processo.</li>
 *   <li>Obter os fluxos de entrada associados ao processo.</li>
 *   <li>Obter os fluxos de saída associados ao processo.</li>
 * </ul>
 *
 * <p><b>Objetivo:</b></p>
 * <ul>
 *   <li>Fornecer uma estrutura consistente para representar e manipular processos.</li>
 * </ul>
 *
 * @see Flow
 */
public interface ProcessInterface {
    /**
     * Obtém o nome do processo.
     *
     * @return Uma string que representa o nome do processo.
     */
    String getProcessName();

    /**
     * Obtém os fluxos de entrada associados ao processo.
     *
     * @return Uma lista de objetos {@link Flow} que representa os fluxos de entrada.
     */
    List<Flow> getInputs();

    /**
     * Obtém os fluxos de saída associados ao processo.
     *
     * @return Uma lista de objetos {@link Flow} que representa os fluxos de saída.
     */
    List<Flow> getOutputs();
}
