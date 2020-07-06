package com.example.ffmpg.demo.concurrent;

/**
 * Thread构建新的线程,因为是一个线程类,所以生产者与消费者公用一个线程
 */
class Producer extends Thread {
        private Factory sharedLocation;

        public Producer( Factory shared) {
        super ( "Producer" ) ;
        sharedLocation = shared;
  }

    /**
     * 线程执行方法
     */
        public void run ( ) {
            //十次循环
        for ( int count = 1; count <= 10; count ++ ) {
            try {
                //睡眠两秒之内
             Thread . sleep ( ( int ) ( Math . random ( ) * 2000) ) ;
             //开始生产
                sharedLocation. set ( count ) ;
            } catch ( InterruptedException e) {
                e. printStackTrace ( ) ;
            }
        }
        System . out. println ( getName ( ) + " 生产结束" ) ;
    }
}
