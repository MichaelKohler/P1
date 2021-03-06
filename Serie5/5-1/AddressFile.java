/*
 * P1 Aufgabe 5-1 / AddressFile.java
 * Authors:
 *  - Michael Kohler - 11-108-289
 *  - Lukas Diener - 11-123-213
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressFile {

  public String filename;
  
  AddressFile(String filename) {
    this.filename = filename;
  }
  
  protected String toLine(Address addr) {
	String address = addr.getId()+","+addr.getName().trim()+","+addr.getStreet().trim()+","+addr.getZipCode()+","+addr.getCity().trim();
    return address;
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
    try{
      int id = Integer.parseInt(items.get(0).trim());
      String name = items.get(1).trim();
      String street = items.get(2).trim();
      int zipCode = Integer.parseInt(items.get(3).trim());
      String city = items.get(4).trim();
      addr = new Address(id, name, street, zipCode, city);
    }
    catch(Exception e){
      throw new AddressFileException("The address has an invalid format!");
    }
    return addr;
  }
  
  public void save(ArrayList<Address> addresses) {
    ArrayList<String> lineAddresses = new ArrayList<String>();
    for (Address address : addresses) {
      String commaseparatedAddress = toLine(address);
      lineAddresses.add(commaseparatedAddress);
    }
    
    // save to file
    Writer writer = null;
    try {
      writer = new OutputStreamWriter(new FileOutputStream(filename));
      for (String line : lineAddresses) {
        writer.write(line + "\n");
      }
      writer.close();
    }
    catch (Exception ex) { }
  }
  
  public ArrayList<Address> load() throws AddressFileException,
                                             FileNotFoundException {
    ArrayList<Address> addrList = new ArrayList<Address>();
    
    // read from file
    ArrayList<String> linesList = new ArrayList<String>();
    Scanner scanner = new Scanner(new File(filename));
    while (scanner.hasNextLine()) {
      linesList.add(scanner.nextLine());
    }
    
    // process
    for (String line : linesList) {
      addrList.add(parseLine(line));
    }
    
    return addrList; 
  }

}
