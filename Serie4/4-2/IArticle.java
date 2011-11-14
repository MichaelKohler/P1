/*
 Programmierung 1 HS 2011
 Aufgabe 4-2
 
 Michael Kohler - 11-108-289
 Lukas Diener - 11-123-213
*/

public interface IArticle {
	  public int id = 0;
	  public String title = "";
	  public int year = 0;
	  public int price = 0; // CHF
	  public String description = "";
	
	  public int getId();
	  public int getPrice();
	  public String getDescription();
}
