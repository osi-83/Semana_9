package ex.com.br.semana_9.service;

import ex.com.br.semana_9.dto.MaterialAceitoDTO;
import ex.com.br.semana_9.dto.PontoColetaDTO;
import ex.com.br.semana_9.entity.MaterialAceito;
import ex.com.br.semana_9.entity.PontoColeta;
import ex.com.br.semana_9.exception.ResourceNotFoundException;
import ex.com.br.semana_9.repository.PontoColetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoColetaService {

    private final PontoColetaRepository repository;

    public PontoColetaService(PontoColetaRepository repository) {
        this.repository = repository;
    }

    private PontoColetaDTO convertToDTO(PontoColeta entity) {
        List<MaterialAceitoDTO> materiaisDTO = entity.getMateriais().stream()
                .map(m -> new MaterialAceitoDTO(m.getId(), m.getNome()))
                .collect(Collectors.toList());
        return new PontoColetaDTO(entity.getId(), entity.getNome(), entity.getEndereco(), materiaisDTO);
    }

    private PontoColeta convertToEntity(PontoColetaDTO dto) {
        PontoColeta entity = new PontoColeta();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());

        List<MaterialAceito> materiais = dto.getMateriais().stream()
                .map(m -> {
                    MaterialAceito material = new MaterialAceito();
                    material.setId(m.getId());
                    material.setNome(m.getNome());
                    material.setPontoColeta(entity);
                    return material;
                })
                .collect(Collectors.toList());
        entity.setMateriais(materiais);

        return entity;
    }

    public PontoColetaDTO create(PontoColetaDTO dto) {
        PontoColeta entity = convertToEntity(dto);
        PontoColeta saved = repository.save(entity);
        return convertToDTO(saved);
    }

    public List<PontoColetaDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PontoColetaDTO findById(Long id) {
        PontoColeta entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de coleta não encontrado com id " + id));
        return convertToDTO(entity);
    }

    public PontoColetaDTO update(Long id, PontoColetaDTO dto) {
        PontoColeta entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de coleta não encontrado com id " + id));

        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());

        // Atualiza materiais (remove os antigos e adiciona os novos)
        entity.getMateriais().clear();
        List<MaterialAceito> materiais = dto.getMateriais().stream()
                .map(m -> {
                    MaterialAceito material = new MaterialAceito();
                    material.setId(m.getId());
                    material.setNome(m.getNome());
                    material.setPontoColeta(entity);
                    return material;
                })
                .collect(Collectors.toList());
        entity.getMateriais().addAll(materiais);

        PontoColeta updated = repository.save(entity);
        return convertToDTO(updated);
    }

    public void delete(Long id) {
        PontoColeta entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de coleta não encontrado com id " + id));
        repository.delete(entity);
    }
}