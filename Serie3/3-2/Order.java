/*
 Programmierung 1 HS 2011
 Aufgabe 3-2
 
 Michael Kohler - 11-108-289
 Lukas Diener - 11-123-213
*/

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Order {

    private static int _id = 0;
    private String _customerName;
    private String _customerAddress;
    private ArrayList _booksList = new ArrayList();

    public Order() {
        _id++;
    }

    public String toString() {
        String output = "Order id: " + _id + ", Customer: " + _customerName +
                        ", " + _customerAddress + "\n";
        SimpleDateFormat fmt = new SimpleDateFormat(Book.DATE_FORMAT);

        for (int i = 0; i < _booksList.size(); i++) {
            Book currentBook = (Book) _booksList.get(i);
            output += currentBook.getId() + ", " + currentBook.getTitle() +
                      ", " + currentBook.getAuthor() + ", " +
                      fmt.format(currentBook.getDateOfPublication()) + ", " +
                      currentBook.getPrice() + " CHF\n";
        }

        output += "Total price: " + getTotalPrice() + " CHF";
        return output;
    }
    
    public void setCustomerName(String newName) {
        _customerName = newName;
    }
    
    public void setCustomerAddress(String newAddress) {
        _customerAddress = newAddress;
    }

    public void addBook(Book newBook) {
        if (_booksList.size() < 5) {
            _booksList.add(newBook);
        }
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < _booksList.size(); i ++) {
            Book currentBook = (Book) _booksList.get(i);
            total += currentBook.getPrice();
        }
        return total;
    }
}
