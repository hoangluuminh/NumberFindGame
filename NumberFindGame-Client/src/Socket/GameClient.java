package Socket;

import Run.GameMain;
import bus.GameClientBUS;
import dto.GameRoom_Client;
import dto.MatchPlayer;

public class GameClient extends Client {
    private GameClientBUS gameClientBUS;
    private MatchPlayer clientPlayer;
    private GameRoom_Client gameRoom;

    public GameClient() {
        this.gameClientBUS = new GameClientBUS(this);
    }

    // Overrides

    @Override
    public void close() {
        super.close();
        this.clientPlayer = null;
        this.gameRoom = null;
    }

    // Properties


    public GameClientBUS getGameClientBUS() {
        return gameClientBUS;
    }

    public MatchPlayer getClientPlayer() {
        return clientPlayer;
    }
    public void setClientPlayer(MatchPlayer clientPlayer) {
        this.clientPlayer = clientPlayer;
    }

    public GameRoom_Client getGameRoom() {
        return gameRoom;
    }
    public void setGameRoom(GameRoom_Client gameRoom) {
        this.gameRoom = gameRoom;
    }
}
