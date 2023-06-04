package algorithm.search;

public class SearchDemos {
    /* 基本查找/顺序查找 O(n) */
    public static int basicSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    /* 二分查找/折半查找（只能对有序数列进行二分查找） O(log(n)) */
    public static int binarySearch(int[] arr, int key) {
        int min = 0;
        int max = arr.length - 1;
        while (min < max) {
            int mid = (min + max) / 2;
            if (key < arr[mid]) max = mid - 1;
            else if (key > arr[mid]) min = mid + 1;
            else return mid;
        }
        return -1;
    }

    /* 插值查找（二分查找优化，对等差有序数列优化） */
    public static int interpolationSearch(int[] arr, int key) {
        int min = 0;
        int max = arr.length - 1;
        while (min < max) {
            int mid = min + (((key - arr[min]) / (arr[max] - arr[min])) * (max - min));
            if (key < arr[mid]) max = mid - 1;
            else if (key > arr[mid]) min = mid + 1;
            else return mid;
        }
        return -1;
    }

    /* 斐波那契查找 根据斐波那契数来搜索有序数组中的元素
     * 斐波那契数满足 F（n） = F（n-1） + F（n-2），F（0） = 0，F（1） = 1
     * 在算法的每次迭代中，搜索范围都会减少大约 1/φ 倍，其中φ是黄金比例 （φ ≈ 1.618）
     */
    public static int fibonacciSearch(int[] arr, int x) {
        /* 初始化斐波那契数 */
        int fibMMm2 = 0; // (m-2)'th Fibonacci No.
        int fibMMm1 = 1; // (m-1)'th Fibonacci No.
        int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci

        int n = arr.length - 1; // n 为数列的元素数量

        /* fibM 存储大于或等于 n 的斐波那契数 */
        while (fibM < n) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }

        // 偏移量，标记已消除的范围
        int offset = -1;

        /* 将arr[fibMm2]与x进行比较。
        当fibM变为1时，fibMm2变为0 */
        while (fibM > 1) {
            // 检查i是否合法
            int i = Math.min(offset + fibMMm2, n - 1);

            /* 如果x大于
            索引fibMm2，剪切子数组数组
            从偏移到i */
            if (arr[i] < x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }
            /* 如果x小于index处的值
            fibMm2，在i+1之后切割子阵列 */
            else if (arr[i] > x) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            }
            /* 找到元素返回下标 */
            else return i;
        }

        /* 比较最后的元素和 x */
        if (fibMMm1 == 1 && arr[n - 1] == x) return n - 1;

        /* 没有找到元素返回 -1 */
        return -1;
    }

}
