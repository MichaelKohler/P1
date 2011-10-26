/*
 Programmierung 1 HS 2011
 Aufgabe 2-1

 Michael Kohler - 11-108-289
 Lukas Diener - 11-123-213
*/

import java.text.DecimalFormat;
import java.util.Random;

public class RandomISBN{

	public static void main(String args[]){
		System.out.println("1st ISBN: " + makeISBN());
		System.out.println("2nd ISBN: " + makeISBN());
		System.out.println("3rd ISBN: " + makeISBN());
	}

	/** generates and returns a random ISBN number
 	    in the format XX-XXX-XX-C */
	public static String makeISBN(){

		String laendercode;
		String bandnr;
		String verlagsnr;
		String checksum;

		// Hilfsmittel initialisieren
		Random randomGenerator = new Random();
		DecimalFormat twoDigitFormatter = new DecimalFormat("00");
		DecimalFormat threeDigitFormatter = new DecimalFormat("000");

		// Laendercode, Bandnummer und Verlagsnummer generieren
		int randomTwoDigit = randomGenerator.nextInt(100) + 1;
		laendercode = twoDigitFormatter.format(randomTwoDigit);
		randomTwoDigit = randomGenerator.nextInt(100) + 1;
		verlagsnr = twoDigitFormatter.format(randomTwoDigit);
		int randomThreeDigit = randomGenerator.nextInt(900) + 100;
		bandnr = threeDigitFormatter.format(randomThreeDigit);

		// Checksumme berechnen
		int checksumtmp = 0;
		String concatenated = "" + laendercode
				+ bandnr + verlagsnr; 
		for (int i = 1; i <= 7; i++) {
			String substring = concatenated.substring(i-1, i);
			int substringInt = Integer.parseInt(substring);
			if (i % 2 == 1) {
				checksumtmp += hashOp(substringInt);
			}
			else {
				checksumtmp += substringInt;
			}
		}
		int finalChecksumInt = checksumtmp % 10;
		checksum = Integer.toString(finalChecksumInt);

		return laendercode+"-"+bandnr+"-"+verlagsnr+"-"+checksum;
	}

	/** multiplies i with 2 and subtracts 9 if result is >= 10 */
	public static int hashOp(int i){
		int doubled = 2 * i;
		if (doubled >= 10){
			doubled = doubled - 9;
		}
		return doubled;
	}
}
