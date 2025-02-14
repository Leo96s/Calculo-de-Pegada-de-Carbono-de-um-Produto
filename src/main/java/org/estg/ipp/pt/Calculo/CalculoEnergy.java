package org.estg.ipp.pt.Calculo;
import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Enums.Type;
import org.estg.ipp.pt.Enums.UnitType;
import org.estg.ipp.pt.Interfaces.Calculo.CalculateEnergy;
import org.estg.ipp.pt.Models.Flow;

import java.util.List;

/**
 * A classe {@code CalculoEnergy} implementa a interface {@link CalculateEnergy} e realiza os cálculos
 * relacionados ao impacto energético com base nos fluxos de energia fornecidos.
 *
 * <p>Esta classe é usada para calcular a pegada energética (Energy Footprint)
 * a partir de uma lista de fluxos de energia. Os resultados são devolvidos como um objeto {@link Flow}
 * que representa o impacto total de energia.</p>
 *
 * @see CalculateEnergy
 * @see Flow
 * @see FlowCategory
 */
public class CalculoEnergy implements CalculateEnergy {

    /**
     * Calcula o impacto energético total com base em uma lista de fluxos de energia.
     *
     * <p>Este método soma os valores de todos os fluxos de energia na lista fornecida e devolve
     * um novo fluxo de energia que representam o impacto total. O fluxo retornado é caracterizado
     * com a categoria {@code ENERGY}, tipo {@code OUTPUT}, e unidade {@code GCO2EQKWH}.</p>
     *
     * @param energyFlows Uma lista de objetos {@link Flow} que representam fluxos de energia.
     *                    Cada fluxo deve ter valores válidos para ser somado.
     * @return Um novo objeto {@link Flow} que contém o impacto total de energia calculado.
     * @throws IllegalArgumentException Se a lista de fluxos de energia for nula ou vazia.
     */
    @Override
    public Flow calculateEnergyImpact(List<Flow> energyFlows) {
        double EnergyFootprint = 0;

        for (Flow flow : energyFlows) {
            EnergyFootprint += flow.getValue();
        }

        return new Flow(FlowCategory.ENERGY,"TOTALENERGY", Type.OUTPUT,EnergyFootprint, UnitType.GCO2EQKWH,0);
    }
}
