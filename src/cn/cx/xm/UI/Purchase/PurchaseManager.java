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
import java.sql.SQLException;
import java.util.List;

public class PurchaseManager extends JFrame {

    PurchaseDao purDao = new PurchaseDaoImpl();
    ProductDao proDao = new ProductDaoImpl();
    JTable jTable;  // 商品表格模型
    PurchaseTableModel putm;

    public PurchaseManager() {
        this.setTitle("商品管理");
        this.setBounds(200, 200, 1000, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);   // 添加控件
        add();   // 显示控件
        this.setVisible(true);
    }

    public void add() {   // 新增
        JButton add = new JButton("新增");
        add.setBounds(10, 10, 80, 30);
        this.add(add);

        // 删除
        JButton del = new JButton("删除");
        del.setBounds(100, 10, 80, 30);
        this.add(del);

        // 编辑
        JButton edit = new JButton("编辑");
        edit.setBounds(190, 10, 80, 30);
        this.add(edit);

        JTextField inputname = new JTextField();
        inputname.setBounds(280, 10, 200, 30);
        this.add(inputname);

        JButton select = new JButton("查询");
        select.setBounds(500, 10, 80, 30);
        this.add(select);

        JButton sign = new JButton("入库");
        sign.setBounds(600, 10, 80, 30);
        this.add(sign);
// 列表--JTable

//        JTextField name_input = new JTextField();
//        name_input.setBounds(150, 120, 200, 30);
//        this.add(name_input);
//
//        JButton select = new JButton("编辑");
//        select.setBounds(190, 10, 80, 30);
//        this.add(select);
//
//        select.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                purDao.findByName();
//            }
//        });

        // 从数据库中查找所有商品
        List<Purchase> list = null;   // 表格
        try {
            list = purDao.findAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        jTable = new JTable();   // 模型
        putm = new PurchaseTableModel(list);
        jTable.setModel(putm);
        jTable.setRowHeight(30);
        jTable.setEnabled(true);   // 滚动条
        JScrollPane jsp = new JScrollPane(jTable);
        jsp.setBounds(0, 80, 970, 360);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.getViewport().add(jTable);
        this.getContentPane().add(jsp);

        select.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> list = null;
                List<Purchase> list1 = null;
                List<Purchase> list2=null;
                try {
                    list = proDao.findIdByName(inputname.getText());
                    for(Product lists:list){
                       list1=purDao.findByName(lists.getId());
//                        list2.add((Purchase) list1);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                jTable = new JTable();   // 模型
                putm = new PurchaseTableModel(list1);
                jTable.setModel(putm);
                jTable.setRowHeight(30);
                jTable.setEnabled(true);   // 滚动条
                JScrollPane jsp = new JScrollPane(jTable);
                jsp.setBounds(0, 80, 970, 360);
                jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                jsp.getViewport().add(jTable);
                PurchaseManager.this.getContentPane().add(jsp);
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 操作员管理界面
                AddPurchase om = new AddPurchase();
                PurchaseManager.this.dispose();
            }
        });
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "是否删除？", "操作 员删除", JOptionPane.YES_NO_OPTION);
                if (result == 0) {// Ok键      // 判断，用户是否选中要删除数据
                    int r = jTable.getSelectedRow(); // 用户选中行号
                    if (r < 0) {
                        JOptionPane.showMessageDialog(null, "请选择要删除的数据!");
                    } else {       // 1、获取用户所选行的数据
                        Purchase Purchase = putm.getValueAt(r);       // 2、根据ID进行删除
                        PurchaseDao pd = new PurchaseDaoImpl();
                        try {
                            pd.delete(Purchase.getId());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "删除成功!");
                        refreshUI();
                    }
                }
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     // 判断，用户是否选中要删除数据
                int r = jTable.getSelectedRow();
                // 用户选中行号
                if (r < 0) {
                    JOptionPane.showMessageDialog(null, "请选择要编辑的数据!");
                } else {      // 1、获取用户所选行的数据
                    Purchase purchase = putm.getValueAt(r);
                    EditPurchase ep = new EditPurchase(purchase);
                    PurchaseManager.this.dispose();
                }
            }
        });
        sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = jTable.getSelectedRow();
                if(r<0){
                    JOptionPane.showMessageDialog(null, "请选择要入库的数据!");
                } else {      // 1、获取用户所选行的数据
                    Purchase purchase = putm.getValueAt(r);
                    int s=purchase.getId();
                    PurchaseDao pd = new PurchaseDaoImpl();
                    try {
                        pd.update1(true,s);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    refreshUI();
                }
            }
        });
    }

    public void refreshUI() {
        List<Purchase> list = null;   // 动态更新表格
        try {
            list = purDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        putm = new PurchaseTableModel(list);
        jTable.setModel(putm);
        jTable.updateUI();
    }
}
