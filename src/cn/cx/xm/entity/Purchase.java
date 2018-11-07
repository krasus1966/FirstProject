package cn.cx.xm.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Purchase {
    private Integer id;
    private String sn;
    private Date pur_date;
    private BigDecimal cost;
    private Integer num;
    private String note;
    private Boolean sign;
    private Integer pro_id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Date getPur_date() {
        return pur_date;
    }

    public void setPur_date(Date pur_date) {
        this.pur_date = pur_date;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getSign() {
        return sign;
    }

    public void setSign(Boolean sign) {
        this.sign = sign;
    }

    public Integer getPro_id() {
        return pro_id;
    }

    public void setPro_id(Integer pro_id) {
        this.pro_id = pro_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
