package com.sjtu.yifei.algorithm;

/**
 * 生产／消费者模型
 */
public class ProducerAndConsumerTest {

    //缓冲区域最大值
    private static final int FULL_BUFFER_SIZE = 10;

    private static int buffer = 0;

    private static final String lock = "lock";

    public static void main(String[] strs) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Producer()).start();
    }

    public static class Producer implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                while (buffer == FULL_BUFFER_SIZE) {//生产已饱和
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer++;
                System.out.println("producer buffer " + buffer);
                lock.notifyAll();
            }
        }
    }

    public static class Consumer implements Runnable {

        @Override
        public void run() {
            synchronized(lock) {
                while (buffer == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer--;
                System.out.println("Customer buffer " + buffer);
                lock.notifyAll();
            }
        }
    }


}
