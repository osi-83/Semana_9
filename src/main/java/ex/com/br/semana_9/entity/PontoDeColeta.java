package ex.com.br.semana_9.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ponto_coleta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontoDeColeta {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(name = "dia_coleta", nullable = false)
    private String diaDeColeta;

    @OneToMany(mappedBy = "pontoDeColeta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialPontoColeta> materiaisAceitos;


}
