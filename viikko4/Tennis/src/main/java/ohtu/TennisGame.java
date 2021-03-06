package ohtu;

public class TennisGame {
    private static final int LOVE = 0;
    private static final int FIFTEEN = 1;
    private static final int THIRTY = 2;
    private static final int FORTY = 3;
    
    private int scorePlayer1;
    private int scorePlayer2;
    private final String namePlayer1;
    private final String namePlayer2;

    public TennisGame(String player1, String player2) {
        namePlayer1 = player1;
        namePlayer2 = player2;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(namePlayer1))
            scorePlayer1 += 1;
        else
            scorePlayer2 += 1;
    }
    
    public String getScore() {
        if (scorePlayer1 == scorePlayer2) {
            return calculateTempScore(scorePlayer1) + allOrNot(scorePlayer1);
        } else if (scorePlayer1 > FORTY || scorePlayer2 > FORTY) {
            return advantageOrWin();
        }
        return calculateTempScore(scorePlayer1) + "-" + calculateTempScore(scorePlayer2);
    }
    
    private String calculateTempScore(int score) {
        switch(score) {
            case LOVE:
                return "Love";
            case FIFTEEN:
                return "Fifteen";
            case THIRTY:
                return "Thirty";
            case FORTY:
                return "Forty";
        }
        return "Deuce";
    }
    
    private String allOrNot(int score) {
        if (score <= FORTY) return "-All";
        return "";
    }
    
    private String advantageOrWin() {
        if (scorePlayer1 - scorePlayer2 == 1) {
            return "Advantage player1";
        }
        else if (scorePlayer1 - scorePlayer2 == -1) {
            return "Advantage player2";
        }
        else if (scorePlayer1 - scorePlayer2 >= 2) {
            return "Win for " + namePlayer1;
        }
        return "Win for " + namePlayer2;
    }
}