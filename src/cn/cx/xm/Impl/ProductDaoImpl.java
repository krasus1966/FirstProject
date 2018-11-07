package cn.cx.xm.Impl;

import cn.cx.xm.common.SqlCommon;
import cn.cx.xm.common.SqlHelper;
import cn.cx.xm.dao.ProductDao;
import cn.cx.xm.entity.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private QueryRunner run = new QueryRunner();
    private BeanHandler<Product> beanHeander = new BeanHandler<Product>(Product.class);
    private BeanListHandler<Product> beanListHandler = new BeanListHandler<Product>(Product.class);

    public void save(Product product) throws SQLException {
        Connection conn = null;
        try {// 1、打开数据库链接
            conn = SqlHelper.getConnection();    // 2、执行SQL
            run.update(conn, SqlCommon.add_product, new Object[]{product.getIsbn(), product.getName(), product.getSale_price(), product.getUnit()});
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
    public void update(Product product) throws SQLException {
        Connection conn = null;
        try {    // 1、创建数据库连接
            conn = SqlHelper.getConnection();    // 2、执行更新sql
            run.update(conn, SqlCommon.update_product, new Object[]{product.getIsbn(), product.getName(),product.getSale_price(), product.getUnit(), product.getId()});
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
            run.update(conn, SqlCommon.delete_manager, id);
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
    public List<Product> findAll() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        try {// 1、创建数据库连接
            conn = SqlHelper.getConnection();
            // 2、执行查询语句
            list = run.query(conn, SqlCommon.findall_product, beanListHandler);
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
    public Product findByOne(int id) throws SQLException {
        Connection conn = null;
        Product product = null;
        try {// 1、创建数据库链接
            conn = SqlHelper.getConnection();    // 2、执行sql语句
            product = run.query(conn,  SqlCommon.id_product, beanHeander, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {    // 3、关闭数据库链接
            SqlHelper.closeConnection(conn);
        }
        return product;
    }
    public  List<Product> findIdByName(String inputname) throws SQLException {
        Connection conn=null;
        List<Product> list = new ArrayList<>();
        Product purchase =null;
        String sql="select id from t_goods where name like '%"+inputname+"%'";
        try {
            conn=SqlHelper.getConnection();
            list=run.query(conn,sql,beanListHandler);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlHelper.closeConnection(conn);
        }
        return list;
    }
    public  List<Product> findById(int id) throws SQLException {
        Connection conn=null;
        List<Product> list = new ArrayList<>();
        String sql="select name from t_goods where id = ?";
        try {
            conn=SqlHelper.getConnection();
            list=run.query(conn,sql,beanListHandler,id);
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
