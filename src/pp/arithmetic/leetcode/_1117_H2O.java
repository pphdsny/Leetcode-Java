package pp.arithmetic.leetcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by wangpeng on 2020-07-15.
 * 1117. H2O 生成
 * <p>
 * 现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
 * <p>
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 * <p>
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 * <p>
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 * <p>
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * <p>
 * 换句话说:
 * <p>
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * 书写满足这些限制条件的氢、氧线程同步代码。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: "HOH"
 * 输出: "HHO"
 * 解释: "HOH" 和 "OHH" 依然都是有效解。
 * 示例 2:
 * <p>
 * 输入: "OOHHHH"
 * 输出: "HHOHHO"
 * 解释: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 "OHHOHH" 依然都是有效解。
 *  
 * <p>
 * 提示：
 * <p>
 * 输入字符串的总长将会是 3n, 1 ≤ n ≤ 50；
 * 输入字符串中的 “H” 总数将会是 2n 。
 * 输入字符串中的 “O” 总数将会是 n 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/building-h2o
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1117_H2O {


    public static void main(String[] args) {

        int n = 6;
        H2O h2O = new H2O();
        for (int i = 0; i < n * 2; i++) {
            //H
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        h2O.hydrogen(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("H");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        for (int i = 0; i < n; i++) {
            //O
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        h2O.oxygen(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("O");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    //使用系统类进行优化
    class H2O2 {

        private Semaphore hs;
        private Semaphore os;
        private CyclicBarrier totalBarrier;

        public H2O2() {
            hs = new Semaphore(2);
            os = new Semaphore(1);
            //await用于标识等待所有的线程都达到barrier才继续执行
            totalBarrier = new CyclicBarrier(3);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hs.acquire();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            try {
                totalBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            hs.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            os.acquire();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            try {
                totalBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            os.release();
        }
    }


    static class H2O {

        private final Object lock = new Object();
        private int hc = 2;
        private int oc = 1;

        public H2O() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

            boolean flag = false;
            synchronized (lock) {
                while (hc == 0) {
                    lock.wait();
                    synchronized (lock) {
                        if (hc > 0) {
                            hc--;
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) hc--;
                // releaseHydrogen.run() outputs "H". Do not change or remove this line.
                releaseHydrogen.run();
                reset();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            boolean flag = false;
            synchronized (lock) {
                while (oc == 0) {
                    lock.wait();
                    synchronized (lock) {
                        if (oc > 0) {
                            oc--;
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) oc--;
                // releaseOxygen.run() outputs "O". Do not change or remove this line.
                releaseOxygen.run();
                reset();
            }
        }

        private void reset() {
            if (hc == 0 && oc == 0) {
                hc = 2;
                oc = 1;
                lock.notifyAll();
            }
        }
    }
}
