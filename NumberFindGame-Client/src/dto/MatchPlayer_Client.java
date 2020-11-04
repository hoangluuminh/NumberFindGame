package dto;

import java.awt.*;
import java.util.ArrayList;

public class MatchPlayer_Client extends MatchPlayer {
    Color uiColor;

    public MatchPlayer_Client(PlayerDTO player) {
        super(player);
    }

    public MatchPlayer_Client(MatchPlayer matchPlayer) {
        super(matchPlayer);
    }

    // Overrides

    @Override
    public String toString() {
        return String.format("%s: %s", getPlayer().getFirstName(), getScore());
    }

    // Functions

    /**
     * Cast danh sách MatchPlayer[] chứa các MatchPlayer_Client về MatchPlayer_Client[]
     * @param matchPlayers PHẢI ĐẢM BẢO là MatchPlayer_Client
     * @return danh sách MatchPlayer_Client[]
     */
    public static ArrayList<MatchPlayer_Client> castToMatchPlayerClients(ArrayList<MatchPlayer> matchPlayers) {
        ArrayList<MatchPlayer_Client> matchPlayerClients = new ArrayList<MatchPlayer_Client>();
        for (MatchPlayer lN : matchPlayers) {
            matchPlayerClients.add((MatchPlayer_Client) lN);
        }

        return matchPlayerClients;
    }

    /**
     * Khác với cast, ép về MatchPlayer[chứa MatchPlayer_Client] để có thể gán setMatchPlayers()
     * Dùng để biến đổi danh sách MatchPlayer[] nhận được từ Server để có thể thực hiện các thao tác Client
     * @param _matchPlayers Danh sách MatchPlayer[] lấy từ Server
     * @return Danh sách MatchPlayer[] chứa các MatchPlayer_Client
     */
    public static ArrayList<MatchPlayer> convertMatchPlayersToMatchPlayerClients(ArrayList<MatchPlayer> _matchPlayers) {
        ArrayList<MatchPlayer> matchPlayers = new ArrayList<MatchPlayer>();

        for (MatchPlayer matchPlayer : _matchPlayers) {
            MatchPlayer_Client matchPlayerClient = new MatchPlayer_Client(matchPlayer);
            matchPlayers.add(matchPlayerClient);
        }

        return matchPlayers;
    }

    /**
     * Gộp dữ liệu MatchPlayer[] mới và dữ liệu MatchPlayer_Client[] hiện tại
     * @param _matchPlayers MatchPlayer[] lấy từ Server
     * @param matchPlayers_OG MatchPlayer_Client[] lúc bấy giờ của Client (có các dữ liệu về UI)
     * @return MatchPlayer[] đã gộp dữ liệu
     */
    public static ArrayList<MatchPlayer> mergeMatchPlayersAndMatchPlayerClients(ArrayList<MatchPlayer> _matchPlayers, ArrayList<MatchPlayer_Client> matchPlayers_OG) {
        ArrayList<MatchPlayer> matchPlayers = convertMatchPlayersToMatchPlayerClients(_matchPlayers);
        for (int i = 0; i < matchPlayers.size(); i++) {
            MatchPlayer matchPlayer = matchPlayers.get(i);
            MatchPlayer matchPlayer_OG = matchPlayers_OG.get(i);
            ((MatchPlayer_Client) matchPlayer).setUiColor(
                    ((MatchPlayer_Client) matchPlayer_OG).getUiColor()
            );
        }
        return matchPlayers;
    }

    // Properties

    public Color getUiColor() {
        return uiColor;
    }

    public void setUiColor(Color uiColor) {
        this.uiColor = uiColor;
    }
}