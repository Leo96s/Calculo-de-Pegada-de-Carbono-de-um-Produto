package org.estg.ipp.pt.Interfaces.Calculo;

import org.estg.ipp.pt.Models.*;

import java.util.List;

/**
 * A interface {@code CalculateEnergy} define o contrato para o cálculo do impacto energético
 * com base numa lista de fluxos de energia.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Calcular o impacto energético a partir dos fluxos de energia fornecidos.</li>
 * </ul>
 *
 * <p>Esta interface pode ser implementada por classes responsáveis por calcular o impacto
 * energético em processos que envolvem múltiplos fluxos de energia.</p>
 *
 * @see Flow
 */
public interface CalculateEnergy{
    /**
     * Calcula o impacto energético com base numa lista de fluxos de energia.
     *
     * @param energyFlows Lista de fluxos de energia a serem considerados no cálculo.
     * @return Um objeto {@link Flow} que representa o impacto energético calculado.
     * @throws IllegalArgumentException Se a lista de fluxos de energia for nula ou vazia.
     */
    Flow calculateEnergyImpact(List<Flow> energyFlows);
}
