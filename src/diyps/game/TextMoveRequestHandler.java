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

import diyps.data.DIYPSGame;
import diyps.data.Moves.AttackRequest;
import diyps.data.Moves.MoveRequest;
import diyps.data.Moves.MoveRequestHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alice Quiros
 */
public class TextMoveRequestHandler implements MoveRequestHandler {

    private final List<MoveRequest> priorityPhase;
    private final List<MoveRequest> battlePhase;
    private final List<AttackRequest> attackPhase;
    private final DIYPSTextGame game;

    public TextMoveRequestHandler(DIYPSTextGame game) {
        battlePhase = new ArrayList(2);
        attackPhase = new ArrayList(2);
        priorityPhase = new ArrayList(2);
        this.game = game;
    }

    @Override
    public void addRequest(MoveRequest request) {
        if (request.type().equals(MoveRequest.Type.FIGHT)) {
            priorityPhase.add(request);
        } else {
            battlePhase.add(request);
        }
    }

    @Override
    public void handleRequests() {
        System.out.println(battlePhase);
        System.out.println(priorityPhase);
        System.out.println(attackPhase);
        handlePriority();
        handleBattle();
        handleAttack();
    }

    private void handlePriority() {

    }

    private void handleBattle() {
        for (MoveRequest move : battlePhase) {

        }
    }

    private void handleAttack() {
        AttackRequest[] attacks = attackPhase.toArray(new AttackRequest[2]);
        AttackRequest temp;

        for (AttackRequest a : attacks) {
            System.out.println(a);
        }

        // ARRANGE MOVES BY SPEED
        int firstSwap = 1, swaps = 1;
        while (swaps > 0) {
            swaps = 0;
            for (int i = firstSwap; i < attacks.length; i++) {
                AttackRequest a = attacks[i - 1];
                AttackRequest b = attacks[i];

                switch (a.compareSpeed(b)) {
                    case 0:
                        if (game.util.random().nextBoolean()) {
                            break;
                        }
                    case 1:
                        temp = a;
                        a = b;
                        b = temp;
                        swaps++;
                        break;
                }
            }
        }

        for (AttackRequest a : attacks) {
            System.out.println(a);
        }
    }
}
