package manhanlou.com.Services;

import manhanlou.com.Bean.DishesMenu;
import manhanlou.com.Dao.BillDAO;
import manhanlou.com.Dao.DiningTableDAO;
import manhanlou.com.Dao.DishesMenuDAO;
import manhanlou.com.Utils.OutUtil;
import manhanlou.com.Utils.ReadUtil;

import java.util.List;

public class DishesMenuServicesManager {

    DishesMenuDAO md = new DishesMenuDAO();
    DiningTableDAO dtd = new DiningTableDAO();
    BillDAO bd = new BillDAO();
    public void showMenu(){
        String sql = "SELECT * FROM MENU";
        List<DishesMenu> dishesMenus = md.queryMulti(sql, DishesMenu.class);
        if(dishesMenus!=null){
            System.out.println("-编号- \t\t\t"+"-菜名- \t\t\t"+"-菜品- \t\t"+"-价格-");
            for(DishesMenu dm : dishesMenus){
                System.out.println(dm.getNo()+"\t\t\t\t"+dm.getName()+"\t\t\t\t\t"+dm.getType()+"\t\t"+dm.getPrice());
            }
            OutUtil.outTitle("显示完成");
        }

    }


    public void orderDishes(){
        int diningTableID;
        int menuID;
        int nums;
        //检查桌号合法性
        do{

            OutUtil.outComm("请输入桌号：(-1退出)");
            diningTableID = ReadUtil.readInt();
            if(diningTableID == -1){
                OutUtil.outInd("退出点单,点单未成功");
                return;
            }
        }while(CommonSelectManager.tableIsNotExist(diningTableID));

        //检查餐桌状态合法性

        if(!CommonSelectManager.tableStateQuery("预订",diningTableID)&&!CommonSelectManager.tableStateQuery("用餐",diningTableID)){
            OutUtil.outErr("该餐桌不可点餐，请重新选择。");
            return;
        }

        //检查编号合法性
        do{
            OutUtil.outComm("请输入菜品编号(-1退出)：");
            menuID = ReadUtil.readInt();
            if(menuID == -1){
                OutUtil.outInd("退出点单,点单未成功");
            }
        }while (!CommonSelectManager.menuIsExist(menuID));


        //检查数量合法性
        do {
            OutUtil.outComm("请输入菜品数量(-1退出)：");
            nums = ReadUtil.readInt();
            if(nums == -1){
                OutUtil.outInd("退出点单,点单未成功");
            }
        } while (!CommonSelectManager.menuNumsInput(nums));


        boolean errFlag = true;

        while(errFlag){

            OutUtil.outComm("是否要点此单?(y or n)");
            String confirm = ReadUtil.readString(1);
            errFlag = false;
            if(confirm.equals("y")){

                String billINS_SQL =
                        "INSERT INTO bill VALUES(null,?,?,?,'点单',?,NOW())";
                String getMoneySQL = "SELECT price FROM MENU WHERE NO=?";
                String tableStateUPD_SQL = "UPDATE diningTable SET state = '用餐' WHERE No = ?";


                Object o = md.querySingleRes(getMoneySQL, menuID);
                double money = (double)o;

                int affectedRows = bd.update(billINS_SQL, menuID, nums, money*nums, diningTableID);
                if(affectedRows == 1){
                    OutUtil.outInd("点单成功！账单已生成。");
                    dtd.update(tableStateUPD_SQL,diningTableID);//点单完毕后，需要更新餐桌状态至："已点单"
                }else{
                    OutUtil.outErr("点单失败，请检查输入是否有误。");
                }
                //生成账单
            }else if(confirm.equals("n")){
                OutUtil.outInd("退出点单，账单未生成。");
                //退出点单
            }else{
                errFlag = true;
                OutUtil.outErr("输入未知命令，请重新输入。");
            }
        }

    }

}
