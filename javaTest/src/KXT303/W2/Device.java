package KXT303.W2;

import java.util.Random;

public class Device {
	protected static final long COMPUTE_LATENCY = 0;
	protected static final long BOOT_LATENCY = 00;
	static volatile int operationCount = 0;
	final Random gen;
	final int faultFreq;
	final int multipliers[] = { 3, 5, 7, 11, 13, 17, 19 };

	public Device(int s) {
		gen = new Random(s);
		faultFreq = 50 + gen.nextInt(100);
		 System.out.println("faultFreq "+faultFreq);
	}

	public static void computationalDelay() throws InterruptedException {
		Thread.sleep(COMPUTE_LATENCY);
	}

	private void delays() throws InterruptedException {
		computationalDelay();
		if (gen.nextInt(faultFreq) < operationCount++) {
			operationCount = 0;
			Thread.sleep(BOOT_LATENCY);
		}
	}

	public long f(long l, int i) throws InterruptedException {

		delays();
		return l * multipliers[i];
	}

	public long g(long l, int i) throws InterruptedException {

		delays();
		if (l % multipliers[i] > 0)
			return 0;
		return l / multipliers[i];
	}
}
