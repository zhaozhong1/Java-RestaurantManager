package manhanlou.com.Services;

import manhanlou.com.Bean.DiningTable;
import manhanlou.com.Dao.DiningTableDAO;
import manhanlou.com.Utils.OutUtil;
import manhanlou.com.Utils.ReadUtil;

import java.util.List;

public class DiningTableServicesManager {
    DiningTableDAO dtd = new DiningTableDAO();

    //查看餐桌状态服务
    public void showTableStatus(){

        String sql = "SELECT * From DiningTable";

        List<DiningTable> dinningTables = dtd.queryMulti(sql, DiningTable.class);

        if(dinningTables!=null){
            System.out.println("餐桌编号\t\t\t餐桌状态");
            for(DiningTable dt : dinningTables){
                System.out.println(dt.getNo()+"\t\t\t\t"+dt.getState());
            }
            OutUtil.outTitle("显示完毕");
        }else{
            OutUtil.outErr("没有该餐桌！");
        }
    }

    //预订餐桌服务

    public void preOrderTable(){
        OutUtil.outComm("请选择你想预订的餐桌(-1退出)：");

        int tableNo = ReadUtil.readInt();

        if(tableNo == -1){
            OutUtil.outInd("退出预订,预订未成功");
            return;
        }

        String sqlSel = "SELECT state From DiningTable WHERE No = ?";
        Object o = dtd.querySingleRes(sqlSel, tableNo);

        if(o==null){
            OutUtil.outErr("该餐桌不存在！请重新选择。");
            return ;
        }else{
            String tableState = (String)o;
            if(!tableState.equals("空闲")){
                OutUtil.outErr("该餐桌不可预订！请重新选择。");
                return ;
            }
        }

        OutUtil.outComm("输入您的姓名：");
        String orderName = ReadUtil.readString(16);

        OutUtil.outComm("输入您的电话：");
        String orderTel = ReadUtil.readString(20);

        String sqlUpd = "Update DiningTable Set orderName=?,orderTel=?,state= '预订' WHERE No=?";

        dtd.update(sqlUpd,orderName,orderTel,tableNo);
        OutUtil.outInd("餐桌预订成功");

    }

}
