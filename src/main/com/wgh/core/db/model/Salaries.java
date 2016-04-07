package com.wgh.core.db.model;


import com.wgh.core.Global.PageUtil;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 */
public class Salaries extends BaseModel implements Serializable{
    private int emp_no;
    private int salary;
    private Date from_date = null;
    private Date to_date = null;

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    @Override
    public String toString(){
        StringBuilder str= new StringBuilder();
        str.append("{emp_no:").append(this.emp_no)
                .append(",salary:").append(this.salary)
                .append(",from_date:").append(this.from_date)
                .append(",to_date:").append(this.to_date).append("}");
        return str.toString();
    }
}
