package com.scg.springcloud.dao;

/**
 * @author: Eliot
 * @date: 2020/10/9
 **/

import com.scg.springcloudAPI.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao {
    boolean addDept(Dept dept);

    Dept findById(Long id);

    List<Dept> findAll();

    boolean delete(Long deptno);
}
