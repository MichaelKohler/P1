/*
 Programmierung 1 HS 2011
 Aufgabe 4-2
 
 Michael Kohler - 11-108-289
 Lukas Diener - 11-123-213
*/

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Order {

    private static int _id = 0;
    private String _customerName;
    private String _customerAddress;
    private ArrayList _orderList = new ArrayList();

    public Order() {
        _id++;
    }
    
    public int getId() {
        return _id;
    }
    
    public String getCustomerName() {
        return _customerName;
    }
    
    public void setCustomerName(String newName) {
        _customerName = newName;
    }
    
    public String getCustomerAddress() {
        return _customerAddress;
    }
    
    public void setCustomerAddress(String newAddress) {
        _customerAddress = newAddress;
    }

    public void add(IArticle aArticle) {
        _orderList.add(aArticle);
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < _orderList.size(); i++) {
            IArticle currentArticle = (IArticle) _orderList.get(i);
            total += currentArticle.getPrice();
        }
        return total;
    }
    
    /** if this method's return type was an ArrayList we couldn't use
        for(Order o : this.orders) in Store.java's listOrders(). Instead we
        would be forced to use for(int i = 0; i < this.orders.size(); i++). */
    public Iterable<IArticle> getOrderedArticles() {
        return _orderList;
    }
}
