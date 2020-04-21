package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@CrossOrigin //跨域
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> brandList = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌集合成功！",brandList);
    }

    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id){
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK,"查询成功",brand);
    }

    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping(value="/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable Integer id){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping(value="/search")
    public Result<List<Brand>> findList(@RequestBody(required = false) Brand brand){
        List<Brand> list = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "条件查询成功",list);
    }

    @GetMapping(value="/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable(value = "page") Integer page,
                                            @PathVariable(value = "size") Integer size){
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询成功", pageInfo);
    }

    @PostMapping(value="search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Brand brand,
                                     @PathVariable(value = "page") Integer page,
                                     @PathVariable(value = "size") Integer size){
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "分页条件查询成功", pageInfo);
    }
}
