package Translator;

import java.io.PrintWriter;

public class ReturnWriter {
    private final PrintWriter writer;

    public ReturnWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void writeReturn() {
        writer.println("// return");

        // 1. R13 <--- FRAME = LCL
        writer.println("@LCL");
        writer.println("D=M");
        writer.println("@R13");
        writer.println("M=D");

        // 2. R14 <--- RET = *(FRAME - 5)
        writer.println("@R13");
        writer.println("D=M");
        writer.println("@5");
        writer.println("A=D-A");
        writer.println("D=M");
        writer.println("@R14");
        writer.println("M=D");

        // 3. *ARG = pop()
        writer.println("@SP");
        writer.println("A=M-1");
        writer.println("D=M");
        writer.println("@ARG");
        writer.println("A=M");
        writer.println("M=D");

        // 4. SP = ARG + 1
        writer.println("@ARG");
        writer.println("D=M+1");
        writer.println("@SP");
        writer.println("M=D");

        // 5. THAT = *(FRAME - 1)
        writer.println("@R13");
        writer.println("A=M-1");
        writer.println("D=M");
        writer.println("@THAT");
        writer.println("M=D");

        // 6. THIS = *(FRAME - 2)
        writer.println("@R13");
        writer.println("D=M");
        writer.println("@2");
        writer.println("A=D-A");
        writer.println("D=M");
        writer.println("@THIS");
        writer.println("M=D");

        // 7. ARG = *(FRAME - 3)
        writer.println("@R13");
        writer.println("D=M");
        writer.println("@3");
        writer.println("A=D-A");
        writer.println("D=M");
        writer.println("@ARG");
        writer.println("M=D");

        // 8. LCL = *(FRAME - 4)
        writer.println("@R13");
        writer.println("D=M");
        writer.println("@4");
        writer.println("A=D-A");
        writer.println("D=M");
        writer.println("@LCL");
        writer.println("M=D");

        // 9. goto RET
        writer.println("@R14");
        writer.println("A=M");
        writer.println("0;JMP");
    }
}
