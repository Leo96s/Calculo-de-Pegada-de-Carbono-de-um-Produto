package org.estg.ipp.pt.Interfaces.Models;


import org.estg.ipp.pt.Models.Flow;

/**
 * A interface {@code DataClassification} define o contrato para classes que implementam
 * métodos de classificação de dados para criar objetos {@link Flow}.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Fornece um método para interpretar dados brutos e convertê-los em instâncias de {@link Flow}.</li>
 * </ul>
 *
 * <p><b>Objetivo:</b></p>
 * <ul>
 *   <li>Facilitar a implementação de algoritmos de classificação de fluxos em diferentes contextos.</li>
 * </ul>
 *
 * @see Flow
 */
public interface DataClassification {
     /**
      * Classifica os dados fornecidos para criar um objeto {@link Flow}.
      *
      * <p>Este método recebe um array de {@code String} contendo informações estruturadas ou semi-estruturadas
      * e processa esses dados para retornar uma instância de {@link Flow}. A implementação específica depende
      * da classe que implementa esta interface.</p>
      *
      * @param data Um array de {@code String} que contém os atributos necessários para criar um {@link Flow}.
      *             <ul>
      *               <li>{@code data[1]}: Tipo do fluxo.</li>
      *               <li>{@code data[2]}: Categoria do fluxo.</li>
      *               <li>{@code data[3]}: Valor do fluxo.</li>
      *               <li>{@code data[4]}: Unidade do fluxo.</li>
      *               <li>{@code data[5]}: Quantidade.</li>
      *             </ul>
      * @return Um objeto {@link Flow} que representa o fluxo classificado, ou {@code null} caso os dados sejam inválidos.
      */
     Flow classifyFlow(String[] data);
}
