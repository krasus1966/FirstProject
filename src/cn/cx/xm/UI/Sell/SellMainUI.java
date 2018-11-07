package cn.cx.xm.UI.Sell;

import cn.cx.xm.Impl.SellDaoImpl;
import cn.cx.xm.UI.Purchase.AddPurchase;
import cn.cx.xm.UI.Purchase.PurchaseTableModel;
import cn.cx.xm.dao.SellDao;
import cn.cx.xm.entity.Purchase;
import cn.cx.xm.entity.Sell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SellMainUI extends JFrame {
    SellDao sellDao = new SellDaoImpl();
    JTable jTable;  // 商品表格模型
    SellTableModel stm;

    public SellMainUI() {
        this.setTitle("销售管理");
        this.setBounds(200, 200, 1000, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);   // 添加控件
        add();   // 显示控件
        this.setVisible(true);
    }

    public void add() {
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
        this.add(edit);   // 列表--JTable

        // 从数据库中查找所有商品
        List<Sell> list = null;   // 表格
        try {
            list = sellDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jTable = new JTable();   // 模型
        stm = new SellTableModel(list);
        jTable.setModel(stm);
        jTable.setRowHeight(30);
        jTable.setEnabled(true);   // 滚动条
        JScrollPane jsp = new JScrollPane(jTable);
        jsp.setBounds(0, 80, 970, 360);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.getViewport().add(jTable);
        this.getContentPane().add(jsp);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 操作员管理界面
                AddSell as = new AddSell();
                SellMainUI.this.dispose();
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
                        Sell sell = (Sell) stm.getValueAt(r);       // 2、根据ID进行删除
                        SellDao sd = new SellDaoImpl();
                        try {
                            sd.delete(sell.getId());
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
                    Sell sell = stm.getValueAt(r);
                    EditSell es = new EditSell(sell);
                    SellMainUI.this.dispose();
                }
            }
        });
    }

    public void refreshUI() {
        List<Sell> list = null;   // 动态更新表格
        try {
            list = sellDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stm = new SellTableModel(list);
        jTable.setModel(stm);
        jTable.updateUI();
    }
}
