package cn.cx.xm.UI.Supplier;

import cn.cx.xm.Impl.SupplierDaoImpl;
import cn.cx.xm.dao.SupplierDao;
import cn.cx.xm.entity.Supplier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddSupplier extends JFrame {

    public AddSupplier() {
        this.setTitle("添加商品");
        this.setBounds(600, 400, 700, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add();
        this.setVisible(true);
    }

    public void add() {
        // 供应商名称
        JLabel name = new JLabel("供应商名称:");
        name.setBounds(60, 60, 80, 30);
        this.add(name);
        // 供应商名称输入框
        JTextField name_input = new JTextField();
        name_input.setBounds(130, 60, 200, 30);
        this.add(name_input);
        //联系方式
        JLabel contact = new JLabel("联系方式:");
        contact.setBounds(370, 60, 80, 30);
        this.add(contact);
        //联系方式输入框
        JTextField contact_input = new JTextField();
        contact_input.setBounds(450, 60, 200, 30);
        this.add(contact_input);
        // 联系电话
        JLabel tel = new JLabel("联系电话:");
        tel.setBounds(70, 120, 80, 30);
        this.add(tel);
        // 联系电话输入框
        JTextField tel_input = new JTextField();
        tel_input.setBounds(130, 120, 200, 30);
        this.add(tel_input);
        // 邮箱
        JLabel email = new JLabel("邮 箱:");
        email.setBounds(370, 120, 80, 30);
        this.add(email);
        // 邮箱输入框
        JTextField email_input = new JTextField();
        email_input.setBounds(450, 120, 200, 30);
        this.add(email_input);
        // 地址
        JLabel address = new JLabel("地 址:");
        address.setBounds(70, 180, 80, 30);
        this.add(address);
        // 地址输入框
        JTextField address_input = new JTextField();
        address_input.setBounds(130, 180, 200, 30);
        this.add(address_input);
        // 邮编
        JLabel code = new JLabel("邮 编:");
        code.setBounds(370, 180, 80, 30);
        this.add(code);
        // 邮编输入框
        JTextField code_input = new JTextField();
        code_input.setBounds(450, 180, 200, 30);
        this.add(code_input);
        // 备注
        JLabel note = new JLabel("备 注:");
        note.setBounds(70, 240, 80, 30);
        this.add(note);
        // 备注输入框
        JTextField note_input = new JTextField();
        note_input.setBounds(130, 240, 400, 30);
        this.add(note_input);
        // 提交按钮
        JButton save = new JButton("提交");
        save.setBounds(200, 280, 100, 30);
        this.add(save);
        // 提交保存按钮事件
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Supplier supplier = new Supplier();
                // 1、供应商名称
                supplier.setName(name_input.getText());
                // 2、联系方式
                supplier.setContact(contact_input.getText());
                // 3、联系电话
                supplier.setTel(tel_input.getText());
                // 4、邮箱
                supplier.setEmail(email_input.getText());
                // 5、地址
                supplier.setAddress(address_input.getText());
                // 6、邮编
                supplier.setCode(code_input.getText());
                // 7、备注
                supplier.setNote(note_input.getText());
                // 8、保存到数据库
                // 获取实现类对象
                SupplierDao productDao = new SupplierDaoImpl();
                try {
                    productDao.save(supplier);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                AddSupplier.this.dispose();
                SupplierManager supplierManager = new SupplierManager();

            }
        });
    }
}
