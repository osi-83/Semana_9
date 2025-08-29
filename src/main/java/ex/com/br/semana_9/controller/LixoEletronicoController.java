package ex.com.br.semana_9.controller;

import ex.com.br.semana_9.dto.LixoEletronicoDTO;
import ex.com.br.semana_9.service.LixoEletronicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eletronic-waste")
public class LixoEletronicoController {

    private final LixoEletronicoService service;

    public LixoEletronicoController(LixoEletronicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LixoEletronicoDTO> create(@RequestBody LixoEletronicoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<LixoEletronicoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LixoEletronicoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LixoEletronicoDTO> update(@PathVariable Long id, @RequestBody LixoEletronicoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

