package Translator;

import java.io.PrintWriter;

class ArithmeticWriter {
    private final PrintWriter writer;
    private String outputName;
    private int eqCount;
    private int gtCount;
    private int ltCount;

    public ArithmeticWriter(String output, PrintWriter writer) {
        this.writer = writer;
        outputName = output;
        eqCount = 0;
        gtCount = 0;
        ltCount = 0;
    }

    public void setFileName(String output) {
        outputName = output;
    }

    public void translateCommandAdd() {
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

    public void translateCommandSub() {
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

    public void translateCommandNeg() {
        writer.println("// neg");

        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=-M");
    }

    public void translateCommandEQ() {
        String labelEQTrue = outputName + ".EQ.true." + eqCount;
        String labelEQEnd = outputName + ".EQ.end." + eqCount;
        eqCount++;

        writer.println("// eq");

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

        // Check to jump
        writer.println("@" + labelEQTrue);
        writer.println("D;JEQ");

        // No jump
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=0");
        writer.println("@" + labelEQEnd);
        writer.println("0;JMP");

        // Jump to here if true/equal
        writer.println("(" + labelEQTrue + ")");
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=-1");

        // End
        writer.println("(" + labelEQEnd + ")");
    }

    public void translateCommandGT() {
        String labelGTTrue = outputName + ".GT.true." + gtCount;
        String labelGTEnd = outputName + ".GT.end." + gtCount;
        gtCount++;

        writer.println("// gt");

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

        // Check to jump
        writer.println("@" + labelGTTrue);
        writer.println("D;JGT");

        // No jump
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=0");
        writer.println("@" + labelGTEnd);
        writer.println("0;JMP");

        // Jump to here if true/greater than
        writer.println("(" + labelGTTrue + ")");
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=-1");

        // End
        writer.println("(" + labelGTEnd + ")");
    }

    public void translateCommandLT() {
        String labelLTTrue = outputName + ".LT.true." + ltCount;
        String labelLTEnd = outputName + ".LT.end." + ltCount;
        ltCount++;

        writer.println("// lt");

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

        // Check to jump
        writer.println("@" + labelLTTrue);
        writer.println("D;JLT");

        // No jump
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=0");
        writer.println("@" + labelLTEnd);
        writer.println("0;JMP");

        // Jump to here if true/lesser than
        writer.println("(" + labelLTTrue + ")");
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=-1");

        // End
        writer.println("(" + labelLTEnd + ")");
    }

    public void translateCommandAnd() {
        writer.println("// and");

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

        // And
        writer.println("@R13");
        writer.println("D=D&M");


        // Store the result
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=D");
    }

    public void translateCommandOr() {
        writer.println("// or");

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

        // Or
        writer.println("@R13");
        writer.println("D=D|M");

        // Store the result
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=D");
    }

    public void translateCommandNot() {
        writer.println("// not");

        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("M=!M");
    }
}
