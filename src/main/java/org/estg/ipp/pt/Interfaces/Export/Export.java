package org.estg.ipp.pt.Interfaces.Export;

import java.util.HashMap;

/**
 * A interface {@code Export} define o contrato para a exportação de dados,
 * valores especialmente relacionados ao PCF (Product Carbon Footprint).
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Exportar um valor de PCF para um destino específico, como ficheiros CSV, entre outros.</li>
 * </ul>
 *
 * <p>Essa interface pode ser implementada por classes que desejam fornecer
 * diferentes formas de exportação de dados.</p>
 *
 * @see org.estg.ipp.pt.Export.ResultExporter
 */
public interface Export {

    /**
     * Exporta os dados relacionados a processos específicos e o valor total do resultado do cálculo PCF.
     *
     * @param resultPerProcess Um {@link HashMap} que contém os resultados por processo, onde as chaves são os nomes dos processos
     *                         e os valores são os respetivos resultados numéricos.
     * @param result O valor total do PCF (Product Carbon Footprint) que será exportado.
     * @param filterOpt Um filtro opcional, implementado pela interface {@link FilterOpt}, para ajustar os dados exportados.
     * @return {@code true} se a exportação for bem-sucedida; {@code false} caso contrário.
     */
    boolean exportData(HashMap<String, Double> resultPerProcess, Double result, FilterOpt filterOpt);

    /**
     * Limpa os dados ou redefine estados internos relacionados ao processo de exportação.
     * <p>Esse método pode ser usado para preparar a instância para uma nova exportação, garantindo que nenhum estado anterior
     * afete as operações futuras. Por exemplo, limpar o ficheiro de export.</p>
     */
    void clear();
}
