public class SlowBigNatural implements BigNatural {

	/**
	 * @convention natural.length &gt; 0 and <br />
	 			   for all i: integer, <br />
	 			   		0 &lt; i &lt; natural.length and natural[i] is in {0,1,2,...,9}
	 * @correspondence Natural n = BigNatural(natural[0].toString + natural[1].toString + ... + natural[i].toString) where i = natural.length-1
	 */

	private byte[] natural;

	// A default constructor. This constructor initializes the natural number to
	// 0.
	
	/**
	 * Creates the default array of bytes (natural) of length 1 and value 0; requires no input.
	 */
	public SlowBigNatural() {
		natural = new byte[1];
		natural[0] = 0;
	}

	// A one-argument constructor that takes an int argument
	/**
	 * Takes an int and converts it to an array of bytes (natural)
	 * @param x int
	 */
	public SlowBigNatural(int x) {
		String digits;
		char[] temp;
		digits = "" + x; // Convert number to string and then to an array of
							// chars
		temp = digits.toCharArray();
		natural = new byte[temp.length];
		for (int i = 0; i < temp.length; i++) { // Convert the digit chars in
												// the array to bytes and store
												// in byte array (math model)
			int k = java.lang.Character.digit(temp[i], 10);
			natural[i] = (byte) k;
		}
	}

	// A one-argument constructor that takes a String argument
	/**
	 * Takes a String and converts it to an array of bytes (natural)
	 * @param str String
	 */
	public SlowBigNatural(String str) { // Same steps as the int conversion,
										// after
		// number has been converted to string
		char[] temp;
		temp = str.toCharArray();
		natural = new byte[temp.length];
		for (int i = 0; i < temp.length; i++) {
			int k = java.lang.Character.digit(temp[i], 10);
			natural[i] = (byte) k;
		}
	}

	// A one-argument constructor that takes a BigNatural argument
	/**
	 * Takes a BigNatural number and converts it to an array of bytes (natural)
	 * @param num BigNatural
	 */
	public SlowBigNatural(BigNatural num) {
		String digits;
		char[] temp;
		digits = num.toString(); // Same steps as above
		temp = digits.toCharArray();
		natural = new byte[temp.length];
		for (int i = 0; i < temp.length; i++) {
			int k = java.lang.Character.digit(temp[i], 10);
			natural[i] = (byte) k;
		}
	}

	// Method "increment", which increases the value of the number by 1 (and
	// returns nothing)
	public void increment() {
		final int last_pos = natural.length - 1;
		final byte upper = 9;

		// Take care of size increase cases (i.e. 69, 499 etc.) by changing
		// those 9's to 0's
		int i;
		for (i = last_pos; i >= 0 && natural[i] == upper; i--) {
			natural[i] = 0;
		}
		if (i >= 0)// Increment normally if there's no size change
		{
			natural[i]++;
		} else // For cases like 999, 9 etc., swap into a new array with
				// incremented length and add the additional digit i.e. 1
		{
			byte[] arrayTemp = new byte[natural.length + 1];
			System.arraycopy(natural, 0, arrayTemp, 1, natural.length);
			arrayTemp[0] = 1;
			natural = arrayTemp;
		}

	}

	// Method "decrement", which decreases the value of the number by 1 if
	// possible, otherwise leaves it unchanged (and returns nothing).
	public void decrement() {
		final int last_pos = natural.length - 1;
		final byte lower = 0;

		int i;

		if (natural[0] != 0) // Do nothing if the natural number is 0
		{
			// Change the 0's at the end to 9's in naturals like 6000, 20, 500
			// etc.
			for (i = last_pos; natural[i] == lower; i--) {
				natural[i] = 9;
			}
			natural[i]--;
			// If the number of digits is more than 1 and all the digits were
			// 9's changed to 0's, shorten the array by swapping into a new
			// array
			if (i == 0 && (natural[i] == 0 && last_pos > 0)) {
				byte[] arrayTemp = new byte[natural.length - 1];
				System.arraycopy(natural, 1, arrayTemp, 0, arrayTemp.length);
				natural = arrayTemp;
			}
		}
	}

	// Method "toString", which returns a string representing the value of the
	// natural number
	public String toString() {
		String str = "";
		// Loop through and output the characters one after the other, from the
		// beginning
		for (int i = 0; i < natural.length; i++) {
			str = str + natural[i];
		}
		return str;
	}

}
