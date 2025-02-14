package org.estg.ipp.pt.Import;

import org.estg.ipp.pt.Interfaces.Import.ImportInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe {@code Import} implementa a interface {@link ImportInterface} para realizar
 * a importação e validação de dados de ficheiros CSV.
 *
 * <p>Esta classe oferece funcionalidades para a leitura de ficheiros CSV e validação de
 * formato, garantindo que os dados importados atendam aos requisitos estruturais
 * esperados pelo sistema.</p>
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Leitura de linhas de arquivos CSV</li>
 * </ul>
 *
 * <p><b>Estrutura Interna:</b></p>
 * <ul>
 *   <li>{@code csvLines}: Lista de strings que representa as linhas do arquivo CSV.</li>
 * </ul>
 *
 * @see ImportInterface
 */
public class Import implements ImportInterface {
    private final List<String> csvLines = new ArrayList<>();

    /**
     * Devolve uma cópia da lista de linhas do ficheiro CSV importado.
     *
     * @return Uma cópia da lista de strings que representa as linhas do CSV.
     */
    public List<String> getCsvLines() {
        return new ArrayList<>(csvLines);
    }

    /**
     * Realiza a importação de um ficheiro CSV a partir de um caminho especificado.
     *
     * <p>O processo inclui várias validações, como:</p>
     * <ul>
     *   <li>Verificar se o caminho é válido e não nulo.</li>
     *   <li>Garantir que o arquivo possui extensão .csv.</li>
     *   <li>Identificar se o ficheiro contém dados primários ou secundários.</li>
     *   <li>Validar o formato do CSV, incluindo o número esperado de campos por linha.</li>
     * </ul>
     *
     * @param path O caminho do ficheiro CSV a ser importado.
     * @return {@code true} se a importação for bem-sucedida; {@code false} caso contrário.
     */
    @Override
    public boolean ImportCSV(String path) {
        // Validação: Caminho não pode ser nulo ou vazio
        if (path == null || path.isEmpty()) {
            System.err.println("Path cannot be null or empty.");
            return false;
        }

        // Validação: Caminho não pode exceder 255 caracteres
        if (path.length() > 255) {
            System.err.println("Path exceeds maximum length of 255 characters.");
            return false;
        }

        // Validação: Extensão do arquivo deve ser .csv
        if (!path.endsWith(".csv")) {
            System.err.println("Invalid file extension. Only .csv files are allowed.");
            return false;
        }

        // Validação: Verificar se o nome do ficheiro é primário ou secundário
        boolean isPrimary = path.toLowerCase().contains("primary");
        boolean isSecondary = path.toLowerCase().contains("secondary");

        if (!isPrimary && !isSecondary) {
            System.err.println("File name must indicate primary or secondary data.");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            // Ignorar a primeira linha se for cabeçalho
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Remover aspas e dividir por vírgulas
                String[] fields = line.replace("\"", "").split(",");
                // Validação: Cada linha deve ter exatamente 6 campos
                if (fields.length != 6) {
                    throw new IllegalArgumentException("Invalid CSV format: Each line must have exactly 6 fields.");
                }

                // Adicionar linha válida à lista
                csvLines.add(line);
            }

            return true;

        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
            return false;

        } catch (IllegalArgumentException e) {
            System.err.println("CSV validation error: " + e.getMessage());
            return false;
        }
    }
}
