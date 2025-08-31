package ex.com.br.semana_9.controller;

import ex.com.br.semana_9.dto.PontoDeColetaDTO;
import ex.com.br.semana_9.service.PontoColetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection-points")
public class PontoColetaController {

    private final PontoColetaService service;

    public PontoColetaController(PontoColetaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PontoDeColetaDTO> create(@RequestBody PontoDeColetaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PontoDeColetaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoDeColetaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/electronic-waste/name/{name}")
    public ResponseEntity<List<PontoDeColetaDTO>> getByLixoEletronicoName(@PathVariable String name) {
        List<PontoDeColetaDTO> pontos = service.findByLixoEletronicoName(name);
        return ResponseEntity.ok(pontos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PontoDeColetaDTO> update(@PathVariable Long id, @RequestBody PontoDeColetaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
