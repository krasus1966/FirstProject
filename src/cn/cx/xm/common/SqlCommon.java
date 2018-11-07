package cn.cx.xm.common;

public class SqlCommon {
    //管理员
    public static final String INSERT_MANAGER = "insert into t_manager(lname,pwd,mobile,email,note,status) values(?,?,?,?,?,?)";
    public static final String DELETE_MANAGER = "delete from t_manager where id= ?";
    public static final String  UPDATE_MANAGER = "update t_manager set lname=?,pwd=?,mobile = ?,email=?,note=?,status=? where id=?";
    public static final String FINDBYID_MANAGER ="select * from t_manager where id=?";
    public static final String FINDALL_MANAGER = "select * from t_manager";
    public static final String LOGIN_MANAGER = "select * from t_manager where lname=? and pwd=?";
    //商品
    public static final String add_product = "insert into t_goods(isbn,name,sale_price,unit) values(?,?,?,?)";
    public static final String update_product = "update t_goods set isbn=?,name=?,sale_price=?,unit=? where id=?";
    public static final String delete_manager = "delete from t_goods where id=?";
    public static final String findall_product = "select * from t_goods";
    public static final String id_product = "select * from t_goods where id=?";
    //供应商
    public static final String add_supplier = "insert into t_supplier(address,code,contact,email,name,tel,note) values(?,?,?,?,?,?,?)";
    public static final String update_supplier = "update t_supplier set address=?,code=?,contact=?,email=?,name=?,tel=?,note=? where id=?";
    public static final String delete_supplier = "delete from t_supplier where id=?";
    public static final String findall_supplier = "select * from t_supplier";
    public static final String id_supplier = "select * from t_supplier where id=?";
    //采购
    public static final String add_purchase = "insert into t_purchase(sn,pur_date,cost,pro_id,num,note,sign) values(?,?,?,?,?,?,?)";
    public static final String update_purchase = "update t_purchase set sn=?,pur_date=?,cost=?,pro_id=?,num=?,note=?,sign=? where id=?";
    public static final String delete_purchase = "delete from t_purchase where id=?";
    public static final String findall_purchase = "select * from t_purchase";
    public static final String id_purchase = "select * from t_purchase where id=?";
    //销售
    public static final String add_sell = "insert into t_sell(sn,sale_date,cost,pro_id,num,note) values(?,?,?,?,?,?)";
    public static final String update_sell = "update t_sell set sn=?,sale_date=?,cost=?,pro_id=?,num=?,note=? where id=?";
    public static final String delete_sell = "delete from t_sell where id=?";
    public static final String findall_sell = "select * from t_sell";
    public static final String id_sell = "select * from t_sell where id=?";
    //模糊查询
}
