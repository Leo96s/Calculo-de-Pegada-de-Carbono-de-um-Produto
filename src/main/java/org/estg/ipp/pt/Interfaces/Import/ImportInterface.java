package org.estg.ipp.pt.Interfaces.Import;

import java.util.List;

/**
 * A interface {@code ImportInterface} define os métodos necessários para a importação de dados a partir
 * de ficheiros CSV.
 * Ela é responsável por fornecer uma abstração para a leitura e processamento de arquivos CSV
 * que contém os dados que podem ser utilizados em processos de modelagem, análise e outros fins.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Leitura de linhas de dados em formato CSV.</li>
 * </ul>
 *
 * <p><b>Estrutura Interna:</b></p>
 * <ul>
 *   <li>{@code getCsvLines()}: Método responsável por devolver as linhas do CSV como uma lista de strings.</li>
 *   <li>{@code ImportCSV()}: Método que realiza a importação dos dados a partir de um arquivo CSV específico.</li>
 * </ul>
 *
 * @see org.estg.ipp.pt.Models.ProcessManager
 * @see org.estg.ipp.pt.Models.Process
 */
public interface ImportInterface {

    /**
     * Retorna as linhas de um ficheiro CSV como uma lista de strings.
     *
     * <p>Este método permite aceder às linhas do ficheiro CSV que foram importadas. Cada linha do CSV
     * é representada por uma string, com as colunas separadas por delimitadores (vírgulas).</p>
     *
     * @return Uma lista de strings que representa as linhas do arquivo CSV.
     */
    List<String> getCsvLines();

    /**
     * Importa um ficheiro CSV a partir do caminho especificado.
     *
     * <p>Este método é responsável por abrir o arquivo CSV localizado no caminho especificado, ler o conteúdo
     * do ficheiro e processá-lo para ser utilizado dentro do sistema. Ele pode incluir verificações de formato
     * e validações para garantir que os dados estão no formato adequado.</p>
     *
     * @param path O caminho do ficheiro CSV a ser importado.
     * @return {@code true} se a importação for bem-sucedida; {@code false} caso contrário.
     */
    boolean ImportCSV(String path);
}
