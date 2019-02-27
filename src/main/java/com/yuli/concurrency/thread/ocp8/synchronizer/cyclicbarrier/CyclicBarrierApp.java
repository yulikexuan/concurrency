//: com.yuli.concurrency.thread.ocp8.synchronizer.cyclicbarrier.CyclicBarrierApp.java


package com.yuli.concurrency.thread.ocp8.synchronizer.cyclicbarrier;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierApp {

    String[] dogsMale = {"max", "charlie", "cooper", "buddy",
            "jack", "rocky", "oliver", "bear", "duke", "tucker"};

    String[] dogsFemale =  {"bella", "lucy", "daisy", "luna",
            "lola", "sadie", "molly", "maggie", "bailey", "sophie"};

    private final List<String> result = new ArrayList<>();

    private final CyclicBarrier cyclicBarrier;

    public CyclicBarrierApp() {
        this.cyclicBarrier = new CyclicBarrier(2, this::collect);
    }

    public static void main(String[] args) {

        CyclicBarrierApp cyclicBarrierApp = new CyclicBarrierApp();

        Thread t1 = new Thread(new DogProcessor(cyclicBarrierApp.dogsMale,
                cyclicBarrierApp.cyclicBarrier));
        Thread t2 = new Thread(new DogProcessor(cyclicBarrierApp.dogsFemale,
                cyclicBarrierApp.cyclicBarrier));

        t1.setName("Processor-1");
        t2.setName("Processor-2");

        t1.start();
        t2.start();

        System.out.println("Main Thread is done!");
    }

    /*
     * Performed by the last thread entering the barrier.
     */
    private void collect() {
        Arrays.stream(this.dogsMale).forEach(this.result::add);
        Arrays.stream(this.dogsFemale).forEach(this.result::add);
        System.out.printf("\r%1$s: the final names are %2$s.",
                Thread.currentThread().getName(), this.result);
    }

}///:~