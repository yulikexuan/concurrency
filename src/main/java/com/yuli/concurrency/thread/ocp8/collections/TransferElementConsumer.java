//: com.yuli.concurrency.thread.ocp8.collections.TransferElementConsumer.java


package com.yuli.concurrency.thread.ocp8.collections;


import com.yuli.concurrency.RuntimeInterruptedException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;


@Slf4j
public class TransferElementConsumer implements Runnable {

	static final int TAKE_SLEEP = 500;

	private final String name;
	private final TransferQueue<String> transferQueue;
	private final long numberOfMessageToConsume;
	public final AtomicLong numberOfConsumedMessages;

	public TransferElementConsumer(
			TransferQueue<String> transferQueue, String name,
			long numberOfMessageToConsume) {

		this.name = name;
		this.transferQueue = transferQueue;
		this.numberOfMessageToConsume = numberOfMessageToConsume;
		this.numberOfConsumedMessages = new AtomicLong();
	}

	@Override
	public void run() {
		LongStream.range(0, numberOfMessageToConsume)
				.forEach(i -> this.consume());
	}

	public long getNumberOfConsumedMessages() {
		return this.numberOfConsumedMessages.get();
	}

	private void consume() {
		try {
			String msg = this.transferQueue.take();
			log.info("=======> {} is processing {}", this.name, msg);
			this.numberOfConsumedMessages.incrementAndGet();
			Thread.sleep(TAKE_SLEEP);
		} catch (InterruptedException ie) {
			throw new RuntimeInterruptedException(ie);
		}
	}

}///:~