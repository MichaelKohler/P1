/*
 Programmierung 1 HS 2011
 Aufgabe 2-2

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

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	// constructors
	public Book() {
	}	

	public Book(int aId, String aTitle, String aAuthor,
		     Date aDateOfPublication) {
		id = aId;
		title = aTitle;
		author = aAuthor;
		dateOfPublication = aDateOfPublication;
        }

	/** Returns the age of the book in days since publication */
	public int age(){
		long miliPublication = dateOfPublication.getTime();
		long miliNow = new Date().getTime();
		long msInAYear = 365 * 24 * 60 * 60 * 1000;
		long ageInMilis = miliNow - miliPublication;
		int age = (int) (ageInMilis / msInAYear);
		return age;
	}

	/** Returns a String representation of the book */
	public String toString(){
		String outputString = id + ", " + title + ", " + author
			+ ", " + dateToString(dateOfPublication);
		return outputString;
	}

	/** Reads all book data from user input */
	public void input() throws ParseException{
		Scanner scn = new Scanner(System.in);
		System.out.print("Please enter id: ");
		id = scn.nextInt();
		System.out.print("Please enter title: ");
		title = scn.next();
		System.out.print("Please enter author: ");
		author = scn.next();
		System.out.print("Please enter date of publication: ");
		dateOfPublication = stringToDate(scn.next());
	}

	// Get-/Set-methods
	public int getId() {
		return id;
	}
	public void setId(int aId) {
		id = aId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String aTitle) {
		title = aTitle;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String aAuthor) {
		author = aAuthor;
	}

	public Date getDateOfPublication() {
		return dateOfPublication;
	}
	public void setDateOfPublication(Date aDate) {
		dateOfPublication = aDate;
	}
	
	/** Converts the Date object d into a String object */
	public String dateToString(Date d){
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(d);
	}

	/** Converts the String object s into a Date object */
	public Date stringToDate(String s) throws ParseException{
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.parse(s);
	}
}
