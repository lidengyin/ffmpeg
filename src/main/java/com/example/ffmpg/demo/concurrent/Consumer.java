package com.example.ffmpg.demo.concurrent;

import com.example.ffmpg.demo.concurrent.Factory;

public class Consumer extends Thread {
    private Factory sharedLocation;

    public Consumer( Factory shared) {
        super ( "Consumer" ) ;
        sharedLocation = shared;
    }

    public void run ( ) {
        int sum = 0;
        for ( int count = 1; count <= 10; count ++ ) {
        try {
            //休眠三秒
        Thread . sleep ( ( int ) ( Math . random ( ) * 3000) ) ;
        //消费商品加一
        sum += sharedLocation. get ( ) ;

        } catch ( InterruptedException e) {
            e. printStackTrace ( ) ;
            }
        }
        System . out. println ( getName ( ) + " 消费总数 " + sum + " 消费结束" ) ;

        }
}
