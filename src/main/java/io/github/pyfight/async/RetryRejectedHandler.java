package io.github.pyfight.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池拒绝策略，会尝试加入任务队列（默认尝试三次），如果无法加入，则抛出 {@link RejectedExecutionException}
 *
 * @author pyfight
 * @date 2018/4/14.
 */
public class RetryRejectedHandler implements RejectedExecutionHandler {
    private static final int DEFAULT_TIMES = 3;

    /**
     * 重试次数
     */
    private int retryTimes;

    public RetryRejectedHandler() {
        this(DEFAULT_TIMES);
    }

    public RetryRejectedHandler(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        BlockingQueue<Runnable> queue = executor.getQueue();
        for (int i = retryTimes; i > 0; i--) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RejectedExecutionException("[新任务被拒绝] - 重试线程中断 " + this.getTaskString(r, executor), e);
            }

            if (queue.offer(r)) {
                return;
            }

        }

        throw new RejectedExecutionException("[新任务被拒绝] - 开启线程/加入队列失败 " + this.getTaskString(r, executor));
    }

    private String getTaskString(Runnable r, ThreadPoolExecutor executor) {
        return r.toString() +
                " rejected from " +
                executor.toString();
    }
}
