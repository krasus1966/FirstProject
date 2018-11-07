package cn.cx.xm.UI.Supplier;

import cn.cx.xm.entity.Supplier;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SupplierTableModel extends AbstractTableModel {
    private List<Supplier> rows = new ArrayList<Supplier>(); // 数据行
    private String[] title = {"供应商编号", "地址", "邮编", "联系方式", "邮箱", "名称", "电话", "备注"};// 列名标题

    public SupplierTableModel() {
    }

    public SupplierTableModel(List<Supplier> rows) {
        this.rows = rows;
    }

    //获取指定列的标题
    @Override
    public String getColumnName(int column) {
        return title[column];
    }

    //返回该模型中的行数
    @Override
    public int getRowCount() {
        return rows.size();
    }

    //返回该模型中的列数
    @Override
    public int getColumnCount() {
        return title.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Supplier sup = rows.get(row);
        if (column == 0) {
            return sup.getId();
        } else if (column == 1) {
            return sup.getAddress();
        } else if (column == 2) {
            return sup.getCode();
        } else if (column == 3) {
            return sup.getContact();
        } else if (column == 4) {
            return sup.getEmail();
        } else if (column == 5) {
            return sup.getName();
        } else if (column == 6) {
            return sup.getTel();
        } else if (column == 7) {
            return sup.getNote();
        }
        return null;
    }

    //添加数据
    public void setDataModel(List<Supplier> rows) {
        this.rows.clear();
        for (Supplier mgr : rows) {
            this.add(mgr);
        }
    }

    //获取指定行的对象数据
    public Supplier getValueAt(int row) {
        return rows.get(row);
    }

    //往模型中添加一个对象
    public void add(Supplier entity) {
        int row = rows.size();
        rows.add(entity);         //通知所有侦听器，已插入范围在 [row, row]的行,以实时刷新表格显示
        fireTableRowsInserted(row, row);
    }

    //从模型中删除指定 id 的对象
    public void remove(int id) {
        int row = -1;
        for (int i = 0; i < rows.size(); i++) {
            if (id == getValueAt(i).getId()) {
                row = i;
                break;
            }
        }
        if (row != -1) {
            rows.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

    //更新模型中的信息
    public void update(Supplier entity) {
        int row = -1;
        for (int i = 0; i <= rows.size(); i++) {
            if (entity.getId() == getValueAt(i).getId()) {
                row = i;
                break;
            }
        }
        if (row != -1) {
            rows.remove(row);
            rows.add(row, entity);
            fireTableRowsUpdated(row, row);
        }
    }
}
