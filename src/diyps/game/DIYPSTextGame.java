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

import diyps.data.Moves.MoveRequest;
import diyps.data.*;
import static diyps.data.DIYPokemonConstants.*;

/**
 * Class containing data and methods related to the central core game
 * functionality
 *
 * @author Alice Quiros
 */
public class DIYPSTextGame extends DIYPSGame {

    private final DIYPSUtility util;

    private int turnNo;

    public DIYPSTextGame() {
        super();
        util = new DIYPSUtility(this);
        turnNo = 0;
    }

    @Override
    public void onStart() {
        out.printBreak();
        out.println("LOADED TRAINER DATA: ");
        out.println("\t" + trainers[0].name());
        for (Pokemon p : trainers[0].getParty()) {
            out.println("\t * " + p.toString());
        }

        out.println("\t" + trainers[1].name());
        for (Pokemon p : trainers[1].getParty()) {
            out.println("\t * " + p.toString());
        }
    }

    @Override
    protected void onLoop() {
        turnNo++;

        out.printBreak();
        out.println("|" + util.center("TURN " + turnNo, PREFERRED_LINE_LENGTH - 2) + "|");
        out.println("|" + util.center(trainers[0].name() + "'s " + activePokemon[0].name()
                + " VERSUS "
                + trainers[1].name() + "'s " + activePokemon[1].name(), PREFERRED_LINE_LENGTH - 2) + "|");
        out.printBreak();

        out.println("What will " + trainers[0].name() + " do?");

        MoveRequest p1Move;
        while ((p1Move = parseMove(util.input().nextLine().split(QUOTES_IGNORE_REGEX))) == null) {
            System.out.println("Error parsing input for " + trainers[0].name());
        }
    }

    private MoveRequest parseMove(String[] args) {
        for (String arg : args) {
            arg = arg.replace("\"", "");
            System.out.println(arg);
        }

        return null;
    }
}
