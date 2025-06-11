package ru.bmstu.WebAppDB.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.WebAppDB.dto.PlayerStatsDTO;
import ru.bmstu.WebAppDB.service.PlayerStatsService;

import java.util.List;

@RestController
@RequestMapping("/api/v2/stats")
public class PlayerStatsController {

    private final PlayerStatsService statsService;

    // Ручной конструктор
    public PlayerStatsController(PlayerStatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerStatsDTO>> listAll() {
        return ResponseEntity.ok(statsService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerStatsDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(statsService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PlayerStatsDTO> create(@Valid @RequestBody PlayerStatsDTO dto) {
        return ResponseEntity.ok(statsService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerStatsDTO> update(@PathVariable Long id,
                                                 @Valid @RequestBody PlayerStatsDTO dto) {
        return ResponseEntity.ok(statsService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        statsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
