package pp.arithmetic.other;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2018/8/28.
 * 排序算法，从小到大
 */
public class Sort {
    public static void main(String[] args) {
        int maxSize = 5;
        Util.printArray(bubbleSort(Util.generateArrayBySize(maxSize)));
        Util.printArray(insertionSort(Util.generateArrayBySize(maxSize)));
        Util.printArray(selectionSort(Util.generateArrayBySize(maxSize)));
        Util.printArray(shellSort(Util.generateArrayBySize(maxSize)));
        Util.printArray(mergeSort(Util.generateArrayBySize(maxSize)));
        Util.printArray(quickSort(Util.generateArrayBySize(maxSize)));
    }

    /**
     * 冒泡排序
     * 1、比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 3、针对所有的元素重复以上的步骤，除了最后一个。
     * 4、持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * 时间复杂度：O(n^2)，最优时间复杂度：O(n)，平均时间复杂度：O(n^2)
     *
     * @param nums
     */
    public static int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //最好的复杂度O(n)
            int flag = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    flag = 1;
                }
            }
            if (flag == 0) {
                break;
            }
        }
        return nums;
    }

    /**
     * 插入排序
     * 1、从第一个元素开始，该元素可以认为已经被排序
     * 2、取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3、如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5、将新元素插入到该位置后
     * 6、重复步骤2~5
     * 时间复杂度：O(n^2)，最优时间复杂度：O(n),平均时间复杂度：O(n^2)
     *
     * @param nums
     * @return
     */
    public static int[] insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            //最优解就是已经排序好，不需要比较
            while (j >= 1 && nums[j] < nums[j - 1]) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
                j--;
            }
        }
        return nums;
    }

    /**
     * 选择排序
     * 首先在未排序序列中找到最小元素，存放到排序序列的起始位置，
     * 然后，再从剩余未排序元素中继续寻找最小元素，然后放到已排序序列的末尾。
     * 时间复杂度：O(n^2)，最优时间复杂度：O(n^2),平均时间复杂度：O(n^2)
     *
     * @param nums
     * @return
     */
    public static int[] selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

    /**
     * 希尔排序
     * 希尔排序通过将比较的全部元素分为几个区域来提升插入排序的性能。
     * 这样可以让一个元素可以一次性地朝最终位置前进一大步。
     * 然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，
     * 但是到了这步，需排序的数据几乎是已排好的了（此时插入排序较快）。
     * 时间复杂度：根据步长而不同，最优时间复杂度：O(n),平均时间复杂度：根据步长而不同
     *
     * @param nums
     * @return
     */
    public static int[] shellSort(int[] nums) {
        int length = nums.length;
        int h = 1;
        while (h < length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                int j = i;
                while (j >= h && nums[j] < nums[j - h]) {
                    int temp = nums[j];
                    nums[j] = nums[j - h];
                    nums[j - h] = temp;
                    j -= h;
                }
            }
            h /= 3;
        }
        return nums;
    }

    /**
     * 堆排序
     * 创建最大堆（Build_Max_Heap）：将堆所有数据重新排序
     * 堆排序（HeapSort）：移除位在第一个数据的根节点，并做最大堆调整的递归运算
     * 时间复杂度：O(nlogn)，最优时间复杂度：O(nlogn),平均时间复杂度：O(nlogn)
     *
     * @param nums
     * @return
     */
    public static int[] heapSort(int[] nums) {
        // TODO: 2018/8/30 堆的相关概念还是不理解 
        return nums;
    }

    /**
     * 归并操作（merge），也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作。
     * <p>
     * 时间复杂度：O(nlogn)，最优时间复杂度：O(n),平均时间复杂度：O(nlogn),空间复杂度O(n)
     * <p>
     * 总结：先拆分，再排序
     *
     * @param nums
     * @return
     */
    public static int[] mergeSort(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }
        int[] left = split(nums, 0, nums.length / 2);
        int[] right = split(nums, nums.length / 2, nums.length);

        int[] mergeLeft = mergeSort(left);
        int[] mergeRight = mergeSort(right);
        int[] merge = merge(mergeLeft, mergeRight);
        return merge;
    }

    private static int[] split(int[] num, int start, int end) {
        int[] retNum = new int[end - start];
        int index = 0;
        for (int i = start; i < end; i++) {
            retNum[index] = num[i];
            index++;
        }
        return retNum;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] newNum = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                newNum[i + j] = left[i];
                i++;
            } else {
                newNum[i + j] = right[j];
                j++;
            }
        }
        if (i < left.length) {
            for (int k = i; k < left.length; k++) {
                newNum[i + j] = left[k];
                i++;
            }
        }
        if (j < right.length) {
            for (int k = j; k < right.length; k++) {
                newNum[i + j] = right[k];
                j++;
            }
        }
        return newNum;
    }

    /**
     * 快速排序
     * 1、从数列中挑出一个元素，称为"基准"（pivot），
     * 2、重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * 3、递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * 时间复杂度：O(n^2)，最优时间复杂度：O(nlogn),平均时间复杂度：O(nlogn)
     * <p>
     * 总结：先粗糙排序，在拆分继续粗糙排序
     *
     * @param nums
     * @return
     */
    public static int[] quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public static void quickSort(int[] nums, int low, int hight) {
        if (low >= hight) {
            return;
        }

        int partition = partition(nums, low, hight);
        quickSort(nums, low, partition);
        quickSort(nums, partition + 1, hight);
    }

    private static int partition(int[] nums, int start, int end) {
        if (start >= end) {
            return start;
        }
        int pivot = nums[start];//  基准点
        while (start < end) {
            while (start < end) {// 从数组尾部往前循环得到小于哨兵元素的一个元素
                if (nums[end--] < pivot) {
                    nums[start++] = nums[++end];
                    break;
                }
            }

            while (start < end) {// 从数组头部往后循环得到大于哨兵元素的一个元素
                if (nums[start++] > pivot) {
                    nums[end--] = nums[--start];
                    break;
                }
            }
        }
        nums[start] = pivot;
        return start;
    }
}
