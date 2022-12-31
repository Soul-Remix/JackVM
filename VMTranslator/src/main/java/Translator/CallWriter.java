package Translator;

import java.io.PrintWriter;


public class CallWriter {
    private String output;
    private final PrintWriter writer;
    private int callCount;

    public CallWriter(String output,PrintWriter writer) {
        this.output = output;
        this.writer = writer;
    }

    public void setFileName(String output) {
        this.output = output;
    }

    public void writeCall(String function,int numArgs) {
        writer.println("// call " + function + " " + numArgs);

        String returnAddress = output + "-return-address-" + callCount;
        callCount++;

        // 1. push return address
        writer.println("@" + returnAddress);
        writer.println("D=A");
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");
        writer.println("@SP");
        writer.println("M=M+1");

        // 2. push LCL
        writer.println("@LCL");
        writer.println("D=M");
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");
        writer.println("@SP");
        writer.println("M=M+1");

        // 3. push ARG
        writer.println("@ARG");
        writer.println("D=M");
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");
        writer.println("@SP");
        writer.println("M=M+1");

        // 4. push THIS
        writer.println("@THIS");
        writer.println("D=M");
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");
        writer.println("@SP");
        writer.println("M=M+1");

        // 5. push THAT
        writer.println("@THAT");
        writer.println("D=M");
        writer.println("@SP");
        writer.println("A=M");
        writer.println("M=D");
        writer.println("@SP");
        writer.println("M=M+1");

        // 6. calculate ARG for called function
        writer.println("@5");
        writer.println("D=A");
        writer.println("@" + numArgs);
        writer.println("D=D+A");
        writer.println("@SP");
        writer.println("D=M-D");
        writer.println("@ARG");
        writer.println("M=D");

        // 7. calculate LCL for called function
        writer.println("@SP");
        writer.println("D=M");
        writer.println("@LCL");
        writer.println("M=D");

        // 8. go to function
        writer.println("@" + function);
        writer.println("0;JMP");

        // 9. mark return address
        writer.println("(" + returnAddress + ")");
    }
}
