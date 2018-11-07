package cn.cx.xm.UI.MainUI;


import cn.cx.xm.UI.Operator.OperatorManager;
import cn.cx.xm.UI.Operator.UpdatePwdManager;
import cn.cx.xm.UI.Sell.SellManager;
import contrib.com.blogofbug.swing.components.ImageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class MainUI extends JFrame {
    JDesktopPane desktopPane = new JDesktopPane();
    public MainUI() {
        this.setTitle("进销存管理主界面");
        this.setBounds(200, 100, 1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add();
        this.setVisible(true);
    }

    public void add() {
        ImageLabel imageLabel = new ImageLabel(new ImageIcon("image/first_tab.png"));
        imageLabel.setBounds(0, 100, 1000, 500);
        this.add(imageLabel);
        // 添加选项卡
        JTabbedPane jtp = new JTabbedPane();
        // 采购管理
        jtp.add(new PurchaseMain(), "采购管理");
        // 销售管理
        jtp.add(new SellManager(), "销售管理");
        // 系统设置
        jtp.add(new SystemManager(), "系统管理");
        this.add(jtp);
    }



    public class SystemManager extends JPanel {

        public SystemManager() {
            this.setLayout(null);
            add();
        }

        // 添加系统管理选项卡的组件
        public void add() {
            // 操作员管理按钮
            JButton op = new JButton("操作员管理");
            op.setBounds(20, 20, 100, 30);
            this.add(op);
            // 更改密码按钮
            JButton pwd = new JButton("更改密码");
            pwd.setBounds(130, 20, 100, 30);
            this.add(pwd);
            // 退出系统按钮
            JButton exit = new JButton("退出系统");
            exit.setBounds(240, 20, 100, 30);
            this.add(exit);
            // 背景图
            ImageLabel bg = new ImageLabel(new ImageIcon("img/first_tab.png"));
            bg.setBounds(0, 60, 1000, 460);
            this.add(bg);

            // 格式化时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 登录时间
            JLabel time = new JLabel("登录时间: " + sdf.format(System.currentTimeMillis()));
            time.setBounds(0, 510, 1000, 30);
            this.add(time);
            // 给操作员管理按钮添加监听事件
            op.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 操作员管理界面
                   //MainUI.this.add(new OperatorManager());
                   OperatorManager operatorManager= new OperatorManager();
//                  SystemManager.this.add(operatorManager);

                }
            });
            // 更改密码
            pwd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UpdatePwdManager upm = new UpdatePwdManager();
                }
            });
            // 退出功能
            exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 终止
                    System.exit(-1);
                }
            });
        }
    }
}

