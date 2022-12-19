package Translator;

import java.io.PrintWriter;

class PopPushWriter {
    private final PrintWriter writer;

    public PopPushWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void translatePushArgument(int index) {
        writer.println("// push argument " + index);

        // Load (segment + index) content
        writer.println("@ARG");
        writer.println("D=M");
        writer.println("@" + index);
        writer.println("A=D+A");
        writer.println("D=M");

        // Push to stack
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M+1");
    }

    private void translatePushLocal(int index) {
        writer.println("// push local " + index);

        // Load (segment + index) content
        writer.println("@LCL");
        writer.println("D=M");
        writer.println("@" + index);
        writer.println("A=D+A");
        writer.println("D=M");

        // Push to stack
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M+1");
    }

    private void translatePushThis(int index) {
        writer.println("// push this " + index);

        // Load (segment + index) content
        writer.println("@THIS");
        writer.println("D=M");
        writer.println("@" + index);
        writer.println("A=D+A");
        writer.println("D=M");

        // Push to stack
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M+1");
    }
}
