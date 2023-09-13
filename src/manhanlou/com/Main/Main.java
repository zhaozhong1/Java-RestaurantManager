package manhanlou.com.Main;

import manhanlou.com.Frame.FirstFrame;
import manhanlou.com.Utils.OutUtil;
import manhanlou.com.Frame.SecondFrame;

public class Main {
    public static void main(String[] args) {
        if(FirstFrame.frame()){
            OutUtil.outInd("登陆成功");
            SecondFrame.frame();
        }
        OutUtil.outInd("退出系统成功");
    }

}
