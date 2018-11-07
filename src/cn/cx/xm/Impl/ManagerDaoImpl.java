package cn.cx.xm.Impl;

import cn.cx.xm.common.SqlCommon;
import cn.cx.xm.common.SqlHelper;
import cn.cx.xm.dao.ManagerDao;
import cn.cx.xm.entity.Manager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {
    private QueryRunner run = new QueryRunner();
    private BeanHandler<Manager> beanHeander = new BeanHandler<Manager>(Manager.class);
    private BeanListHandler<Manager> beanListHeander = new BeanListHandler<Manager>(Manager.class);

    @Override
    public void save(Manager manager) throws SQLException {
        Connection conn = null;
        try {
            conn = SqlHelper.getConnection();
            run.update(conn, SqlCommon.INSERT_MANAGER, new Object[]{manager.getLname(), manager.getPwd(), manager.getMobile(), manager.getEmail(), manager.getNote(), manager.getStatus()});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = SqlHelper.getConnection();
            run.update(conn, SqlCommon.DELETE_MANAGER, id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
    }

    @Override
    public void update(Manager manager) throws SQLException {
        Connection conn = null;
        try {
            conn = SqlHelper.getConnection();
            run.update(conn, SqlCommon.UPDATE_MANAGER, new Object[]{manager.getLname(), manager.getPwd(), manager.getMobile(), manager.getEmail(), manager.getNote(), manager.getStatus(),manager.getId()});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }

    }

    @Override
    public List findAll() throws SQLException {
        Connection conn = null;
        List list = new ArrayList<>();
        try {
            conn = SqlHelper.getConnection();
            list = run.query(conn, SqlCommon.FINDALL_MANAGER, beanListHeander);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Manager findById(int id) throws SQLException {
        Connection conn = null;
        Manager manager = null;
        try {
            conn = SqlHelper.getConnection();
            run.update(conn, SqlCommon.FINDBYID_MANAGER, id, beanHeander);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
        return manager;
    }

    @Override
    public Manager login(String name, String pwd) throws SQLException {
        Connection conn = null;
        Manager manager = null;
        try {
            conn = SqlHelper.getConnection();
            manager = run.query(conn, SqlCommon.LOGIN_MANAGER, new Object[]{name, pwd}, beanHeander);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
        return manager;
    }

//    public boolean findpassword(String name) throws SQLException {
//        Connection conn = null;
//        Manager manager = null;
//        Boolean ani=null;
//        String sql = "select pwd from t_manager where lname=?";
//        try {
//            conn = SqlHelper.getConnection();
//            ani = run.query(conn, sql, new Object[]{name},beanHeander);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            SqlHelper.closeConnection(conn);
//        }
//        return ani;
//    }

    public Manager updatepassword(String name, String pwd) throws SQLException {
        Connection conn = null;
        Manager manager = null;
        String sql = "update t_manager set pwd=? where lname=?";
        try {
            conn = SqlHelper.getConnection();
            manager = run.query(conn, sql, new Object[]{name}, beanHeander);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
        return manager;
    }
}
