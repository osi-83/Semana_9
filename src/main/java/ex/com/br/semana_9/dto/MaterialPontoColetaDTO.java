package ex.com.br.semana_9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialPontoColetaDTO {
    private Long id;
    private String tipoLixoEletronico;
    private Integer capacidadeMaxima;
}
