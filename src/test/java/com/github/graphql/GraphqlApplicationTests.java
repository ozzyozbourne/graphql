package com.github.graphql;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
final class GraphqlApplicationTests {

	private static final int REQUEST = 1000;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final RestTemplate template = new RestTemplate();

	@Test
	void loadTestWithVirtualThreads() {
		final long before = System.currentTimeMillis();
		try(final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
			IntStream.range(0, REQUEST).forEach(i -> executorService.submit(() -> {
				final ThreadInfo info =  template.getForObject("http://localhost:8080/thread", ThreadInfo.class);
				log.trace("Completed -> {}", info);
			}));
			log.trace("All request send away after ms: {}", System.currentTimeMillis() - before);
		}
	}

}
