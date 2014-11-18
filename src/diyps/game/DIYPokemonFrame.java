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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

/**
 * Extension of JFrame which contains all the data for a basic DIYPokemonSim
 * game
 *
 * @author Alice Quiros
 */
public class DIYPokemonFrame extends JFrame {

    JTextArea console;
    JPanel leftTrainer;
    JPanel rightTrainer;

    public DIYPokemonFrame() {
        this.setMinimumSize(new Dimension(1024, 768));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridheight = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;

        /**
         * Player 1 *
         */
        leftTrainer = new JPanel();
        leftTrainer.add(new JLabel("Trainer 1"));

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(leftTrainer, c);

        /**
         * Player 2 *
         */
        rightTrainer = new JPanel();
        rightTrainer.add(new JLabel("Trainer 2"));

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        add(rightTrainer, c);

        /**
         * CONSOLE *
         */
        console = new JTextArea();
        console.setBorder(BorderFactory.createLoweredBevelBorder());
        console.setBackground(Color.black);
        console.setForeground(Color.WHITE);
        console.setEditable(false);
        console.append("Hello World!");

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.BOTH;
        add(console, c);
    }
}
