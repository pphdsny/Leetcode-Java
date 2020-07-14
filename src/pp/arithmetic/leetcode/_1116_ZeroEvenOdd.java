package pp.arithmetic.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Created by wangpeng on 2020-07-09.
 * 1116. 打印零与奇偶数
 * <p>
 * 假设有这么一个类：
 * <p>
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 * public void zero(printNumber) { ... }  // 仅打印出 0
 * public void even(printNumber) { ... }  // 仅打印出 偶数
 * public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 * <p>
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出："0102030405"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1116_ZeroEvenOdd {

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        IntConsumer printNumber = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        };
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    zeroEvenOdd.zero(printNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    zeroEvenOdd.even(printNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    zeroEvenOdd.odd(printNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    static class ZeroEvenOdd {
        private int n;
        private Semaphore zeroSe = new Semaphore(1);
        private Semaphore evenSe = new Semaphore(0);
        private Semaphore oddSe = new Semaphore(0);

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                zeroSe.acquire();
                printNumber.accept(0);
                if (i % 2 == 0) {
                    evenSe.release();
                } else {
                    oddSe.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i+=2) {
                evenSe.acquire();
                printNumber.accept(i);
                zeroSe.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i+=2) {
                oddSe.acquire();
                printNumber.accept(i);
                zeroSe.release();
            }
        }
    }
}
