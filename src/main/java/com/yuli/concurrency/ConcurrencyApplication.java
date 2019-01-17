package com.yuli.concurrency;


import com.yuli.concurrency.thread.LoggingWidget;
import com.yuli.concurrency.thread.ocp8.DefiningThreads;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ConcurrencyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LoggingWidget loggingWidget = new LoggingWidget();
		loggingWidget.doSomething();

		// Defining threads to run Runnables
		DefiningThreads definingThreads = new DefiningThreads();
		definingThreads.testRunable();
	}

}///:~