/*************
 * Michael Kohler - 11-108-289
 *************/

public class ProblemFixed {

  public static double a = 17; // darf nicht final sein, da später eine
                               // Zuweisung erfolgt

  public static void main(String[] args) { // darf kein ; enthalten,
                                           // da nicht abstract
    double b = 24;
    double c = 3.41;
    System.out.println("a = " + a);
    a = a + b;
    System.out.println("a = " + a);
    b = c/2;       // möglicher Verlust der Präzision, da
                   // typeof(b) == int und nicht double
    System.out.println("b = " + b);
  }

}
