package com.yanglibo.thread;

/**
 * Created by Administrator on 2016/10/6.
 */
public class SafeDemo {
    private int[] arr = {10, 10, 10, 10, 10};

    final Bank bank = new Bank();

    public static void main(String[] args) {
        SafeDemo unSafeDemo = new SafeDemo();
        for (int j = 0; j < 10000; j++) {
            new Thread(unSafeDemo.new TransferRunner()).start();
        }
    }

    class TransferRunner implements Runnable {

        @Override
        public void run() {
            int fromAccount = (int) (arr.length * Math.random());
            bank.transfer(0, fromAccount, 5);
        }
    }

    class Bank {

        //此处添加 synchronized关键字，输出的结果会一直为：total=50
        synchronized void transfer(int fromAccount, int toAccount, int amount) {
            arr[fromAccount] -= 10;
            arr[toAccount] += 10;
            System.out.println("total=" + getTotal());
        }

        int getTotal() {
            int result = 0;
            for (int i = 0; i < arr.length; i++) {
                result += arr[i];
            }
            return result;
        }
    }
}
