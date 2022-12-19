package Translator;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser(args[0]);
            CodeWriter writer = new CodeWriter(args[0]);
            while (parser.hasMoreCommands()) {
                parser.advance();
                if(parser.commandType().equals("C_ARITHMETIC")) {
                    writer.writeArithmetic(parser.arg1());
                } else if (parser.commandType().equals("C_PUSH")) {
                    writer.writePushPop("push", parser.arg1(), parser.arg2());
                }
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}