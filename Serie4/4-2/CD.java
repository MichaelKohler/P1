/*
 Programmierung 1 HS 2011
 Aufgabe 4-2
*/

public class CD implements IArticle {
  	private int id;
	  private String title;
	  private String author;
	  private int year;
	  private int price; // CHF

	  /** constructor */
	  public CD(int id, String title, String author, int year, int price) {
		    this.id = id;
		    this.title = title;
		    this.author = author;
		    this.year = year;
		    this.price = price;
	  }
	  
	  public int getId() {
  	    return this.id;
  	}
	
  	public int getPrice() {
  	    return this.price;
  	}
	
  	public String getDescription() {
  	    String description = this.id + " (CD) " + this.author + ", " +
  	        this.title + ", " + this.year + ", " + this.price + " CHF";
  	    return description;
  	}
}
