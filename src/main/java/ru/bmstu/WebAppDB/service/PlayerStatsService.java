package ru.bmstu.WebAppDB.service;

import org.springframework.stereotype.Service;
import ru.bmstu.WebAppDB.dto.PlayerStatsDTO;
import ru.bmstu.WebAppDB.entity.Player;
import ru.bmstu.WebAppDB.entity.PlayerStats;
import ru.bmstu.WebAppDB.exception.NotFoundExceptionFactory;
import ru.bmstu.WebAppDB.exception.ValidationExceptionFactory;
import ru.bmstu.WebAppDB.facade.PlayerStatsFacade;
import ru.bmstu.WebAppDB.repository.PlayerRepository;
import ru.bmstu.WebAppDB.repository.PlayerStatsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerStatsService {

    private final PlayerStatsRepository playerStatsRepository;
    private final PlayerRepository playerRepository;
    private final PlayerStatsFacade playerStatsFacade;
    private final ValidationExceptionFactory validationExceptionFactory;
    private final NotFoundExceptionFactory notFoundExceptionFactory;

    public PlayerStatsService(PlayerStatsRepository playerStatsRepository,
                              PlayerRepository playerRepository,
                              PlayerStatsFacade playerStatsFacade,
                              ValidationExceptionFactory validationExceptionFactory,
                              NotFoundExceptionFactory notFoundExceptionFactory) {
        this.playerStatsRepository = playerStatsRepository;
        this.playerRepository = playerRepository;
        this.playerStatsFacade = playerStatsFacade;
        this.validationExceptionFactory = validationExceptionFactory;
        this.notFoundExceptionFactory = notFoundExceptionFactory;
    }

    private void validateStats(PlayerStatsDTO dto) {
        if (dto.getGamesPlayed() < 0 || dto.getWins() < 0 || dto.getPoints() < 0) {
            throw validationExceptionFactory.create("stats.negative");
        }
        if (dto.getWins() > dto.getGamesPlayed()) {
            throw validationExceptionFactory.create("stats.wins_exceed");
        }
    }

    public List<PlayerStatsDTO> listAll() {
        return playerStatsRepository.findAll().stream()
                .map(playerStatsFacade::toDTO)
                .collect(Collectors.toList());
    }

    public PlayerStatsDTO getById(Long id) {
        PlayerStats stats = playerStatsRepository.findById(id)
                .orElseThrow(() -> notFoundExceptionFactory.create("playerstats"));
        return playerStatsFacade.toDTO(stats);
    }

    public PlayerStatsDTO create(PlayerStatsDTO dto) {
        validateStats(dto);
        Player player = playerRepository.findById(dto.getPlayerId())
                .orElseThrow(() -> notFoundExceptionFactory.create("player"));
        PlayerStats stats = playerStatsFacade.toEntity(dto, player);
        stats = playerStatsRepository.save(stats);
        return playerStatsFacade.toDTO(stats);
    }

    public PlayerStatsDTO update(Long id, PlayerStatsDTO dto) {
        validateStats(dto);
        PlayerStats existing = playerStatsRepository.findById(id)
                .orElseThrow(() -> notFoundExceptionFactory.create("playerstats"));

        Player player = playerRepository.findById(dto.getPlayerId())
                .orElseThrow(() -> notFoundExceptionFactory.create("player"));

        existing.setPlayer(player);
        existing.setGamesPlayed(dto.getGamesPlayed());
        existing.setWins(dto.getWins());
        existing.setPoints(dto.getPoints());

        playerStatsRepository.save(existing);
        return playerStatsFacade.toDTO(existing);
    }

    public void delete(Long id) {
        if (!playerStatsRepository.existsById(id)) {
            throw notFoundExceptionFactory.create("playerstats");
        }
        playerStatsRepository.deleteById(id);
    }
}
