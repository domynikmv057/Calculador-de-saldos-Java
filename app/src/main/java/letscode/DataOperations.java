package letscode;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@ToString

public class DataOperations {
    Map<String, ArrayList<OperacaoBancaria>> map = new HashMap<>();

    public void put(OperacaoBancaria operacao) {
        ArrayList<OperacaoBancaria> operationsList = new ArrayList<>();
        String idConta = operacao.getContaBancaria().getId();
        ArrayList<OperacaoBancaria> contaOperations = map.get(idConta);
        if (contaOperations == null) {
            operationsList.add(operacao);
            map.put(idConta, operationsList);
        } else {
            operationsList = contaOperations;
            ArrayList<OperacaoBancaria> listaOrdenada = adicionarOrdenado(operationsList, operacao);
            map.put(idConta, listaOrdenada);
        }
    }

    public ArrayList<OperacaoBancaria> getKey(String chave) {
        return map.get(chave);
    }

    public Set<String> keys() {
        return map.keySet();
    }


    public ArrayList<OperacaoBancaria> adicionarOrdenado(ArrayList<OperacaoBancaria> operacoesConta, OperacaoBancaria novaOperacao) {
        boolean checkEquals;
        ArrayList<OperacaoBancaria> listaOrdenada = new ArrayList<>();
        System.out.println("Dentro do adicionar ordenado");
        Date novaData = novaOperacao.getDataHoraOperacao();
        long novaDataEpoch = novaData.getTime();
        boolean controle = true;
        int i=0;
        System.out.println(novaOperacao);
        while (i < operacoesConta.size()) {
            Date itemData = operacoesConta.get(i).getDataHoraOperacao();
            long itemDataEpoch = itemData.getTime();
            if (controle) {
                if (novaDataEpoch == itemDataEpoch) {
                    checkEquals = operacoesConta.get(i).getOperador().equals(novaOperacao.getOperador()) && operacoesConta.get(i).getTipo().equals(novaOperacao.getTipo()) && operacoesConta.get(i).getValor() == novaOperacao.getValor();
                    if (checkEquals) {
                        i = i+1;
                    } else {
                        listaOrdenada.add(novaOperacao);
                        controle = false;
                    }
                }
                if (novaDataEpoch > itemDataEpoch) {
                    listaOrdenada.add(operacoesConta.get(i));
                    i = i+1;
                }
                if (novaDataEpoch < itemDataEpoch) {
                    listaOrdenada.add(novaOperacao);
                    controle = false;
                }
            } else {
                listaOrdenada.add(operacoesConta.get(i));
                i = i+1;
            }
        }
        if (controle) {
            listaOrdenada.add(novaOperacao);
        }
        return listaOrdenada;
    }

}
