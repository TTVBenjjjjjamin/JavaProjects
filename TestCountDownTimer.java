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
//	@Test (expected = IllegalArgumentException.class)
//	public void testConstructorString1ParameterAlpha() {
//		new CountDownTimer("a");
//	}
//
//	@Test (expected = IllegalArgumentException.class)
//	public void testConstructorStringLarge() {
//		new CountDownTimer("1:23:45:678");
//	}



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
	public void testDec1Second() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// dec 1
		s.dec();
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 59);
		assertEquals(s.getSeconds(), 58);
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

	@Test (expected = IllegalArgumentException.class)
	public void testEqualsNull() {
		CountDownTimer s = new CountDownTimer();
		s.equals(null);
	}
}
