package Translator;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CodeWriter {
    private PrintWriter writer;

    public CodeWriter(String output) throws IOException {
        PrintWriter writer = new PrintWriter(output + ".vm", StandardCharsets.UTF_8);
    }

    public void writeArithmetic(String command) {
        if(command.equals("add")) {
            translateCommandAdd();
        }
        if(command.equals("sub")) {
            translateCommandSub();
        }
    }

    public void writePushPop(String command, String segment, int index) {

    }

    public void close() {
        writer.close();
    }

    private void translateCommandAdd() {
        writer.println("// add");

        // Load second operand
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("D=M");
        writer.println("@R13");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M-1");

        // Load first operand
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("D=M");

        // Add
        writer.println("@R13");
        writer.println("D=D+M");

        // Store the result
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=D");
    }

    private void translateCommandSub() {
        writer.println("// sub");

        // Load second operand
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("D=M");
        writer.println("@R13");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M-1");

        // Load first operand
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("D=M");

        // Sub
        writer.println("@R13");
        writer.println("D=D-M");

        // Store the result
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=D");
    }
}
