package GUI;

import bus.GameResultBUS;

import javax.swing.*;
import java.awt.*;

public class GameResultView {
    // GameView.form's Components
    private JPanel contentPane;
    // ViewBinder's components
    private JLabel lblWinner;
    private JList listPlayers;
    // Others
    private JButton btnOkay;

    private GameResultBUS gameResultBUS;

    public GameResultView(GameResultBUS gameResultBUS) {
        this.gameResultBUS = gameResultBUS;

        $$$setupUI$$$();
        customizeComponents();
        bindListeners();
        initViewBinder();
    }

    // Functions

    private void customizeComponents() {

    }

    private void bindListeners() {

    }

    private void initViewBinder() {
        this.gameResultBUS.viewBinder.lblWinner = lblWinner;
        this.gameResultBUS.viewBinder.listPlayers = listPlayers;
    }

    // Properties
    public JPanel getContentPane() {
        return contentPane;
    }

    public GameResultBUS getGameResultBUS() {
        return gameResultBUS;
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
        contentPane.setBackground(new Color(-14142061));
        lblWinner = new JLabel();
        Font lblWinnerFont = this.$$$getFont$$$(null, -1, 24, lblWinner.getFont());
        if (lblWinnerFont != null) lblWinner.setFont(lblWinnerFont);
        lblWinner.setForeground(new Color(-1));
        lblWinner.setText("You are 0th place");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 24, 0);
        contentPane.add(lblWinner, gbc);
        listPlayers = new JList();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipadx = 300;
        gbc.ipady = 400;
        contentPane.add(listPlayers, gbc);
        btnOkay = new JButton();
        btnOkay.setText("OK");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(24, 0, 0, 0);
        contentPane.add(btnOkay, gbc);
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
