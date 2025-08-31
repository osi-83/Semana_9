package ex.com.br.semana_9.service;

import ex.com.br.semana_9.dto.MaterialAceitoDTO;
import ex.com.br.semana_9.dto.MaterialPontoColetaDTO;
import ex.com.br.semana_9.dto.PontoDeColetaDTO;
import ex.com.br.semana_9.entity.LixoEletronico;
import ex.com.br.semana_9.entity.MaterialPontoColeta;
import ex.com.br.semana_9.entity.PontoDeColeta;
import ex.com.br.semana_9.exception.ResourceNotFoundException;
import ex.com.br.semana_9.repository.PontoColetaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoColetaService {

    private final PontoColetaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public PontoColetaService(PontoColetaRepository repository) {
        this.repository = repository;
    }

    private PontoDeColetaDTO convertToDTO(PontoDeColeta entity) {
        List<MaterialPontoColetaDTO> materiaisDTO = entity.getMateriaisAceitos().stream()
                .map(m -> new MaterialPontoColetaDTO(
                        m.getId(),
                        m.getLixoEletronico().getTipo(),
                        m.getCapacidadeMaxima()
                ))
                .collect(Collectors.toList());

        return new PontoDeColetaDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getDiaDeColeta(),
                materiaisDTO
        );
    }

    public PontoDeColetaDTO create(PontoDeColetaDTO dto) {
        PontoDeColeta entity = new PontoDeColeta();
        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());
        entity.setDiaDeColeta(dto.getDiaDeColeta());

        List<MaterialPontoColeta> materiais = dto.getMateriais().stream().map(m -> {
            LixoEletronico lixo = entityManager.find(LixoEletronico.class, m.getId());
            MaterialPontoColeta material = new MaterialPontoColeta();
            material.setCapacidadeMaxima(m.getCapacidadeMaxima());
            return material;
        }).collect(Collectors.toList());

        entity.setMateriaisAceitos(materiais);

        PontoDeColeta saved = repository.save(entity);
        return convertToDTO(saved);
    }

    public List<PontoDeColetaDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PontoDeColetaDTO> findByLixoEletronicoName(String name) {
        return repository.findByLixoEletronicoName(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PontoDeColetaDTO findById(Long id) {
        PontoDeColeta entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de coleta não encontrado com id " + id));
        return convertToDTO(entity);
    }

    public void delete(Long id) {
        PontoDeColeta entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de coleta não encontrado com id " + id));
        repository.delete(entity);
    }

    public PontoDeColetaDTO update(
            Long id,
            PontoDeColetaDTO dto) {
    }
}

