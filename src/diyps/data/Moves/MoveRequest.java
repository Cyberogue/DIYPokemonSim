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

import diyps.data.Trainer;

/**
 * An abstract class containing data about everything a trainer can do for their
 * move
 *
 * @author Alice Quiros
 */
public abstract class MoveRequest {

    protected Type type;
    protected Trainer trainer;

    /**
     * Returns the trainer who initiated the move
     *
     * @return the trainer who initiated the move
     */
    public Trainer trainer() {
        return trainer;
    }

    /**
     * Returns a label corresponding to what the trainer did on the move
     *
     * @return a label corresponding to what the trainer did on the move
     */
    public Type type() {
        return type;
    }

    @Override
    public String toString() {
        return trainer.name() + '[' + type.name() + ']';
    }

    /**
     * Enumeration containing labels for all the different motions during a move
     */
    public static enum Type {

        FIGHT, SWAP, ITEM, NONE
    }
}
