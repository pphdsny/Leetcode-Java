package pp.arithmetic.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Created by wangpeng on 2020-07-16.
 * 1195. 交替打印字符串
 * <p>
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 * <p>
 * 假设有这么一个类：
 * <p>
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 * public void fizz(printFizz) { ... }          // only output "fizz"
 * public void buzz(printBuzz) { ... }          // only output "buzz"
 * public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 * public void number(printNumber) { ... }      // only output the numbers
 * }
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 * <p>
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1195_FizzBuzz {

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(16);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    fizzBuzz.fizz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("fizz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    fizzBuzz.buzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("buzz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    fizzBuzz.fizzbuzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("fizzbuzz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    fizzBuzz.number(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 解题思路：对于多线程的问题，无非是加锁、等待、解锁、通知，
     */
    static class FizzBuzz {
        private int n;
        private int pn = 1;
        private Semaphore fs = new Semaphore(0);
        private Semaphore bs = new Semaphore(0);
        private Semaphore fbs = new Semaphore(0);
        private Semaphore ns = new Semaphore(1);

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            while (pn <= n) {
                fs.acquire();
                if (pn > n) break;
                printFizz.run();
                pn++;
                notifyPrint();
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            while (pn <= n) {
                bs.acquire();
                if (pn > n) break;
                printBuzz.run();
                pn++;
                notifyPrint();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (pn <= n) {
                fbs.acquire();
                if (pn > n) break;
                printFizzBuzz.run();
                pn++;
                notifyPrint();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            while (pn <= n) {
                ns.acquire();
                if (pn > n) break;
                printNumber.accept(pn);
                pn++;
                notifyPrint();
            }
        }

        private void notifyPrint() {
            if (pn > n) {
                fs.release();
                bs.release();
                fbs.release();
                ns.release();
                return;
            }
            boolean m3 = pn % 3 == 0;
            boolean m5 = pn % 5 == 0;
            if (m3 && m5) {
                fbs.release();
            } else if (m3) {
                fs.release();
            } else if (m5) {
                bs.release();
            } else {
                ns.release();
            }
        }
    }
}
