package com.example.ffmpg.demo.concurrent;

//在同一个进程地址空间内执行的两个线程。
// 生产者线程生产物品，然后将物品放置在一个空缓冲区中供消费者线程消费。
// 消费者线程从缓冲区中获得物品，然后释放缓冲区。
// 当生产者线程生产物品时，如果没有空缓冲区可用，那么生产者线程必须等待消费者线程释放出一个空缓冲区。
// 当消费者线程消费物品时，如果没有满的缓冲区，那么消费者线程将被阻塞，直到新的物品被生产出来。

public class Test {
        public static void main( String args[ ] ) {
                //构建对象
        FactorySynchronizedImpl factory = new FactorySynchronizedImpl( ) ;
        factory. displayState( "初始化" ) ;
        //生产
        Producer p = new Producer( factory) ;
        //消费
        Consumer c = new Consumer( factory) ;
        //线程执行run不确定
        p. start ( ) ;
        c. start ( ) ;
    }
}
