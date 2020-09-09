package com.gyf.shopping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gyf.shopping.pojo.Brand;
import com.gyf.shopping.pojo.ResBean;
import com.gyf.shopping.pojo.ResultPage;
import com.gyf.shopping.service.BrandService;
import com.gyf.shopping.service.SpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "BrandController", description = "品牌后台服务接口")
public class BrandController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private SpecificationService specService;

    @GetMapping("/queryAllBrand")
    @ApiOperation("查询全部品牌")
    public List<Brand> queryAllNews() {
        System.out.println("进入BrandController:/queryAllNews");
        return brandService.queryAllBrand();
    }

    @GetMapping("/queryBrandById/{id}")
    @ApiOperation("根据ID查询品牌")
    public Brand queryBrandById(@PathVariable @ApiParam("品牌id") Long id) {
        System.out.println("进入BrandController:/queryBrandById/{id}" + id);
        return brandService.queryBrandById(id);
    }

    @PostMapping("/createBrand")
    @ApiOperation("新增品牌")
    public ResBean createBrand(@RequestBody @ApiParam("品牌对象") Brand brand) {
        System.out.println("进入BrandController:/createBrand" + brand.getId());

        try {
            brandService.createBrand(brand);
            return ResBean.ok("新增品牌成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("新增品牌错误!");
        }
    }

    @PostMapping("/updateBrand")
    @ApiOperation("更新品牌")
    public ResBean updateBrand(@RequestBody @ApiParam("品牌对象") Brand brand) {
        System.out.println("进入BrandController:/updateBrand" + brand.getId());
        try {
            brandService.updateBrand(brand);
            return ResBean.ok("更新品牌成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("更新品牌错误!");
        }
    }

    @GetMapping("/deleteManyBrand")
    @ApiOperation("根据ids[]批量删除品牌")
    public ResBean updateBrand(@ApiParam("品牌id数组") Long[] ids) {
        System.out.println("进入BrandController:/deleteManyNews" + ids[0]);
        try {
            brandService.deleteManyBrand(ids);
            return ResBean.ok("批量删除品牌成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量删除品牌错误!!");
        }
    }


    @GetMapping("/findByPage")
    @ApiOperation("分页查询")
    public ResultPage findByPage(@ApiParam("当前页码") Integer pageNum, @ApiParam("页面大小") Integer pageSize) {
        System.out.println("进入分页查询");
        PageHelper.startPage(pageNum, pageSize);
        Page<Brand> pageBean = (Page<Brand>) brandService.queryAllBrand();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(pageBean.getResult());
        resultPage.setTotal(pageBean.getTotal());
        return resultPage;

    }

    @GetMapping("/selectBrandMap")
    @ApiOperation("查询品牌map")
    public List<Map> selectBrandMap() {
        System.out.println("进入/selectBrandMap");
        return brandService.selectOptionList();


    }


    @GetMapping("/selectSpecMap")
    @ApiOperation("查询规格map")
    public List<Map> selectSpecMap() {
        System.out.println("进入/selectSpecMap");
        return specService.selectOptionList();
    }


}
