package cli;

import Socket.GameServer;
import Socket.Server;
import util.EncryptionHelper;

public class Main {
    static Server server;

    public static void main(String[] args) {
        EncryptionHelper encryptionHelper = new EncryptionHelper();
        encryptionHelper.getKeysFromFile();

        server = new GameServer(54321);
        server.listen();

        ConsoleCommand consoleCommand = new ConsoleCommand(server);
        consoleCommand.init();
    }
}
