package letscode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OperacaoBancaria {
    private String operador;
    private String tipo;
    private double valor;
    private Date dataHoraOperacao;
//    private String dataHoraOperacao; //tipo string para teste
//    private ContaBancaria contaBancaria;
//    private String contaBancaria; //tipo string para teste
    private ContaBancaria contaBancaria;

}
