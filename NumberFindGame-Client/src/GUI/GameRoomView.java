package GUI;

import bus.GameRoomBUS;
import dto.PlayerDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;

import Run.GameMain;
import Socket.GameClient;

public class GameRoomView {
	// GameView.form's Components
	private JPanel contentPane;
	// ViewBinder's components
	private JLabel lblPlayerCount;
	// Others
	private JLabel lblFavor01;
	private JButton btnStartGame;

	private GameRoomBUS gameRoomBUS;
	private JButton btnUpdateInfo;
	private JButton btnQuitGame;
	private JButton btnLeaderBoard;
	private JLabel lblUsername;

	private int usernameSize;

	public GameRoomView(GameRoomBUS gameRoomBUS) {
		this.gameRoomBUS = gameRoomBUS;

		$$$setupUI$$$();
		customizeComponents();
		bindListeners();
		initViewBinder();
	}

	// Functions

	private void customizeComponents() {
		return;
	}

	private void bindListeners() {
		PlayerDTO player = ((GameClient) GameMain.client).getClientPlayer().getPlayer();
		lblUsername.setText(player.getUsername());
		usernameSize = player.getUsername().length();

        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameRoomView.this.gameRoomBUS.action_RequestStartGame();
            }
        });
        btnUpdateInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameRoomView.this.gameRoomBUS.action_GotoUpdateInfoView();
            }
        });
        btnLeaderBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameRoomView.this.gameRoomBUS.action_GotoLeaderboardView();
            }
        });
        btnQuitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameRoomView.this.gameRoomBUS.action_QuitGame();
            }
        });
    }

	private void initViewBinder() {
		this.gameRoomBUS.viewBinder.lblPlayerCount = lblPlayerCount;
		this.gameRoomBUS.viewBinder.btnStartGame = btnStartGame;
	}

	// Properties

	public JPanel getContentPane() {
		return contentPane;
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT
	 * edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#35455d"));
		contentPane.setSize(1024, 768);
		lblFavor01 = new JLabel();
		lblFavor01.setHorizontalAlignment(SwingConstants.CENTER);
		lblFavor01.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFavor01.setForeground(new Color(-1));
		lblFavor01.setText("Waiting for more players...");
		GridBagConstraints gbc;
		lblPlayerCount = new JLabel();
		lblPlayerCount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPlayerCount.setForeground(new Color(-1));
		lblPlayerCount.setText("1/8");
		btnStartGame = new JButton();
		btnStartGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStartGame.setText("Start Game");

		btnUpdateInfo = new JButton("Update Info");
		btnUpdateInfo.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnQuitGame = new JButton("Quit Game");
		btnQuitGame.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnLeaderBoard = new JButton("Leaderboard");
		btnLeaderBoard.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(401)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(41)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnStartGame, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnUpdateInfo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnQuitGame, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnLeaderBoard, Alignment.TRAILING)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(91).addComponent(lblPlayerCount,
								GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(447, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(406)
						.addComponent(lblFavor01, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE).addGap(405))
				.addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addContainerGap(760, Short.MAX_VALUE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
								.addGap(45)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGap(271).addComponent(lblFavor01).addGap(16).addComponent(lblPlayerCount).addGap(15)
						.addComponent(btnStartGame).addGap(16).addComponent(btnUpdateInfo).addGap(16)
						.addComponent(btnLeaderBoard).addGap(16).addComponent(btnQuitGame)
						.addContainerGap(233, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return contentPane;
	}

}
