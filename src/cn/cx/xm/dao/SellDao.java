package cn.cx.xm.dao;

import cn.cx.xm.entity.Sell;

import java.sql.SQLException;
import java.util.List;

public interface SellDao {
    public void save(Sell sell) throws SQLException;
    public void update(Sell sell) throws SQLException;
    public void delete(int id) throws SQLException;
    public List<Sell> findAll() throws SQLException;
}
