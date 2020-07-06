package com.example.ffmpg.demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class AAA {
    public static void makePro(int[] pro, int id, int i, int mutex, int full, int empty) {
        if(mutex == 1) {
            mutex = 0;
            if(empty >= 1) {
                boolean flag = true;
                while(flag) {
                    System.out.println("在二死循环");
                    i = i % 10;

                    if(pro[i] == 0) {
                        System.out.println("pro[i]:"+pro[i]);
                        pro[i] = id;
                        full++;
                        empty--;
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            BufferedWriter out = new BufferedWriter(new FileWriter("F:\\操作系统\\实验二\\result.txt", true));
                            out.write(i+": "+id+"\r\n");
                            out.close();
                        } catch (IOException e) {
                            System.out.println("文件写入失败！");
                        }
                        flag = false;
                    }else {
                        i++;
                    }
                }
            }
            mutex = 1;
        }
    }

    public static void movePro(int[] pro, int i, int mutex, int full, int empty) {
        if (mutex == 1) {
            mutex = 0;
            if(full >= 1) {
                boolean flag = true;
                while(flag) {
                    i = i % 10;

                    System.out.println("在一死循环");
                    if(pro[i] != 0) {
                        pro[i] = 0;
                        full--;
                        empty++;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            BufferedWriter out = new BufferedWriter(new FileWriter("F:\\操作系统\\实验二\\result.txt", true));
                            out.write(i+": "+"0"+"\r\n");
                            out.close();
                        } catch (IOException e) {
                            System.out.println("文件写入失败！");
                        }
                        flag = false;
                    }else {
                        i++;
                    }
                }
            }
            mutex = 1;
        }
    }

    public static void main(String[] args) {
        int[] buffer = new int[10];
        int num = 0, mutex = 1, full = 0, empty = 10;
        for(int i=0; i<20; i++) {
            int max=4;
            int min=1;
            Random random = new Random();
            int s = random.nextInt(max)%(max-min+1) + min;
            System.out.println(s);
            if(s == 4) {
                movePro(buffer, num, mutex, full, empty);
            }else {
                makePro(buffer, s, num, mutex, full, empty);
            }
        }

    }

}