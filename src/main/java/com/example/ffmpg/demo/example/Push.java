package com.example.ffmpg.demo.example;

import java.io.IOException;

public class Push {
    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("ffmpeg  -i /home/lidengyin/Videos/ffmpegTest/video-01.avi -f image2  %04d.png\n");
        
    }
}
