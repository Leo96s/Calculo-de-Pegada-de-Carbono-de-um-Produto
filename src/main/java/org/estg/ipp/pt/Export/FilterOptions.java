package org.estg.ipp.pt.Export;

import org.estg.ipp.pt.Interfaces.Export.FilterOpt;

/**
 * A classe {@code FilterOptions} implementa a interface {@link FilterOpt} e fornece as opções de filtragem
 * para a exportação de dados.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Define os valores mínimos e máximos para filtragem dos processos por valor.</li>
 *   <li>Permite filtrar por nome do processo.</li>
 * </ul>
 *
 * @see FilterOpt
 */
public class FilterOptions implements FilterOpt {
    /**
     * Valor mínimo para a filtragem.
     */
    double minValue;

    /**
     * Valor máximo para a filtragem.
     */
    double maxValue;

    /**
     * Nome do processo associado à filtragem.
     */
    String processName;

    /**
     * Construtor para inicializar os atributos {@code minValue}, {@code maxValue} e {@code processName}.
     *
     * @param minValue O valor mínimo permitido.
     * @param maxValue O valor máximo permitido.
     * @param processName O nome do processo a ser filtrado.
     */
    public FilterOptions(double minValue, double maxValue, String processName) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.processName = processName;
    }

    /**
     * Obtém o valor mínimo de filtragem.
     *
     * @return O valor mínimo.
     */
    @Override
    public double getMinValue() {
        return minValue;
    }

    /**
     * Define o valor mínimo de filtragem.
     *
     * @param minValue O valor mínimo a ser definido.
     */
    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    /**
     * Obtém o valor máximo de filtragem.
     *
     * @return O valor máximo.
     */
    @Override
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * Define o valor máximo de filtragem.
     *
     * @param maxValue O valor máximo a ser definido.
     */
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Obtém o nome do processo associado à filtragem.
     *
     * @return O nome do processo.
     */
    @Override
    public String getProcessName() {
        return processName;
    }

    /**
     * Define o nome do processo associado à filtragem.
     *
     * @param processName O nome do processo a ser definido.
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
