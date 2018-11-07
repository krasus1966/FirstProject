package cn.cx.xm.dao;

import cn.cx.xm.entity.Manager;

import java.sql.SQLException;
import java.util.List;

public interface ManagerDao {
    public void save(Manager manager ) throws SQLException;
    public void delete(int id) throws SQLException;
    public void update(Manager manager) throws SQLException;
    public List findAll() throws SQLException;
    public Manager findById(int id) throws SQLException;
    public Manager login(String name,String pwd) throws SQLException;
}
