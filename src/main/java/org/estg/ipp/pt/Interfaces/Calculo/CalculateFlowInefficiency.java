package org.estg.ipp.pt.Interfaces.Calculo;


import org.estg.ipp.pt.Models.Flow;

/**
 * A interface {@code CalculateFlowInefficiency} define o contrato para o cálculo
 * da ineficiência de um fluxo.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Calcular a ineficiência de um fluxo com base nas suas características.</li>
 * </ul>
 *
 * <p>Esta interface pode ser implementada por classes responsáveis por calcular a ineficiência
 * de fluxos.</p>
 *
 * @see Flow
 */
public interface CalculateFlowInefficiency {
    /**
     * Calcula a ineficiência de um fluxo.
     *
     * @param flow O fluxo cuja ineficiência será calculada.
     * @return O valor da ineficiência do fluxo, representado por um valor numérico.
     * @throws IllegalArgumentException Se o fluxo fornecido for nulo.
     */
    double calculateFlowInefficiency(Flow flow);
}
