/*
 Programmierung 1 HS 2011
 Aufgabe 3-2
 
 Michael Kohler - 11-108-289
 Lukas Diener - 11-123-213
*/

import java.util.Date;
import java.text.*;
import java.util.Scanner;

public class Book{
	private int id;
	private String title;
	private String author;
	private Date dateOfPublication;
	
	/** added variable */
        private int price;

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	public Book(int tmpId, String tmpTitle, String tmpAuthor,
                    Date tmpDateOfPubl, int tmpPrice){
		id = tmpId;
		title = tmpTitle;
		author = tmpAuthor;
		dateOfPublication = tmpDateOfPubl;
		
		/** added assignment */
                price = Math.max(0, tmpPrice);
        }

	public Book(){
		id = 0;
		title = "";
		author = "";
		dateOfPublication = new Date();
		
		/** added assignment */
                price = 0;
	}


	/** Returns the age of the book in days since publication */
	public int age(){
		Date now = new Date();
		long age_ms = now.getTime() - dateOfPublication.getTime();
                              //age in milliseconds
		return (int) (age_ms/((long)1000*3600*24)); //convert to days
	}

	/** Returns a String representation of the book */
	public String toString(){
		return id + ", " + title + ", " + author +
			", " + dateToString(dateOfPublication);
	}

	/** Reads all book data from user input */
	public void input() throws ParseException{
		Scanner scn = new Scanner(System.in);
		System.out.print("Please enter id: ");
		id = Integer.parseInt(scn.nextLine());
		System.out.print("Please enter the title: ");
		title = scn.nextLine();
		System.out.print("Please enter the author's first" +
                                 "and lastname: ");
		author = scn.nextLine();
		System.out.print("Please enter date of publication " +
                                 "(e.g. 01.12.2007): ");
		dateOfPublication = stringToDate(scn.nextLine());
	}


	// Get-/Set-methods
	public int getId(){
		return id;
	}
	public void setId(int newId){
		id = newId;
	}
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String newAuthor){
		author = newAuthor;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String newTitle){
		title = newTitle;
	}
	public Date getDateOfPublication(){
		return dateOfPublication;
	}
	public void setDateOfPublication(Date newDate){
		dateOfPublication = newDate;
	}
	
	/** added method */
        public int getPrice() {
                return price;
        }
  
        /** added method */
        public void setPrice(int newPrice) {
                price = Math.max(0, newPrice);
        }
	
	// private methods --------------------------------------------
	/** Converts the Date object d into a String object */
	private String dateToString(Date d){
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(d);
	}

	/** Converts the String object s into a Date object */
	private Date stringToDate(String s) throws ParseException{
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.parse(s);
	}
}
