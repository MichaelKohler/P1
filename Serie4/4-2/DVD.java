/*
 Programmierung 1 HS 2011
 Aufgabe 4-2
 
 Michael Kohler - 11-108-289
 Lukas Diener - 11-123-213
*/

public class DVD implements IArticle {
	  private int id;
	  private String title;
	  private int year;
	  private int price; // CHF

	  /** constructor */
	  public DVD(int id, String title, int year, int price) {
		    this.id = id;
		    this.title = title;
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
  	    String description = this.id + " (DVD) " + this.title + ", " +
  	        this.year + ", " + this.price + " CHF";
  	    return description;
  	}
}
