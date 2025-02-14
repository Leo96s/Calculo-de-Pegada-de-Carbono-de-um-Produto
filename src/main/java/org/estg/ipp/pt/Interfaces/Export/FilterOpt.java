package org.estg.ipp.pt.Interfaces.Export;

/**
 * A interface {@code FilterOpt} define a estrutura para a implementação de filtros
 * baseados em valores mínimos, máximos e nomes de processos.
 *
 * <p><b>Propósito:</b></p>
 * <ul>
 *   <li>Fornecer uma especificação para classes que implementam as opções de filtragem.</li>
 *   <li>Garantir que os filtros tenham um valor mínimo, máximo e um nome de processo associado.</li>
 * </ul>
 *
 * <p><b>Métodos:</b></p>
 * <ul>
 *   <li>{@link #getMinValue()} - Obtém o valor mínimo de filtragem.</li>
 *   <li>{@link #getMaxValue()} - Obtém o valor máximo de filtragem.</li>
 *   <li>{@link #getProcessName()} - Obtém o nome do processo associado ao filtro.</li>
 * </ul>
 */
public interface FilterOpt {

    /**
     * Obtém o valor mínimo para filtragem.
     *
     * @return O valor mínimo.
     */
    double getMinValue();

    /**
     * Obtém o valor máximo para filtragem.
     *
     * @return O valor máximo.
     */
    double getMaxValue();

    /**
     * Obtém o nome do processo associado à filtragem.
     *
     * @return O nome do processo.
     */
    String getProcessName();
}
