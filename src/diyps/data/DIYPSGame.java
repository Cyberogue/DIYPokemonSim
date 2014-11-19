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
package diyps.data;

import diyps.game.*;
import diyps.data.Moves.MoveRequestHandler;

/**
 * Class containing data and methods related to the central core game
 * functionality
 *
 * @author Alice Quiros
 */
public abstract class DIYPSGame {

    public final GameLogger out = new GameLogger();

    protected final Trainer[] trainers;
    private Trainer winner = null;
    protected MoveRequestHandler moves;

    public DIYPSGame() {
        trainers = new Trainer[2];
        moves = null;
    }

    public void setRequestHandler(MoveRequestHandler handler) {
        this.moves = handler;
    }

    public void loadCombatants(String trainer1xml, String trainer2xml) throws Exception {
        PokemonXMLLoader xmlloader;
        try {
            xmlloader = new PokemonXMLLoader();

            out.startTask("LOADING TRAINER ONE DATA...");
            trainers[0] = xmlloader.loadTrainer(trainer1xml);
            out.completeTask(true);

            out.startTask("LOADING TRAINER TWO DATA...");
            trainers[1] = xmlloader.loadTrainer(trainer2xml);
            out.completeTask(true);
        } catch (Exception e) {
            out.println(e);
            throw e;
        }
    }

    public boolean hasWinner() {
        return (winner != null);
    }

    public Trainer getWinner() {
        return winner;
    }

    public void setWinner(Trainer trainer) {
        this.winner = trainer;
    }

    public final void start() {
        onStart();
        while (winner == null) {
            onLoop();
            moves.handleRequests();
        }
        onEnd();
    }

    public Trainer getFirstTrainer() {
        return trainers[0];
    }

    public Trainer getSecondTrainer() {
        return trainers[1];
    }

    protected void onStart() {

    }

    protected abstract void onLoop();

    protected void onEnd() {

    }
}
