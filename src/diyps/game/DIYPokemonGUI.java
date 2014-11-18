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
package diyps.game;

import static diyps.data.DIYPokemonConstants.*;
import diyps.data.Trainer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Extension of JFrame which contains all the data for a basic DIYPokemonSim
 * game
 *
 * @author Alice Quiros
 */
public class DIYPokemonGUI extends JFrame {

    JTextArea console;
    Trainer player1;
    Trainer player2;

    public DIYPokemonGUI(Trainer player1, Trainer player2) {
        this.setMinimumSize(new Dimension(1024, 768));
        this.setPreferredSize(new Dimension(1024, 768));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.player1 = player1;
        this.player2 = player2;

        initializeComponents();
    }

    private void initializeComponents() {
        this.setLayout(new BorderLayout());

        /**
         * PLAYER 1
         */
        /**
         * ROUND END BUTTON
         */
        add(initializeMoveSelectPane(), BorderLayout.EAST);

        /**
         * CONSOLE
         */
        console = new JTextArea(10, 90);
        console.setEditable(false);
        console.setWrapStyleWord(true);
        console.setLineWrap(true);
        console.setMinimumSize(new Dimension(1024, 768 / 2));

        console.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        add(new JScrollPane(console), BorderLayout.SOUTH);
    }

    private JPanel initializeMoveSelectPane() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createEtchedBorder());

        JComboBox p1ComboBox = new JComboBox();
        p1ComboBox.setModel(new DefaultComboBoxModel());

        JComboBox p2ComboBox = new JComboBox();
        p1ComboBox.setModel(new DefaultComboBoxModel());

        root.add(p1ComboBox);
        root.add(p2ComboBox);

        JButton endTurnButton = new JButton(END_TURN_TEXT);
        root.add(endTurnButton);

        return root;
    }
}
