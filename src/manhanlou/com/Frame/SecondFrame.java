package manhanlou.com.Frame;

import manhanlou.com.Bean.Emp;
import manhanlou.com.Services.BillServicesManager;
import manhanlou.com.Services.DiningTableServicesManager;
import manhanlou.com.Services.DishesMenuServicesManager;
import manhanlou.com.Services.EmpManager;
import manhanlou.com.Utils.OutUtil;
import manhanlou.com.Utils.ReadUtil;

import java.util.Scanner;

public class SecondFrame {
    static DiningTableServicesManager diningTableServicesManager = new DiningTableServicesManager();
    static DishesMenuServicesManager dishesMenuServicesManager = new DishesMenuServicesManager();
    static BillServicesManager billServicesManager = new BillServicesManager();

    static Emp loginEmp = EmpManager.getLoginEmp();

    public static void frame(){
        String opName = loginEmp.getName();
        boolean loopFlag = true;
        System.out.println("欢迎操作员："+ opName);
        while(loopFlag){
            System.out.println("操作员："+ opName);
            OutUtil.outTitle("满汉楼二级菜单");

            OutUtil.outOptions("1 显示餐桌状态");
            OutUtil.outOptions("2 预订餐桌");
            OutUtil.outOptions("3 显示所有菜品");
            OutUtil.outOptions("4 点餐服务");
            OutUtil.outOptions("5 查看账单");
            OutUtil.outOptions("6 结账");
            OutUtil.outOptions("9 退出满汉楼");

            OutUtil.outComm("输入选项号：");
            int option = ReadUtil.readInt();


            switch (option){
                case 1:
                    diningTableServicesManager.showTableStatus();
                    break;
                case 2:
                    diningTableServicesManager.preOrderTable();
                    break;
                case 3:
                    dishesMenuServicesManager.showMenu();
                    break;
                case 4:
                    dishesMenuServicesManager.orderDishes();
                    break;
                case 5:
                    billServicesManager.showCheck();
                    break;
                case 6:
                    billServicesManager.check();
                    break;
                case 9:
                    loopFlag = false;
                    break;
                default:
                    OutUtil.outErr("未知操作");
            }
            if(loopFlag){
                OutUtil.outComm("键入回车以继续...");
                new Scanner(System.in).nextLine();
            }
        }

    }
}
