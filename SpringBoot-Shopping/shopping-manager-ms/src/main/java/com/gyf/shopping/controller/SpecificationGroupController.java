package com.gyf.shopping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gyf.shopping.pojo.ResBean;
import com.gyf.shopping.pojo.ResultPage;
import com.gyf.shopping.pojo.Specification;
import com.gyf.shopping.pojoGrouip.SpecificationGroup;
import com.gyf.shopping.service.SpecificationGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SpecificationController", description = "规格后台服务接口")
public class SpecificationGroupController {
    @Autowired
    private SpecificationGroupService groupService;

    @GetMapping("/queryAllSpecification")
    @ApiOperation("查询全部规格")
    public List<Specification> queryAllSpecification() {
        System.out.println("进入SpecificationController:/queryAllSpecification");
        return groupService.findAll();
    }


    @GetMapping("/deleteManySpecification")
    @ApiOperation("根据ids[]批量删除规格")
    public ResBean deleteManySpecification(@ApiParam("规格id数组") Long[] ids) {
        System.out.println("进入SpecificationController:/deleteManySpecification" + ids[0]);
        try {
            groupService.delete(ids);
            return ResBean.ok("批量删除规格成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量删除规格错误!!");
        }
    }


    @GetMapping("/querySpecificationById/{id}")
    public SpecificationGroup querySpecificationById(@PathVariable Long id) {

        SpecificationGroup group = groupService.findOne(id);
        return group;

    }

    @GetMapping("/findByPageSpecification")
    @ApiOperation("分页查询")
    public ResultPage findByPageSpecification(@ApiParam("当前页码") Integer pageNum, @ApiParam("页面大小") Integer pageSize) {
        System.out.println("进入分页查询");
        PageHelper.startPage(pageNum, pageSize);
        Page<Specification> pageBean = (Page<Specification>) groupService.findAll();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(pageBean.getResult());
        resultPage.setTotal(pageBean.getTotal());
        return resultPage;
    }


    @PostMapping("/createSpecification")
    @ApiOperation("新增规格")
    public ResBean createSpecification(@RequestBody @ApiParam("规格对象") SpecificationGroup group) {
        System.out.println("进入新增规格"+group.getSpecification().getSpecName());
        try {
            groupService.add(group);
            return ResBean.ok("新增规格成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("新增规格错误!");
        }
    }

    @PostMapping("/updateSpecification")
    @ApiOperation("更新规格")
    public ResBean updateSpecification(@RequestBody @ApiParam("规格对象") SpecificationGroup group) {
        System.out.println("进入更新规格:"+group.getSpecification().getSpecName());
        try {
            groupService.update(group);
            return ResBean.ok("更新规格成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("更新规格错误!");
        }
    }




}
