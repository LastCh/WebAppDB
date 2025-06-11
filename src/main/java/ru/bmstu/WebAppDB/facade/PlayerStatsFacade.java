package ru.bmstu.WebAppDB.facade;

import org.springframework.stereotype.Component;
import ru.bmstu.WebAppDB.dto.PlayerStatsDTO;
import ru.bmstu.WebAppDB.entity.Player;
import ru.bmstu.WebAppDB.entity.PlayerStats;

@Component
public class PlayerStatsFacade {

    public PlayerStatsDTO toDTO(PlayerStats stats) {
        if (stats == null) return null;
        return new PlayerStatsDTO(
                stats.getId(),
                stats.getPlayer().getId(),
                stats.getGamesPlayed(),
                stats.getWins(),
                stats.getPoints()
        );
    }

    public PlayerStats toEntity(PlayerStatsDTO dto, Player player) {
        if (dto == null) return null;
        PlayerStats stats = new PlayerStats();
        stats.setId(dto.getId());
        stats.setPlayer(player);
        stats.setGamesPlayed(dto.getGamesPlayed());
        stats.setWins(dto.getWins());
        stats.setPoints(dto.getPoints());
        return stats;
    }
}
