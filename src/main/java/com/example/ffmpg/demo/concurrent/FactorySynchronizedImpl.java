package com.example.ffmpg.demo.concurrent;

import com.example.ffmpg.demo.concurrent.Factory;

/**
  * 注意get set两个方法使用同一把锁
  */
public class FactorySynchronizedImpl implements Factory {
    //标识操作序号
    private int item = - 1;
    //标识是否可以购买以及当前库存量
    private int sign = 0; // >0是是可以买 ==0是不能卖
    //标识最大库存
    private static final int maxSize = 3; // 最大库存


    //synchronized标识异步方法，已经是轻量级的了

    /**
     * 生产者生产
     * @param value
     */
    public synchronized void set ( int value ) {
        //获取当前线程是生产者还是消费者
        String name = Thread . currentThread ( ) . getName ( ) ;
        //判断库存是否超过最大库存
        while ( sign >= maxSize) {
        try {
            //命令行显示
        displayState(   name + " 现在库存已满!等待..." ) ;
        //阻塞线程使得消费者消费
        wait () ;
            } catch ( InterruptedException e) {
            e. printStackTrace ( ) ;
            }
        }

        item = value ;
        //
        // 增加一个库存
        ++ sign ;
        displayState( name + " 生产 " + item ) ;
        // 唤醒同锁线程:唤醒消费者,消费者消费,生产者继续执行
        notify ( ) ;
    }

    public synchronized int get ( ) {
        String name = Thread . currentThread ( ) . getName ( ) ;
        //是否等待生产者生产
        while ( sign <= 0) {
        try {
            displayState( "无产品 " + name + " 等待" ) ;
            //释放当前锁,让出CPU,进入等待状态,直到生产者唤醒或者sign>0
                wait ( ) ;
            } catch ( InterruptedException e) {
                e. printStackTrace ( ) ;
            }
        }
        //消费一个商品
        -- sign ;
        displayState( name + " 买 " + item ) ;
        // 唤醒同锁线程:唤醒一个或者多个正处于等待状态的线程,然后向下执行
        notify ( ) ;
        return item ;
    }

    public void displayState( String operation ) {
    System . out. println ( "操作序号:" + item + "库存:" + sign + "/t" + operation ) ;
 }
}
