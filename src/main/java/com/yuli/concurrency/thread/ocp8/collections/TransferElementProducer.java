//: com.yuli.concurrency.thread.ocp8.collections.TransferElementProducer.java


package com.yuli.concurrency.thread.ocp8.collections;


import com.yuli.concurrency.RuntimeInterruptedException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;


@Slf4j
public class TransferElementProducer implements Runnable {

	static final String MESSAGE_PREFIX = "Message";
	static final int TRANSFER_TIME_OUT = 1000;

	private final TransferQueue<String> transferQueue;
	private final String name;
	private final long numberOfMessageToProduce;
	private final AtomicLong numberOfProducedMessages;

	public TransferElementProducer(
			@NonNull final TransferQueue<String> transferQueue,
			@NonNull final String name,
			@NonNull long numberOfMessageToProduce) {

		this.transferQueue = transferQueue;
		this.name = name;
		this.numberOfMessageToProduce = numberOfMessageToProduce;
		this.numberOfProducedMessages = new AtomicLong();
	}

	@Override
	public void run() {
		long producedNum = LongStream.range(0, this.numberOfMessageToProduce)
				.filter(this::produceMessage)
				.count();
		this.numberOfProducedMessages.addAndGet(producedNum);
	}

	public long getNumberOfProducedMessages() {
		return this.numberOfProducedMessages.get();
	}

	private boolean produceMessage(long i) {
		String msg = String.format("%1$s_%2$d", MESSAGE_PREFIX, i);
		try {
			log.info("=======> {} is waiting for transfering '{}'",
					this.name, msg);
			return this.transferQueue.tryTransfer(msg, TRANSFER_TIME_OUT,
					TimeUnit.MILLISECONDS);
		} catch (InterruptedException ie) {
			throw new RuntimeInterruptedException(ie);
		}
	}

}///:~