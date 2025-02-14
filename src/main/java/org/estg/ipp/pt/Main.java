package org.estg.ipp.pt;

import org.estg.ipp.pt.Calculo.Calculo;
import org.estg.ipp.pt.Export.FilterOptions;
import org.estg.ipp.pt.Export.ResultExporter;
import org.estg.ipp.pt.Import.Import;
import org.estg.ipp.pt.Interfaces.Calculo.Resultado;
import org.estg.ipp.pt.Interfaces.Export.Export;
import org.estg.ipp.pt.Interfaces.Import.ImportInterface;
import org.estg.ipp.pt.Interfaces.Models.ProcessInterface;
import org.estg.ipp.pt.Interfaces.Models.ProcessModeling;
import org.estg.ipp.pt.Models.Process;
import org.estg.ipp.pt.Models.ProcessManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Pedir o caminho dos arquivos
        System.out.println("Iniciando o programa...");
        System.out.print("Digite o caminho do arquivo secondaryData.csv: ");
        String secondaryDataPath = scanner.nextLine();

        System.out.print("Digite o caminho do arquivo primaryData.csv: ");
        String primaryDataPath = scanner.nextLine();

        // Importação dos dados
        ImportInterface importSecondary = new Import();
        importSecondary.ImportCSV(secondaryDataPath);
        List<String> secondaryData = importSecondary.getCsvLines();
        ImportInterface importPrimary = new Import();
        importPrimary.ImportCSV(primaryDataPath);
        List<String> primaryData = importPrimary.getCsvLines();

        // Processamento dos dados
        ProcessModeling processModeling = new ProcessManager();
        processModeling.modeling(secondaryData);
        processModeling.modeling(primaryData);
        List<ProcessInterface> processes = new ArrayList<>();
        System.out.println(processModeling.getPrimaryProcesses().size());
        System.out.println(processModeling.getSecondaryProcesses().size());
        for(Process process : processModeling.getPrimaryProcesses()){
            System.out.println("Processo: " + process.getProcessName());
            processes.add(process);
        }

        // Cálculo do PCF
        Resultado calculo = new Calculo();
        calculo.calculatePCFvalue(processes);

        System.out.println("Calculado com sucesso!");
        // Perguntar o tipo de exportação
        System.out.println("===============Escolha o tipo de exportação:=================");
        System.out.println("1 - Exportar todos os dados");
        System.out.println("2 - Exportar dados filtrados");
        System.out.println("=============================================================");
        System.out.print("Opção: ");

        int exportChoice = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (exportChoice == 1) {
            // Exportar todos os dados
            Export export = new ResultExporter();
            export.exportData(calculo.getResultadoPerProcess(), calculo.getResultado(), new FilterOptions(0, 50, null));
        } else if (exportChoice == 2) {
            // Pedir filtros para a exportação
            System.out.print("Digite o nome do processo para filtrar: ");
            String processName = scanner.nextLine();

            System.out.print("Digite o valor mínimo para o filtro: ");
            double minValue = scanner.nextDouble();

            System.out.print("Digite o valor máximo para o filtro: ");
            double maxValue = scanner.nextDouble();

            // Realizar a filtragem e exportar
            Export export = new ResultExporter();
            // Supondo que o método de exportação aceite o filtro
            export.exportData(calculo.getResultadoPerProcess(), calculo.getResultado(), new FilterOptions(minValue, maxValue, processName));
        } else {
            System.out.println("Escolha inválida. O programa será encerrado.");
        }

        scanner.close();
    }
}
