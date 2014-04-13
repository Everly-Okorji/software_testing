/**
 * @mathmodel BigNatural is modeled by <A href="http://download.oracle.com/javase/1.4.2/docs/api/java/lang/Number.html">Number</A>
 * @constraint n &gt;= 0
 * @author Everly Okorji
 */
public interface BigNatural {

	/**
	 * Increases the value of the number by 1. This method does not return a value.
	 * @ensures n = #n + 1;
	 */
	public void increment();

	/**
	 * Decreases the value of the number by 1. If the number is 0 (lowest possible
	 * natural), the method does nothing. Does not return a value.
	 * 
	 * @ensures if #n &lt; 0, n = #n - 1 else #n = n
	 */
	public void decrement();

	/**
	 * Converts the BigNatural number to a string. The string contains only the
	 * digits of the number—no commas, decimals, leading zeros, or other
	 * extraneous characters. For example, this method would return "0", "134",
	 * and "4332993" for the corresponding natural numbers.
	 * 
	 * @return a string representing the value of the natural number.
	 */
	public String toString();

}
