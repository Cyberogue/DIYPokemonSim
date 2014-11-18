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

/**
 * Class containing data regarding a move
 *
 * @author Alice Quiros
 */
public class Move {

    private String name;
    private ElementType movetype;
    private MoveMode movemode;
    private int value;

    public Move(String name, ElementType movetype, MoveMode movemode, int value) {
        this.name = name;
        this.movetype = movetype;
        this.movemode = movemode;
        this.value = value;
    }

    public String name() {
        return name;
    }

    public ElementType movetype() {
        return movetype;
    }

    public MoveMode movemode() {
        return movemode;
    }

    public int value() {
        return value;
    }

    public static enum MoveMode {

        DAMAGE, ATTK, ATTK_SELF, DEF, DEF_SELF, SPD, SPD_SELF
    }
}
