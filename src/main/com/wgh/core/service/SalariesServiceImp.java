package com.wgh.core.service;

import com.wgh.core.Global.PageUtil;
import com.wgh.core.db.mapper.SalariesMapper;
import com.wgh.core.db.model.Salaries;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service(value = "salariesService")
public class SalariesServiceImp implements SalariesService {
    @Autowired
    private SalariesMapper salariesMapper;

    @CacheEvict(value = "salaries", allEntries = true)
    @Override
    public void insert(Salaries salaries) {
        salariesMapper.insertSalaries(salaries);
    }

    @Cacheable(value = "salaries")
    @Override
    public Salaries find(int emp_no) {
        return salariesMapper.selectSalaries(emp_no);
    }

    @CacheEvict(value = "salaries", allEntries = true)
    @Override
    public void delete(Salaries salaries) {
        salariesMapper.deleteSalaries(salaries);
    }

    @CacheEvict(value = "salaries", allEntries = true)
    @Override
    public void update(Salaries salaries) {
        salariesMapper.updateSalaries(salaries);
    }

    @Cacheable(value = "salaries")
    @Override
    public List<Salaries> find(Map param, PageUtil pageUtil) {
        return salariesMapper.selectSalariesList(param, pageUtil);
    }
}
