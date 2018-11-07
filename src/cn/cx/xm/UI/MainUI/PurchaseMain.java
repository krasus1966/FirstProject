package cn.cx.xm.UI.MainUI;

import cn.cx.xm.UI.Product.ProductManager;
import cn.cx.xm.UI.Supplier.SupplierManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseMain extends JPanel {

        public PurchaseMain() {
            this.setLayout(null);
            add();
        }
    public void add() {
        // 商品管理按钮
        JButton goods = new JButton("商品管理");
        goods.setBounds(20, 20, 100, 30);
        this.add(goods);
        // 供应商管理按钮
        JButton supplier = new JButton("供应商管理");
        supplier.setBounds(130, 20, 100, 30);
        this.add(supplier);
        // 采购管理按钮
        JButton purchase = new JButton("采购管理");
        purchase.setBounds(240, 20, 100, 30);
        this.add(purchase);

        //商品管理监听事件
        goods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductManager productManager = new ProductManager();
            }
        });

        //供应商管理监听事件
        supplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierManager supplierManager = new SupplierManager();
            }
        });

        //采购管理监听事件
        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cn.cx.xm.UI.Purchase.PurchaseManager purchaseManager = new cn.cx.xm.UI.Purchase.PurchaseManager();
            }
        });
    }
}
