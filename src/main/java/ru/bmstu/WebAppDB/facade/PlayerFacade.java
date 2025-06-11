package ru.bmstu.WebAppDB.facade;

import org.springframework.stereotype.Component;
import ru.bmstu.WebAppDB.dto.PlayerDTO;
import ru.bmstu.WebAppDB.entity.Player;

@Component
public class PlayerFacade {

    public PlayerDTO toDTO(Player player) {
        if (player == null) return null;
        return new PlayerDTO(player.getId(), player.getUsername(), player.getEmail());
    }

    public Player toEntity(PlayerDTO dto) {
        if (dto == null) return null;
        return new Player(dto.getId(), dto.getUsername(), dto.getEmail());
    }
}