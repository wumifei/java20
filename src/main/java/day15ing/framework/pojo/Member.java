package day15ing.framework.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Member {
    private  int id;
    private String reg_name;
    private String pwd;
    private String mobile_phone;
    private int type;
    private BigDecimal leave_amount;
    private Timestamp reg_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BigDecimal getLeave_amount() {
        return leave_amount;
    }

    public void setLeave_amount(BigDecimal leave_amount) {
        this.leave_amount = leave_amount;
    }

    public Timestamp getReg_time() {
        return reg_time;
    }

    public void setReg_time(Timestamp reg_time) {
        this.reg_time = reg_time;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Member{");
        sb.append("id=").append(id);
        sb.append(", reg_name='").append(reg_name).append('\'');
        sb.append(", pwd='").append(pwd).append('\'');
        sb.append(", mobile_phone='").append(mobile_phone).append('\'');
        sb.append(", type=").append(type);
        sb.append(", leave_amount=").append(leave_amount);
        sb.append(", reg_time=").append(reg_time);
        sb.append('}');
        return sb.toString();
    }
}
