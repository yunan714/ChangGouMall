package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface BrandService {
    //全量查找
    List<Brand> findAll();
    //依赖ID查找
    Brand findById(Integer id);
    //增加条目
    void add(Brand brand);
    //修改
    void update(Brand brand);
    //删除
    void delete(Integer id);
    //根据条件搜索品牌
    List<Brand> findList(Brand brand);
    //分页查询
    PageInfo<Brand> findPage(Integer page, Integer size);
    //条件分页查询
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);
}
