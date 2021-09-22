package project1;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class CountDownTimer {
	/** used to track the hours of the timer */
	int hours;

	/** used to track the minutes of the timer */
	int minutes;

	/** used to track the seconds of the timer */
	int seconds;

	/** used to determine if the timer is suspended or not allowing it to be mutated or not */
	private static boolean suspend = false;


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
	 * @throws IllegalArgumentException when any of the numbers are negative or if
	 * minutes or seconds are greater than 60
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
	 * A constructor that initializes the instance variables
	 * with the provided values. Initialize hours to 0.
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

	/***********************************************************
	 *
	 *  A constructor that accepts a String as a parameter with the following format: “1:21:30”
	 *  where 1 indicates hours, 21 indicates minutes,  and 30 indicates seconds.
	 *  R the format “15:20” where the 15 indicates minutes, and 20 indicates seconds,
	 *  OR the format “30” where 30 indicates seconds.
	 *
	 * @param startTime The input is the new CountDown Timer in String form separated by colons
	 * @throws IllegalArgumentException An error gets thrown everytime the variables are below zero,
	 * greater than 60 or there are just too many numbers in a single index of the array
	 */
	public CountDownTimer(String startTime) {
		int s = 0;
		int m = 0;
		int h = 0;
		//reading through the string and separating values based on where the colon is
		String[] timeSplit = startTime.split(":");

		//Checking if timeSplit has too many numbers or too little
		if (timeSplit.length > 3 || timeSplit.length <0) {
			throw new IllegalArgumentException();
		}

		//first checking how many indexes are in the array and then assigning the first index in the array
		//to either minutes seconds or hours depending on that.
		if (timeSplit.length == 1){
			if (timeSplit[0].length() > 2){
				throw new IllegalArgumentException();
			}
			s = Integer.parseInt(timeSplit[0]);
		}

		if (timeSplit.length == 2){
			if (timeSplit[0].length() > 2 || timeSplit[1].length() > 2){
				throw new IllegalArgumentException();
			}
			m = Integer.parseInt(timeSplit[0]);
			s = Integer.parseInt(timeSplit[1]);
		}

		if (timeSplit.length == 3){
			if (timeSplit[1].length() > 2 || timeSplit[2].length() > 2) {
				throw new IllegalArgumentException();
			}
			h = Integer.parseInt(timeSplit[0]);
			m = Integer.parseInt(timeSplit[1]);
			s = Integer.parseInt(timeSplit[2]);
		}

		//checking one last time to make sure none of the values are less than 0
		if (h < 0 || m < 0 || s < 0) {
			throw new IllegalArgumentException();
		}

		//reassigning them to their Integer counterparts
		this.hours = h;
		this.minutes = m;
		this.seconds = s;
	}


	/************************************************************
	 *
	 * A method that returns true if  “this” CountDownTimer
	 * object is exactly the same as the other object
	 *
	 * @param other The other object
	 * @return true if the two objects are equal and false if they are not
	 * @throws IllegalArgumentException Throw when the parameter has nothing in it
	 */
	public boolean equals(Object other) {
		if (other != null) {
			CountDownTimer temp = (CountDownTimer) other;
			//checking if all variables are equal to each other
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
	 * A static method that returns true if CountDownTimer object t1
	 * is exactly the same as CountDownTimer object t2
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
	 * A method that returns 1 if “this” CountDownTimer object is greater
	 * than the other CountDownTimer object; returns -1 if “this” CountDownTimer
	 * object is less than the other CountDownTimer; or returns 0 if “this”
	 * CountDownTimer object is equal to the other CountDownTimer object.
	 *
	 * @param other The other object
	 * @return An int that's value says which object is greater or if they are equal
	 * @throws IllegalArgumentException if there is noting in other it throws an error
	 */
	public int compareTo(CountDownTimer other) {
		int totalSeconds = (this.seconds + (this.minutes * 60) + (this.hours * 3600));
		int otherSeconds = (other.seconds + (other.minutes * 60) + (other.hours * 3600));
		if (totalSeconds == otherSeconds) {
			return 0;
		} else if (totalSeconds > otherSeconds) {
			return 1;
		} else {
			return -1;
		}
	}


	/***********************************************************
	 *
	 * A method that returns 1 if  CountDownTimer object t1
	 * is greater than CountDownTimer object t2; returns -1 if the CountDownTimer
	 * object t1 is less than CountDownTimer object t2;
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

		if (t1Seconds == t2Seconds) {
			return 0;
		} else if (t1Seconds > t2Seconds) {
			return 1;
		} else {
			return -1;
		}
	}


	/***********************************************************
	 *
	 * A method that decrements “this” CountDownTimer by 1 second.
	 *
	 * @throws IllegalArgumentException if the timer goes below zero it throws an error
	 */
	public void dec() {
		if (suspend != true) {
			//first convert everything to seconds and then subtract 1
			int total = (this.hours * 3600) + (this.minutes * 60) + this.seconds;
			total--;
			//make sure it does not fall below zero
			if (total < 0)
				throw new IllegalArgumentException();

			//reassign all values to hours, minutes, and seconds
			hours = total / 3600;
			total %= 3600;
			minutes = total / 60;
			seconds = total %= 60;
		}
	}

	/***********************************************************
	 *
	 * A method that subtracts the given number of seconds from “this” CountDownTimer object.
	 *
	 * @param seconds user input of number of seconds to subtract from the countdown timer.
	 */
	public void sub(int seconds) {
		if (suspend != true) {
			//uses a loop to run through dec() just subtracting 1 for every second,
			// and checking if it falls below zero
			for (int i = 0; i < seconds; i++)
				dec();
		}
	}


	/***********************************************************
	 *
	 * A method that subtracts CountDownTimer other from the “this” CountDownTimer object.
	 *
	 * @param other The other Countdown timer that is used to
	 *                subtract its total seconds from the main timer.
	 */
	public void sub(CountDownTimer other) {
		if (suspend != true) {
			//turns other into seconds so we can just reuse our sub() with a seconds parameter
			int temp = other.seconds + (other.minutes * 60) + (other.hours * 3600);
			this.sub(temp);
		}
	}

	/***********************************************************
	 *
	 * A method that increments the “this” CountDownTimer by 1 second.
	 *
	 */
	public void inc() {
		if (suspend != true) {
			//increment seconds by 1 and also checks if it increments an entire new minute or hour
			this.seconds++;
			if (seconds >= 60) {
				seconds = 00;
				minutes++;
				if (minutes >= 60) {
					minutes = 00;
					hours++;
				}
			}
		}
	}

	/***********************************************************
	 *
	 * A method that adds the number of seconds to “this” CountDownTimer object.
	 *
	 * @param seconds user input amount of seconds to add to the current CountDown Timer.
	 */
	public void add(int seconds) {
		if (suspend != true) {
			int temp = this.seconds + seconds;
			int temp2 = temp % 60;
			if (temp < 60) {
				this.seconds = this.seconds + seconds;
			} else {
				this.seconds = temp2;
				this.minutes = this.minutes + (temp / 60);
				if (this.minutes >= 60) {
					this.hours = this.hours + (this.minutes / 60);
					this.minutes = this.minutes % 60;
				} else {
					this.minutes = this.minutes % 60;
				}
			}
		}
	}

	/***********************************************************
	 *
	 * A method that adds CountDownTimer other to “this” CountDownTimer object.
	 *
	 * @param other The other Countdown timer that is used to add its total seconds to the main timer.
	 */
	public void add(CountDownTimer other) {
		if (suspend != true) {
			//turning other into only seconds then using our add() method that's parameter is seconds
			int temp = other.seconds + (other.minutes * 60) + (other.hours * 3600);
			this.add(temp);
		}
	}

	/***********************************************************
	 *
	 * A method that returns a string that represents the state of a
	 * CountDownTimer with the following format:  “1:06:01”. Display the hours as is;
	 * minutes with 2 digits including a leading “0” if minutes < 10, and seconds with 2 digits
	 * again including a leading “0” if seconds < 10.
	 *
	 * @return returns the new String with the numbers being Integers and having ":" seperating them
	 */
	public String toString() {
		int minLength = String.valueOf(minutes).length();
		int secLength = String.valueOf(seconds).length();
		String minLength1;
		String secLength1;
		if (minLength < 2) {
			minLength1 = "0" + String.valueOf(minutes);
		} else {
			minLength1 = String.valueOf(minutes);
		}

		if (secLength < 2) {
			secLength1 = "0" + String.valueOf(seconds);
		} else {
			secLength1 = String.valueOf(seconds);
		}

		return (hours + ":" + minLength1 + ":" + secLength1);
	}

	/***********************************************************
	 *
	 * A  method that loads the “this” CountDownTimer object from a file;
	 * use the parameter filename for the name of the file
	 *
	 * @param fileName
	 */
	public void load(String fileName) {
		try {
			//open the data file
			Scanner fileReader = new Scanner(new File(fileName));
			Scanner lineReader;

			//read one int at a time
			this.hours = fileReader.nextInt();
			this.minutes = fileReader.nextInt();
			this.seconds = fileReader.nextInt();

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

	/***********************************************************
	 *
	 * A method that saves the “this” CountDownTimer to a file;
	 * use the parameter filename for the name of the file.
	 *
	 * @param fileName
	 */
	public void save(String fileName) {
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

	/***********************************************************
	 *
	 * A method that sets suspend to true or false
	 *
	 * @param pSuspend
	 */
	public static void setSuspend(boolean pSuspend) {
		suspend = pSuspend;
	}

	/***********************************************************
	 *
	 *
	 *
	 * @return
	 */
	public static boolean isSuspend() {
		return suspend;
	}

	/***********************************************************
	 *
	 * A method that gets hours from the object then returns it
	 *
	 * @return returns hours variable
	 */
	public int getHours() {
		return hours;
	}

	/***********************************************************
	 *
	 * A method that sets the hours for the object
	 *
	 * @param hours the amount you want to set hours to
	 * @throws IllegalArgumentException throws error if hours is negative
	 */
	public void setHours(int hours) {
		if (suspend != true) {
			if (hours < 0) {
				throw new IllegalArgumentException();
			}
			this.hours = hours;
		}
	}

	/***********************************************************
	 *
	 * a method that gets minutes from the object then returns it
	 *
	 * @return returns minutes variable
	 */
	public int getMinutes() {
		return minutes;
	}

	/***********************************************************
	 *
	 * A method that sets the minutes for the object
	 *
	 * @param minutes the amount you want to set minutes to
	 * @throws IllegalArgumentException throws error if minutes is negative or greater than 60
	 */
	public void setMinutes(int minutes) {
		if (suspend != true) {
			if (minutes < 0 || minutes > 60) {
				throw new IllegalArgumentException();
			}
			this.minutes = minutes;
		}
	}

	/***********************************************************
	 *
	 * a method that gets seconds from the object than returns it
	 *
	 * @return returns seconds variable
	 */
	public int getSeconds() {
		return seconds;
	}

	/***********************************************************
	 *
	 * A method that sets the seconds for the object
	 *
	 * @param seconds the amount of seconds you want to set seconds to
	 * @throws IllegalArgumentException throws error if seconds is negative or greater than 60
	 */
	public void setSeconds(int seconds) {
		if (suspend != true) {
			if (seconds < 0 || seconds > 60) {
				throw new IllegalArgumentException();
			}
			this.seconds = seconds;
		}
	}
}
