package ru.bmstu.WebAppDB.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "player_stats", schema = "my_schema")
public class PlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stats_seq")
    @SequenceGenerator(name = "stats_seq", sequenceName = "stats_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player_id", nullable = false, unique = true)
    private Player player;

    private int gamesPlayed;
    private int wins;
    private int points;

    public PlayerStats() {}

    public PlayerStats(Long id, Player player, int gamesPlayed, int wins, int points) {
        this.id = id;
        this.player = player;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
