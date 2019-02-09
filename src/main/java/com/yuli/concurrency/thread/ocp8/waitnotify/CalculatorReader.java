//: com.yuli.concurrency.thread.ocp8.waitnotify.CalculatorReader.java


package com.yuli.concurrency.thread.ocp8.waitnotify;


class Calaulator implements Runnable {

	int total;

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			synchronized (this) {
				for (int i = 0; i < 100; i++) {
					total += i;
					Thread.sleep(10);
				}
				notifyAll();
			}
		} catch (InterruptedException ie) {
		}
	}
}


public class CalculatorReader extends Thread {

	final Calaulator calaulator;

	public CalculatorReader(Calaulator calaulator, String name) {
		this.calaulator = calaulator;
		this.setName(name);
	}

	@Override
	public void run() {
		synchronized (this.calaulator) {
			try {
				System.out.println(">>>>>>> Waiting for calculation result ... ... ");
				this.calaulator.wait();
			} catch (InterruptedException ie) {
			}
			System.out.printf("%nThread %1$s, the calaulation result is %2$d.%n",
					this.getName(), calaulator.total);
		}
	}

	public static void main(String... args) {
		Calaulator calaulator = new Calaulator();
		new CalculatorReader(calaulator, "Thread-1").start();
		new CalculatorReader(calaulator, "Thread-2").start();
		new CalculatorReader(calaulator, "Thread-3").start();
		new Thread(calaulator).start();
	}

}///:~