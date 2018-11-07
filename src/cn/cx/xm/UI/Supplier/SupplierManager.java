package cn.cx.xm.UI.Supplier;

import cn.cx.xm.Impl.SupplierDaoImpl;
import cn.cx.xm.dao.SupplierDao;
import cn.cx.xm.entity.Supplier;
import org.jvnet.substance.utils.SubstanceInternalFrameTitlePane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SupplierManager extends JFrame {
    SupplierDao supplierDao = new SupplierDaoImpl() ;
    JTable jTable;
    SupplierTableModel stm;

    public SupplierManager() {
        this.setTitle("供应商管理");
        this.setBounds(200, 200, 1000, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);   // 添加控件
        add();   // 显示控件
        this.setVisible(true);
    }
        public void add (){   // 新增
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

            JTextField inputname = new JTextField();
            inputname.setBounds(280, 10, 200, 30);
            this.add(inputname);

            JButton select = new JButton("查询");
            select.setBounds(500, 10, 80, 30);
            this.add(select);



            List<Supplier> list = null;   // 表格
            try {
                list = supplierDao.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jTable = new JTable();   // 模型
            stm = new SupplierTableModel(list);
            jTable.setModel(stm);
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
                    List<Supplier> list = null;
                    try {
                        list = supplierDao.findByName(inputname.getText());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    jTable = new JTable();   // 模型
                    stm = new SupplierTableModel(list);
                    jTable.setModel(stm);
                    jTable.setRowHeight(30);
                    jTable.setEnabled(true);   // 滚动条
                    JScrollPane jsp = new JScrollPane(jTable);
                    jsp.setBounds(0, 80, 970, 360);
                    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    jsp.getViewport().add(jTable);
                    SupplierManager.this.getContentPane().add(jsp);
                }
            });
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 操作员管理界面
                    AddSupplier om = new AddSupplier();
                    SupplierManager.this.dispose();
                }
            });
            del.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(null, "是否删除？", "删除", JOptionPane.YES_NO_OPTION);
                    if (result == 0) {// Ok键      // 判断，用户是否选中要删除数据
                        int r = jTable.getSelectedRow(); // 用户选中行号
                        if (r < 0) {
                            JOptionPane.showMessageDialog(null, "请选择要删除的数据!");
                        } else {       // 1、获取用户所选行的数据
                            Supplier supplier =  stm.getValueAt(r);       // 2、根据ID进行删除
                            SupplierDao sd = new SupplierDaoImpl();
                            try {
                                sd.delete(supplier.getId());
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
                        Supplier supplier = stm.getValueAt(r);
                        EditSupplier ep = new EditSupplier(supplier);
                        SupplierManager.this.dispose();
                    }
                }
            });
        }
    public void refreshUI(){
        List<Supplier> list = null;   // 动态更新表格
        try {
            list = supplierDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stm = new SupplierTableModel(list);
        jTable.setModel(stm);
        jTable.updateUI();
    }
}
