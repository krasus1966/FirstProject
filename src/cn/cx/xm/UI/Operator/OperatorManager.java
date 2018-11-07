package cn.cx.xm.UI.Operator;

import cn.cx.xm.Impl.ManagerDaoImpl;
import cn.cx.xm.dao.ManagerDao;
import cn.cx.xm.entity.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class OperatorManager extends JFrame{
    ManagerDao managerDao = new ManagerDaoImpl();
    JTable jTable;
    ManagerTableModel mtm;

    public OperatorManager() {
        this.setTitle("操作员管理");
        this.setBounds(0, 50, 1000, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        add();
        this.setVisible(true);
    }

    public void add() {

        JButton add = new JButton("新增");
        add.setBounds(20, 20, 100, 30);
        this.add(add);

        JButton edit = new JButton("编辑");
        edit.setBounds(120, 20, 100, 30);
        this.add(edit);

        JButton del = new JButton("删除");
        del.setBounds(220, 20, 100, 30);
        this.add(del);

        jTable = new JTable();
        List<Manager> list = null;
        try {
            list = list = managerDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mtm = new ManagerTableModel(list);
        jTable.setModel(mtm);
        jTable.setRowHeight(30);
        jTable.setEnabled(true);

        JScrollPane jsp = new JScrollPane(jTable);
        jsp.getViewport().add(jTable);
        jsp.setBounds(0,80,970,360);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.getViewport().add(jTable);
        this.getContentPane().add(jsp);


        // 删除监听事件
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "是否删除？", "操作 员删除", JOptionPane.YES_NO_OPTION);
                if (result == 0) {// Ok键      // 判断，用户是否选中要删除数据
                    int r = jTable.getSelectedRow(); // 用户选中行号
                    if (r < 0) {
                        JOptionPane.showMessageDialog(null, "请选择要删除的数据!");
                    } else {       // 1、获取用户所选行的数据
                        Manager manager = mtm.getValueAt(r);       // 2、根据ID进行删除
                        ManagerDao md = new ManagerDaoImpl();
                        try {
                            md.delete(manager.getId());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "删除成功!");
                        refreshUI();
                    }
                }
            }
        });

        //编辑
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     // 判断，用户是否选中要删除数据
                int r = jTable.getSelectedRow();
                // 用户选中行号
                if (r < 0) {
                    JOptionPane.showMessageDialog(null, "请选择要编辑的数据!");
                } else {      // 1、获取用户所选行的数据
                    Manager manager = mtm.getValueAt(r);
                    UpdateManager update = new UpdateManager(manager);
                    OperatorManager.this.dispose();
                }
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddManager addManager = new AddManager();
                OperatorManager.this.dispose();
            }
        });
    }

    public void refreshUI() {
        List<Manager> list = null;
        try {
            list = managerDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mtm = new ManagerTableModel(list);
        jTable.setModel(mtm);
        jTable.updateUI();
    }
}
