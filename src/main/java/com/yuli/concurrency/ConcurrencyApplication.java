package com.yuli.concurrency;


import com.yuli.concurrency.thread.LoggingWidget;
import com.yuli.concurrency.thread.ocp8.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Slf4j
@SpringBootApplication
public class ConcurrencyApplication implements CommandLineRunner {

	private final Map<String, IDetective> targets = new HashMap<>();

	public ConcurrencyApplication() {
		this.targets.put(DefiningThreads.class.getSimpleName(),
				new DefiningThreads());
		this.targets.put(ThreadJoin.class.getSimpleName(),
				new ThreadJoin());
		this.targets.put(ThreadScheduler.class.getSimpleName(),
				new ThreadScheduler());
		this.targets.put(SynchronizingBlock.class.getSimpleName(),
				new SynchronizingBlock());
	}

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			String target = args[0];
			log.info(">>>>>>> The target is {}", target);
			Optional.ofNullable(this.targets.get(target))
					.ifPresent(i -> i.investigate());
		}
	}

}///:~