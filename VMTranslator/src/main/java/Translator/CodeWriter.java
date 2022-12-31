package Translator;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CodeWriter {
    private final PrintWriter writer;
    private final ArithmeticWriter arithmeticWriter;
    private final PopPushWriter popPushWriter;
    private final CallWriter callWriter;
    private final ReturnWriter returnWriter;

    public CodeWriter(String output) throws IOException {
        writer = new PrintWriter(output + ".asm", StandardCharsets.UTF_8);
        arithmeticWriter = new ArithmeticWriter(output, writer);
        popPushWriter = new PopPushWriter(output, writer);
        callWriter = new CallWriter(output, writer);
        returnWriter = new ReturnWriter(writer);
    }

    public void setFileName(String output) {
        arithmeticWriter.setFileName(output);
        popPushWriter.setFileName(output);
        callWriter.setFileName(output);
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
        } else if (command.equals("pop")) {
            switch (segment) {
                case "argument" -> popPushWriter.translatePopArgument(index);
                case "local" -> popPushWriter.translatePopLocal(index);
                case "this" -> popPushWriter.translatePopThis(index);
                case "that" -> popPushWriter.translatePopThat(index);
                case "static" -> popPushWriter.translatePopStatic(index);
                case "constant" -> popPushWriter.translatePopConstant(index);
                case "pointer" -> popPushWriter.translatePopPointer(index);
                case "temp" -> popPushWriter.translatePopTemp(index);
            }
        }
    }

    public void writeLabel(String command) {
        writer.println("// label" + command);

        writer.println("(" + command + ")");
    }

    public void writeGoTo(String command) {
        writer.println("// goto " + command);

        writer.println("@" + command);
        writer.println("0;JMP");
    }

    public void writeIf(String command) {
        writer.println("// if-goto " + command);

        // pop the top most value
        writer.println("@SP");
        writer.println("AM=M-1");
        // check condition and jump
        writer.println("D=M");
        writer.println("@" + command);
        writer.println("D;JNE");
    }

    public void writeFunction(String function, int numArgs) {
        writer.println("// function " + function + " " + numArgs);

        writer.println("(" + function + ")");
        for (int i = 0; i < numArgs; i++) {
            writer.println("@SP");
            writer.println("A=M");
            writer.println("M=0");
            writer.println("@SP");
            writer.println("M=M+1");
        }
    }

    public void writeCall(String function, int numArgs) {
        callWriter.writeCall(function, numArgs);
    }

    public void writeReturn() {
        returnWriter.writeReturn();
    }

    public void writeInit() {
        writer.println("@256");
        writer.println("D=A");
        writer.println("@SP");
        writer.println("M=D");
        writer.println("@256");
        this.writeCall("Sys.init", 0);
    }

    public void close() {
        writer.close();
    }
}
