/*
 * P1 Aufgabe 5-1 / AddressFileLabelled.java
 * Authors:
 *  - Michael Kohler - 11-108-289
 *  - Lukas Diener - 11-123-213
 */
 
import java.util.ArrayList;
import java.util.Scanner;

public class AddressFileLabelled extends AddressFile {

  AddressFileLabelled(String filename) {
    super(filename);
  }
  
  @Override
  public String toLine(Address addr) {    
    int id = addr.getId();
    String name = addr.getName().trim();
    String street = addr.getStreet().trim();
    int zipCode = addr.getZipCode();
    String city = addr.getCity().trim();
    
    return "id:" + id + ";name:" + name + ";street:" + street + ";zip:" +
           zipCode + ";city:" + city + ";";
  }
  
  @Override
  protected Address parseLine(String line) throws AddressFileException {
    ArrayList<String> vals = new ArrayList<String>();
    String[] labelArray = { "id", "name", "street", "zip", "city" };
    
    Scanner scan = new Scanner(line);
    for (String label : labelArray) {
      scan.findInLine(label +"[\\ s ]*:[\\ s ]*([^;]*)");
      String value = scan.match().group(1).trim();
      vals.add(value);
    }
    
    Address addr = new Address(Integer.parseInt(vals.get(0)), vals.get(1),
                               vals.get(2), Integer.parseInt(vals.get(3)),
                               vals.get(4));
    return addr;
  }

}
