package ru.bmstu.WebAppDB.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bmstu.WebAppDB.dto.PlayerDTO;
import ru.bmstu.WebAppDB.entity.Player;
import ru.bmstu.WebAppDB.exception.NotFoundExceptionFactory;
import ru.bmstu.WebAppDB.exception.ValidationExceptionFactory;
import ru.bmstu.WebAppDB.facade.PlayerFacade;
import ru.bmstu.WebAppDB.repository.PlayerRepository;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private final PlayerRepository playerRepository;
    private final PlayerFacade playerFacade;
    private final ValidationExceptionFactory validationExceptionFactory;
    private final NotFoundExceptionFactory notFoundExceptionFactory;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public PlayerService(PlayerRepository playerRepository,
                         PlayerFacade playerFacade,
                         ValidationExceptionFactory validationExceptionFactory,
                         NotFoundExceptionFactory notFoundExceptionFactory) {
        this.playerRepository = playerRepository;
        this.playerFacade = playerFacade;
        this.validationExceptionFactory = validationExceptionFactory;
        this.notFoundExceptionFactory = notFoundExceptionFactory;
    }

    private void validatePlayer(PlayerDTO dto) {
        logger.debug("Validating player: {}", dto);
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            logger.error("Username validation failed: null or blank");
            throw validationExceptionFactory.create("username");
        }
        if (dto.getEmail() == null || !EMAIL_PATTERN.matcher(dto.getEmail()).matches()) {
            logger.error("Email validation failed: email={}", dto.getEmail());
            throw validationExceptionFactory.create("email");
        }
    }

    public List<PlayerDTO> listAll() {
        return playerRepository.findAll().stream()
                .map(playerFacade::toDTO)
                .collect(Collectors.toList());
    }

    public PlayerDTO getById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> notFoundExceptionFactory.create("player"));
        return playerFacade.toDTO(player);
    }

    public PlayerDTO create(PlayerDTO dto) {
        validatePlayer(dto);
        if (playerRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw validationExceptionFactory.create("username.exists");
        }
        Player player = playerFacade.toEntity(dto);
        player = playerRepository.save(player);
        return playerFacade.toDTO(player);
    }

    public PlayerDTO update(Long id, PlayerDTO dto) {
        validatePlayer(dto);
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> notFoundExceptionFactory.create("player"));

        if (!player.getUsername().equals(dto.getUsername()) &&
                playerRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw validationExceptionFactory.create("username.exists");
        }

        player.setUsername(dto.getUsername());
        player.setEmail(dto.getEmail());
        playerRepository.save(player);
        return playerFacade.toDTO(player);
    }

    public void delete(Long id) {
        if (!playerRepository.existsById(id)) {
            throw notFoundExceptionFactory.create("player");
        }
        playerRepository.deleteById(id);
    }
}