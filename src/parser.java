/*
created by: Alaa Mah-moud
FEE-CSE level 4-1
section 1
 */
import java.io.FileNotFoundException;
import java.util.HashMap;

public class parser {
    private HashMap<String, String> tokens;

    public parser(HashMap<String, String> tokens) {
        this.tokens = tokens;
    }

    public void parse() throws FileNotFoundException {
        boolean forLoopFound = false;
        boolean openBraceFound = false;
        boolean closeBraceFound = false;
        boolean openBracketFound = false;
        boolean closedBracketFound = false;
        boolean caseFound = false;
        boolean switchFound = false;
        boolean colonFound = false;
        boolean semicolonFound = false;
        boolean breakFound = false;
        boolean defaultFound = false;
        //count and compare to their flags
        int dataTypeFound = 0;
        int operatorFound = 0;
        int identifierFound = 0;
        int op_flag =0;
        int da_flag =0;
        int id_flag=3;

        //count how many operators and data types found from the scanner
        for(String X : tokens.keySet())
        {
            //count operators to be compared with parser result
            if(tokens.get(X).equals("operator"))
                op_flag ++ ;
            //count data types to be compared with parser result
            if(tokens.get(X).equals("keyword") && (X.equals("int") || X.equals("double") || X.equals("float") ||
                    X.equals("char") || X.equals("long") || X.equals("short")))
                da_flag ++ ;

        }

        for (String token : tokens.keySet()) {
            String type = tokens.get(token);
            //for loop checking and its main components ( ; ; ) , {}
            if (!forLoopFound) {
                if (type.equals("keyword") && token.equals("for")) {
                    forLoopFound = true;
                }
            } else {
                if (!openBraceFound) {
                    if (type.equals("special character") && token.equals("{")) {
                        openBraceFound = true;
                    }
                } else {
                    if (type.equals("special character") && token.equals("}")) {
                        closeBraceFound = true;
                    }
                }
                if (!openBracketFound) {
                    if (type.equals("special character") && token.equals("(")) {
                        openBracketFound = true;
                    }
                } else {
                    if (type.equals("special character") && token.equals(")")) {
                        closedBracketFound = true;
                    }
                }
                if (!semicolonFound) {
                    if (type.equals("special character") && token.equals(";")) {
                        semicolonFound = true;
                    }
                }
            }
            //switch case checking and its main components ( ) , case : , break , { }
            if (!switchFound) {
                if (type.equals("keyword") && token.equals("switch")) {
                    switchFound = true;
                }
            } else {
                if (!openBraceFound) {
                    if (type.equals("special character") && token.equals("{")) {
                        openBraceFound = true;
                    }
                } else {
                    if (type.equals("special character") && token.equals("}")) {
                        closeBraceFound = true;
                    }
                }
                if (!openBracketFound) {
                    if (type.equals("special character") && token.equals("(")) {
                        openBracketFound = true;
                    }
                } else {
                    if (type.equals("special character") && token.equals(")")) {
                        closedBracketFound = true;
                    }
                }
                if (!caseFound) {
                    if (type.equals("keyword") && token.equals("case")) {
                        caseFound = true;
                    }
                }else {
                    if (!colonFound) {
                        if (type.equals("special character") && token.equals(":")) {
                            colonFound = true;
                        }
                    }
                }
                    if (!breakFound) {
                    if (type.equals("keyword") && token.equals("break")) {
                        breakFound = true;
                    }
                }
                if (!defaultFound) {
                    if (type.equals("keyword") && token.equals("default")) {
                        defaultFound = true;
                    }
                }
            }
            if (type.equals("operator") && (token.equals("+") || token.equals("-") || token.equals("++")
                || token.equals("--") || token.equals(">") || token.equals("<") || token.equals(">=") ||
                token.equals("<=") || token.equals("!=") || token.equals("==") || token.equals("=") ||
                token.equals("*") || token.equals("/") || token.equals("%") || token.equals("&&") ||
                token.equals("||") || token.equals(">>") || token.equals("<<") || token.equals("^") ||
                token.equals("~") || token.equals("!") ) ) {
                    operatorFound ++;
            }
            if (type.equals("keyword") && (token.equals("int") || token.equals("char") || token.equals("short")
                || token.equals("long") || token.equals("double") || token.equals("float") ) ) {
                    dataTypeFound ++;
            }
            if (type.equals("identifier"))
                identifierFound ++;

        }

        if (forLoopFound && openBraceFound && closeBraceFound && switchFound && caseFound && colonFound && semicolonFound
        && closedBracketFound && openBracketFound && breakFound && defaultFound &&
                (dataTypeFound == da_flag) && (operatorFound == op_flag) && (identifierFound == id_flag)) {
            System.out.println("Program parsed successfully. Syntax is correct.");
        } else {
            System.out.println("Syntax error in the program.");
        }
    }

}