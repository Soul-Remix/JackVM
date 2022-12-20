package Translator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        String input = args[0];
        if (!input.endsWith(".vm")) {
            throw new IllegalArgumentException();
        }
        String output = input.substring(0, input.length() - 3);

        Parser parser = new Parser(input);
        CodeWriter writer = new CodeWriter(output);
        while (parser.hasMoreCommands()) {
            parser.advance();
            if (parser.commandType().equals("C_ARITHMETIC")) {
                writer.writeArithmetic(parser.arg1());
            } else if (parser.commandType().equals("C_PUSH")) {
                writer.writePushPop("push", parser.arg1(), parser.arg2());
            } else if (parser.commandType().equals("C_POP")) {
                writer.writePushPop("pop", parser.arg1(), parser.arg2());
            }
        }
        writer.close();
    }
}