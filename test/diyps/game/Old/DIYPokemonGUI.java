/*
 * The MIT License
 *
 * Copyright 2014 Alice Quiros.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package diyps.game.Old;

import static diyps.data.DIYPokemonConstants.*;
import diyps.data.Move;
import diyps.data.Pokemon;
import diyps.data.Trainer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Extension of JFrame which contains all the data for a basic DIYPokemonSim
 * game
 *
 * @author Alice Quiros
 */
public class DIYPokemonGUI extends JFrame {

    private DIYPokemonGame game;
    private JTextArea console;
    private Trainer player1;
    private Trainer player2;
    private PlayerOptionsPanel pOpt1;
    private PlayerOptionsPanel pOpt2;

    public DIYPokemonGUI(DIYPokemonGame game, Trainer player1, Trainer player2) {
        this.setMinimumSize(new Dimension(1024, 768));
        this.setPreferredSize(new Dimension(1024, 768));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.game = game;
        this.player1 = player1;
        this.player2 = player2;

        try {
            this.setIconImage(ImageIO.read(new File(IMAGE_FILEPATH + PROGRAM_ICON_IMAGE)));
        } catch (IOException ioe) {

        }

        initializeComponents();
    }

    private void initializeComponents() {
        this.setLayout(new BorderLayout());

        /**
         * Battle pane
         */
        JBackgroundPanel battlePanel = new JBackgroundPanel(IMAGE_FILEPATH + BATTLE_BACKGROUND_IMAGE);
        battlePanel.setMinimumSize(new Dimension(500, 500));
        battlePanel.setPreferredSize(new Dimension(500, 500));
        battlePanel.setBorder(BorderFactory.createEtchedBorder());
        add(battlePanel, BorderLayout.CENTER);

        /**
         * Side Pane
         */
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEtchedBorder());

        sidePanel.add(pOpt1 = new PlayerOptionsPanel(player1));
        sidePanel.add(new JSeparator());
        sidePanel.add(pOpt2 = new PlayerOptionsPanel(player2));

        JButton endTurnButton = new JButton(END_TURN_TEXT);
        endTurnButton.setPreferredSize(new Dimension(200, 30));
        endTurnButton.setAlignmentX(0.5f);
        final DIYPokemonGame game = this.game;
        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.submitTurn(pOpt1.getTurnMode(), pOpt1.getCurrentSelection(),
                        pOpt2.getTurnMode(), pOpt2.getCurrentSelection());
            }
        });

        sidePanel.add(endTurnButton);

        add(sidePanel, BorderLayout.EAST);

        /**
         * CONSOLE
         */
        console = new JTextArea(10, 100);
        console.setBorder(BorderFactory.createLoweredBevelBorder());
        console.setEditable(false);
        console.setWrapStyleWord(true);
        console.setLineWrap(true);
        console.setBackground(Color.gray);
        console.setForeground(Color.white);

        add(new JScrollPane(console), BorderLayout.SOUTH);
    }

    public void println(String line) {
        console.append("   > " + line + "\n");
        console.setCaretPosition(console.getDocument().getLength());
    }

    private static class PlayerOptionsPanel extends JPanel {

        private Trainer trainer;
        private JRadioButton fightButton, switchButton, itemButton;
        private JLabel trainerName;
        private JList<String> selectionList;

        protected PlayerOptionsPanel(Trainer trainer) {
            this.trainer = trainer;
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBorder(BorderFactory.createLoweredBevelBorder());

            trainerName = new JLabel(trainer.name());
            trainerName.setForeground(Color.red);
            trainerName.setAlignmentX(0.5f);
            trainerName.setAlignmentY(0f);
            trainerName.setFont(trainerName.getFont().deriveFont(16f));

            this.add(trainerName);

            fightButton = new JRadioButton(FIGHT_SELECT_TEXT);
            switchButton = new JRadioButton(SWITCH_SELECT_TEXT);
            itemButton = new JRadioButton(ITEM_SELECT_TEXT);
            fightButton.setSelected(true);

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(fightButton, BorderLayout.WEST);
            buttonPanel.add(switchButton, BorderLayout.CENTER);
            buttonPanel.add(itemButton, BorderLayout.EAST);

            JRadioButton[] buttons = {fightButton, switchButton, itemButton};
            ButtonGroup group = new ButtonGroup();
            for (JRadioButton button : buttons) {
                group.add(button);
                button.setVerticalTextPosition(JRadioButton.TOP);
                button.setHorizontalTextPosition(JRadioButton.CENTER);

                final PlayerOptionsPanel pane = this;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        pane.update();
                    }
                });
            }
            this.add(buttonPanel);

            selectionList = new JList();
            selectionList.setVisibleRowCount(4);
            selectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.add(new JScrollPane(selectionList));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            update();
        }

        public void update() {
            ArrayList<String> list = new ArrayList<String>();
            if (fightButton.isSelected()) {
                for (Move move : trainer.getWithdrawnPokemon().getMoveset()) {
                    list.add(move.name() + " [" + move.type() + "]");
                }
            } else if (switchButton.isSelected()) {
                for (Pokemon pokemon : trainer.getParty()) {
                    list.add(pokemon.name() + " [" + pokemon.health() + "/" + pokemon.initialHealth() + "]");
                }
            } else if (itemButton.isSelected()) {

            } else {
                list.add("------");
            }
            selectionList.setListData(list.toArray(new String[4]));
        }

        public DIYPokemonGame.TurnMode getTurnMode() {
            if (fightButton.isSelected()) {
                return DIYPokemonGame.TurnMode.FIGHT;
            } else if (switchButton.isSelected()) {
                return DIYPokemonGame.TurnMode.SWAP;
            } else if (itemButton.isSelected()) {
                return DIYPokemonGame.TurnMode.ITEM;
            } else {
                return null;
            }
        }

        public int getCurrentSelection() {
            return selectionList.getSelectedIndex();
        }
    }
}
