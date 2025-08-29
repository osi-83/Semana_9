package ex.com.br.semana_9.service;

import ex.com.br.semana_9.dto.LixoEletronicoDTO;
import ex.com.br.semana_9.entity.LixoEletronico;
import ex.com.br.semana_9.exception.ResourceNotFoundException;
import ex.com.br.semana_9.repository.LixoEletronicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LixoEletronicoService {

    private final LixoEletronicoRepository repository;

    public LixoEletronicoService(LixoEletronicoRepository repository) {
        this.repository = repository;
    }

    public LixoEletronicoDTO create(LixoEletronicoDTO dto) {
        LixoEletronico entity = new LixoEletronico();
        entity.setTipo(dto.getTipo());
        entity.setDescricao(dto.getDescricao());
        LixoEletronico saved = repository.save(entity);
        return new LixoEletronicoDTO(saved.getId(), saved.getTipo(), saved.getDescricao());
    }

    public List<LixoEletronicoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(l -> new LixoEletronicoDTO(l.getId(), l.getTipo(), l.getDescricao()))
                .collect(Collectors.toList());
    }

    public LixoEletronicoDTO findById(Long id) {
        LixoEletronico entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lixo eletrônico não encontrado com id " + id));
        return new LixoEletronicoDTO(entity.getId(), entity.getTipo(), entity.getDescricao());
    }

    public LixoEletronicoDTO update(Long id, LixoEletronicoDTO dto) {
        LixoEletronico entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lixo eletrônico não encontrado com id " + id));
        entity.setTipo(dto.getTipo());
        entity.setDescricao(dto.getDescricao());
        LixoEletronico updated = repository.save(entity);
        return new LixoEletronicoDTO(updated.getId(), updated.getTipo(), updated.getDescricao());
    }

    public void delete(Long id) {
        LixoEletronico entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lixo eletrônico não encontrado com id " + id));
        repository.delete(entity);
    }
}
