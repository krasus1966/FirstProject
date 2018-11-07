package cn.cx.xm.dao;

import cn.cx.xm.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    public void save(Product product) throws SQLException;
    public void update(Product product) throws SQLException;
    public void delete(int id) throws SQLException;
    public List<Product> findAll() throws SQLException;
    public Product findByOne(int id) throws SQLException;
    public List<Product> findIdByName(String inputname) throws SQLException;
    public List<Product> findById(int id) throws SQLException;

}

