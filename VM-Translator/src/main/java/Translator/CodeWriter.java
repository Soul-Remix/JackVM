package Translator;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CodeWriter {
    private final PrintWriter writer;
    private ArithmeticWriter arithmeticWriter;

    public CodeWriter(String output) throws IOException {
        writer = new PrintWriter(output + ".vm", StandardCharsets.UTF_8);
        arithmeticWriter = new ArithmeticWriter(output, writer);
    }

    public void writeArithmetic(String command) {
        switch (command) {
            case "add" -> arithmeticWriter.translateCommandAdd();
            case "sub" -> arithmeticWriter.translateCommandSub();
            case "neg" -> arithmeticWriter.translateCommandNeg();
            case "eq" -> arithmeticWriter.translateCommandEQ();
            case "gt" -> arithmeticWriter.translateCommandGT();
            case "lt" -> arithmeticWriter.translateCommandLT();
            case "and" -> arithmeticWriter.translateCommandAnd();
            case "or" -> arithmeticWriter.translateCommandOr();
            case "not" -> arithmeticWriter.translateCommandNot();
        }
    }

    public void writePushPop(String command, String segment, int index) {

    }

    public void close() {
        writer.close();
    }
}
