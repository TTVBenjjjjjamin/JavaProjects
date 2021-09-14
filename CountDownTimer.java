package project1;

public class CountDownTimer {
	int hours;
	int minutes;
	int seconds;


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

	public CountDownTimer(int seconds) {
		if (seconds < 0 || seconds >= 60) {
			throw new IllegalArgumentException();
		}
		this.seconds = seconds;
		this.minutes = 0;
		this.hours = 0;
	}

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
	 * @param other
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
		this.seconds--;
		if (seconds < 0){
			seconds = 59;
			minutes --;
			if (minutes < 0){
				minutes = 59;
				hours--;
			}
		}
	}

	public void sub(int seconds) {
		int temp = this.seconds - seconds;
		int temp2 = seconds % 60;
		int temp3 = seconds / 60;
		int temp4 = temp2 - this.seconds;
		if (temp > 0) {
			this.seconds = this.seconds - seconds;
		} else {
			int positive = Math.abs(temp4 - 60);
			this.seconds = positive;
			//this.minutes;
			//if (this.minutes < 0){
				//this.hours = this.hours - (this.minutes/60);
				//this.minutes = this.minutes
			//}
		}
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
					this.seconds = this.seconds + seconds;
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
