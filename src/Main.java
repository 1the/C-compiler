/*
created by: Alaa Mah-moud
FEE-CSE level 4-1
section 1
 */
import java.lang.*;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n****Simple C Compiler****");
        System.out.println("Scanned file\n---------------------------------------------");
        //create object from scanner class
        scanner scan = new scanner();
        //calling scanned function to print the content of the file
        scan.scanned();
        System.out.println("----------------------------------------------");
        System.out.println("Token\t\t\t\t Type");
        System.out.println("----------------------------------------------");
        scan.separator();

    }
}