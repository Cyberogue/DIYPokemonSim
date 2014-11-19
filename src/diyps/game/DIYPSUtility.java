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

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Alice Quiros
 */
public class DIYPSUtility {

    private Random random;
    private Scanner input;

    public DIYPSUtility() {
        random = new Random();
        input = new Scanner(System.in);
    }

    public Random random() {
        return random;
    }

    public Object random(Object[] obj) {
        return obj[random.nextInt(obj.length)];
    }

    public Scanner input() {
        return input;
    }

    public String scanFor(String retryMessage, String[] valid) {
        String s;

        while (true) {
            s = input.nextLine();

            for (String v : valid) {
                if (s.toLowerCase(Locale.UK).equals(v.toLowerCase(Locale.UK))) {
                    return s;
                }
            }

            System.out.println(retryMessage);
        }
    }
}
