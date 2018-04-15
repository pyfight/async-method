package io.github.pyfight.async;

import java.util.concurrent.*;
import java.util.function.*;

/**
 * @author pyfight
 * @date 2018/4/14.
 */
public class AsyncExecutor implements AsyncAware {

    private ExecutorService executorService = new ThreadPoolExecutor(100, 100,
            0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(200),
            new NameThreadFactory("asyncFunction"), new RetryRejectedHandler());

    @Override
    public Future async(Runnable method) {
        return executorService.submit(method);
    }

    @Override
    public <P> Future async(Consumer<P> method, P param) {
        return executorService.submit(() -> method.accept(param));
    }

    @Override
    public <P1, P2> Future async(BiConsumer<P1, P2> method, P1 param1, P2 param2) {
        return executorService.submit(() -> method.accept(param1, param2));
    }

    @Override
    public <R> Future<R> async(Supplier<R> method) {
        return executorService.submit(method::get);
    }

    @Override
    public <P, R> Future<R> async(Function<P, R> method, P param) {
        return executorService.submit(() -> method.apply(param));
    }

    @Override
    public <P1, P2, R> Future<R> async(BiFunction<P1, P2, R> method, P1 param1, P2 param2) {
        return executorService.submit(() -> method.apply(param1, param2));
    }

    protected void destroy() {
        executorService.shutdown();
    }
}
