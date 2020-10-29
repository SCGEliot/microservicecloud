package com.scg.springcloud.controller;

import java.util.List;

import com.scg.springcloudAPI.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class DeptController_Consumer {

    /**x
     *
     * restTemplate.postForObject(REST_URL_PREFIX + "dept/add",dept,boolean.class);
     * 使用 使用restTemplate访问restful接口非常的简单粗暴无脑。
     * (url, requestMap,ResponseBean.class)
     * 这三个参数分别代表 REST请求地址、请求参数、HTTP响应时,被转换成的对象类型。
     *
     */

    //private static final String REST_URL_PREFIX="http://localhost:8001/";
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT/";

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/consumer/dept/add")
    public boolean add(@RequestBody Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "dept/add",dept,boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id)
    {
        //restTemplate.delete(REST_URL_PREFIX + "dept/delete/" + id);
        //restTemplate.put(REST_URL_PREFIX + "dept/get/" + id,Dept.class);
        return restTemplate.getForObject(REST_URL_PREFIX + "dept/get/" + id,Dept.class);
    }

    @RequestMapping(value = "/consumer/dept/list", method = RequestMethod.GET)
    public List<Dept> list()
    {
        return restTemplate.getForObject(REST_URL_PREFIX + "dept/list",List.class);
    }

    // 测试@EnableDiscoveryClient,消费端可以调用服务发现
    @RequestMapping(value = "/consumer/dept/delete/{id}")
    public Object discovery(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/delete/" + id, Object.class);
    }

}
