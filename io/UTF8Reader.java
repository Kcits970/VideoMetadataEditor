package io;

import java.io.*;

public class UTF8Reader extends BufferedReader {
    private UTF8Reader(Reader in) {
        super(in);
    }

    public static UTF8Reader getInstance(String filename) throws IOException {
        FileInputStream stream;
        InputStreamReader reader;

        stream = new FileInputStream(filename);
        reader = new InputStreamReader(stream, "UTF-8");
        return new UTF8Reader(reader);
    }

    @Override
    public String readLine() throws IOException {
        String line;

        for(;;) {
            line = super.readLine();
            if (line == null || !line.isBlank()) break;
        }
        return line;
    }

    public boolean readUntil(char c) throws IOException {
        int character = 0;

        for (;;) {
            character = read();
            if (character == c)
                return true;
            if (character == -1)
                return false;
        }
    }

    public void eatWhitespace() throws IOException {
        for(;;) {
            mark(1);
            if (!Character.isWhitespace(read())) {
                reset();
                break;
            }
        }
    }

    public boolean eat(String s) throws IOException {
        eatWhitespace();

        mark(s.length());
        char[] buffer = new char[s.length()];
        read(buffer, 0, s.length());

        if (!new String(buffer).equals(s)) {
            reset();
            return false;
        } else
            return true;
    }

    public boolean isAtEOF() throws IOException {
        mark(1);
        if (read() < 0)
            return true;
        else {
            reset();
            return false;
        }
    }
}
