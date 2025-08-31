package ex.com.br.semana_9.repository;

import ex.com.br.semana_9.entity.PontoDeColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoDeColeta, Long> {

    @Query(value = """
                SELECT DISTINCT p
                FROM PontoDeColeta p
                JOIN p.materiaisAceitos m
                JOIN m.lixoEletronico le
                WHERE LOWER(le.tipo) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<PontoDeColeta> findByLixoEletronicoName(@Param("name") String name);
}