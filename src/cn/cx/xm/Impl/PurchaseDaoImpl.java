package cn.cx.xm.Impl;

import cn.cx.xm.common.SqlCommon;
import cn.cx.xm.common.SqlHelper;
import cn.cx.xm.dao.PurchaseDao;
import cn.cx.xm.entity.Purchase;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDaoImpl implements PurchaseDao {
    /** 定义一个SQL执行器的实例 */
    private QueryRunner run = new QueryRunner();
    private BeanListHandler<Purchase> beanListHandler = new BeanListHandler<Purchase>(Purchase.class);

    @Override
    public void save(Purchase purchase) throws SQLException {
        Connection conn = null;
        try {
            conn = SqlHelper.getConnection();    // 执行sql
            run.update(conn, SqlCommon.add_purchase, new Object[] {purchase.getSn(),purchase.getPur_date(),purchase.getCost(),purchase.getPro_id(),purchase.getNum(), purchase.getNote(),purchase.getSign()});
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
    }
    @Override
    public void delete(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = SqlHelper.getConnection();    // 执行sql
            run.update(conn, SqlCommon.delete_purchase, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
    }
    @Override
    public void update(Purchase purchase) throws SQLException {
        Connection conn = null;
        try {
            conn = SqlHelper.getConnection();    // 执行sql
            run.update(conn, SqlCommon.update_purchase, new Object[] {purchase.getSn(), purchase.getPur_date(), purchase.getCost(),purchase.getPro_id(), purchase.getNum(), purchase.getNote(), purchase.getSign(), purchase.getId()});
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
    }
    @Override
    public List<Purchase> findAll() throws SQLException {
        Connection conn = null;
        List<Purchase> list = null;
        try {
            conn = SqlHelper.getConnection();    // 执行sql
            list = run.query(conn, SqlCommon.findall_purchase, beanListHandler);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
        return list;
    }

    @Override

//    public  List<Purchase> findIdByName(String inputname) throws SQLException {
//        Connection conn=null;
//        List<Purchase> list = new ArrayList<>();
//        Purchase purchase =null;
//        String sql="select id from t_goods where name like '%"+inputname+"%'";
//        try {
//            conn=SqlHelper.getConnection();
//            list=run.query(conn,sql,beanListHandler);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            SqlHelper.closeConnection(conn);
//        }
//        return list;
//    }
    public List<Purchase> findByName(Integer id) throws SQLException {
        Connection conn= null;
        List<Purchase> list = new ArrayList<>();
        Purchase purchase = null;
        String sql="select * from t_purchase where pro_id = ?";
        try {
            conn= SqlHelper.getConnection();
            list=run.query(conn,sql,beanListHandler, id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlHelper.closeConnection(conn);
        }
        return list;
    }
    public void update1(Boolean sign,int id) throws SQLException {
        Connection conn = null;
       String  sql="update t_purchase set sign=? where id = ?";
        try {
            conn = SqlHelper.getConnection();    // 执行sql
            run.update(conn, sql,sign,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
    }
}
