package Translator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        String input = args[0];
        File file = new File(input);

        if (!file.exists()) {
            throw new IllegalArgumentException();
        }

        ArrayList<File> files = new ArrayList<>();
        String fileOutPath;

        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.getName().endsWith(".vm")) {
                    files.add(f);
                }
            }

            if (files.size() == 0) {
                throw new IllegalArgumentException("No .VM files found in directory");
            }

            fileOutPath = file.getAbsolutePath() + "/" + file.getName();
        } else {
            String path = file.getAbsolutePath();

            if (!path.endsWith(".vm")) {
                throw new IllegalArgumentException(".vm file is required!");
            }

            files.add(file);
            fileOutPath = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("."));
        }

        CodeWriter writer = new CodeWriter(fileOutPath);

        for (File f : files) {
            Parser parser = new Parser(f);
            writer.setFileName(f.getName());

            while (parser.hasMoreCommands()) {
                parser.advance();
                if (parser.commandType().equals("C_ARITHMETIC")) {
                    writer.writeArithmetic(parser.arg1());
                } else if (parser.commandType().equals("C_PUSH")) {
                    writer.writePushPop("push", parser.arg1(), parser.arg2());
                } else if (parser.commandType().equals("C_POP")) {
                    writer.writePushPop("pop", parser.arg1(), parser.arg2());
                } else if (parser.commandType().equals("C_LABEL")) {
                    writer.writeLabel(parser.arg1());
                } else if (parser.commandType().equals("C_GOTO")) {
                    writer.writeGoTo(parser.arg1());
                } else if (parser.commandType().equals("C_IF")) {
                    writer.writeIf(parser.arg1());
                } else if (parser.commandType().equals("C_RETURN")) {

                } else if (parser.commandType().equals("C_FUNCTION")) {
                    writer.writeFunction(parser.arg1(), parser.arg2());
                } else if (parser.commandType().equals("C_CALL")) {

                }
            }
        }


        writer.close();
    }
}