package manhanlou.com.Main;

import manhanlou.com.Bean.Bill;
import manhanlou.com.Bean.DiningTable;
import manhanlou.com.Dao.BillDAO;
import manhanlou.com.Dao.DiningTableDAO;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) {


        System.out.println(LocalDateTime.now());
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if(m == 0){
            for(int i = 0;i<n;i++){
                nums1[i] = nums2[0];
            }
        }
        if(n == 0){
            return;
        }
        for(int i = m;i<m+n;i++){
            nums1[i] = nums2[i-m];
        }
        Arrays.sort(nums1);
        System.out.println(Arrays.toString(nums1));

    }


}
