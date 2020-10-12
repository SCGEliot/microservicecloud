package com.scg.springcloud.service;

import com.scg.springcloudAPI.entities.Dept;

import java.util.List;

public interface DeptService {
    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();

    public boolean delete(Long id);

}
