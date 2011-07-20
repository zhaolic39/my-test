package KXT303.W2;

public class Main_MyList {
	public static void main(String[] args) {
		Device device = new Device(1);
		long[] seq = new long[50000];
		MyList mylist = new MyList(2);
		int index = 0;
		long startTime = System.currentTimeMillis();

		try {
			while (index <= 49999) {
				long next = mylist.takeFirst();
				seq[index] = next;
				index++;
				for (int i = 0; i < 7; i++) {
					mylist.addNumber(device.f(next, i));
					
				}
				mylist.sayDone();
			}
		} catch (Exception e) {
			System.out.println("Device failed.");
		}
		// System.out.println("seq[49999] = " + seq[49999]);
		System.out.println("Total time = "
				+ (System.currentTimeMillis() - startTime));
	}
}
