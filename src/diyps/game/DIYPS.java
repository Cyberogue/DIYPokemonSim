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
/**
 *
 * @author Alice Quiros
 */
package diyps.game;

import diyps.data.PokeCalculator;
import static diyps.data.DIYPokemonConstants.*;

/**
 * Class containing the main entry point for the program
 *
 * @author Rogue <Alice Q.>
 */
public class DIYPS {

    /**
     * DIYPokemonSim entry point for the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DIYPSTextGame game = new DIYPSTextGame();;
        try {
            game.out.println("GAME START");

            String[] trainers = {TRAINER1_FILE, TRAINER2_FILE};
            if (args.length >= 1) {
                trainers[0] = args[0];

                if (args.length >= 2) {
                    trainers[1] = args[1];
                } else {
                    trainers[1] = TRAINER1_FILE;
                }
            }
            game.loadCombatants(trainers[0], trainers[1]);

            game.out.startTask("LOADING TYPE DATA...");
            PokeCalculator.loadTypeDataFromCSV("typeadv.csv", ",");
            game.out.completeTask(true);

            game.start();
        } catch (Exception e) {
            game.out.completeTask(false);
            game.out.println(e);
            e.printStackTrace();
        }
    }
}
