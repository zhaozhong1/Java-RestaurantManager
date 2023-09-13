package manhanlou.com.Services;

import manhanlou.com.Bean.Bill;
import manhanlou.com.Dao.BillDAO;
import manhanlou.com.Dao.DiningTableDAO;
import manhanlou.com.Dao.DishesMenuDAO;
import manhanlou.com.Utils.OutUtil;
import manhanlou.com.Utils.ReadUtil;

import java.util.List;

public class BillServicesManager {

    BillDAO bd = new BillDAO();
    DiningTableDAO dtd = new DiningTableDAO();

    DishesMenuDAO dmd = new DishesMenuDAO();



    //查看账单服务
    public void showCheck(){
        String sql = "SELECT * FROM bill";
        List<Bill> bills = bd.queryMulti(sql, Bill.class);
        System.out.println("账单编号\t\t菜品编号\t\t数量\t\t\t金额\t\t\t\t日期\t\t\t\t\t\t账单状态\t\t桌号");

        if(bills != null){
            for(Bill bill : bills){
                System.out.println(bill.toString());
            }
        }else{
            OutUtil.outErr("暂时没有账单。。");
        }


    }

    public void check(){
        /*
        1.将订单状态修改为已结账->billDAO
        2.将餐桌状态修改为空闲->diningTableDAO
         */
        int checkTableNo;
        OutUtil.outComm("输入需要结账的餐桌(-1退出)：");

        do{
            checkTableNo = ReadUtil.readInt();
            if(checkTableNo == -1){
                OutUtil.outInd("退出结账,结账未成功");
                return;
            }
        }while(CommonSelectManager.tableIsNotExist(checkTableNo));

        /*
            餐桌的状态必须是'用餐中'才可进行结账操作
         */

        if(!CommonSelectManager.tableStateQuery("用餐",checkTableNo)){
            OutUtil.outErr("该餐桌不能结账！请检查餐桌状态。");
            return;
        }

        /*
            查询出来的账单格式保留单号（暂未添加）和桌号，新增Total，并且得到一个该餐桌点菜的列表。
         */

        String getSingleTableMenuListSQL = "SELECT menuID,nums,Money From bill WHERE diningTableID=? AND state != '已结账'";

        List<Bill> checkTableMenuList = bd.queryMulti(getSingleTableMenuListSQL, Bill.class, checkTableNo);

        if (checkTableMenuList != null) {

            System.out.println("菜名\t\t数量\t\t金额\t\t点单日期");

            String menuNameSelSQL = "SELECT name FROM menu WHERE No = ?";

            double total = 0;

            for(Bill bill : checkTableMenuList){
                Object selRes = dmd.querySingleRes(menuNameSelSQL, bill.getMenuID());
                String name = (String) selRes;
                total+= bill.getMoney();
                System.out.println(name+"\t"+bill.getNums()+"\t\t"+ bill.getMoney()+"\t\t"+bill.getDate());
            }

            System.out.println("总金额："+total);

        }

        /*
            输入支付方式：
         */
        int payWay;
        do {
            OutUtil.outComm("请输入支付方式(信用卡-1/支付宝-2/微信-3/现金-4)：");
            payWay = ReadUtil.readInt();
        } while (payWay<0||payWay>4);


        // 修改账单状态为'已结账'，并修改餐桌状态为'空闲'
        String billUPD_SQL = "UPDATE bill SET state='已结账' WHERE diningTableId = ?";
        int billAffected = bd.update(billUPD_SQL, checkTableNo);
        if(billAffected==0){
            OutUtil.outErr("结账失败！");
        }else{
            OutUtil.outInd("结账成功，谢谢惠顾！");
            String tableUPD_SQL = "UPDATE diningTable SET state='空闲' WHERE no = ?";
            dtd.update(tableUPD_SQL,checkTableNo);
        }

    }

}
