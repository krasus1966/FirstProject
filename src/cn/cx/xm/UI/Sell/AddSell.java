package cn.cx.xm.UI.Sell;

import cn.cx.xm.Impl.ProductDaoImpl;
import cn.cx.xm.Impl.SellDaoImpl;
import cn.cx.xm.dao.ProductDao;
import cn.cx.xm.dao.SellDao;
import cn.cx.xm.entity.Product;
import cn.cx.xm.entity.Sell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class AddSell extends JFrame{
    private Sell sell;
    ProductDao productDao = new ProductDaoImpl();
    List<Product> list = null;
    public AddSell() {
        this.sell=sell;
        this.setTitle("编辑商品");
        this.setBounds(400, 200, 700, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add();
        this.setVisible(true);
    }

    public void add() {   // 商品条形码
        // sn序列号
        JLabel sn = new JLabel("sn序列号:");
        sn.setBounds(80, 60, 80, 30);
        this.add(sn);
        // sn序列号输入框   // 获取UUID码
        UUID id = UUID.randomUUID();
        JTextField sn_input = new JTextField();
        sn_input.setBounds(150, 60, 200, 30);
        sn_input.setText(id.toString());
        this.add(sn_input);
        // 采购商品
        JLabel pro_id = new JLabel("采购商品:");
        pro_id.setBounds(370, 60, 80, 30);
        this.add(pro_id);
        // 采购商品输入框
        JComboBox<String> pro_goods = new JComboBox<>();
        pro_goods.setBounds(450, 60, 200, 30);
        try {
            list = productDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Product product : list) {
            pro_goods.addItem(product.getName());
        }
        this.add(pro_goods);
        // 采购数量
        JLabel num = new JLabel("销售数量:");
        num.setBounds(80, 120, 80, 30);
        this.add(num);
        // 采购数量输入框
        JTextField num_input = new JTextField();
        num_input.setBounds(150, 120, 200, 30);
        this.add(num_input);
        // 采购总金额
        JLabel cost = new JLabel("销售总金额:");
        cost.setBounds(80, 180, 80, 30);
        this.add(cost);
        // 采购总金额输入框
        JTextField cost_input = new JTextField();
        cost_input.setBounds(150, 180, 200, 30);
        this.add(cost_input);// 提交按钮
        JButton save = new JButton("提交");
        save.setBounds(280, 230, 100, 30);
        this.add(save);   // 提交保存按钮事件
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Sell Sell = new Sell();
                // 1、sn序列号
                Sell.setSn(sn_input.getText());
                // 2、采购商品
                int index = pro_goods.getSelectedIndex();
                Product product = list.get(index);
                Sell.setPro_id(product.getId());
                //3、采购时间
//                java.util.Date udate;
//                java.sql.Date sdate;
//                udate = new java.util.Date();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String  sdf1=sdf.format(System.currentTimeMillis());
                //获取系统时间
                Timestamp d= new Timestamp(System.currentTimeMillis());
//                sdate = new java.sql.Date(udate.getTime());
                Sell.setSale_date(d);
                // 4、采购数量
                Sell.setNum(new Integer(num_input.getText()));
                //5、采购总金额
                Sell.setCost(new BigDecimal(cost_input.getText()));
                // 6、保存到数据库
                // 获取实现类对象
                SellDao sellDao = new SellDaoImpl();
                try {
                    sellDao.save(Sell);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                AddSell.this.dispose();
                SellMainUI SellMainUI = new SellMainUI();

            }
        });
    }

}
