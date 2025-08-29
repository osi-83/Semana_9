package ex.com.br.semana_9.repository;

import ex.com.br.semana_9.entity.LixoEletronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LixoEletronicoRepository extends JpaRepository<LixoEletronico, Long> {

}
