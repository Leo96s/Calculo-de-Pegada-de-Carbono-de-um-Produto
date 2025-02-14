package org.estg.ipp.pt.Enums;

/**
 * A enumeração {@code UnitType} define os diferentes tipos de unidades de medida utilizadas
 * para representar os valores associados a fluxos de processos, como energia, materiais, emissões, distância,
 * entre outros.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Representa as diferentes unidades de medida usadas para quantificar os fluxos de entradas e saídas no processo.</li>
 *   <li>Permite a categorização e conversão entre diferentes tipos de unidades durante a modelagem de processos.</li>
 * </ul>
 *
 * <p><b>Categorias de Unidades:</b></p>
 * <ul>
 *   <li>{@code KWH}: Kilowatt-hora, unidade de medida de energia.</li>
 *   <li>{@code KG}: Quilograma, unidade de medida de massa.</li>
 *   <li>{@code KGCO2EQ}: Quilograma de CO2 equivalente, usada para representar emissões de carbono.</li>
 *   <li>{@code GCO2EQKWH}: Gramas de CO2 equivalente por kilowatt-hora, usada para expressar a intensidade de emissões em relação ao consumo de energia.</li>
 *   <li>{@code KM}: Quilómetro, unidade de medida de distância.</li>
 *   <li>{@code x100}: Unidade multiplicada por 100, geralmente usada para valores representados em porcentagem.</li>
 *   <li>{@code KGCO2TONELADAKM}: Quilograma de CO2 equivalente por tonelada por quilómetro, usada para representar as emissões de transporte por unidade de carga e distância.</li>
 *   <li>{@code NONE}: Representa a ausência de uma unidade de medida aplicável.</li>
 * </ul>
 */
public enum UnitType {
    KWH, KG, KGCO2EQ, GCO2EQKWH, KM, x100, KGCO2TONELADAKM, NONE
}
