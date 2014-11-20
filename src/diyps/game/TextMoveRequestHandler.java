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

import diyps.data.AttackRecord;
import static diyps.data.DIYPokemonConstants.PREFERRED_LINE_LENGTH;
import diyps.data.Move;
import diyps.data.Moves.AttackRequest;
import diyps.data.Moves.MoveRequest;
import diyps.data.Moves.MoveRequestHandler;
import diyps.data.Moves.SwapRequest;
import diyps.data.Pokemon;
import diyps.data.Trainer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alice Quiros
 */
public class TextMoveRequestHandler implements MoveRequestHandler {

    private final List<MoveRequest> battlePhase;
    private final List<AttackRequest> attackPhase;
    private final DIYPSTextGame game;

    public TextMoveRequestHandler(DIYPSTextGame game) {
        battlePhase = new ArrayList(2);
        attackPhase = new ArrayList(2);
        this.game = game;
    }

    @Override
    public void addRequest(MoveRequest request) {
        if (request.type().equals(MoveRequest.Type.FIGHT)) {
            attackPhase.add((AttackRequest) request);
        } else {
            battlePhase.add(request);
        }
    }

    @Override
    public void handleRequests() {
        try {
            game.out.println();
            handleBattle();

            sortAttack();
            handleAttack();
            Thread.sleep(2000);
        } catch (InterruptedException ie) {

        }

        battlePhase.clear();
        attackPhase.clear();
    }

    private void handleBattle() throws InterruptedException {
        for (MoveRequest move : battlePhase) {
            if (move.type() == MoveRequest.Type.SWAP) {
                replacePokemon(move.trainer(), ((SwapRequest) move).newPokemon());
            }
            game.out.println();

            Thread.sleep(500);
        }
    }

    private void replacePokemon(Trainer trainer, Pokemon pokemon) {
        game.out.println(trainer.name() + " called back "
                + trainer.getWithdrawn().name() + " and sent out "
                + pokemon.name());

        trainer.withdrawPokemon(pokemon);
    }

    private void sortAttack() {
        AttackRequest temp;

        // ARRANGE MOVES BY SPEED
        int lastSwap = attackPhase.size(), swaps = 1;
        while (swaps > 0) {
            swaps = 0;
            for (int i = 1; i < lastSwap; i++) {
                switch (attackPhase.get(i - 1).compareSpeed(attackPhase.get(i))) {
                    case 0:
                        if (game.util.random().nextBoolean()) {
                            break;
                        }
                    case -1:
                        temp = attackPhase.get(i);
                        attackPhase.set(i, attackPhase.get(i - 1));
                        attackPhase.set(i - 1, temp);
                        lastSwap = i;
                        swaps++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void handleAttack() {
        for (int i = 0; i < attackPhase.size(); i++) {
            AttackRequest req = attackPhase.get(i);

            Trainer target = (game.getFirstTrainer() == req.trainer() ? game.getSecondTrainer() : game.getFirstTrainer());
            Pokemon defender = target.getWithdrawn();
            Pokemon attacker = req.getAttacker();
            Move move = req.getAttackMove();

            try {
                game.out.println(req.trainer().name() + "'s " + attacker.name() + " used " + move.name() + "!");

                switch (move.mode()) {
                    case DAMAGE:
                        Thread.sleep(500);
                        handleAttackExchange(new AttackRecord(move, attacker, defender));
                        if (defender.isDead()) {
                            Pokemon next = target.getFirstUsablePokemon();
                            if (next == null) {
                                game.setWinner(req.trainer());
                            } else {
                                replacePokemon(target, next);
                            }

                            for (int x = 0; x < attackPhase.size(); x++) {
                                if (attackPhase.get(x).getAttacker() == defender) {
                                    attackPhase.remove(x);
                                }
                            }
                        }
                        break;
                    case ATTACK:
                        defender.getAttributes().changeAttack(-move.value());
                        game.out.println(defender.name() + "'s attack dropped! [" + defender.attack() + "]");
                        break;
                    case ATTACK_SELF:
                        attacker.getAttributes().changeAttack(move.value());
                        game.out.println(attacker.name() + "'s attack went up! [" + attacker.attack() + "]");
                        break;
                    case DEFENSE:
                        defender.getAttributes().changeDefense(-move.value());
                        game.out.println(defender.name() + "'s defense dropped! [" + defender.defense() + "]");
                        break;
                    case DEFENSE_SELF:
                        attacker.getAttributes().changeDefense(move.value());
                        game.out.println(defender.name() + "'s attack went up! [" + defender.defense() + "]");
                        break;
                    case SPEED:
                        defender.getAttributes().changeSpeed(-move.value());
                        game.out.println(defender.name() + "'s speed dropped! [" + defender.speed() + "]");
                        break;
                    case SPEED_SELF:
                        attacker.getAttributes().changeSpeed(move.value());
                        game.out.println(defender.name() + "'s speed went up! [" + defender.speed() + "]");
                        break;
                }
                game.out.println();

                Thread.sleep(2000);
            } catch (InterruptedException ie) {

            }
        }
    }

    private void handleAttackExchange(AttackRecord record) throws InterruptedException {
        switch (record.effectiveness()) {
            case AttackRecord.EFFECTIVENESS_NONE:
                game.out.println("... It had no effect on " + record.defender().name());
                return;
            case AttackRecord.EFFECTIVENESS_LOW:
                game.out.println("It's not very effective... ");
                break;
            case AttackRecord.EFFECTIVENESS_HIGH:
                game.out.println("It's super effective!!! " + ".");
                break;
            default:
                break;
        }

        Thread.sleep(500);

        int damage = record.getTotalDamage();
        record.defender().getAttributes().changeHealth(-1 * damage);
        game.out.println(record.attacker().name() + " dealt " + damage + " points of damage to " + record.defender().name() + " [HP:" + record.defender().health() + "]");

        if (record.defender().isDead()) {
            Thread.sleep(500);
            game.out.println(record.defender().name() + " fainted!");
        }
    }

}
