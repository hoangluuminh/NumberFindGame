package GUI;

import javax.swing.*;
import java.awt.*;

public class TEST_MatchPlayerCell {
    private JPanel paneMatchPlayer;
    private JPanel paneAvatar;
    private JLabel lblNameInitial;
    private JLabel lblScore;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        paneMatchPlayer = new JPanel();
        paneMatchPlayer.setLayout(new GridBagLayout());
        paneMatchPlayer.setBackground(new Color(-1));
        paneMatchPlayer.setMinimumSize(new Dimension(75, 50));
        paneMatchPlayer.setPreferredSize(new Dimension(75, 50));
        panel1.add(paneMatchPlayer);
        paneAvatar = new JPanel();
        paneAvatar.setLayout(new GridBagLayout());
        paneAvatar.setAlignmentX(0.5f);
        paneAvatar.setAlignmentY(0.5f);
        paneAvatar.setMinimumSize(new Dimension(50, 50));
        paneAvatar.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        paneMatchPlayer.add(paneAvatar, gbc);
        lblNameInitial = new JLabel();
        lblNameInitial.setText("HL");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        paneAvatar.add(lblNameInitial, gbc);
        lblScore = new JLabel();
        lblScore.setForeground(new Color(-16777216));
        lblScore.setHorizontalAlignment(0);
        lblScore.setText("1");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        paneMatchPlayer.add(lblScore, gbc);
    }
}
