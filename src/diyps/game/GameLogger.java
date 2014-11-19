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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alice Quiros
 */
public class GameLogger {

    private List<PrintStream> streams;
    private String task = null;

    public GameLogger() {
        streams = new ArrayList(1);

        streams.add(System.out);
    }

    public void addStream(PrintStream stream) {
        streams.add(stream);
    }

    public boolean openFileStream(String directory) {
        File f = new File(directory);
        if (f.exists()) {
            return false;
        }

        return openFileStreamOverwrite(directory);
    }

    public boolean openFileStreamOverwrite(String directory) {
        try {
            streams.add(new PrintStream(directory));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public void print(String s) {
        for (PrintStream ps : streams) {
            ps.print(s);
        }
    }

    public void print(Object o) {
        print(o.toString());
    }

    public void println() {
        print('\n');
    }

    public void println(Object o) {
        print(o.toString() + '\n');
    }

    public void println(String s) {
        print(s + '\n');
    }

    public void log(Object o) {
        log(o.toString());
    }

    public void log(String s) {
        print(new Date().toString() + "\t" + s);
    }

    public void logln(Object o) {
        logln(o.toString());
    }

    public void logln(String s) {
        print(new Date().toString() + "\t" + s + '\n');
    }

    public void closeAll() {
        for (PrintStream ps : streams) {
            ps.close();
        }
    }

    public void reset() {
        streams.clear();
        streams.add(System.out);
    }

    public void startTask(String s) {
        if (task != null) {
            println("\tINTERRUPTED");
        }
        print(s);
        task = s;
    }

    public void completeTask(boolean success) {
        if (task == null) {
            return;
        }
        if (success) {
            println("\tDONE");
        } else {
            println("\tFAILED");
        }
        task = null;
    }
}
