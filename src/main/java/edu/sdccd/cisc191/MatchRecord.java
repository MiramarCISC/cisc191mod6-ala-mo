package edu.sdccd.cisc191;

public record MatchRecord(
        String arenaName,
        PlayerAccount playerOne,
        PlayerAccount playerTwo,
        String winnerUsername
) {
    public String summary() {
        return playerOne.username() + " vs " + playerTwo.username() + " @ " + arenaName;
    }
}