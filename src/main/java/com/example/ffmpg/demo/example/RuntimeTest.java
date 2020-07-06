package com.example.ffmpg.demo.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RuntimeTest {
    public static void main(String[] args) {
        RuntimeTest s = new RuntimeTest();
        s.test();
    }
    public void test(){
        Runtime run =Runtime.getRuntime();
        try {
            Process p = run.exec("ffmpeg  -i ~/Videos/ffmpegTest/0010.png -f mpegts -codec:v mpeg1video -codec:a mp2  -s 960x540  -b:v 10k  http://121.36.145.230:8081/supersecret");
            InputStream ins= p.getInputStream();
            InputStream ers= p.getErrorStream();
            new Thread(new inputStreamThread(ins)).start();
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    class inputStreamThread implements Runnable{
        private InputStream ins = null;
        private BufferedReader bfr = null;
        public inputStreamThread(InputStream ins){
            this.ins = ins;
            this.bfr = new BufferedReader(new InputStreamReader(ins));
        }
        @Override
        public void run() {
            String line = null;
            byte[] b = new byte[100];
            int num = 0;
            try {
                while((num=ins.read(b))!=-1){
                    System.out.println(new String(b,"gb2312"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
