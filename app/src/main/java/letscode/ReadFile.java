package letscode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ReadFile {
    private String path;
    private String name;
    private String extension;

    public DataOperations openFile() {
        System.out.println("Abrindo arquivo, aguarde...");
        String separator = System.getProperty("file.separator");
        String pathName = this.path + separator + this.name + "." + this.extension;
        DataOperations data = new DataOperations();
        try {
            File file;
            Scanner scan;
            String line;
            OperacaoBancaria operacao;
            ContaBancaria contaBancaria;
            Date dateLine;
            switch (this.extension.toLowerCase()) {
                case "csv":
                    file = new File(pathName);
                    scan = new Scanner(file);
                    SimpleDateFormat formatterDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    line = scan.nextLine();
                    while (scan.hasNextLine()){
                        line = scan.nextLine();
                        String[] parts = line.split(",");
                        dateLine=formatterDate.parse(parts[0].replace("T", " "));
                        contaBancaria = new ContaBancaria(parts[1], parts[2], parts[3], parts[4]);
//                        operacao = new OperacaoBancaria(parts[0],parts[1],Double.parseDouble(parts[2]), dateLine, parts[4]);
                        operacao = new OperacaoBancaria(parts[5], parts[6], Double.parseDouble(parts[7]), dateLine, contaBancaria);
//                        System.out.println(operacao);
                        data.put(operacao);
                    };
                    break;
                case "txt":
                    // implementar
                    break;

                default:
                    System.out.println("Tipo de arquivo não suportado");
                    // lançar erro
            }


        } catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    };


    public void closeFile() {
        // implementar fechamento do arquivo
        System.out.println("Arquivo fechado.");
    }
}
