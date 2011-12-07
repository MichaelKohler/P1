/*
 * P1 Aufgabe 5-1 / AddressFileException.java
 * Authors:
 *  - Michael Kohler - 11-108-289
 *  - Lukas Diener - 11-123-231
 */
 
import java.util.ArrayList;
import java.util.Scanner;

public class AddressFile {

  public String filename = "";
  
  AddressFile(String filename) {
    this.filename = filename;
  }
  
  public String toLine(Address addr) {
    return addr.toString();
  }
  
  protected Address parseLine(String line) throws AddressFileException {
    String delimiter = ",";
    Scanner scanner = new Scanner(line);
    scanner.useDelimiter(delimiter);
    ArrayList<String> items = new ArrayList<String>();
    while (scanner.hasNext()) {
      items.add(scanner.next());
    }
    
    Address addr;
    if (items.size() == 5) {
      int id = Integer.parseInt(items.get(0).trim());
      String name = items.get(1).trim();
      String street = items.get(2).trim();
      int zipCode = Integer.parseInt(items.get(3).trim());
      String city = items.get(4).trim();
      addr = new Address(id, name, street, zipCode, city);
    }
    else {
      throw new AddressFileException("The address has an invalid format!");
    }
    return addr; // addr is null if an AddressFileException is thrown
  }
  
  protected void save(ArrayList<Address> addresses) {
    ArrayList<String> lineAddresses = new ArrayList<String>();
    for (Address address : addresses) {
      String commaseparatedAddress = toLine(address);
      lineAddresses.add(commaseparatedAddress);
    }
    
    // save to file
    try {
      Writer writer = new OutputStreamWriter(new FileOutputStream(filename));
      for (String line : lineAddresses) {
        writer.write(line + "\n");
      }
    }
    catch (Exception ex) {
      throw new AddressFileException("Error while writing!");
    }
    finally {
      writer.close();
    }
  }
  
  protected ArrayList<Address> load() throws AddressFileException {
    ArrayList<Address> addrList = new ArrayList<Address>();
    
    // read from file
    ArrayList<String> linesList = new ArrayList<String>();
    
    
    // process
    for (String line : linesList) {
      addrList.add(parseLine(line));
    }
    
    return addrList; 
  }

}