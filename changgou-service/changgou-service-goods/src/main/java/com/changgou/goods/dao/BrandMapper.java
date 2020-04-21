package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.springframework.stereotype.Repository;
import  tk.mybatis.mapper.common.Mapper;
import java.util.List;
//继承了mapper接口自动实现了增删查改方法

@Repository
public interface BrandMapper extends Mapper<Brand>{

}
