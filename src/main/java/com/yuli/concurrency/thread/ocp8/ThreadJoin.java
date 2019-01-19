//: com.yuli.concurrency.thread.ocp8.ThreadJoin.java


package com.yuli.concurrency.thread.ocp8;


import com.yuli.concurrency.IDetective;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;


@Slf4j
public class ThreadJoin implements IDetective {

	@Override
	public void investigate() {

		Thread thread = new Thread(() -> {
			IntStream.range(1, 10)
					.forEach(i -> {
						IntStream.range(1, i)
								.forEach(j -> System.out.print("+"));
						System.out.println();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ie) {}
					});
		});

		log.info(">>>>>>> Waiting for the thread to be finished ... ...");

		thread.start();
		try {
			thread.join();
		} catch (InterruptedException ie) {}

		log.info(">>>>>>> The thread has been finished.");
	}

}///:~