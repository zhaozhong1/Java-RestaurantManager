package manhanlou.com.Bean;

import java.time.LocalDateTime;
import java.util.Date;

public class Bill {
    int No;
    int menuID;
    int nums;
    double Money;

    LocalDateTime date;

    String state;
    int diningTableId;

    public Bill() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;

    }

    public int getNo() {
        return No;
    }

    public int getMenuID() {
        return menuID;
    }

    public int getNums() {
        return nums;
    }

    public double getMoney() {
        return Money;
    }

    public String getState() {
        return state;
    }

    public void setNo(int no) {
        No = no;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public void setMoney(double money) {
        Money = money;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDiningTableId(int diningTableId) {
        this.diningTableId = diningTableId;
    }

    public int getDiningTableId() {
        return diningTableId;
    }

    @Override
    public String toString() {
        return  No +
                "\t\t\t" + menuID +
                "\t\t\t" + nums +
                "\t\t" + Money +
                "\t\t\t\t" + date +
                "\t\t" + state +
                "\t\t\t" + diningTableId;
    }
}
