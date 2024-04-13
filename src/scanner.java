/*
created by: Alaa Mah-moud
FEE-CSE level 4-1
section 1
 */
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class scanner {

    //print scanned file
    void scanned() throws Exception{
        File file = new File("check_me.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        //read line by line
        while ((st = br.readLine()) != null) {
            // Print the string
            System.out.println(st);
        }
    }


    //separate tokens
    void separator()throws Exception{
        //database
        String keywords[]={"if","for","while","switch","case","else","else if","return","printf","scanf","int","double",
                "float","char","long","short","break","default","unsigned","signed","static","goto","typedef","continue",
                "void","struct","sizeof","#define","#include","stdio.h","do"};
        String operators[]={"+","-","*","/","%","=","==","!=",">","<",">=","<=","++","--","<<",">>","&&","||","^","~","!"};
        String specialCharacters[]={"[","]","{","}","(",")",";",":","\"","'",","};
        String numericConstants[]=new String[101];



        //variables
        String[] splited;
        String data;
        char flag='x';
        //to pass the tokens to be checked by REs
        HashMap<String,String> tokens = new HashMap<String,String>();

        //storing numeric
        for(int b=0;b<=100;b++)
            numericConstants[b]=Integer.toString(b);

        //catching file
        data = new String(Files.readAllBytes(Paths.get("check_me.txt")));

        //delete any separations due to new lines
        for(int g=0;g<data.length();g++) {
            data = data.replace("\r", " ");
            data = data.replace("\n", " ");
        }

        //spliting data
        splited = data.split(" ");

        //check tokens
        for(int i=0;i<splited.length;i++){
            int j;
            for(j=0;j<keywords.length;j++) {
                if (splited[i].equals(keywords[j])) {
                    System.out.print(splited[i] + "\t\t\t\t\t" + "keyword\n");
                    tokens.put(splited[i],"keyword");
                    flag = 'a';
                    break;
                }
            }
            for(j=0;j<operators.length;j++) {
                if (splited[i].equals(operators[j])) {
                    System.out.print(splited[i] + "\t\t\t\t\t" + "operator\n");
                    tokens.put(splited[i],"operator");
                    flag = 'b';
                    break;
                }
            }
            for(j=0;j<specialCharacters.length;j++) {
                if (splited[i].equals(specialCharacters[j])) {
                    System.out.print(splited[i] + "\t\t\t\t\t" + "special character\n");
                    tokens.put(splited[i],"special character");
                    flag = 'c';
                    break;
                }
            }
            for(j=0;j<numericConstants.length;j++) {
                if (splited[i].equals(numericConstants[j])) {
                    System.out.print(splited[i] + "\t\t\t\t\t" + "numeric constant\n");
                    tokens.put(splited[i],"numeric constant");
                    flag = 'd';
                    break;
                }
            }
            if(splited[i].equals(" ") ||splited[i].equals("")){/*avoid having empty spaces, not to be recognized as identifiers*/ }
            else if(flag!='a' && flag!='b' && flag!='c' && flag!='d') {
                System.out.print(splited[i] + "\t\t\t\t\t" + "identifier\n");
                tokens.put(splited[i], "identifier");
            }
            flag='x';
        }

        //calling check_RE from regularExpressions to check matched tokens
        regularExpressions reg= new regularExpressions();
        System.out.println("----------------------------------------------");
        System.out.println("Matched Regular Expressions");
        System.out.println("----------------------------------------------");
        System.out.println("Result" + "\t\t\t\t\t" + "Token" + "\t\t\t\t\t" + "RE Type");
        reg.check_RE(tokens);

        // calling generateCode method of the CodeGenerator class
        codeGenerator codeGenerator = new codeGenerator(tokens,splited);
        codeGenerator.generateCode("generated.txt");
    }
}
