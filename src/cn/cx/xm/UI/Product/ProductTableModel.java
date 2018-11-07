package cn.cx.xm.UI.Product;

import cn.cx.xm.entity.Product;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    private List<Product> rows = new ArrayList<Product>(); // 数据行
    private String[] title = {"编号", "商品名", "商品码", "价格", "单位"};// 列名标题

    public ProductTableModel() {
    }

    public ProductTableModel(List<Product> rows) {
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
        Product pro = rows.get(row);
        if (column == 0) {
            return pro.getId();
        }else if(column ==1){
            return pro.getName();
        }else if (column == 2) {
            return pro.getIsbn();
        } else if (column == 3) {
            return pro.getSale_price();
        } else if (column == 4) {
            return pro.getUnit();
        }
        return null;
    }
    //添加数据
    public void setDataModel(List<Product> rows) {
        this.rows.clear();
        for (Product mgr : rows) {
            this.add(mgr);
        }
    }

    //获取指定行的对象数据
    public Product getValueAt(int row) {
        return rows.get(row);
    }

    //往模型中添加一个对象
    public void add(Product entity) {
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
    public void update(Product entity) {
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
