package org.estg.ipp.pt.Models;

import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Enums.Type;
import org.estg.ipp.pt.Enums.UnitType;
import org.estg.ipp.pt.Interfaces.Models.DataClassification;

import java.util.Arrays;
import java.util.List;

/**
 * A classe {@code FlowClassifier} implementa a interface {@link DataClassification}
 * e fornece um método para classificar e criar objetos do tipo {@link Flow} com base
 * em dados fornecidos.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Interpretação de dados para determinar a categoria, tipo, unidade e valores associados a um fluxo.</li>
 *   <li>Tratamento de erros para entradas inválidas.</li>
 * </ul>
 *
 * <p><b>Estrutura Interna:</b></p>
 * <ul>
 *   <li>Método {@code classifyFlow}: Responsável por analisar os dados e devolver um objeto {@link Flow}.</li>
 * </ul>
 *
 * @see Flow
 * @see FlowCategory
 * @see Type
 * @see UnitType
 */
public class FlowClassifier implements DataClassification {

    /**
     * Classifica um fluxo com base em dados fornecidos.
     *
     * <p>Os dados são processados para determinar os atributos do fluxo, como categoria,
     * tipo, valor, unidade e outros.</p>
     *
     * <p><b>Estrutura esperada dos dados:</b></p>
     * <ol>
     *   <li>{@code data[1]}: Tipo do fluxo ({@link Type}).</li>
     *   <li>{@code data[2]}: Categoria do fluxo ({@link FlowCategory}).</li>
     *   <li>{@code data[3]}: Valor do fluxo (double).</li>
     *   <li>{@code data[4]}: Unidade do fluxo ({@link UnitType}).</li>
     *   <li>{@code data[5]}: Quantidade (double).</li>
     * </ol>
     *
     * <p>Se os dados estiverem incompletos ou incorretos, será devolvido {@code null}.</p>
     *
     * @param data Array de {@code String} contendo os atributos do fluxo.
     * @return Um objeto {@link Flow} criado a partir dos dados, ou {@code null} em caso de erro.
     */
    @Override
    public Flow classifyFlow(String[] data) {
        try {
            String[] categorySplit = data[2].split(";");

            FlowCategory category = FlowCategory.valueOf(categorySplit[0].toUpperCase());

            String name = categorySplit[0].toUpperCase();

            if (categorySplit.length >= 2) {
                name = categorySplit[1].toUpperCase();
            }

            double value = 0.0;
            if (data.length >= 4 && (data[3] != null && !data[3].trim().isEmpty())) {
                value = Double.parseDouble(data[3]);
            }

            UnitType unitType = UnitType.NONE;
            if (data.length >= 5 && (data[4] != null && !data[4].trim().isEmpty())) {
                unitType = UnitType.valueOf(data[4].toUpperCase());
            }

            double lossRate = 0.0;
            if (data.length >= 6 && (data[5] != null && !data[5].trim().isEmpty())) {
                lossRate = Double.parseDouble(data[5]);
            }

            return new Flow(
                    category,
                    name,
                    Type.valueOf(data[1].trim().toUpperCase()),
                    value,
                    unitType,
                    lossRate
            );
        } catch (Exception e) {
            return null;
        }
    }
}
