package org.estg.ipp.pt.Enums;

/**
 * A enumeração {@code Type} define os dois tipos principais de fluxos que podem ser associados
 * aos processos durante a modelagem: entrada e saída.
 * Esta enumeração é fundamental para classificar os fluxos no contexto da gestão de processos,
 * de forma entre os fluxos de dados que entram no processo e os que saem dele.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Classificação de fluxos como entrada ou saída do processo.</li>
 *   <li>Permite a organização e o tratamento adequado dos fluxos durante a modelagem de processos.</li>
 * </ul>
 *
 * <p><b>Categorias:</b></p>
 * <ul>
 *   <li>{@code INPUT}: Representa os fluxos que entram no processo, como materiais, energia.</li>
 *   <li>{@code OUTPUT}: Representa os fluxos que saem do processo, como produtos acabados, emissões ou desperdícios.</li>
 * </ul>
 *
 * @see org.estg.ipp.pt.Models.Flow
 * @see org.estg.ipp.pt.Models.Process
 */
public enum Type {
    INPUT, OUTPUT
}
