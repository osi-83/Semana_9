package ex.com.br.semana_9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontoColetaDTO {
    private Long id;
    private String nome;
    private String endereco;
    private List<MaterialAceitoDTO> materiais;
}
