import java.math.BigInteger;

class TrivialBigNatural {
	private BigInteger value;

	public TrivialBigNatural() {
		value = new BigInteger("0");
	}

	public TrivialBigNatural(int init) {
		value = new BigInteger(String.valueOf(init));
	}

	public TrivialBigNatural(String init) {
		value = new BigInteger(init);
	}

	public TrivialBigNatural(TrivialBigNatural b) {
		value = new BigInteger(b.toString());
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public void increment() {
		value = value.add(BigInteger.ONE);
	}

	public void decrement() {
		if (!value.equals(BigInteger.ZERO)) {
			value = value.subtract(BigInteger.ONE);
		}
	}

}
