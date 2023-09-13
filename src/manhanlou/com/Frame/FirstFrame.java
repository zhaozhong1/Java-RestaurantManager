package manhanlou.com.Frame;

import manhanlou.com.Bean.Emp;
import manhanlou.com.Dao.EmpDAO;
import manhanlou.com.Services.EmpManager;
import manhanlou.com.Utils.OutUtil;
import manhanlou.com.Utils.ReadUtil;

public class FirstFrame {

    public static boolean frame(){
        boolean b = false;
        while(!b){
            OutUtil.outTitle("满汉楼一级菜单");
            OutUtil.outOptions("1 登陆满汉楼");
            OutUtil.outOptions("2 退出满汉楼");

            OutUtil.outComm("输入选项号：");
            int in = ReadUtil.readInt();

            if(in == 1){
                EmpManager em = new EmpManager();
                b = em.loginSystem();

            }else if(in == 2){
                return false;
            }else{
                OutUtil.outErr("未知操作");
            }
        }
        return true;
    }
}
