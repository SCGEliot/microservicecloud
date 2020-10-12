package com.scg.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.scg.springcloud.service.DeptService;
import com.scg.springcloudAPI.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DeptController {

	@Autowired
	private DeptService service;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
        Dept dept = service.get(id);
        if(dept == null){
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }
		return dept;
	}

    public Dept processHystrix_Get(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id)
            .setDname("该ID："+id+" 没有对应的信息,null--@HystrixCommand")
            .setDb_source("no this database in MySQL");
    }

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list()
	{
		return service.list();
	}

    @RequestMapping(value = "/dept/delete/{id}", method = RequestMethod.GET)
    public boolean delete(@PathVariable("id") Long id)
    {
        return service.delete(id);
    }
	
	//@Autowired
	//private DiscoveryClient client;
    //
	//@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	//public Object discovery(){
	//	List<String> list = client.getServices();
	//	System.out.println("**********" + list);
    //
	//	List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
	//	for (ServiceInstance element : srvList) {
	//		System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
	//				+ element.getUri());
	//	}
	//	return this.client;
	//}

}
