/*
 * Author: Michael Kohler, 11-108-289
 *
 */

import java.io.*;
import java.util.Scanner;

public class Divide {

  public static void main(String[] args) {
    double a = 0;
    double b = 0;
    String standardErrorMessage = "Es ist ein Fehler aufgetreten. " +
                                    "Bitte neu starten.";

    if (args.length == 0) {
      // get numbers from user
      System.out.println("Bitte die Zahl a eingeben:");
      Scanner scanner = new Scanner(System.in);
      try {
        a = scanner.nextDouble();
        System.out.println("Bitte die Zahl b eingeben:");
        b = scanner.nextDouble();
      }
      catch (Exception ex) {
        System.out.println(standardErrorMessage);
        System.exit(0);
      }
    }
    else {
      try {
        a = Double.parseDouble(args[0]);
        b = Double.parseDouble(args[1]);
      }
      catch (Exception ex) {
        System.out.println(standardErrorMessage);
        System.exit(0);
      }
    }

    if (b == 0) {
      System.out.println("b sollte nicht 0 sein!");
      System.exit(0);
    }

    // calculate results
    double result = Math.pow(a, 2) / b;
    double resultRounded = Math.floor(result);
    double rest = Math.pow(a, 2) % b;    

    // output
    System.out.println("ungerundet: " + result);
    System.out.println("gerundet: " + resultRounded + " Rest " + rest);
  }

}
