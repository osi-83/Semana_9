package ex.com.br.semana_9.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lixo_eletronico")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class LixoEletronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(length = 500)
    private String descricao;
}
