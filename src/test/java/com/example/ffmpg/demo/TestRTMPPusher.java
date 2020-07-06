//package com.example.ffmpg.demo;
//
//
//import com.example.ffmpg.demo.rtmp.Pusher;
//import org.bytedeco.javacv.CanvasFrame;
//
//public class TestRTMPPusher {
//    public static void main(String[] args) throws Exception {
//        //String url="rtsp://192.168.56.1:554/Sword";
//        String url="rtmp://121.36.145.230:1935/mylive/4";
//        Pusher pusher = new Pusher(url);
//        CanvasFrame cf = Windows.build("测试RTMP推流", w -> {
//            System.out.println("窗口已关闭！");
//            try {
//                pusher.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        pusher.push(f -> {
//            cf.showImage(f);
//        });
//        pusher.start();
//    }
//}
//
