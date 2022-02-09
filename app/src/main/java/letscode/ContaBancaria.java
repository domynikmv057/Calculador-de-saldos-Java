package letscode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ContaBancaria {
    private String id;
    private String banco;
    private String agencia;
    private String conta;
}
