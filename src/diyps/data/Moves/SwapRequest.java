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
package diyps.data.Moves;

import diyps.data.Pokemon;
import diyps.data.Trainer;

/**
 * Class containing the data for a move during which a trainer swapped Pokémon
 *
 * @author Alice Quiros
 */
public class SwapRequest extends MoveRequest {

    private Pokemon replacement;

    /**
     * Basic constructor
     *
     * @param trainer the Pokémon's trainer
     * @param fielded the Pokémon that was out on the battle field
     * @param replacement the Pokémon that would be replacing the fielded
     * Pokémon
     */
    public SwapRequest(Trainer trainer, Pokemon replacement) {
        this.type = MoveRequest.Type.SWAP;
        this.trainer = trainer;
        this.replacement = replacement;
    }

    /**
     * Returns the Pokémon replacing the previous one
     *
     * @return the Pokémon replacing the previous one
     */
    public Pokemon newPokemon() {
        return replacement;
    }

    @Override
    public String toString() {
        return trainer.name() + '[' + "<= " + replacement.name() + ']';
    }
}
