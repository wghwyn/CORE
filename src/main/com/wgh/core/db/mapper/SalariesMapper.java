package com.wgh.core.db.mapper;


import com.wgh.core.Global.PageUtil;
import com.wgh.core.db.model.Salaries;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface SalariesMapper {
    public void insertSalaries(Salaries salaries);
    public void updateSalaries(Salaries salaries);
    public void deleteSalaries(Salaries salaries);
    public List<Salaries> selectAll();
    public Salaries selectSalaries(int emp_no) ;
    public List<Salaries> selectSalariesList(@Param("param")Map param, @Param("pageUtil")PageUtil pageUtil) ;
    public Map<Integer,Salaries> selectSalariesMap(int emp_no) ;
}
