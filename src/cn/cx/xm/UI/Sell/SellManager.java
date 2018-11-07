package cn.cx.xm.UI.Sell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellManager extends JPanel {
    public SellManager() {
        this.setLayout(null);
        add();
    }
    public void add(){
        JButton sell = new JButton("销售管理");
        sell.setBounds(20, 20, 100, 30);
        this.add(sell);

        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 SellMainUI sellMainUI=new SellMainUI();
            }
        });
    }
}
