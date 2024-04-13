/*
created by: Alaa Mah-moud
FEE-CSE level 4-1
section 1
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class codeGenerator {
 private HashMap<String, String> tokens;
 private StringBuilder codeBuilder;
 private String[] desiredOrder;

 public codeGenerator(HashMap<String, String> tokens, String[] desiredOrder) {
  this.tokens = tokens;
  this.codeBuilder = new StringBuilder();
  this.desiredOrder = desiredOrder;
 }

 public void generateCode(String outputFilePath) {
  //having the same order while keeping duplicated elements
  ArrayList<String> sortedTokens = new ArrayList<>();

  for (String token : desiredOrder) {
   if (tokens.containsKey(token)) {
    String type = tokens.get(token);
    appendToken(token, type);    //function calling
   }
   sortedTokens.add(token); // Add token to the end of the list
  }

  // Process remaining tokens not in desiredOrder
  for (String token : tokens.keySet()) {
   if (!sortedTokens.contains(token)) {
    String type = tokens.get(token);
    appendToken(token, type);      //function calling
   }
  }

  //display the generated code from tokens
  for (String token : sortedTokens) {
    String type = tokens.get(token);
    appendCode(token);              //function calling
  }

  saveCodeToFile(outputFilePath);
 }

 private void appendToken(String token, String type) {
  codeBuilder.append(type).append(" : ").append(token).append("\n");
  // Add your code generation logic here based on the token and type
 }

 private void appendCode(String token) {
  codeBuilder.append(token).append(" ");
  // Add your code generation logic here based on the token and type
 }

 private void saveCodeToFile(String filePath) {
  try {
   FileWriter writer = new FileWriter(filePath);
   writer.write(codeBuilder.toString());
   writer.close();
   System.out.println("Generated code saved to file: " + filePath);
  } catch (IOException e) {
   System.out.println("Error occurred while saving code to file: " + e.getMessage());
  }
 }

 private class TokenComparator implements Comparator<String> {
  public int compare(String token1, String token2) {
   int index1 = getIndex(token1);
   int index2 = getIndex(token2);
   return Integer.compare(index1, index2);
  }

  private int getIndex(String token) {
   for (int i = 0; i < desiredOrder.length; i++) {
    if (token.equals(desiredOrder[i])) {
     return i;
    }
   }
   return -1; // Token not found in the desired order array
  }
 }
}