package letscode;

import lombok.AllArgsConstructor;

import java.util.Locale;
import java.util.Set;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

@AllArgsConstructor
public class Extratos {
    DataOperations dados;


    public void fazerExtratos(String pathSave) {
        Set<String> contasSet = dados.keys();
        System.out.println(contasSet);
        String[] contasArray = new String[contasSet.size()];
        contasSet.toArray(contasArray);
        for(int i=0; i<contasArray.length; i++){
            criarExtrato(pathSave, contasArray[i]);
        }
    }

    public void criarExtrato(String pathSave, String conta) {
//        String conta = contaData.getId();
        double saldo = 0.0;
        Long dateEpoch;
        String separator = System.getProperty("file.separator");
        // colocar para passar o caminho fora
        String file = pathSave+separator+conta+".txt";

        ArrayList<OperacaoBancaria> trasacoes = dados.getKey(conta);

        try {
            FileWriter arq = new FileWriter(file);
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf("Banco "+trasacoes.get(0).getContaBancaria().getBanco()+"\n");

            gravarArq.printf("Agência: ... "+trasacoes.get(0).getContaBancaria().getAgencia()+"\n");
            gravarArq.printf("Conta: ..... "+trasacoes.get(0).getContaBancaria().getConta()+"\n\n");

            gravarArq.printf("Data \t\t\t");

            gravarArq.printf("Tipo \t\t");
            gravarArq.printf("Valor \t\t");
            gravarArq.printf("Operador \n\n");
            System.out.println(trasacoes.get(0).getContaBancaria().getBanco());
            for (OperacaoBancaria item : trasacoes) {

//                gravarArq.printf(item.getDataHoraOperacao()+"\t");
                dateEpoch = item.getDataHoraOperacao().getTime();
                String pattern = "dd-MM-yy HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date(dateEpoch));
                gravarArq.printf(date+"\t");

                if (item.getTipo().equals("SAQUE")) {

                    saldo = saldo - item.getValor();
                    gravarArq.printf(item.getTipo() + "\t\t");
                    gravarArq.printf("-%.2f\t\t", item.getValor());
                }
                if (item.getTipo().equals("DEPOSITO")) {

                    saldo = saldo + item.getValor();
                    gravarArq.printf(item.getTipo() + "\t");;
                    gravarArq.printf("+%.2f\t\t", item.getValor());
                }

                gravarArq.printf(item.getOperador()+"\t\n");
            }
            gravarArq.printf("\nSaldo: ...............................\t");
            gravarArq.printf(Double.toString(saldo));
            arq.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
