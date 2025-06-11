package ru.bmstu.WebAppDB.dto;

public class PlayerStatsDTO {
    private Long id;
    private Long playerId;
    private int gamesPlayed;
    private int wins;
    private int points;

    public PlayerStatsDTO() {
    }

    public PlayerStatsDTO(Long id, Long playerId, int gamesPlayed, int wins, int points) {
        this.id = id;
        this.playerId = playerId;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.points = points;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
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
