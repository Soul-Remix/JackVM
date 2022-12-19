package Translator;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser(args[0]);
            while (parser.hasMoreCommands()) {
                parser.advance();
                System.out.println(parser.commandType());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}