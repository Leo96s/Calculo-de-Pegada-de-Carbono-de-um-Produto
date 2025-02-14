package org.estg.ipp.pt.Interfaces.Calculo;

import org.estg.ipp.pt.Models.Flow;

/**
 * A interface {@code CalculateImpact} define o contrato para o cálculo do impacto ambiental
 * de um fluxo, com base no fluxo de emissão associado. Ela herda as funcionalidades de
 * cálculo da ineficiência de fluxo através da interface {@link CalculateFlowInefficiency}.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Calcular o impacto ambiental de um fluxo, considerando um fluxo de emissão.</li>
 *   <li>Capacidades adicionais de cálculo da ineficiência de fluxo através de {@link CalculateFlowInefficiency}.</li>
 * </ul>
 *
 * <p>Esta interface pode ser implementada por classes responsáveis por calcular o impacto ambiental
 * em processos que envolvem fluxos e emissões associadas.</p>
 *
 * @see CalculateFlowInefficiency
 * @see Flow
 */
public interface CalculateImpact  extends CalculateFlowInefficiency {
    /**
     * Calcula o impacto ambiental de um fluxo, considerando o fluxo de emissão associado.
     *
     * @param flow O fluxo cujo impacto será calculado.
     * @param emissionFlow O fluxo de emissão associado ao fluxo principal.
     * @return O valor do impacto calculado.
     * @throws IllegalArgumentException Se algum dos fluxos fornecidos for nulo.
     */
    double calculateImpact(Flow flow, Flow emissionFlow);
}
