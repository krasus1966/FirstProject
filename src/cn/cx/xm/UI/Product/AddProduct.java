package cn.cx.xm.UI.Product;

import cn.cx.xm.Impl.ProductDaoImpl;
import cn.cx.xm.dao.ProductDao;
import cn.cx.xm.entity.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class AddProduct extends JFrame {
    public AddProduct() {
        this.setTitle("添加商品");
        this.setBounds(400, 200, 700, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add();
        this.setVisible(true);
    }

    public void add() {
        // 商品条形码
        JLabel isbn = new JLabel("商品码:");
        isbn.setBounds(80, 60, 80, 30);
        this.add(isbn);
        // 商品条形码输入框   // 获取UUID码
        UUID id = UUID.randomUUID();
        JTextField isbn_input = new JTextField();
        isbn_input.setBounds(130, 60, 200, 30);
        isbn_input.setText(id.toString());
        this.add(isbn_input);   // 商品名称
        JLabel name = new JLabel("商品名称:");
        name.setBounds(370, 60, 80, 30);
        this.add(name);   // 商品名称输入框
        JTextField name_input = new JTextField();
        name_input.setBounds(450, 60, 200, 30);
        this.add(name_input);   // 价格
        JLabel price = new JLabel("单   价:");
        price.setBounds(80, 120, 80, 30);
        this.add(price);
        // 价格输入框
        JTextField price_input = new JTextField();
        price_input.setBounds(130, 120, 200, 30);
        this.add(price_input);   // 单位
        JLabel unit = new JLabel("单   位:");
        unit.setBounds(370, 120, 80, 30);
        this.add(unit);   // 单位输入框
        JTextField unit_input = new JTextField();
        unit_input.setBounds(450, 120, 200, 30);
        this.add(unit_input);   // 提交按钮
        JButton save = new JButton("提交");
        save.setBounds(280, 230, 100, 30);
        this.add(save);   // 提交保存按钮事件
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Product product = new Product();
                // 1、商品条形码
                product.setIsbn(isbn_input.getText());
                // 2、商品名称
                product.setName(name_input.getText());
                // 3、商品价格
                product.setSale_price(new BigDecimal(price_input.getText()));
                // 4、商品单位
                product.setUnit(unit_input.getText());
                // 5、保存到数据库
                // 获取实现类对象
                ProductDao productDao = new ProductDaoImpl();
                try {
                    productDao.save(product);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                AddProduct.this.dispose();
                ProductManager productManager = new ProductManager();

            }
        });
    }
}

