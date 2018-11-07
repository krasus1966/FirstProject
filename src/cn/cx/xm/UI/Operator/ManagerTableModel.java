package cn.cx.xm.UI.Operator;

import cn.cx.xm.entity.Manager;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ManagerTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 429084827501808762L;

    private String[] title = {"编号", "登录名", "密码", "邮箱", "联系电话", "日志","状态"};// 列名标题
    private List<Manager> rows = new ArrayList<Manager>(); // 数据行

    public ManagerTableModel() {
    }

    public ManagerTableModel(List<Manager> rows) {
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

    //返回指定行和指定列位置的单元格值
    @Override
    public Object getValueAt(int row, int column) {
        Manager mgr = rows.get(row);
        if (column == 0) {
            return mgr.getId();
        } else if (column == 1) {
            return mgr.getLname();
        } else if (column == 2) {
            return mgr.getPwd();
        } else if (column == 3) {
            return mgr.getEmail();
        } else if (column == 4) {
            return mgr.getMobile();
        } else if (column == 5){
            return mgr.getNote();
        } else if (column == 6) {
            return mgr.getStatus() == 0 ? "正常" : "锁定";
        }
        return null;
    }

    //添加数据
    public void setDataModel(List<Manager> rows) {
        this.rows.clear();
        for (Manager mgr : rows) {
            this.add(mgr);
        }
    }

    //获取指定行的对象数据
    public Manager getValueAt(int row) {
        return rows.get(row);
    }

    //往模型中添加一个对象
    public void add(Manager entity) {
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
    public void update(Manager entity) {
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