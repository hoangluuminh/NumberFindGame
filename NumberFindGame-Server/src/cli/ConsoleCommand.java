package cli;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Stream;

import Socket.ClientHandler;
import Socket.Server;
import bus.PlayerBUS;
import dto.MatchPlayer;
import dto.MatchConfig;
import dto.PlayerDTO;
import util.Display;
import util.JsonHelper;
import util.NumberValidException;

public class ConsoleCommand {

	// TODO Auto-generated method stub
	private Server server; // PARENT
	static Scanner scan = new Scanner(System.in);

	Display tableDisplay = new Display();

	private String command;
	private String option;
	private String value;

	/**
	 * @param server : the server to set
	 */
	public ConsoleCommand(Server server) {
		this.server = server;
	}

	/**
	 * 
	 * @param inputCommand: the input string from user
	 */
	public void handleCommand(String inputCommand) {
		this.option = "";
		this.value = "";
		String s[] = inputCommand.trim().split(" ");
		// TODO check null
		this.command = s[0];
		if (s.length >= 2) {
			option = s[1];
		}
		if (s.length >= 3) {
			value = s[2];
		}
	}

	/**
	 * Initiate server cli
	 * 
	 * @throws NumberValidException
	 */
	public void init() throws NumberValidException {
		boolean isCommanding = true;
		showCommand("command");
		do {
			System.out.print("\nInput: ");
			String inputCommand = scan.nextLine();
			handleCommand(inputCommand);
			switch (command) {
			case "list": {
				list();
				break;
			}
			case "config": {
				config();
				break;
			}
			case "exit": {
				server.halt();
				isCommanding = false;
				break;
			}
			case "command": {
				showCommand("command");
				break;
			}
			default: {
				System.out.println("Command not found");
				showCommand("command");
				break;
			}
			}
		} while (isCommanding);
	}

	/**
	 * Method for 'list' commands
	 */
	public void list() {
		switch (option) {
		case "user": {
			getUsers();
			break;
		}
		case "online": {
			getOnlineUsers();
			break;
		}
		default: {
			System.out.println("Command not found");
			showCommand("list");
			break;
		}
		}
	}

	/**
	 * Method for 'config' commands
	 */
	public void config() {
		MatchConfig matchConfig = new MatchConfig();
		JsonHelper jsonHelper = new JsonHelper();
		matchConfig = jsonHelper.readConfig();
		int val = 0;

		if (value.equals("")) {
//			if (option.equals("")) {
//				option =" ";
//			}
			if (!option.equals("show") && !option.equals("")) {
				System.out.println("Error. Value can not be empty");
				// option = "";
				return;
			}

		} else {
			try {
				val = Integer.parseInt(value);
				// System.out.print(n);

			} catch (NumberFormatException e) {
				System.out.println("Error. Value must be integer");
				// option = "";
				return;
			}
		}

		switch (option) {
		case "numberQty": {
			numberQtyConfig(val);
			break;
		}
		case "time": {
			timeConfig(val);
			break;
		}
		case "maxPlayer": {
			maxPlayerConfig(val);
			break;
		}
		case "show": {
			readConfig();
			break;
		}
		default: {
			System.out.println("Command not found");
			showCommand("config");
			break;
		}
		}
	}

	/**
	 * Config number quantity
	 * 
	 * @param value: the value to set number quantity
	 * @throws NumberValidException
	 */
	public void numberQtyConfig(int value) {
		MatchConfig matchConfig = new MatchConfig();
		JsonHelper jsonHelper = new JsonHelper();
		matchConfig = jsonHelper.readConfig();

		if (value < 1 || value > 900) {
			System.out.println("Error");
			System.out.println("Value must be in range [1,900]");
			return;
		}
		matchConfig.setNumberQty(value);
		jsonHelper.saveConfig(matchConfig);
		System.out.println("\nSuccessfully change game's number quantity\n");
		readConfig();
	}

	/**
	 * Config time
	 * 
	 * @param value: the value to set time
	 * @throws NumberValidException
	 */
	public void timeConfig(int value) {
		MatchConfig matchConfig = new MatchConfig();
		JsonHelper jsonHelper = new JsonHelper();
		matchConfig = jsonHelper.readConfig();

		if (value < 1000 || value > 3600000) {
			System.out.println("Error");
			System.out.println("Value must be in range [1000,3600000]");
			return;
		}
		matchConfig.setTime(value);
		jsonHelper.saveConfig(matchConfig);
		System.out.println("\nSuccessfully change game's time\n");
		readConfig();
	}

	/**
	 * Config max player
	 * 
	 * @param value: the value to set max player
	 * @throws NumberValidException
	 */
	public void maxPlayerConfig(int value) {
		MatchConfig matchConfig = new MatchConfig();
		JsonHelper jsonHelper = new JsonHelper();
		matchConfig = jsonHelper.readConfig();

		if (value < 2 || value > 8) {
			System.out.println("Error");
			System.out.println("Value must be in range [2,8]");
			return;
		}
		matchConfig.setMaxPlayer(value);
		jsonHelper.saveConfig(matchConfig);
		System.out.println("\nSuccessfully change game's max player\n");
		readConfig();
	}

	/**
	 * Get file config
	 */
	public void readConfig() {
		MatchConfig matchConfig = new MatchConfig();
		JsonHelper jsonHelper = new JsonHelper();
		matchConfig = jsonHelper.readConfig();

		System.out.printf("numberQty  : %s\n", matchConfig.getNumberQty());
		System.out.printf("time       : %,d miliseconds\n", matchConfig.getTime());
		System.out.printf("maxPlayer  : %s player\n", matchConfig.getMaxPlayer());
	}

	/**
	 * List users
	 */
	public void getUsers() {
		PlayerBUS playerBus = new PlayerBUS();
		ArrayList<PlayerDTO> players = new ArrayList<PlayerDTO>();
		players = playerBus.getAll();
		if (players.isEmpty()) {
			System.out.println("No user");
			return;
		}

		String[] table = new String[] { "Username", "Email", "First Name", "Last Name" };
		List<String[]> tableList = new ArrayList<>();
		tableList.add(table);
		for (PlayerDTO player : players) {
			table = new String[] { player.getUsername(), player.getEmail(), player.getFirstName(),
					player.getLastName() };
			tableList.add(table);
		}
		tableDisplay.tableDisplay(tableList);

	}

	/**
	 * list online users
	 */
	public void getOnlineUsers() {

		HashMap<UUID, ClientHandler> clientConections = this.server.getClientManager().getClientConnections();
		UUID clientHandlerID;
		Socket clientSocket;
		PlayerDTO onlinePlayer;
		if (clientConections.size() == 0) {
			System.out.println("No user is online");
			return;
		}
		String[] table = new String[] { "Username", "Connection UUID", "IP" };
		List<String[]> tableList = new ArrayList<>();
		tableList.add(table);
		for (ClientHandler clientHandler : clientConections.values()) {
			onlinePlayer = ((MatchPlayer) clientHandler.getClientIdentifier()).getPlayer();
			clientHandlerID = clientHandler.getId();
			clientSocket = clientHandler.getClient();

			table = new String[] { onlinePlayer.getUsername(), clientHandlerID.toString(),
					clientSocket.getInetAddress().getHostAddress() };
			tableList.add(table);
		}
		tableDisplay.tableDisplay(tableList);
	}

	/**
	 * Show commands
	 * @param s: string s define case to show command
	 */
	public void showCommand(String s) {
		switch (s) {
		case "command":
			System.out.print("Command:\n\tcommand \t\t\tshow all command\n" + "\tlist <option> \t\t\tlist users\n"
					+ "\tconfig <option> <value> \tconfig game\n" + "\texit \t\t\t\tshut down server\n");
			break;
		case "list":
			System.out.print("Command: list <option>\n" + "\t user\t Show list of registered users\n"
					+ "\t online\t Show list of active users\n");
			break;
		case "config":
			System.out.print(
					"Syntax: config <option> <value>\n" + "\t numberQty <1-900>\t Config game number quantity\n"
							+ "\t time <1000-3600000>\t Config game time\n"
							+ "\t maxPlayer <2-8>\t Config game max number of player\n"
							+ "\t show\t\t\t Show current game config\n");
			break;
		}
		System.out.println();
	}
}
