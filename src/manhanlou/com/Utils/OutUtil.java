package manhanlou.com.Utils;

public class OutUtil {
    public static void outOptions(String str){
        System.out.println("\t\t"+str);
    }

    public static void outTitle(String str){
        System.out.println("========="+str+"=========");
    }

    public static void outComm(String str){
        System.out.print("- 输入操作 > "+str);
    }
    public static void outInd(String str){
        System.out.println("- 系统提示 > "+str);
    }
    public static void outErr(String str){
        System.out.println("- 错   误 > "+str);
    }

}
