package project1;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;

import javax.activation.CommandObject;

public class TestCountDownTimer {

	/**
	 * Tests for Default Constructor public CountDownTimer()
	 */
	@Test
	public void testDefaultConstructor() {
		CountDownTimer s = new CountDownTimer();
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
	}

	/**
	 * Tests for public CountDownTimer(int hours, int minutes, int seconds)
	 */
	@Test
	public void testConstructor3Parameters() {
		CountDownTimer s = new CountDownTimer(0, 0, 0);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		s = new CountDownTimer(2, 3, 4);
		assertTrue(s.getHours() == 2);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(-2, 3, 4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegHour() {
		new CountDownTimer(-2, 3, 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(2, -3, 4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegMinute() {
		new CountDownTimer(2, -3, 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(2, 3, -4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegSecond() {
		new CountDownTimer(2, 3, -4);
	}

	// Testing for exceptions; testing all 3 at the same time
	@Test
	public void testConstructor3ParametersNegInput() {
		try {
			new CountDownTimer(-2, 3, 4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}

		try {
			new CountDownTimer(2, -3, 4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}

		try {
			new CountDownTimer(2, 3, -4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer(12,67,14);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeMinute() {
		new CountDownTimer(12, 60, 14);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeSecond() {
		new CountDownTimer(12, 59, 60);
	}


	/**
	 * Tests for public CountDownTimer(int minutes, int seconds)
	 */
	@Test
	public void testConstructor2Parameters(){
		CountDownTimer s = new CountDownTimer(4, 30);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 4);
		assertTrue(s.getSeconds() == 30);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersNegSecond(){
		new CountDownTimer(23, -20);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersNegMinute(){
		new CountDownTimer(-12, 5);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersLargeSecond(){
		new CountDownTimer(2, 65);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor2ParametersLargeMinute(){
		new CountDownTimer(200, 4);
	}


	/**
	 * public CountDownTimer(int seconds)
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterNegSeconds(){
		new CountDownTimer(-43);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterLargeSeconds(){
		new CountDownTimer(60);
	}

	@Test
	public void testConstructor1Parameter(){
		CountDownTimer s = new CountDownTimer(12);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 12);
	}

	@Test
	public void testConstructor1Parameter59(){
		CountDownTimer s = new CountDownTimer(59);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 59);
	}


	/**
	 * Tests for public CountDownTimer(CountDownTimer other)
	 */
	@Test
	public void testConstructorOtherParameter(){
		CountDownTimer b = new CountDownTimer(1, 4, 6);
		CountDownTimer s = new CountDownTimer(b);
		assertTrue(s.getHours() == 1);
		assertTrue(s.getMinutes() == 4);
		assertTrue(s.getSeconds() == 6);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorOtherParameterLargeNum(){
		CountDownTimer s = new CountDownTimer(1, 62, 54);
		CountDownTimer s2 = new CountDownTimer(s);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorOtherParameterNegNum(){
		CountDownTimer s = new CountDownTimer(1, 54, -54);
		CountDownTimer s2 = new CountDownTimer(s);
	}

	@Test
	public void testConstructorOtherParameter2(){
		//passing the values through a few objects
		CountDownTimer s = new CountDownTimer(1, 42, 13);
		CountDownTimer s2 = new CountDownTimer(s);
		CountDownTimer s3 = new CountDownTimer(s2);
		assertTrue(s3.getSeconds() == 13);
		assertTrue(s3.getMinutes() == 42);
		assertTrue(s3.getHours() == 1);
	}


	/**
	 * Tests for the CountDownTimer(string startTime)
	 */
	// Testing for an exception; no lines of code after
	// "new CountDownTimer("a");" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString1ParameterAlpha() {
		new CountDownTimer("a");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringLarge() {
		new CountDownTimer("1:23:45:678");
	}

	@Test
	public void testConstructorString() {
		CountDownTimer s = new CountDownTimer("1:23:32");
		//just testing the constructor
		assertTrue(s.getHours() == 1);
		assertTrue(s.getMinutes() == 23);
		assertTrue(s.getSeconds() == 32);
	}

	@Test
	public void testConstructorStringMinSec(){
		CountDownTimer s = new CountDownTimer("32:12");
		//Getting the Constructor to work with two values
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 32);
		assertTrue(s.getSeconds() == 12);
	}

	@Test
	public void testConstructorStringSec(){
		CountDownTimer s = new CountDownTimer("45");

		//Getting the Constructor to work with only one value
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 45);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString1ParameterNull() {
		//Throws error because we were provided with nothing
		new CountDownTimer("");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringParameterNeg(){
		//Should throw an error because one of the numbers is negative
		CountDownTimer s = new CountDownTimer("-12:23:12");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString3ParamLarge(){
		//Should throw an error because minutes has too many numbers/ It is too large
		CountDownTimer s = new CountDownTimer("3:946:32");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString2ParamLarge(){
		//Should throw an error because Seconds has too many numbers/ It is too large
		CountDownTimer s = new CountDownTimer("23:432");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString1ParamLarge(){
		//Should throw an error because seconds has too many numbers/ it's too large
		CountDownTimer s = new CountDownTimer("384");
	}

	/**
	 * Tests for public boolean equals(Object other)
	 */
	@Test
	public void testEquals() {
		CountDownTimer s1 = new CountDownTimer(5, 59, 30);
		CountDownTimer s2 = new CountDownTimer(6, 01, 20);
		CountDownTimer s3 = new CountDownTimer(5, 59, 30);
		CountDownTimer s4 = new CountDownTimer(5, 59, 20);
		CountDownTimer s5 = new CountDownTimer(5, 40, 30);
		CountDownTimer s6 = new CountDownTimer(4, 59, 30);
		CountDownTimer s7 = new CountDownTimer(5, 40, 20);
		CountDownTimer s8 = new CountDownTimer(4, 59, 20);
		CountDownTimer s9 = new CountDownTimer(4, 40, 30);

		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
		assertEquals(s3, s1);
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		assertFalse(s1.equals(s9));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testEqualsNull() {
		CountDownTimer s = new CountDownTimer();
		s.equals(null);
	}

	/**
	 *  Tests for public static boolean equals(CountDownTimer t1, CountDownTimer t2)
	 */
	@Test
	public void testEquals2Parameters() {
		CountDownTimer t1 = new CountDownTimer(2, 1 ,20);
		CountDownTimer t2 = new CountDownTimer(2, 3, 25);
		CountDownTimer t3 = new CountDownTimer(3, 4, 20);
		CountDownTimer t4 = new CountDownTimer(2, 1, 20);
		CountDownTimer t5 = new CountDownTimer(4, 54, 52);
		CountDownTimer t6 = new CountDownTimer(4, 54, 52);
		CountDownTimer t7 = new CountDownTimer(6, 34,26);

		assertFalse(CountDownTimer.equals(t1,t2));
		assertFalse(CountDownTimer.equals(t1,t3));
		assertTrue(CountDownTimer.equals(t1,t4));
		assertTrue(CountDownTimer.equals(t5,t6));
		assertFalse(CountDownTimer.equals(t5, t2));
		assertFalse(CountDownTimer.equals(t5, t7));
	}

	/**
	 * Tests for public int compareTo(CountDownTimer other)
	 */
	@Test
	public void testCompareTo(){
		CountDownTimer t1 = new CountDownTimer(5, 45, 23);
		CountDownTimer t2 = new CountDownTimer(3,56,14);
		CountDownTimer t3 = new CountDownTimer(3, 57, 35);
		CountDownTimer t4 = new CountDownTimer(5, 45, 23);

		assertEquals(0, t1.compareTo(t4));
		assertEquals(1, t1.compareTo(t3));
		assertEquals(-1,t2.compareTo(t1));
	}

	/**
	 * Tests for public static int compareTo(CountDownTimer t1, CountDownTimer t2)
	 */
	@Test
	public void testCompareTo2Parameters(){
		CountDownTimer t1 = new CountDownTimer(3, 45, 59);
		CountDownTimer t2 = new CountDownTimer(1,0,14);
		CountDownTimer t3 = new CountDownTimer(2, 59, 35);
		CountDownTimer t4 = new CountDownTimer(3, 45, 59);

		assertEquals(0, CountDownTimer.compareTo(t1, t4));
		assertEquals(1, CountDownTimer.compareTo(t3, t2));
		assertEquals(-1, CountDownTimer.compareTo(t2, t4));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testEquals2ParametersNull(){
		CountDownTimer s = new CountDownTimer();
		CountDownTimer s2 = new CountDownTimer();


		//seeing if the equals method works with a null
		s.equals(null,s2);
	}


	/**
	 * Tests for public void dec()
	 */
	@Test
	public void testDec1Second() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// dec 1
		s.dec();
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 58);
	}

	@Test
	public void testDec1Second2() {
		CountDownTimer s = new CountDownTimer(1, 0, 0);

		//dec1 testing on the seams to see if hours correctly goes to 0 and the others to 59
		s.dec();
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testDecPastZero(){
		CountDownTimer s = new CountDownTimer(0,0,0);

		//dec1 to push seconds to be -1
		s.dec();
	}

	@Test
	public void testDecMultiple(){
		CountDownTimer s = new CountDownTimer(0,12,21);

		//dec 5 times in a row
		s.dec();
		s.dec();
		s.dec();
		s.dec();
		s.dec();
		assertEquals(s.getSeconds(), 16);
		assertEquals(s.getMinutes(), 12);
		assertEquals(s.getHours(), 0);
	}

	/**
	 * Tests for public void sub(int seconds)
	 */
	@Test
	public void testSub1(){
		CountDownTimer s = new CountDownTimer(10);

		//subtract 1 second
		s.sub(1);
		assertEquals(s.getSeconds(), 9);
	}

	@Test
	public void testSub30(){
		CountDownTimer s = new CountDownTimer(2, 25);

		//subtract 30 seconds
		s.sub(30);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getSeconds(), 55);
	}

	@Test
	public void testSub60(){
		CountDownTimer s = new CountDownTimer(1, 30, 5);

		//subtract 60 seconds
		s.sub(60);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 29);
		assertEquals(s.getSeconds(), 5);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSubPastZero(){
		CountDownTimer s = new CountDownTimer(0 , 1, 45);

		//subtract 300 seconds which should make seconds negative
		s.sub(300);
	}

	/**
	 * Tests for public void sub(CountDownTimer other)
	 */
	@Test
	public void testSubOther(){
		CountDownTimer s = new CountDownTimer(1, 45);
		CountDownTimer s2 = new CountDownTimer(1,43);

		//subtract s2 from s
		s.sub(s2);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 2);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSubOtherNeg(){
		CountDownTimer s = new CountDownTimer(25);
		CountDownTimer s2 = new CountDownTimer(1, 43);

		//subtracting s2 from s so that we get a negative number that throws an
		//IllegalArgumentException
		s.sub(s2);
	}

	/**
	 * Tests for public void inc()
	 */
	@Test
	public void testInc1Second(){
		CountDownTimer s = new CountDownTimer(1, 59,59);

		//increment by 1
		s.inc();
		assertEquals(s.getHours(), 2);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testIncMultipleSeconds(){
		CountDownTimer s = new CountDownTimer(1, 23, 58);


		//Increment by 5
		s.inc();
		s.inc();
		s.inc();
		s.inc();
		s.inc();
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 24);
		assertEquals(s.getSeconds(), 3);
	}

	@Test
	public void testIncHourIncreases(){
		CountDownTimer s = new CountDownTimer(1,59, 59);


		//incrementing to increase hours
		s.inc();
		assertEquals(s.getSeconds(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getHours(), 2);
	}

	/**
	 * Tests for public void add(int seconds)
	 */
	@Test
	public void testAdd1() {
		CountDownTimer s = new CountDownTimer();

		s.add(1);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 1);
	}

	@Test
	public void testAdd10() {
		CountDownTimer s = new CountDownTimer();

		s.add(10);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 10);
	}

	@Test
	public void testAdd59() {
		CountDownTimer s = new CountDownTimer();

		s.add(59);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}

	@Test
	public void testAdd60() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 min
		s.add(60);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getSeconds(), 0);
	}

	@Test
	public void testAddHour(){
		CountDownTimer s = new CountDownTimer(1,50,0);

		//adding 660 seconds to check and see if hours will go to 2 and minutes to 1
		s.add(660);
		assertEquals(s.getSeconds(), 0);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getHours(), 2);
	}

	/**
	 * Tests for public void add(CountDownTimer other)
	 */
	@Test
	public void testAddOther(){
		CountDownTimer s = new CountDownTimer(20);
		CountDownTimer s2 = new CountDownTimer(1,5,20);

		//adding s2 to s
		s.add(s2);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 5);
		assertEquals(s.getSeconds(), 40);
	}

	@Test
	public void testAssOtherIncreaseHourMin(){
		CountDownTimer s = new CountDownTimer(1,35, 32);
		CountDownTimer s2 = new CountDownTimer(1,5,20);

		//adding them to each other so that hour increases and minutes
		s.add(s2);
		assertEquals(s.getHours(), 2);
		assertEquals(s.getMinutes(), 40);
		assertEquals(s.getSeconds(), 52);
	}

	/**
	 * Tests for public String toString()
	 */
	@Test
	public void testToString(){
		CountDownTimer s = new CountDownTimer(2, 45, 56);

		//Testing toString
		s.toString();
		assertEquals(s.toString(), "2:45:56");
	}

	@Test
	public void testToStringAddZeroSec(){
		CountDownTimer s = new CountDownTimer(3, 43,5);

		//Testing if toString will add a zero placeholder to the text for seconds
		s.toString();
		assertEquals(s.toString(), "3:43:05");
	}

	@Test
	public void testToStringAddZeroMin(){
		CountDownTimer s = new CountDownTimer(1, 3,43);

		//Testing if toString will add a zero placeholder to the text for minutes
		s.toString();
		assertEquals(s.toString(), "1:03:43");
	}

	/**
	 * Tests for suspend methods
	 */
	@Test
	public void setSuspend(){
		CountDownTimer s = new CountDownTimer(1, 30, 30);

		// Set suspend to true
		CountDownTimer.setSuspend(true);
		//attempt to add while suspended
		s.add(50);
		//attempt to sub while suspended
		s.sub(400);
		//attempt to increment and decrement while suspended
		s.inc();
		s.dec();
		// set suspend back to false
		CountDownTimer.setSuspend(false);

		assertEquals(s.getSeconds(),30);
		assertEquals(s.getMinutes(), 30);
		assertEquals(s.getHours(), 1);
	}

	/**
	 * Tests for setters
	 */


	@Test
	public void testSetHours(){
		CountDownTimer s = new CountDownTimer();

		//setting hours to 4
		s.setHours(4);
		assertEquals(s.getHours(), 4);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetHoursNeg(){
		CountDownTimer s = new CountDownTimer();

		//attempting to set hours to a negative number
		s.setHours(-3);
	}

	@Test
	public void testSetMinutes(){
		CountDownTimer s = new CountDownTimer();

		//setting minutes to 59
		s.setMinutes(59);
		assertEquals(s.getMinutes(), 59);
	}

	@Test (expected =  IllegalArgumentException.class)
	public void testSetMinutesNeg(){
		CountDownTimer s = new CountDownTimer();

		//attempting to set minutes to a negative number
		s.setMinutes(-45);
	}

	@Test (expected =  IllegalArgumentException.class)
	public void testSetMinutesLarge(){
		CountDownTimer s = new CountDownTimer();

		//attempting to set minutes to a number larger than 60
		s.setMinutes(75);
	}

	@Test
	public void testSetSeconds(){
		CountDownTimer s = new CountDownTimer();

		//setting seconds to 59
		s.setSeconds(59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test (expected =  IllegalArgumentException.class)
	public void testSetSecondsNeg(){
		CountDownTimer s = new CountDownTimer();

		//attempting to set seconds to a negative number
		s.setSeconds(-43);
	}

	@Test (expected =  IllegalArgumentException.class)
	public void testSetSecondsLarge(){
		CountDownTimer s = new CountDownTimer();

		//attempting to set seconds to a number larger than 60
		s.setSeconds(75);
	}

}
