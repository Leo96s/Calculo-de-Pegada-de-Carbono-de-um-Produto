package org.estg.ipp.pt.Enums;

/**
 * A enumeração {@code FlowCategory} define as categorias possíveis de fluxos que podem
 * ser associados aos processos. Cada categoria representa um tipo específico de fluxo
 * que pode ser classificado durante a modelagem de processos, como o desperdício, a energia,
 * o material, o transporte ou a emissão.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Classificação dos fluxos com base no tipo: desperdício, energia, material, transporte ou emissão.</li>
 *   <li>Utilização desta enumeração para categorizar fluxos dentro dos processos modelados.</li>
 * </ul>
 *
 * <p><b>Categorias:</b></p>
 * <ul>
 *   <li>{@code WASTE}: Representa os fluxos de desperdício ou resíduo gerado durante o processo.</li>
 *   <li>{@code ENERGY}: Representa os fluxos de energia, seja como entrada ou saída do processo.</li>
 *   <li>{@code MATERIAL}: Representa os fluxos de material utilizado ou produzido pelo processo.</li>
 *   <li>{@code TRANSPORT}: Representa os fluxos associados ao transporte de materiais ou produtos durante o processo.</li>
 *   <li>{@code EMISSION}: Representa os fluxos de emissão de gases ou substâncias nocivas geradas durante o processo.</li>
 * </ul>
 *
 * @see Process
 * @see org.estg.ipp.pt.Models.Flow
 */
public enum FlowCategory {
    WASTE, ENERGY, MATERIAL, TRANSPORT, EMISSION
}
