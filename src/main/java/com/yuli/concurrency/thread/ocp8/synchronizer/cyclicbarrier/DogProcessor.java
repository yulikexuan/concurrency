package com.yuli.concurrency.thread.ocp8.synchronizer.cyclicbarrier;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;

public class DogProcessor implements Runnable {

    private final String[] dogs;
    private final CyclicBarrier cyclicBarrier;

    public DogProcessor(String[] dogs, CyclicBarrier cyclicBarrier) {
        this.dogs = dogs;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        Arrays.stream(this.dogs)
                .map(this::convert)
                .collect(Collectors.toList()).toArray(dogs);

        try {
            System.out.println(Thread.currentThread().getName() + " is waiting ... ");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException ibe) {
            ibe.printStackTrace();
        }
    }


    private String convert(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
