//: com.yuli.concurrency.thread.ocp8.collections.TransferQueueTest.java


package com.yuli.concurrency.thread.ocp8.collections;


import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class TransferQueueTest {

	private TransferQueue<String> transferQueue;

	private long numOfMsg2Produce;
	private long numOfMsg2Consume;

	private ExecutorService exService;

	private TransferElementProducer producer;
	private TransferElementConsumer consumer;

	@Before
	public void setUp() throws Exception {
		this.transferQueue = new LinkedTransferQueue<>();
		this.exService = Executors.newFixedThreadPool(2);
	}

	@Test
	public void test_One_Producer_And_None_Consumer() throws Exception {

		// Given
		this.numOfMsg2Produce = 7;

		this.producer = new TransferElementProducer(this.transferQueue,
				"Producer-1", this.numOfMsg2Produce);

		// When
		this.exService.execute(this.producer);

		// Then
		this.exService.awaitTermination(10 * 1000, TimeUnit.MILLISECONDS);
		this.exService.shutdown();
		System.out.println("Is the executor service shutdown? " + this.exService.isShutdown());

		assertThat(this.producer.getNumberOfProducedMessages(), is(0L));
		assertThat(this.transferQueue.getWaitingConsumerCount(), is(0));
	}

	@Test
	public void test_One_Producer_And_One_Consumer() throws Exception {

		// Given
		this.numOfMsg2Produce = 7;
		this.numOfMsg2Consume = 7;

		this.producer = new TransferElementProducer(this.transferQueue,
				"Producer-1", this.numOfMsg2Produce);

		this.consumer = new TransferElementConsumer(this.transferQueue,
				"Consumer-1", this.numOfMsg2Consume);

		// When
		this.exService.submit(this.consumer);
		this.exService.submit(this.producer);

		// Then
		this.exService.awaitTermination(10 * 1000, TimeUnit.MILLISECONDS);
		this.exService.shutdown();
		System.out.println("Is the executor service shutdown? " +
				this.exService.isShutdown());

		assertThat(this.producer.getNumberOfProducedMessages(),
				is(this.numOfMsg2Produce));
		assertThat(this.consumer.getNumberOfConsumedMessages(),
				is(this.numOfMsg2Consume));
		assertThat(this.transferQueue.getWaitingConsumerCount(), is(0));
	}

	@Test
	public void when_Multi_Consumers_And_Multi_Producers_Then_Process_All_Messages() throws Exception {

		// Given
		this.numOfMsg2Produce = 3;
		this.numOfMsg2Consume = 3;

		this.producer = new TransferElementProducer(this.transferQueue,
				"Producer-1", this.numOfMsg2Produce);

		TransferElementProducer producer_2 = new TransferElementProducer(
				this.transferQueue, "Producer-2", this.numOfMsg2Produce);

		this.consumer = new TransferElementConsumer(this.transferQueue,
				"Consumer-1", this.numOfMsg2Consume);

		TransferElementConsumer consumer_2 = new TransferElementConsumer(
				this.transferQueue, "Consumer-2", this.numOfMsg2Consume);

		this.exService = Executors.newFixedThreadPool(4);

		// When
		this.exService.submit(this.consumer);
		this.exService.submit(consumer_2);
		this.exService.submit(this.producer);
		this.exService.submit(producer_2);

		// Then
		this.exService.awaitTermination(12 * 1000, TimeUnit.MILLISECONDS);
		this.exService.shutdown();
		System.out.println("Is the executor service shutdown? " +
				this.exService.isShutdown());

		assertThat(this.producer.getNumberOfProducedMessages(),
				is(this.numOfMsg2Produce));
		assertThat(producer_2.getNumberOfProducedMessages(),
				is(this.numOfMsg2Produce));
		assertThat(this.consumer.getNumberOfConsumedMessages(),
				is(this.numOfMsg2Consume));
		assertThat(consumer_2.getNumberOfConsumedMessages(),
				is(this.numOfMsg2Consume));
		assertThat(this.transferQueue.getWaitingConsumerCount(), is(0));
	}

}///:~