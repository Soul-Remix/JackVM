package Translator;

import java.io.PrintWriter;

class PopPushWriter {
    private final PrintWriter writer;
    private final String name;

    public PopPushWriter(String name, PrintWriter writer) {
        this.writer = writer;
        this.name = name;
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

    public void translatePushLocal(int index) {
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

    public void translatePushThis(int index) {
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

    public void translatePushThat(int index) {
        writer.println("// push that " + index);

        // Load (segment + index) content
        writer.println("@THAT");
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

    public void translatePushStatic(int index) {
        writer.println("// push static " + index);

        // Load (segment + index) content
        writer.println("@" + name + ".static." + index);
        writer.println("D=M");

        // Push to stack
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M+1");
    }

    public void translatePushConstant(int index) {
        writer.println("// push constant " + index);

        // Load (segment + index) content
        writer.println("@" + index);
        writer.println("D=A");

        // Push to stack
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M+1");
    }

    public void translatePushPointer(int index) {
        writer.println("// push pointer " + index);

        // Load (segment + index) content
        writer.println("@3");
        writer.println("D=A");
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

    public void translatePushTemp(int index) {
        writer.println("// push temp " + index);

        // Load (segment + index) content
        writer.println("@5");
        writer.println("D=A");
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

    public void translatePopArgument(int index) {
        writer.println("// pop argument " + index);

        writer.println("@ARG");
        writer.println("D=M");
        writer.println("@" + index);
        writer.println("D=D+A");
        writer.println("@R13");
        writer.println("M=D");

        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("D=M");
        writer.println("@R13");
        writer.println("A=M");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M-1");
    }

    public void translatePopLocal(int index) {
        writer.println("// pop local " + index);

        writer.println("@LCL");
        writer.println("D=M");
        writer.println("@" + index);
        writer.println("D=D+A");
        writer.println("@R13");
        writer.println("M=D");

        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("D=M");
        writer.println("@R13");
        writer.println("A=M");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M-1");
    }

    public void translatePopThis(int index) {
        writer.println("// pop this " + index);

        writer.println("@THIS");
        writer.println("D=M");
        writer.println("@" + index);
        writer.println("D=D+A");
        writer.println("@R13");
        writer.println("M=D");

        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("D=M");
        writer.println("@R13");
        writer.println("A=M");
        writer.println("M=D");

        // Update stack pointer
        writer.println("@SP");
        writer.println("M=M-1");
    }
}
