package com.wgh.core.service;


import com.wgh.core.Global.PageUtil;
import com.wgh.core.db.model.Salaries;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface SalariesService {
    public void insert(Salaries salaries);
    public void update(Salaries salaries);
    public void delete(Salaries salaries);
    public List<Salaries> find(Map param, PageUtil pageUtil);
    public Salaries find(int emp_no);
}
