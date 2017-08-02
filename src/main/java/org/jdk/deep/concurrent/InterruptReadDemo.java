package org.jdk.deep.concurrent;

import java.io.IOException;

/**
 * Created by junfengchen on 7/26/2017.
 */
public class InterruptReadDemo {
    private static class A extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println(System.in.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("exit");
        }

        public void cancel() {
            try {
                System.in.close();
                System.out.println("close the stream in cancelling");
            } catch (IOException e) {
                e.printStackTrace();
            }
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        A t = new A();
        t.start();
        Thread.sleep(1000);

        System.out.println("before cancelling case");
        t.cancel();
    }
}