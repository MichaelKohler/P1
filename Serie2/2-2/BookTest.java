/*
 * This class tests the Book class.
 *
 * Michael Kohler - 11-108-289
 * Lukas Diener - 11-123-213
 */

import java.util.Date;
import java.text.ParseException;

public class BookTest {
    public static Book _testBook;
    public static boolean _allTestsPassed;

    public static void main(String[] args) throws ParseException {
	initTest();
	shouldReturnBookString();
	shouldReturnId();
	shouldSetId();
	shouldReturnTitle();
        shouldSetTitle();
	shouldReturnAuthor();
	shouldSetAuthor();
	shouldReturnDate();
	shouldSetDate();
	shouldCalcAge();
	shouldGetUserInput();

	String message = _allTestsPassed ? "All tests passed" : 
				"Not all tests passed!";
	System.out.println(message);
    }

    public static void initTest() {
	long initialTimestamp = 1107202550434L;
        Date date = new Date(initialTimestamp);
	_testBook = new Book(3, "TestBook", "TestAuthor", date);
	_allTestsPassed = true;
    }

    public static void shouldReturnBookString() {
	String bookString = _testBook.toString();
	String correctOutput = "3, TestBook, TestAuthor, 31.01.2005";
	if (!bookString.equals(correctOutput))
	    _allTestsPassed = false;
    }

    public static void shouldReturnId() {
	int id = _testBook.getId();
	if (id != 3)
	    _allTestsPassed = false;
    }

    public static void shouldSetId() {
	_testBook.setId(500);
	int newid = _testBook.getId();
	if (newid != 500)
	    _allTestsPassed = false;
    }

    public static void shouldReturnTitle() {
	String title = _testBook.getTitle();
	if (!title.equals("TestBook"))
	    _allTestsPassed = false;
    }

    public static void shouldSetTitle() {
	_testBook.setTitle("NewTitle");
	String newTitle = _testBook.getTitle();
	if (!newTitle.equals("NewTitle"))
	    _allTestsPassed = false;
    }

    public static void shouldReturnAuthor() {
	String author = _testBook.getAuthor();
	if (!author.equals("TestAuthor"))
	    _allTestsPassed = false;
    }

    public static void shouldSetAuthor() {
	_testBook.setAuthor("NewAuthor");
	String newAuthor = _testBook.getAuthor();
	if (!newAuthor.equals("NewAuthor"))
	    _allTestsPassed = false;
    }

    public static void shouldReturnDate() {
	Date date = _testBook.getDateOfPublication();
	if (date.getTime() != 1107202550434L)	
	    _allTestsPassed = false;
    }

    public static void shouldSetDate() {
	Date newDateToSet = new Date(1107202550435L);
	_testBook.setDateOfPublication(newDateToSet);
	Date newDate = _testBook.getDateOfPublication();
	if (newDate.getTime() != 1107202550435L)	
	    _allTestsPassed = false;
    }

    public static void shouldCalcAge() {
	// set date to one and a half years before today
	// => should return 1 year
	Date today = new Date();
	long oneAHalfYears = Math.round((365 * 24 * 60 * 60 * 1000) * 1.5);
	long correctTimestamp = today.getTime() - oneAHalfYears;
	Date oneAndaHalfYearsAgo = new Date(correctTimestamp);
	_testBook.setDateOfPublication(oneAndaHalfYearsAgo);
	
	if (_testBook.age() != 1)	
	    _allTestsPassed = false;
    }	

    public static void shouldGetUserInput() throws ParseException {
	// this method also tests dateToString()
	// needed input: 1, Title, Author, 01.01.2011	
	_testBook.input();
	int id = _testBook.getId();
	String title = _testBook.getTitle();
	String author = _testBook.getAuthor();
	Date date = _testBook.getDateOfPublication();
	if (id != 1 || !title.equals("Title") || !author.equals("Author")
		|| !_testBook.dateToString(date).equals("01.01.2011")) {
	    _allTestsPassed = false;
	}
    }
}
