package io.github.pyfight.async;

import java.util.concurrent.Future;
import java.util.function.*;

/**
 * 异步调用器，将方法变成异步调用
 *
 * @author pyfight
 * @date 2018/4/14.
 */
public interface AsyncAware {
    /**
     * 无入参，无返回值的异步执行方法 , void noStaticFoo()
     *
     * @param method 要执行的方法，如 user::noStaticFoo;
     * @return Future对象，用以判断是否执行结束
     */
    Future async(Runnable method);

    /**
     * 有单个入参，无返回值的异步执行方法，如 void noStaticFoo(Long id)
     *
     * @param method 要执行的方法，如, user::noStaticFoo
     * @param param  方法执行的入参，如id
     * @param <P>    入参类型，如Long
     * @return Future对象，用以判断是否执行结束
     */
    <P> Future async(Consumer<P> method, P param);

    /**
     * 有两个参数但是无返回值的异步执行方法, 如void noStaticFoo(Long id,Entity entity)
     *
     * @param method 要执行的方法，如 , user::noStaticFoo
     * @param param1 第一个入参值，如id
     * @param param2 二个入参值，如entity
     * @param <P1>   第一个入参类型
     * @param <P2>   第二个入参类型
     * @return Future对象，用以判断是否执行结束
     */
    <P1, P2> Future async(BiConsumer<P1, P2> method, P1 param1, P2 param2);

    /**
     * 无参数有返回值的异步执行方法 , Entity noStaticFoo()
     *
     * @param method 要执行的方法，如 , user::noStaticFoo
     * @param <R>    返回值类型,如 Entity
     * @return Future对象，用以判断是否执行结束、获取返回结果
     */
    <R> Future<R> async(Supplier<R> method);

    /**
     * 单个入参，有返回值的异步执行方法 , Entity noStaticFoo(Long id)
     *
     * @param method 要执行的方法，如 , user::noStaticFoo
     * @param param  入参值，如 id
     * @param <P>    入参类型，如Long
     * @param <R>    返回值类型,如 Entity
     * @return Future对象，用以判断是否执行结束、获取返回结果
     */
    <P, R> Future<R> async(Function<P, R> method, P param);

    /**
     * 单个入参，有返回值的异步执行方法 , Entity noStaticFoo(Long id)
     *
     * @param method 要执行的方法，如 , user::noStaticFoo
     * @param param1 第一个入参值，如id
     * @param param2 二个入参值，如entity
     * @param <P1>   第一个入参类型
     * @param <P2>   第二个入参类型
     * @param <R>    返回值类型,如 Entity
     * @return Future对象，用以判断是否执行结束、获取返回结果
     */
    <P1, P2, R> Future<R> async(BiFunction<P1, P2, R> method, P1 param1, P2 param2);
}
