package cn.cx.xm.Impl;

import cn.cx.xm.common.SqlCommon;
import cn.cx.xm.common.SqlHelper;
import cn.cx.xm.dao.SupplierDao;
import cn.cx.xm.entity.Supplier;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    private QueryRunner run = new QueryRunner();
    private BeanHandler<Supplier> beanHeander = new BeanHandler<Supplier>(Supplier.class);
    private BeanListHandler<Supplier> beanListHandler = new BeanListHandler<Supplier>(Supplier.class);

    public void save(Supplier Supplier) throws SQLException {
        Connection conn = null;
        try {// 1、打开数据库链接
            conn = SqlHelper.getConnection();    // 2、执行SQL
            run.update(conn, SqlCommon.add_supplier, new Object[]{Supplier.getAddress(),Supplier.getCode(),Supplier.getContact(),Supplier.getEmail(),Supplier.getName(), Supplier.getTel(), Supplier.getNote()});
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {    // 3、关闭数据库链接
            SqlHelper.closeConnection(conn);
        }
    }

    /**
     * 更新商品
     */
    @Override
    public void update(Supplier Supplier) throws SQLException {
        Connection conn = null;
        try {    // 1、创建数据库连接
            conn = SqlHelper.getConnection();    // 2、执行更新sql
            run.update(conn, SqlCommon.update_supplier, new Object[]{Supplier.getAddress(),Supplier.getCode(),Supplier.getContact(),Supplier.getEmail(),Supplier.getName(), Supplier.getTel(), Supplier.getNote(),Supplier.getId()});
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {    // 3、关闭数据库连接
            SqlHelper.closeConnection(conn);
        }

    }

    /**
     * 删除商品
     */
    @Override
    public void delete(int id) throws SQLException {
        Connection conn = null;
        try {    // 1、打开数据库链接
            conn = SqlHelper.getConnection();    // 2、执行sql语句
            run.update(conn, SqlCommon.delete_supplier, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {    // 3、关闭数据库链接
            SqlHelper.closeConnection(conn);
        }
    }

    /**
     * 查找所有商品
     */
    @Override
    public List<Supplier> findAll() throws SQLException {
        List<Supplier> list = new ArrayList<>();
        Connection conn = null;
        try {// 1、创建数据库连接
            conn = SqlHelper.getConnection();
            // 2、执行查询语句
            list = run.query(conn, SqlCommon.findall_supplier, beanListHandler);
        } catch (
                SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {    // 3、关闭数据库连接
            SqlHelper.closeConnection(conn);
        }
        return list;
    }

    /**
     * 商品某一个商品
     */
    @Override
    public Supplier findByOne(int id) throws SQLException {
        Connection conn = null;

        Supplier Supplier = null;
        try {// 1、创建数据库链接
            conn = SqlHelper.getConnection();    // 2、执行sql语句
            Supplier = run.query(conn,  SqlCommon.id_supplier, beanHeander, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {    // 3、关闭数据库链接
            SqlHelper.closeConnection(conn);
        }
        return Supplier;

    }

    @Override
    public  List<Supplier> findByName(String inputname) throws SQLException {
        Connection conn=null;
        List<Supplier> list = new ArrayList<>();
        Supplier supplier =null;
        String sql1="select * from t_supplier where name like '%"+inputname+"%'";
        try {
            conn=SqlHelper.getConnection();
            list=run.query(conn,sql1,beanListHandler);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlHelper.closeConnection(conn);
        }
       return list;
    }
}
