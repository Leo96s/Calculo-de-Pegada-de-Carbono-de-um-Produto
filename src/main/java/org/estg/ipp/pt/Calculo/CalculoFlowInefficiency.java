package org.estg.ipp.pt.Calculo;

import org.estg.ipp.pt.Interfaces.Calculo.CalculateImpact;
import org.estg.ipp.pt.Models.Flow;

/**
 * A classe {@code CalculoFlowInefficiency} implementa a interface {@link CalculateImpact} e fornece os métodos
 * para calcular a ineficiência de fluxos e o impacto resultante com base em fluxos de entrada e emissão.
 *
 * <p><b>Funcionalidades principais:</b></p>
 * <ul>
 *   <li>Calcular a ineficiência de um fluxo com base no valor e na taxa de perda associada.</li>
 *   <li>Determinar o impacto entre dois fluxos, considerando a ineficiência calculada para ambos.</li>
 * </ul>
 *
 * @see CalculateImpact
 * @see Flow
 */
public class CalculoFlowInefficiency implements CalculateImpact {
    /**
     * Calcula a ineficiência de um fluxo com base no seu valor e taxa de perda.
     *
     * <p>Se o fluxo for nulo, o método devlve {@code 0}, indicando que não há ineficiência
     * a ser calculada.</p>
     *
     * @param flow O objeto {@link Flow} que representa o fluxo a ser analisado.
     * @return O valor da ineficiência calculada como um número {@code double}.
     */
    @Override
    public double calculateFlowInefficiency(Flow flow) {
        if(flow == null){
            return 0;
        }

        return flow.getValue() * (1 + flow.getLossRate());
    }

    /**
     * Calcula o impacto entre um fluxo e um fluxo de emissão associado.
     *
     * <p>O impacto é calculado através da multiplicação as ineficiências de ambos os fluxos.
     * Caso qualquer um dos fluxos seja nulo, o impacto devolvido será {@code 0}.</p>
     *
     * @param flow O objeto {@link Flow} que representa o fluxo principal.
     * @param emissionFlow O objeto {@link Flow} que representa o fluxo de emissão associado.
     * @return O impacto calculado como um número {@code double}.
     */
    @Override
    public double calculateImpact(Flow flow, Flow emissionFlow) {
        if(flow == null || emissionFlow == null) {
            return 0;
        }
        return calculateFlowInefficiency(flow) * calculateFlowInefficiency(emissionFlow);
    }
}
