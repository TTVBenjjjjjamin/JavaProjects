package project1;

import java.io.*;
import java.util.Scanner;

public class CountDownTimer {
	int hours;
	int minutes;
	int seconds;
	private static boolean suspend = true;


	/*****************************************************
	 *
	 *Default constructor that sets the CountDownTimer to zero
	 *
	 */
	public CountDownTimer() {
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}


	/*****************************************************
	 *
	 * A constructor that initializes the instance variables with the provided values.
	 *
	 * @param hours initializes hours
	 * @param minutes initializes minutes
	 * @param seconds initializes seconds
	 * @throws IllegalArgumentException when any of the numbers are negative or if minutes or seconds are greater than 60
	 */
	public CountDownTimer(int hours, int minutes, int seconds) {
		if (hours < 0 || minutes < 0 || seconds < 0 || minutes >= 60 || seconds >= 60) {
			throw new IllegalArgumentException();
		}
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}


	/***********************************************************
	 *
	 * A constructor that initializes the instance variables with the provided values. Initialize hours to 0.
	 *
	 * @param minutes initializes minutes
	 * @param seconds initializes seconds
	 * @throws IllegalArgumentException When either of the parameters are less than 0 or more than 60
	 */
	public CountDownTimer(int minutes, int seconds) {
		if (minutes < 0 || seconds < 0 || minutes >= 60 || seconds >= 60) {
			throw new IllegalArgumentException();
		}
		this.minutes = minutes;
		this.seconds = seconds;
		this.hours = 0;
	}


	/***********************************************************
	 *
	 *A constructor that initializes the instance variable seconds with the provided value.
	 * Initialize hours and minutes to 0.
	 *
	 * @param seconds initializes seconds
	 * @throws IllegalArgumentException when the parameter seconds is less than 0 or more than 60
	 */
	public CountDownTimer(int seconds) {
		if (seconds < 0 || seconds >= 60) {
			throw new IllegalArgumentException();
		}
		this.seconds = seconds;
		this.minutes = 0;
		this.hours = 0;
	}

	/***********************************************************
	 *
	 * A constructor that initializes the instance variables with the other
	 * CountDownTimer parameter’s instance variables.
	 *
	 * @param other The other object is becoming initialized
	 */
	public CountDownTimer(CountDownTimer other) {
			this.hours = other.hours;
			this.minutes = other.minutes;
			this.seconds = other.seconds;
	}

	public CountDownTimer(String startTime) {
		String[] timeSplit = startTime.split(":");

//		System.out.println(timeSplit[0]);
//		if (timeSplit.length() == 0){
//			this.hours = 0;
//			this.minutes = 0;
//			this.seconds = timeSplit[0];
//		}
	}


	/************************************************************
	 *
	 * A method that returns true if  “this” CountDownTimer object is exactly the same as the other object
	 *
	 * @param other The other object
	 * @return true if the two objects are equal and false if they are not
	 * @throws IllegalArgumentException Throw when the parameter has nothing in it
	 */
	public boolean equals(Object other) {
		if (other != null) {
			CountDownTimer temp = (CountDownTimer) other;
			if (this.seconds == temp.seconds && this.minutes == temp.minutes && this.hours == temp.hours) {
				return true;
			} else {
				return false;
			}

		}
		throw new IllegalArgumentException();
	}


	/***********************************************************
	 *
	 * A static method that returns true if CountDownTimer object t1 is exactly the same as CountDownTimer object t2
	 *
	 * @param t1 the first CountDownTimer Object to compare
	 * @param t2 the second CountDownTimer Object to compare
	 * @return true if the two objects are equal and false if they are not
	 * @throws IllegalArgumentException Throw when the parameter has nothing in it
	 */
	public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
		if (t1 != null && t2 != null) {
			if (t1.seconds == t2.seconds && t1.minutes == t2.minutes & t1.hours == t2.hours) {
				return true;
			} else {
				return false;
			}
		}
		throw new IllegalArgumentException();
	}

	/***********************************************************
	 *
	 * A method that returns 1 if “this” CountDownTimer object is greater than the other CountDownTimer object;
	 * returns -1 if “this” CountDownTimer object is less than the other CountDownTimer;
	 * or returns 0 if “this” CountDownTimer object is equal to the other CountDownTimer object.
	 *
	 * @param other The other object
	 * @return An int that's value says which object is greater or if they are equal
	 * @throws IllegalArgumentException if there is noting in other it throws an error
	 */
	public int compareTo(CountDownTimer other) {
		int totalSeconds = (this.seconds + (this.minutes * 60) + (this.hours *3600));
		int otherSeconds = (other.seconds + (other.minutes * 60) + (other.hours * 3600));
		if (other != null) {
			if (totalSeconds == otherSeconds) {
				return 0;
			} else if (totalSeconds > otherSeconds) {
				return 1;
			} else {
				return -1;
			}
		}
		throw new IllegalArgumentException();
	}

	/***********************************************************
	 *
	 * A method that returns 1 if  CountDownTimer object t1 is greater than CountDownTimer object t2;
	 * returns -1 if the CountDownTimer object t1 is less than CountDownTimer object t2;
	 * returns 0 if the CountDownTimer object t1 is equal to CountDownTimer object t2.
	 *
	 * @param t1 A first countdown timer used to compare
	 * @param t2 A second countdown timer used to compare
	 * @return An int that's value says which object is greater or if they are equal
	 * @throws IllegalArgumentException if either of the objects have nothing in them it throws an error
	 */
	public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
		int t1Seconds = (t1.seconds + (t1.minutes * 60) + (t1.hours * 3600));
		int t2Seconds = (t2.seconds + (t2.minutes * 60) + (t2.hours * 3600));

		if (t1 != null && t2 != null) {
			if (t1Seconds == t2Seconds) {
				return 0;
			} else if (t1Seconds > t2Seconds) {
				return 1;
			} else {
				return -1;
			}
		}
		throw new IllegalArgumentException();
	}

	public void dec() {
		int total = (this.hours * 3600) + (this.minutes * 60) + this.seconds;
		total--;
		if (total < 0)
			throw new IllegalArgumentException();

		hours = total / 3600;
		total %= 3600;
		minutes = total / 60;
		seconds = total %= 60;

	}

	public void sub(int seconds) {
		for (int i = 0; i < seconds; i++)
			dec();
	}


	public void sub(CountDownTimer other){
		int temp = other.seconds + (other.minutes * 60) + (other.hours * 3600);
		this.sub(temp);
	}

	public void inc(){
		this.seconds ++;
		if (seconds >=60){
			seconds = 00;
			minutes++;
			if(minutes >= 60){
				minutes = 00;
				hours++;
			}

		}
	}

	public void add(int seconds) {
		int temp = this.seconds + seconds;
		int temp2 = temp % 60;
		if (temp < 60){
			this.seconds = this.seconds + seconds;
		}else {
			this.seconds = temp2;
			this.minutes = this.minutes + (temp / 60);
			if (this.minutes >= 60) {
				this.hours = this.hours + (this.minutes/60);
				this.minutes = this.minutes % 60;
			} else {
				this.minutes = this.minutes % 60;
			}
		}
	}

	public void add(CountDownTimer other){
		int temp = other.seconds + (other.minutes * 60) + (other.hours * 3600);
		this.add(temp);
	}

	public String toString(){
		int minLength = String.valueOf(minutes).length();
		int secLength = String.valueOf(seconds).length();
		String minLength1;
		String secLength1;
		if (minLength < 2){
			minLength1 = "0" + String.valueOf(minutes);
		} else {
			minLength1 = String.valueOf(minutes);
		}

		if (secLength < 2){
			secLength1 = "0" + String.valueOf(seconds);
		} else {
			secLength1 = String.valueOf(seconds);
		}

		return (hours + ":" + minLength1 + ":" + secLength1);
	}

	public void load(String fileName) {
		//if (!CountDownTimer.suspend) {
		//  int hours;
		//  int minutes;
		// int seconds;

		//Scanner fileReader = null;
		try {
			//open the data file
			Scanner fileReader = new Scanner(new File(fileName));

			//read one int at a time
			hours = fileReader.nextInt();
			minutes = fileReader.nextInt();
			seconds = fileReader.nextInt();

			System.out.println(hours);
			System.out.println(minutes);
			System.out.println(seconds);
		}
		// problem reading the file
		catch (Exception error) {
			throw new RuntimeException();
			// System.out.println("Oops!  Something went wrong.");
		}
	}


	public void save (String fileName){
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(hours);
		out.println(minutes);
		out.println(seconds);
		out.close();
	}


	public static void setSuspend(boolean pSuspend) {
		suspend = pSuspend;
	}

	public static boolean isSuspend(){
		return suspend;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		if (hours < 0){
			throw new IllegalArgumentException();
		}
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		if (minutes < 0 || minutes > 60) {
			throw new IllegalArgumentException();
		}
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		if (seconds < 0 || seconds > 60){
			throw new IllegalArgumentException();
		}
		this.seconds = seconds;
	}
}
