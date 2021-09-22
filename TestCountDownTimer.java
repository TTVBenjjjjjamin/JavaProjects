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

	@Test
	public void testDefaultConstructor() {
		CountDownTimer s = new CountDownTimer();
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
	}

	// All 3 parameter constructor input tests
	//
	//
	//
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


	//All 2 parameter constructor input tests
	//
	//
	//
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


	//All 1 parameter constructor input tests
	//
	//
	//
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterNegSeconds(){
		new CountDownTimer(-43);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor1ParameterLargeSeconds(){
		new CountDownTimer(67);
	}

	@Test
	public void testConstructor1Parameter(){
		CountDownTimer s = new CountDownTimer(12);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 12);
	}


	// Tests for Other parameter constructor
	@Test
	public void testConstructorOtherParameter(){
		CountDownTimer b = new CountDownTimer(1, 4, 6);
		CountDownTimer s = new CountDownTimer(b);
		assertTrue(s.getHours() == 1);
		assertTrue(s.getMinutes() == 4);
		assertTrue(s.getSeconds() == 6);
	}

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

		assertTrue(s.getHours() == 1);
		assertTrue(s.getMinutes() == 23);
		assertTrue(s.getSeconds() == 32);
	}

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
	public void testDec1Second() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// dec 1
		s.dec();
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 58);
	}

	@Test
	public void testDec1Second2(){
		CountDownTimer s = new CountDownTimer(1,0,0);

		//dec1 testing on the seams to see if hours correctly goes to 0 and the others to 59
		s.dec();
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 59);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testDecPastZero(){
		CountDownTimer s = new CountDownTimer(0,0,0);

		//dec1
		s.dec();
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
	public void testEqualsNull() {
		CountDownTimer s = new CountDownTimer();
		s.equals(null);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testEquals2ParametersNull(){
		CountDownTimer s = new CountDownTimer();
		CountDownTimer s2 = new CountDownTimer();

		s.equals(null,s2);
	}

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
	public void testSettMinutes(){
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

}
