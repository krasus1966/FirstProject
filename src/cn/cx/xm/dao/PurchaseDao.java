package cn.cx.xm.dao;

import cn.cx.xm.entity.Purchase;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseDao {
    public void save(Purchase purchase) throws SQLException;
    public void update(Purchase purchase) throws SQLException;
    public void delete(int id) throws SQLException;
    public List<Purchase> findAll() throws SQLException;
//    public  List<Purchase> findIdByName(String inputname)  throws SQLException;
    public List<Purchase> findByName(Integer pro_id) throws SQLException;

   public void update1(Boolean sign,int id) throws SQLException;
}
