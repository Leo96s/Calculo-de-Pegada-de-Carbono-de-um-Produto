package org.estg.ipp.pt.Interfaces.Calculo;

import org.estg.ipp.pt.Interfaces.Models.ProcessInterface;
import org.estg.ipp.pt.Models.*;

import java.util.HashMap;
import java.util.List;

/**
 * A interface {@code Resultado} define o contrato para o cálculo do valor PCF (Product Carbon Footprint)
 * a partir de uma lista de processos, além de herdar funcionalidades para o cálculo de impacto.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Calcular o valor do PCF com base em uma lista de processos modelados.</li>
 *   <li>Capacidades adicionais de cálculo do impacto através de {@link CalculateImpact}.</li>
 * </ul>
 *
 * <p>Esta interface pode ser implementada por classes que realizam cálculos específicos
 * relacionados ao impacto ambiental e à pegada de carbono.</p>
 *
 * @see CalculateImpact
 * @see ProcessInterface
 */
public interface Resultado extends CalculateImpact {
    /**
     * Calcula o valor do PCF (Product Carbon Footprint) com base nos processos fornecidos.
     *
     * @param processes Uma lista de objetos {@link ProcessInterface} que representam os processos a serem considerados no cálculo.
     * @return O valor calculado do PCF como um número double.
     * @throws IllegalArgumentException Se a lista de processos for nula ou vazia.
     */
    double calculatePCFvalue(List<ProcessInterface> processes);

    /**
     * Devolve o valor total calculado do resultado do impacto ambiental.
     *
     * @return O resultado agregado como um valor {@code double}.
     */
    double getResultado();

    /**
     * Devolve um mapeamento detalhado do impacto ambiental por processo.
     *
     * <p>A chave do mapa é o identificador único ou o nome de cada processo,
     * enquanto o valor é o impacto correspondente como um {@code double}.</p>
     *
     * @return Um {@code HashMap<String, Double>} que contém o impacto por processo.
     */
    HashMap<String,Double> getResultadoPerProcess();

}
