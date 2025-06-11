package ru.bmstu.WebAppDB.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.bmstu.WebAppDB.entity.Player;
import ru.bmstu.WebAppDB.entity.PlayerStats;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class PlayerStatsRepositoryTest {

    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testSaveAndFindById() {
        Player player = new Player(null, "statsuser", "stats@example.com");
        player = playerRepository.save(player);

        PlayerStats stats = new PlayerStats(null, player, 10, 5, 100);
        PlayerStats saved = playerStatsRepository.save(stats);

        assertNotNull(saved.getId());
        Optional<PlayerStats> found = playerStatsRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(10, found.get().getGamesPlayed());
    }

    @Test
    public void testFindByPlayerId() {
        Player player = new Player(null, "statsuser2", "stats2@example.com");
        player = playerRepository.save(player);

        PlayerStats stats = new PlayerStats(null, player, 20, 10, 200);
        playerStatsRepository.save(stats);

        Optional<PlayerStats> found = playerStatsRepository.findByPlayerId(player.getId());
        assertTrue(found.isPresent());
        assertEquals(20, found.get().getGamesPlayed());
    }


}