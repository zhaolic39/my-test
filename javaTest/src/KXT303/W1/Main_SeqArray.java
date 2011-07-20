package KXT303.W1;

class Main_SeqArray {

	public static void main(String[] args) {
		
		Device device = new Device(1);
		long[] seq = new long[50000];
		int index = 0;
		
		long test = 2;
		
		
		long startTime = System.currentTimeMillis();
		
		seq[index] = test;
		
		try {
			while (index != 49999) {
				boolean found = false; // Find next number in the sequence

				while (!found) {
					long seek;
					int j;

					test+=2;

					for (int i = 6; !found && i >= 0; i--) {
						seek = device.g(test, i);
						if (seek < 2)
							continue;
						j = index;

						while (!found && j >= 0 && seq[j] > seek)
							j--;
						if (found = (seq[j] == seek))
							break;
					}
				}

				seq[++index] = test;
				if (index%10==9) System.out.print(".");
				if (index%1000==999)
					System.out.println("seq[" + index + "] = " + test);
			}
		} catch (Exception e) {
			System.out.println("Device failed.");
		}
		System.out.println("seq[1999] = " + seq[49999]);	
		System.out.println("Total time = "+(System.currentTimeMillis()-startTime));
	}
}
