package ru.bmstu.WebAppDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bmstu.WebAppDB.entity.PlayerStats;

import java.util.Optional;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {
    Optional<PlayerStats> findByPlayerId(Long playerId);
}