package Models;

public class MatchSettings {
    int numberQty;
    int timeInMillis;
    int maxPlayer;

    public MatchSettings(int numberQty, int timeInMillis, int maxPlayer) {
        this.numberQty = numberQty;
        this.timeInMillis = timeInMillis;
        this.maxPlayer = maxPlayer;
    }

    public int getNumberQty() {
        return numberQty;
    }

    public int getTimeInMillis() {
        return timeInMillis;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }
}
