package cn.cx.xm.UI.Operator;

import cn.cx.xm.Impl.ManagerDaoImpl;
import cn.cx.xm.dao.ManagerDao;
import cn.cx.xm.entity.Manager;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddManager extends JFrame {
    ManagerDao managerDao = new ManagerDaoImpl();

    public AddManager() {
        this.setTitle("添加管理员");
        this.setBounds(400, 200, 700, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add();
        this.setVisible(true);
    }

    public void add() {
        // 用户名
        JLabel user = new JLabel("用户名:");
        user.setBounds(80, 60, 80, 30);
        this.add(user);
        // 用户名输入框
        JTextField user_input = new JTextField();
        user_input.setBounds(130, 60, 200, 30);
        this.add(user_input);
        // 密码
        JLabel pwd = new JLabel("密  码:");
        pwd.setBounds(370, 60, 80, 30);
        this.add(pwd);
        // 密码输入框
        JPasswordField pwd_input = new JPasswordField();
        pwd_input.setBounds(450, 60, 200, 30);
        this.add(pwd_input);
        // 邮箱
        JLabel email = new JLabel("邮  箱:");
        email.setBounds(80, 120, 80, 30);
        this.add(email);
        // 用户名输入框
        JTextField email_input = new JTextField();
        email_input.setBounds(130, 120, 200, 30);
        this.add(email_input);
        // 联系方式
        JLabel mobile = new JLabel("联系方式:");
        mobile.setBounds(370, 120, 80, 30);
        this.add(mobile);
        // 密码输入框
        JTextField mobile_input = new JTextField();
        mobile_input.setBounds(450, 120, 200, 30);
        this.add(mobile_input);
        // 状态
        JLabel status = new JLabel("状   态:");
        status.setBounds(80, 180, 80, 30);
        this.add(status);
        ButtonGroup bg = new ButtonGroup();
        // 状态
        JRadioButton jr = new JRadioButton("正常");
        jr.setBounds(150, 180, 130, 30);
        JRadioButton njr = new JRadioButton("锁定");
        njr.setBounds(280, 180, 130, 30);
        jr.setSelected(true);
        bg.add(jr);
        bg.add(njr);
        this.add(jr);
        this.add(njr);
        // 提交按钮
        JButton save = new JButton("提交");
        save.setBounds(280, 230, 100, 30);
        this.add(save);
        // 提交保存按钮事件
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Manager manager = new Manager();     // 用户名
                manager.setLname(user_input.getText());     // 密码
                manager.setPwd(pwd_input.getText());     // 手机
                manager.setMobile(mobile_input.getText());     // 邮箱
                manager.setEmail(email_input.getText());     // 状态
                System.out.println(jr.isSelected());
                if (jr.isSelected()) {// 正常
                    manager.setStatus(0);
                } else {// 锁定
                    manager.setStatus(1);
                }     // 保存
                try {
                    managerDao.save(manager);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                AddManager.this.dispose();     // 回到操作员管理界面
                OperatorManager om = new OperatorManager();
            }
        });
    }
}
