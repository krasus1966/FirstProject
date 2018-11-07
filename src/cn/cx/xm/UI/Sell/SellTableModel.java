package cn.cx.xm.UI.Sell;

import cn.cx.xm.Impl.ProductDaoImpl;
import cn.cx.xm.dao.ProductDao;
import cn.cx.xm.entity.Product;
import cn.cx.xm.entity.Sell;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellTableModel extends AbstractTableModel {
    private List<Sell> rows = new ArrayList<Sell>(); // 数据行
    private String[] title = {"编号", "sn序列号", "销售商品", "销售日期","销售数量", "销售总金额"};// 列名标题
    String list1 = null;
    ProductDao productDao = new ProductDaoImpl();
    List<Product> list = null;
    public SellTableModel() {
    }

    public SellTableModel(List<Sell> rows) {
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
        Sell sell = rows.get(row);
        if (column == 0) {
            return sell.getId();
        }else if(column ==1){
            return sell.getSn();
        }else if (column == 2) {
            try {
                list = productDao.findById(sell.getPro_id());
                for(Product lists:list){
                    System.out.println(sell.getPro_id());
                    System.out.println(lists.getName());
                    list1=lists.getName();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list1;
        } else if (column == 3) {
            return sell.getSale_date();
        }else if (column == 4) {
            return sell.getNum();
        }else if (column == 5) {
            return sell.getCost();
        }
        return null;
    }
    //添加数据
    public void setDataModel(List<Sell> rows) {
        this.rows.clear();
        for (Sell mgr : rows) {
            this.add(mgr);
        }
    }

    //获取指定行的对象数据
    public Sell getValueAt(int row) {
        return rows.get(row);
    }

    //往模型中添加一个对象
    public void add(Sell entity) {
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
    public void update(Sell entity) {
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
