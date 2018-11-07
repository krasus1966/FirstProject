package cn.cx.xm.Impl;

import cn.cx.xm.common.SqlCommon;
import cn.cx.xm.common.SqlHelper;
import cn.cx.xm.dao.SellDao;
import cn.cx.xm.entity.Sell;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SellDaoImpl implements SellDao {
    /** 定义一个SQL执行器的实例 */
    private QueryRunner run = new QueryRunner();
    private BeanListHandler<Sell> beanListHandler = new BeanListHandler<Sell>(Sell.class);

    @Override
    public void save(Sell Sell) throws SQLException {
        Connection conn = null;
        try {
            conn = SqlHelper.getConnection();    // 执行sql
            run.update(conn, SqlCommon.add_sell, new Object[] {Sell.getSn(),Sell.getSale_date(),Sell.getCost(),Sell.getPro_id(),Sell.getNum(),Sell.getNote()});
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            SqlHelper.closeConnection(conn);
        }
    }
    @Override
    public void delete(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = SqlHelper.getConnection();    // 执行sql
            run.update(conn, SqlCommon.delete_sell, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
    }
    @Override
    public void update(Sell Sell) throws SQLException {
        Connection conn = null;
        try {
            conn = SqlHelper.getConnection();    // 执行sql
            run.update(conn, SqlCommon.update_sell, new Object[] {Sell.getSn(),Sell.getSale_date(),Sell.getCost(),Sell.getPro_id(),Sell.getNum(),Sell.getNote(),Sell.getId()});
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
    }
    @Override
    public List<Sell> findAll() throws SQLException {
        Connection conn = null;
        List<Sell> list = null;
        try {
            conn = SqlHelper.getConnection();    // 执行sql
            list = run.query(conn, SqlCommon.findall_sell, beanListHandler);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.closeConnection(conn);
        }
        return list;
    }
}
