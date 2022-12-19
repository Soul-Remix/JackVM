package Translator;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CodeWriter {
    private final PrintWriter writer;
    private final ArithmeticWriter arithmeticWriter;
    private final PopPushWriter popPushWriter;

    public CodeWriter(String output) throws IOException {
        writer = new PrintWriter(output + ".vm", StandardCharsets.UTF_8);
        arithmeticWriter = new ArithmeticWriter(output, writer);
        popPushWriter = new PopPushWriter(output, writer);
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
        if (command.equals("push")) {
            switch (segment) {
                case "argument" -> popPushWriter.translatePushArgument(index);
                case "local" -> popPushWriter.translatePushLocal(index);
                case "this" -> popPushWriter.translatePushThis(index);
                case "that" -> popPushWriter.translatePushThat(index);
                case "static" -> popPushWriter.translatePushStatic(index);
                case "constant" -> popPushWriter.translatePushConstant(index);
                case "pointer" -> popPushWriter.translatePushPointer(index);
                case "temp" -> popPushWriter.translatePushTemp(index);
            }
        }
    }

    public void close() {
        writer.close();
    }
}
