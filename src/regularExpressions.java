/*
created by: Alaa Mah-moud
FEE-CSE level 4-1
section 1
 */
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regularExpressions {

    //regular expressions mathematically
    String keywordPattern = "(auto|break|case|char|const|continue|default|do|double|else|enum|extern|float|" +
            "for|goto|if|int|long|register|return|short|signed|sizeof|static|struct|switch|typedef|union|" +
            "unsigned|void|volatile|while)";
    String identifierPattern = "[a-zA-Z_][a-zA-Z0-9_]*";
    String specialCharPattern = "([{}()\\[\\];:',])";
    String numericConstantPattern = "([0-9]+(\\.[0-9]+)?([eE][-+]?[0-9]+)?)";
    String operatorPattern = "(\\+|-|\\*|\\/|%|=|==|!=|>|<|>=|<=|\\+\\+|\\-\\-|\\&\\&|\\|\\||\\!|\\&|\\||\\^|~|>>|<<)";
    
    void check_RE(HashMap<String,String> token) throws FileNotFoundException {

        //catch the pattern for each token type to be checked
        Pattern keywordRegex = Pattern.compile(keywordPattern);
        Pattern identifierRegex = Pattern.compile(identifierPattern);
        Pattern specialCharRegex = Pattern.compile(specialCharPattern);
        Pattern numericConstantRegex = Pattern.compile(numericConstantPattern);
        Pattern operatorRegex = Pattern.compile(operatorPattern);

        //match the actual RE with expected RE
        Matcher matcher;
        for(String X : token.keySet())
        {
            if (token.get(X).equals("keyword")) {
                matcher = keywordRegex.matcher(X);
                if (matcher.matches()) {
                    System.out.println("Matched \t\t\t\t\t" + X + "\t\t\t\t\tkeyword");
                }
                else
                    System.out.println("Didn't match \t\t\t\t\t" + X + "\t\t\t\t\tnot specified");
            }else if(token.get(X).equals("operator")) {
                matcher = operatorRegex.matcher(X);
                if (matcher.matches()) {
                    System.out.println("Matched \t\t\t\t\t" + X + "\t\t\t\t\toperator");
                }
                else
                    System.out.println("Didn't match \t\t\t\t\t" + X + "\t\t\t\t\tnot specified");
            }else if(token.get(X).equals("special character")) {
                matcher = specialCharRegex.matcher(X);
                if (matcher.matches()) {
                    System.out.println("Matched \t\t\t\t\t" + X + "\t\t\t\t\tspecial character");
                }
                else
                    System.out.println("Didn't match \t\t\t\t\t" + X + "\t\t\t\t\tnot specified");
            }else if(token.get(X).equals("identifier")) {
                matcher = identifierRegex.matcher(X);
                if (matcher.matches()){
                    System.out.println("Matched \t\t\t\t\t" + X + "\t\t\t\t\tidentifier");
                }
                else
                    System.out.println("Didn't match \t\t\t\t\t" + X + "\t\t\t\t\tnot specified");
            }else if(token.get(X).equals("numeric constant")) {
                matcher = numericConstantRegex.matcher(X);
                if (matcher.matches()){
                    System.out.println("Matched \t\t\t\t\t" + X + "\t\t\t\t\tnumeric constant");
                }
                else
                    System.out.println("Didn't match \t\t\t\t\t" + X + "\t\t\t\t\tnot specified");
            }
        }
        // calling parse method of the Parser class
        parser parser = new parser(token);
        System.out.println("----------------------------------------------");
        System.out.println("Parsing Result");
        System.out.println("----------------------------------------------");
        parser.parse();
    }
}