package ex.com.br.semana_9.entity;

import jakarta.persistence.*;

public class MaterialPontoColeta {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ponto_coleta_id", nullable = false)
    private PontoDeColeta pontoDeColeta;

    @ManyToOne
    @JoinColumn(name = "lixo_eletronico_id", nullable = false)
    private LixoEletronico lixoEletronico;

    @Column(name = "capacidade_maxima", nullable = false)
    private Integer capacidadeMaxima;
}
