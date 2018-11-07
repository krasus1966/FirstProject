package cn.cx.xm.dao;

import cn.cx.xm.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDao {
    public void save(Supplier supplier) throws SQLException;
    public void update(Supplier supplier) throws SQLException;
    public void delete(int id) throws SQLException;
    public List<Supplier> findAll() throws SQLException;
    public Supplier findByOne(int id) throws SQLException;
    public List<Supplier> findByName(String inputname) throws SQLException;
}
