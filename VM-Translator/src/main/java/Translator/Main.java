package Translator;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser(args[0]);
            CodeWriter writer = new CodeWriter(args[0]);
            while (parser.hasMoreCommands()) {
                parser.advance();
                if(parser.commandType() == "C_ARITHMETIC") {
                    writer.writeArithmetic(parser.arg1());
                }
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}