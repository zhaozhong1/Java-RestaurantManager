package manhanlou.com.Services;

import manhanlou.com.Bean.Emp;
import manhanlou.com.Dao.EmpDAO;
import manhanlou.com.Utils.OutUtil;
import manhanlou.com.Utils.ReadUtil;

public class EmpManager {

    private static Emp loginEmp = null;

    public boolean loginSystem(){
        OutUtil.outComm("输入员工号：");
        String id = ReadUtil.readString(16);
        OutUtil.outComm("输入密  码：");
        String pwd = ReadUtil.readString(16);
        //查询数据库 如果输入正确，返回真
        EmpDAO empDAO = new EmpDAO();
        String sql = "SELECT * FROM emp WHERE id = ? AND pwd = MD5(?)";
        loginEmp = empDAO.querySingleRow(sql, Emp.class, id, pwd);
        if(loginEmp!=null){
            return true;
        }else {//错误，打印错误结果
            OutUtil.outErr("登陆失败，账号名或密码错误！");
        }
        return false;
    }

    public static Emp getLoginEmp() {
        return loginEmp;
    }

}
