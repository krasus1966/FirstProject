package cn.cx.xm.UI.MainUI;

import cn.cx.xm.Impl.ManagerDaoImpl;
import cn.cx.xm.dao.ManagerDao;
import cn.cx.xm.entity.Manager;
import contrib.com.blogofbug.swing.components.ImageLabel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginJFrame extends JFrame {
    public static Manager manager = null;
    private ManagerDao managerDao = new ManagerDaoImpl();

    public LoginJFrame() {   // 标题
        this.setTitle("进销存管理系统");
        this.setBounds(200, 100, 500, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add();
        this.setVisible(true);
    }

    public void add() {   // 图片
        ImageLabel imageLabel = new ImageLabel(new ImageIcon("image/lgbg.png"));
        imageLabel.setBounds(0, 0, 500, 120);
        this.add(imageLabel);
        // 用户名
        JLabel user_jLabel = new JLabel("用户名:");
        user_jLabel.setBounds(120, 140, 80, 30);
        this.add(user_jLabel);
        // 用户名输入框
        JTextField username = new JTextField();
        username.setBounds(200, 140, 200, 30);
        this.add(username);

        // 密码
        JLabel pwd_jLabel = new JLabel("密  码:");
        pwd_jLabel.setBounds(120, 180, 80, 30);
        this.add(pwd_jLabel);
        // 密码输入框
        JPasswordField password = new JPasswordField();
        password.setBounds(200, 180, 200, 30);
        this.add(password);

        // 登录
        JButton login = new JButton("登录");
        login.setBounds(140, 240, 100, 30);
        this.add(login);

        // 重置
        JButton resert = new JButton("重置");
        resert.setBounds(260, 240, 100, 30);
        this.add(resert);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager manager = null;
                try {
                    ManagerDao managerDao = new ManagerDaoImpl();
                    manager = managerDao.login(username.getText(), password.getText());
                    System.out.println(username.getText() + password.getText());
                    String uname = username.getText();
                    String upwd = password.getText();

//                    if (manager != null) {
//                        System.out.println("登录成功");
//                    } else {
//                        JOptionPane.showMessageDialog(null, "用户名或密码错误");
//                    }
//                    System.out.println(manager.getLname()+manager.getPwd());
                    if (manager == null) {
                        JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                    } else if(manager.getStatus()==1){
                        System.out.println(manager.getStatus());
                        JOptionPane.showMessageDialog(null, "未激活！");
                    }else{
                        System.out.println(manager.getStatus());
                        LoginJFrame.manager = manager;
                        MainUI ui = new MainUI();
                        LoginJFrame.this.dispose();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // 重置监听事件
        resert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                password.setText("");
            }

        });
    }
}

