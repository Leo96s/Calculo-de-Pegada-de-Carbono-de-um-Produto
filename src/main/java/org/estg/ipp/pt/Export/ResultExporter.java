package org.estg.ipp.pt.Export;

import org.estg.ipp.pt.Interfaces.Export.Export;
import org.estg.ipp.pt.Interfaces.Export.FilterOpt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A classe {@code ResultExporter} implementa a interface {@link Export} e é responsável
 * por exportar dados relacionados a valores PCF (Product Carbon Footprint) para um
 * arquivo CSV.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Exportar os valores PCF para um ficheiro CSV.</li>
 *   <li>Aplicar filtros aos dados antes da exportação, através da interface {@link FilterOpt}.</li>
 *   <li>Gerar automaticamente o diretório 'Export', caso ausente.</li>
 *   <li>Validar os valores antes de exportar.</li>
 *   <li>Limpar o conteúdo do ficheiro exportado, se necessário.</li>
 * </ul>
 *
 * <p><b>Estrutura Interna:</b></p>
 * <ul>
 *   <li>{@code FILE_PATH}: Caminho padrão onde o ficheiro CSV será guardado.</li>
 * </ul>
 *
 * <p>Esta classe fornece uma implementação para a exportação de dados,
 * com verificações de integridade e manipulação de erros de I/O.</p>
 *
 * @see Export
 */
public class ResultExporter implements Export {
    private static final String FILE_PATH = "./Export/PFC.csv";

    /**
     * Exporta os dados de resultados por processo e o valor total de PCF para um ficheiro CSV.
     *
     * @param resultPerProcess Um {@link HashMap} que contém os resultados por processo, com as chaves que representam
     *                         os nomes dos processos e os valores que representam os resultados numéricos.
     * @param result O valor total de PCF que será exportado.
     * @param filter Um filtro implementado pela interface {@link FilterOpt}, que é utilizado para ajustar
     *               os dados exportados.
     * @return {@code true} se a exportação for bem-sucedida; {@code false} caso contrário.
     * @throws IllegalArgumentException Se o valor total de PCF for negativo, o filtro for inválido,
     *                                  ou os valores no filtro forem negativos.
     */
    @Override
    public boolean exportData(HashMap<String, Double> resultPerProcess, Double result, FilterOpt filter) {
        if (result == null || result < 0) {
            throw new IllegalArgumentException("Result value must be non-negative.");
        }

        if (resultPerProcess == null) {
            return export(resultPerProcess, result);
        }

        if(filter == null){
            throw new IllegalArgumentException("Filter can't be null");
        }

        if(filter.getMinValue() < 0 || filter.getMaxValue() < 0){
            throw new IllegalArgumentException("minValue and maxValue on filter must both be non-negative.");
        }

        resultPerProcess = filter(resultPerProcess, filter);

        return export(resultPerProcess, result);
    }

    /**
     * Executa a lógica principal para exportar os dados processados para o ficheiro CSV.
     *
     * @param resultPerProcess O {@link HashMap} filtrado que contém os resultados por processo.
     * @param result O valor total de PCF.
     * @return {@code true} se a exportação foi bem-sucedida; {@code false} caso contrário.
     */
    private boolean export(HashMap<String, Double> resultPerProcess, Double result) {
        try {
            File file = new File(FILE_PATH);
            File directory = file.getParentFile();

            if (directory != null && !directory.exists() && !directory.mkdirs()) {
                throw new IOException("Failed to create directory: " + directory.getAbsolutePath());
            }

            String header = "Result";
            if (resultPerProcess != null) {
                header += header + ", Result per Process";
            }

            header += "\n";

            try (FileWriter writer = new FileWriter(file, true)) {
                if (file.length() == 0) {
                    writer.append(header);
                }

                writer.append(String.valueOf(result)).append(",");

                if (resultPerProcess != null && !resultPerProcess.isEmpty()) {
                    StringBuilder perProcessData = new StringBuilder();
                    for (String key : resultPerProcess.keySet()) {
                        perProcessData.append(key).append(": ").append(resultPerProcess.get(key)).append("; ");
                    }

                    writer.append(perProcessData.toString()).append("\n");
                }
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Filtra os dados do {@link HashMap} de resultados com base nos critérios do filtro fornecido.
     *
     * @param resultPerProcess O {@link HashMap} original que contém os resultados por processo.
     * @param filter O filtro utilizado para selecionar os dados relevantes.
     * @return Um {@link HashMap} que contém apenas os dados que correspondem aos critérios do filtro.
     */
    private HashMap<String, Double> filter(HashMap<String, Double> resultPerProcess, FilterOpt filter) {
        HashMap<String, Double> result = new HashMap<>();

        for (String key : resultPerProcess.keySet()) {
            if (((filter.getProcessName() != null && filter.getProcessName().equals(key) || filter.getProcessName() == null)
                    && filter.getMinValue() <= resultPerProcess.get(key) && filter.getMaxValue() >= resultPerProcess.get(key))) {
                result.put(key, resultPerProcess.get(key));
            }
        }
        return result;
    }

    /**
     * Método para impedir a instânciação do método {@code exportData} com parâmetros inválidos
     *
     * @param pcfValues parâmetros que não correspondem a valores pedidos no {@code exportData}
     * @return f {@code false} caso uma exceção não seja lançada
     * @throws IllegalArgumentException caso um parâmetro não seja o pedido pela função {@code exportData}
     */
    public boolean exportData(Object... pcfValues) throws IllegalArgumentException {
        if (pcfValues.length != 3) {
            throw new IllegalArgumentException("Two parameters are expected.");
        }

        if (!(pcfValues[0] instanceof HashMap<?, ?>)) {
            throw new IllegalArgumentException("First parameter must be a HashMap or a Double.");
        }

        HashMap<?, ?> map = (HashMap<?, ?>) pcfValues[0];

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!(entry.getKey() instanceof String)) {
                throw new IllegalArgumentException("All keys in the HashMap must be Strings.");
            }
            if (!(entry.getValue() instanceof Double)) {
                throw new IllegalArgumentException("All values in the HashMap must be Doubles.");
            }
        }

        if (!(pcfValues[1] instanceof Double)) {
            throw new IllegalArgumentException("Second parameter must be a Double.");
        }

        if (!(pcfValues[2] instanceof FilterOpt)) {
            throw new IllegalArgumentException("Second parameter must be a Double.");
        }

        return false;
    }

    /**
     * Limpa os dados ou redefine o ficheiro exportado.
     * <p>Este método apaga todo o conteúdo do ficheiro CSV, de forma a ser possível criar um novo.</p>
     */
    public void clear() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("");
                    System.out.println("File cleared successfully.");
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to clear the file.");
        }
    }
}
