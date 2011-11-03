import java.util.ArrayList;

public class Order {

    private static int id;
    private String customerName;
    private String customerAddress;
    private ArrayList booksList;

    public Order() {
       id++;
    }

    public String toString() {
        String output = "Order id: " + id + ", Customer: " + customerName + ", " + customerAddress;
        return output;
    }

    public void addBook(Book newBook) {
        if (booksList.size() < 5) {
          booksList.add(newBook);
        }
    }

    public int getTotalPrice() {
        int total = 0;
        for (Book book : booksList) {
            total += book.getPrice();
        }
        return total;
    }
}
