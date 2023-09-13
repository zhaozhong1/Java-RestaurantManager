package manhanlou.com.Services;

import manhanlou.com.Bean.Bill;
import manhanlou.com.Bean.DiningTable;
import manhanlou.com.Bean.DishesMenu;
import manhanlou.com.Bean.Emp;
import manhanlou.com.Dao.BillDAO;
import manhanlou.com.Dao.DiningTableDAO;
import manhanlou.com.Dao.DishesMenuDAO;
import manhanlou.com.Dao.EmpDAO;
import manhanlou.com.Utils.OutUtil;

/**
 * 该类用于收集各种常用查询，如餐桌查询、员工查询、菜单查询、账单查询等，通过对传入的编号进行判断。
 * 该类也收集各种输入有效性判断，输入无效则返回false.
 */

public class CommonSelectManager {
    private static BillDAO bd = new BillDAO();
    private static DiningTableDAO dtd = new DiningTableDAO();
    private static DishesMenuDAO dmd = new DishesMenuDAO();
    private static EmpDAO ed = new EmpDAO();

    public static boolean tableIsNotExist(int No){
        String sql = "SELECT * FROM diningTable WHERE NO=?";
        DiningTable diningTable = dtd.querySingleRow(sql, DiningTable.class, No);
        if(diningTable == null){
            OutUtil.outErr("该餐桌不存在。");
        }

        return diningTable == null;
    }

    public static boolean tableStateQuery(String state,int No){
        String sql = "SELECT state FROM diningTable WHERE No=?";
        Object o = dtd.querySingleRes(sql, No);
        String _state = (String) o;
        return _state.equals(state);
    }

    public static boolean empIsExist(int id){
        String sql = "SELECT * FROM emp WHERE id=?";
        Emp emp = ed.querySingleRow(sql, Emp.class, id);

        if(emp == null){
            OutUtil.outErr("该员工不存在。");
        }

        return emp != null;
    }

    public static boolean menuIsExist(int No){
        String sql = "SELECT * FROM menu WHERE NO=?";
        DishesMenu dishesMenu = dmd.querySingleRow(sql, DishesMenu.class, No);
        if(dishesMenu == null){
            OutUtil.outErr("该菜品不存在。");
        }

        return dishesMenu != null;
    }

    public static boolean billIsExist(int No){
        String sql = "SELECT * FROM bill WHERE NO=?";
        Bill bill = bd.querySingleRow(sql, Bill.class, No);
        if(bill == null){
            OutUtil.outErr("该账单不存在。");
        }

        return bill != null;
    }

    //菜品输入数字不得在1~5之外
    public static boolean menuNumsInput(int nums){
        if(!(nums > 0 && nums <= 5)){
            OutUtil.outErr("该数字不合法");
        }

        return nums > 0 && nums <= 5;

    }

}
