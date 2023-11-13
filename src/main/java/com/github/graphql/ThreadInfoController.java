package com.github.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ThreadInfoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping(value = "/thread", produces = MediaType.APPLICATION_JSON_VALUE)
    public ThreadInfo LogThreadInfo() throws InterruptedException {
        final ThreadInfo threadInfo = ThreadInfo.create(Thread.currentThread());
        logger.trace(threadInfo.toString());
        // simulate work
        //Thread.sleep(2000);
        return threadInfo;
    }
}
