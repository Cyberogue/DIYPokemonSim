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
import diyps.data.Moves.AttackRequest;
import diyps.data.Moves.SwapRequest;

/**
 * Class containing data and methods related to the central core game
 * functionality
 *
 * @author Alice Quiros
 */
public class DIYPSTextGame extends DIYPSGame {

    protected final DIYPSUtility util;

    private int turnNo;

    public DIYPSTextGame() {
        super();
        super.setRequestHandler(new TextMoveRequestHandler(this));
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

        Pokemon[] withdrawn = {trainers[0].getWithdrawn(), trainers[1].getWithdrawn()};

        out.printBreak();
        out.println("|" + util.center("TURN " + turnNo, PREFERRED_LINE_LENGTH - 2) + "|");
        out.println("|" + util.center(trainers[0].name() + "'s " + withdrawn[0].name()
                + " VERSUS "
                + trainers[1].name() + "'s " + withdrawn[1].name(), PREFERRED_LINE_LENGTH - 2) + "|");
        out.println("|" + util.center("", PREFERRED_LINE_LENGTH - 2) + "|");

        String s = withdrawn[0].name() + " ";
        for (Move move : withdrawn[0].getMoveset()) {
            s += " | " + (move.name().length() > 8 ? move.name().substring(0, 6) + ".." : move.name());
        }
        s += " | HP:" + withdrawn[0].health();
        out.println("|" + util.center(s, PREFERRED_LINE_LENGTH - 2) + "|");

        s = withdrawn[1].name() + " ";
        for (Move move : withdrawn[1].getMoveset()) {
            s += " | " + (move.name().length() > 8 ? move.name().substring(0, 6) + ".." : move.name());
        }
        s += " | HP:" + withdrawn[1].health();
        out.println("|" + util.center(s, PREFERRED_LINE_LENGTH - 2) + "|");

        out.printBreak();

        MoveRequest request[] = new MoveRequest[2];

        request[0] = nextMove(trainers[0]);
        request[1] = nextMove(trainers[1]);

        moves.addRequest(request[0]);
        moves.addRequest(request[1]);
    }

    @Override
    public void onEnd() {
        out.println(super.getWinner().name() + " wins the battle!");
    }

    private MoveRequest nextMove(Trainer trainer) {
        out.println("What will " + trainer.name() + " do?");

        MoveRequest req = null;
        Pokemon withdrawn = trainer.getWithdrawn();
        int index;

        while (req == null) {
            String input = util.input().nextLine();
            String[] tokens = input.split("\\s+");

            if (tokens.length < 2) {
                out.println("Not enough parameters");
                continue;
            }

            try {
                index = Integer.parseInt(tokens[1]) - 1;

                switch (tokens[0].toLowerCase()) {
                    case "attack":
                    case "a":
                    case "-a":
                        if (index >= withdrawn.getMoveset().size() || index < 0) {
                            out.println("Error parsing move data: invalid move selection [" + index + "]");
                            break;
                        }
                        req = new AttackRequest(trainer, withdrawn, withdrawn.getMove(index));
                        return req;
                    case "swap":
                    case "s":
                    case "-s":
                        if (index >= trainer.getParty().size() || index < 0) {
                            out.println("Error parsing move data: invalid Pokemon selection [" + index + "]");
                            break;
                        }
                        if (trainer.getPokemon(index) == withdrawn) {
                            out.println("Can't swap out for the same Pokemon!");
                            break;
                        }
                        if (trainer.getPokemon(index).isDead()) {
                            out.println("Can't swap out for a fainted Pokemon!");
                            break;
                        }
                        req = new SwapRequest(trainer, trainer.getPokemon(index));
                        return req;
                    default:
                        out.println("Unknown identifier " + tokens[0]);
                        break;
                }
            } catch (NumberFormatException e) {
                out.println("Error parsing move data");
            }

            out.flushAll();
        }

        return req;
    }
}
