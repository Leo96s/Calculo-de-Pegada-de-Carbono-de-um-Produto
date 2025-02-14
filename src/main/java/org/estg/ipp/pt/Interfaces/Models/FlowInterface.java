package org.estg.ipp.pt.Interfaces.Models;

import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Enums.Type;
import org.estg.ipp.pt.Enums.UnitType;

/**
 * A interface {@code FlowInterface} define o contrato para classes que representam
 * um fluxo de materiais, energia ou emissões em um sistema.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Obter informações detalhadas sobre a categoria, tipo, valor, unidade e taxa de perda de um fluxo.</li>
 * </ul>
 *
 * <p><b>Objetivo:</b></p>
 * <ul>
 *   <li>Fornecer uma estrutura consistente para lidar com os fluxos em diferentes implementações.</li>
 * </ul>
 *
 * @see FlowCategory
 * @see Type
 * @see UnitType
 */
public interface FlowInterface {
    /**
     * Obtém a categoria do fluxo.
     *
     * @return A categoria do fluxo, representada por {@link FlowCategory}.
     */
    FlowCategory getCategory();

    /**
     * Obtém o nome do fluxo.
     *
     * @return Uma string representando o nome do fluxo.
     */
    String getName();

    /**
     * Obtém o tipo do fluxo (INPUT ou OUTPUT).
     *
     * @return O tipo do fluxo, representado por {@link Type}.
     */
    Type getType();

    /**
     * Obtém o valor associado ao fluxo.
     *
     * @return O valor numérico associado ao fluxo.
     */
    double getValue();

    /**
     * Obtém a unidade de medida do fluxo.
     *
     * @return A unidade de medida, representada por {@link UnitType}.
     */
    UnitType getUnit();

    /**
     * Obtém a taxa de perda do fluxo.
     *
     * <p>Esse valor representa a percentagem de perda associada ao fluxo.</p>
     *
     * @return A taxa de perda do fluxo como um valor decimal (exemplo: 0.1 para 10%).
     */
    double getLossRate();
}
