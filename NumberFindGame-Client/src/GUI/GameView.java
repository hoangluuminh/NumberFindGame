package GUI;

import Models.LevelNode;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameView {
    // GameView.form's Components
    public JPanel contentPane;
    private JLabel lblPlayerScore;
    private JButton btnQuit;
    private JPanel gamePane;
    private JPanel infoPane;
    private JList listPlayers;
    private JLabel lblTimer;

    // Runtime Components
    private ArrayList<JButton> btnLevelArr;

    public GameView() {
        $$$setupUI$$$();

        bindListeners();
        gamePane.setLayout(null);
    }

    public void bindListeners() {
        gamePane.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                renderLevel();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });

        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("NotImplemented: Quit Game");
            }
        });
    }

    private void renderLevel() {
        int btnSize = 30;
        int screenMargin = 15;

        Rectangle gameRect = new Rectangle();
        gameRect.setRect(gamePane.getX(), gamePane.getY(), gamePane.getWidth(), gamePane.getHeight());

        ArrayList<LevelNode> level = generateLevel(100);        // TODO: Get from config
        for (LevelNode levelNode : level) {
            // Calculate X and Y range
            int posX =
                    (int) (
                        (levelNode.getCoord().x * (gameRect.width - screenMargin * 2))
                        +
                        (gameRect.x + screenMargin)
                    )
                    -
                    (btnSize / 2);
            int posY =
                    (int) (
                        (levelNode.getCoord().y * (gameRect.height - screenMargin * 2))
                        +
                        (gameRect.y + screenMargin)
                    )
                    -
                    (btnSize / 2);

            JButton btnTest = new JButton();
            gamePane.add(btnTest);
            btnTest.setText(String.format("%d", levelNode.getValue()));
            btnTest.setBounds(posX, posY, btnSize, btnSize);
        }
    }

    private ArrayList<LevelNode> generateLevel(int count) {        // TODO: Move to Server
        double marginX = 0.2, marginY = 0.125;
        Random rand = new Random();         // TODO: add Seed support
        ArrayList<LevelNode> levelNodes = new ArrayList<LevelNode>();

        ArrayList<Integer> valueList = new ArrayList<Integer>();
        for (int i = 1; i <= count; i++) {
            valueList.add(i);
        }

        // GRID
        int blockPerRow = (int) Math.sqrt(count);
        for (int i = 0; i < blockPerRow; i++) {
            for (int j = 0; j < blockPerRow; j++) {
                LevelNode levelNode = new LevelNode();

                levelNode.setCoord(new Point2D.Double(
                        (
                                i
                                + (
                                    marginX + (1 - marginX * 2) * rand.nextDouble()
                                )
                            ) * ((double) blockPerRow / count),
                        (
                                j
                                + (
                                    marginY + (1 - marginY * 2) * rand.nextDouble()
                                )
                            ) * ((double) blockPerRow / count)
                ));

                levelNodes.add(levelNode);
            }
        }

        Collections.shuffle(levelNodes, rand);
        Collections.shuffle(valueList, rand);

        for (int i = 0; i < count; i++) {
            levelNodes.get(i).setValue(valueList.get(i));
        }

        return levelNodes;
    }

    public void setData(GameView data) {
    }

    public void getData(GameView data) {
    }

    public boolean isModified(GameView data) {
        return false;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(new Color(-1049857));
        gamePane = new JPanel();
        gamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gamePane.setBackground(new Color(-7411713));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(gamePane, gbc);
        infoPane = new JPanel();
        infoPane.setLayout(new GridBagLayout());
        infoPane.setBackground(new Color(-1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(infoPane, gbc);
        btnQuit = new JButton();
        btnQuit.setText("Quit");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        infoPane.add(btnQuit, gbc);
        lblPlayerScore = new JLabel();
        Font lblPlayerScoreFont = this.$$$getFont$$$(null, -1, 32, lblPlayerScore.getFont());
        if (lblPlayerScoreFont != null) lblPlayerScore.setFont(lblPlayerScoreFont);
        lblPlayerScore.setText("10");
        lblPlayerScore.putClientProperty("html.disable", Boolean.FALSE);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 0, 20, 0);
        infoPane.add(lblPlayerScore, gbc);
        listPlayers = new JList();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        infoPane.add(listPlayers, gbc);
        lblTimer = new JLabel();
        Font lblTimerFont = this.$$$getFont$$$(null, -1, 21, lblTimer.getFont());
        if (lblTimerFont != null) lblTimer.setFont(lblTimerFont);
        lblTimer.setText("3:00");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        infoPane.add(lblTimer, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
