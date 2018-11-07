package cn.cx.xm.UI.Purchase;

import cn.cx.xm.Impl.ProductDaoImpl;
import cn.cx.xm.Impl.PurchaseDaoImpl;
import cn.cx.xm.dao.ProductDao;
import cn.cx.xm.dao.PurchaseDao;
import cn.cx.xm.entity.Product;
import cn.cx.xm.entity.Purchase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class EditPurchase extends JFrame {
    private Purchase purchase;
    ProductDao productDao = new ProductDaoImpl();
    List<Product> list = null;
    public EditPurchase(Purchase purchase) {
        this.purchase = purchase;
        this.setTitle("编辑供应商");
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
//        // 订单日期
//        JLabel pur_date = new JLabel("订单日期:");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        pur_date.setBounds(80, 120, 80, 30);
//        this.add(pur_date);
//        // 订单日期输入框
//        JTextField pur_date_input = new JTextField();
//        pur_date_input.setBounds(130, 120, 200, 30);
//        pur_date_input.setText(sdf.format(System.currentTimeMillis()));
//        this.add(pur_date_input);
        // 采购数量
        JLabel num = new JLabel("采购数量:");
        num.setBounds(80, 120, 80, 30);
        this.add(num);
        // 采购数量输入框
        JTextField num_input = new JTextField();
        num_input.setBounds(150, 120, 200, 30);
        num_input.setText(String.valueOf(purchase.getNum()));
        this.add(num_input);
        // 采购总金额
        JLabel cost = new JLabel("采购总金额:");
        cost.setBounds(80, 180, 80, 30);
        this.add(cost);
        // 采购总金额输入框
        JTextField cost_input = new JTextField();
        cost_input.setBounds(150, 180, 200, 30);
        cost_input.setText(String.valueOf(purchase.getCost()));
        this.add(cost_input);// 提交按钮
        JButton save = new JButton("提交");
        save.setBounds(280, 230, 100, 30);
        this.add(save);   // 提交保存按钮事件
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                purchase.setSn(sn_input.getText());
//                purchase.setPro_id(new Integer(pro_id_input.getText()));
                int index = pro_goods.getSelectedIndex();
                Product product = list.get(index);
                purchase.setPro_id(product.getId());
                Timestamp d= new Timestamp(System.currentTimeMillis());
                purchase.setPur_date(purchase.getPur_date());
                purchase.setNum(new Integer(num_input.getText()));
                purchase.setCost(new BigDecimal(cost_input.getText()));
                purchase.setSign(purchase.getSign());
                PurchaseDao purchaseDao = new PurchaseDaoImpl();
                try {
                    purchaseDao.update(purchase);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                EditPurchase.this.dispose();
                PurchaseManager purchaseManager = new PurchaseManager();

            }
        });
    }
}
