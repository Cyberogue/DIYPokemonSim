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

import java.util.ArrayList;
import java.util.List;

/**
 * Class which receives move requests and handles them in the correct order as
 * needed
 *
 * @author Alice Quiros
 */
public abstract class MoveRequestHandler {

    protected final List<MoveRequest> priorityPhase;
    protected final List<MoveRequest> battlePhase;
    protected final List<MoveRequest> attackPhase;

    public MoveRequestHandler() {
        battlePhase = new ArrayList(2);
        attackPhase = new ArrayList(2);
        priorityPhase = new ArrayList(2);
    }

    public void addRequest(MoveRequest request) {
        if (request.type.equals(MoveRequest.Type.FIGHT)) {
            priorityPhase.add(request);
        } else {
            battlePhase.add(request);
        }
    }

    public abstract void handleRequests();
}
