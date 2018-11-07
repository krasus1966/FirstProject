package cn.cx.xm.UI.Operator;

import cn.cx.xm.Impl.ManagerDaoImpl;
import cn.cx.xm.UI.MainUI.LoginJFrame;
import cn.cx.xm.dao.ManagerDao;
import cn.cx.xm.entity.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdatePwdManager extends JFrame {

    public UpdatePwdManager() {

        this.setTitle("修改密码");
        this.setBounds(200, 100, 400, 450);
        this.setLayout(null);   // 添加控件
        add();
        this.setVisible(true);
    }

    /**
     * 添加登录界面控件
     */
    public void add() {
        // 旧密码
        JLabel oldpwd = new JLabel("旧密码:");
        oldpwd.setBounds(60, 60, 80, 30);
        this.add(oldpwd);
        // 旧密码输入框
        JTextField oldpwd_input = new JTextField();
        oldpwd_input.setBounds(130, 60, 200, 30);
        this.add(oldpwd_input);
        // 新密码
        JLabel newpwd = new JLabel("新密 码:");
        newpwd.setBounds(60, 120, 80, 30);
        this.add(newpwd);
        // 新密码输入框
        JTextField newpwd_input = new JTextField();
        newpwd_input.setBounds(130, 120, 200, 30);
        this.add(newpwd_input);
        // 重复密码
        JLabel repwd = new JLabel("重复密码:");
        repwd.setBounds(60, 180, 80, 30);
        this.add(repwd);
        // 重复密码输入框
        JTextField repwd_input = new JTextField();
        repwd_input.setBounds(130, 180, 200, 30);
        this.add(repwd_input);
        // 提交
        JButton login = new JButton("提交");
        login.setBounds(140, 230, 100, 30);
        this.add(login);
        // 给提交按钮添加监听事件
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 1、获取旧密码
                String op = oldpwd_input.getText();

                // 2、获取新密码
                String np = newpwd_input.getText();
                // 3、获取重复密码
                String rp = repwd_input.getText();
                if (np.equals(rp)) {
                    ManagerDao md = new ManagerDaoImpl();
                    try {
                        if ((LoginJFrame.manager.getPwd().equals(op))){
                            LoginJFrame.manager.setPwd(np);       // 更新密码
                            md.update(LoginJFrame.manager);
                            JOptionPane.showMessageDialog(null, "更新成功！");
                            UpdatePwdManager.this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "旧密码输入错误！请重新输 入！");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }


                } else {
                    JOptionPane.showMessageDialog(null, "新密码和重复密码不一样！请重 新输入！");
                }
            }
        });
    }
}