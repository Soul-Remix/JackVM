package Translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Parser {
    private Scanner reader;
    private String currentCommand;
    private String arg1;
    private String arg2;

    private final HashMap<String, Boolean> arithmeticCommands;

    public Parser(File input) throws FileNotFoundException {
        reader = new Scanner(input);

        arithmeticCommands = new HashMap<>();
        arithmeticCommands.put("add", true);
        arithmeticCommands.put("sub", true);
        arithmeticCommands.put("neg", true);
        arithmeticCommands.put("eq", true);
        arithmeticCommands.put("gt", true);
        arithmeticCommands.put("lt", true);
        arithmeticCommands.put("and", true);
        arithmeticCommands.put("or", true);
        arithmeticCommands.put("not", true);
    }

    public Boolean hasMoreCommands() {
        return reader.hasNextLine();
    }

    public String advance() {
        currentCommand = reader.nextLine().trim();
        arg1 = null;
        arg2 = null;
        if ((currentCommand.isBlank() || currentCommand.startsWith("//")) && this.hasMoreCommands()) {
            currentCommand = this.advance();
        }
        return currentCommand;
    }

    public String commandType() {
        String[] arr = currentCommand.split(" ");
        if (arithmeticCommands.containsKey(arr[0])) {
            arg1 = arr[0];
            return "C_ARITHMETIC";
        }
        if (arr[0].equals("push")) {
            arg1 = arr[1];
            arg2 = arr[2];
            return "C_PUSH";
        }
        if (arr[0].equals("pop")) {
            arg1 = arr[1];
            arg2 = arr[2];
            return "C_POP";
        }
        return "";
    }

    public String arg1() {
        return arg1;
    }

    public Integer arg2() {
        return Integer.parseInt(arg2);
    }
}
