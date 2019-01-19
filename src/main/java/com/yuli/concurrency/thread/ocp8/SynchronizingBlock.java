//: com.yuli.concurrency.thread.ocp8.SynchronizingBlock.java


package com.yuli.concurrency.thread.ocp8;


import com.yuli.concurrency.IDetective;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;


@Slf4j
public class SynchronizingBlock implements Runnable, IDetective {

	public static final int CHECK_INDEX = 0;
	public static final char FINAL_LETTER = 'Z';

	private final StringBuffer letterBuffer;

	public SynchronizingBlock() {
		this.letterBuffer = new StringBuffer("A");
		log.info(">>>>>>> The length of the buffer is {}",
				this.letterBuffer.length());
	}

	@Override
	public void investigate() {
		Thread printer_1 = new Thread(this, "printer_1");
		Thread printer_2 = new Thread(this, "printer_2");
		Thread printer_3 = new Thread(this, "printer_3");
		printer_1.start();
		printer_2.start();
		printer_3.start();
	}

	@Override
	public void run() {
		synchronized (this.letterBuffer) {
			while (this.letterBuffer.charAt(CHECK_INDEX) <= FINAL_LETTER) {
				char letter = this.letterBuffer.charAt(CHECK_INDEX);
				System.out.print(Thread.currentThread().getName() + " -------> ");
				IntStream.range(1, 30)
						.forEach(i -> {
							System.out.print(letter + " ");
							try {
								Thread.yield();
								Thread.sleep(10);
							} catch (InterruptedException ie) {}
						});

				this.letterBuffer.setCharAt(CHECK_INDEX, (char) (letter + 1));

				try {
					Thread.yield();
					Thread.sleep(100);
				} catch (InterruptedException ie) {}

				System.out.println();
			}
		}
	}

}///:~