package com.scg.springcloud.service.impl;

import java.util.List;
import com.scg.springcloudAPI.entities.Dept;
import com.scg.springcloud.dao.DeptDao;
import com.scg.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao dao;
	
	@Override
	public boolean add(Dept dept)
	{
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id){
		return dao.findById(id);
	}

    @Override
	public boolean delete(Long id){
		return dao.delete(id);
	}



	@Override
	public List<Dept> list()
	{
		return dao.findAll();
	}

}
