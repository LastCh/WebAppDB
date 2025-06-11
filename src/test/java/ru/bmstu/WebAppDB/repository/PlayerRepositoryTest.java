package ru.bmstu.WebAppDB.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.bmstu.WebAppDB.entity.Player;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testSaveAndFindById() {
        Player player = new Player(null, "testuser", "test@example.com");
        Player saved = playerRepository.save(player);
        assertNotNull(saved.getId());
        Optional<Player> found = playerRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("testuser", found.get().getUsername());
    }

    @Test
    public void testFindByUsername() {
        Player player = new Player(null, "uniqueuser", "unique@example.com");
        playerRepository.save(player);
        Optional<Player> found = playerRepository.findByUsername("uniqueuser");
        assertTrue(found.isPresent());
        assertEquals("uniqueuser", found.get().getUsername());
    }

}