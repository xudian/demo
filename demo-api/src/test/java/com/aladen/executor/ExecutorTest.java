package com.aladen.executor;

import com.aladen.DemoApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.ExecutorService;

public class ExecutorTest extends DemoApplicationTests {

    @Autowired
    @Qualifier("taskExecutor")
    private ExecutorService executorService;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            final int a = i;
            executorService.execute(new Thread(() -> {System.out.println(a);}));
        }
    }
}
