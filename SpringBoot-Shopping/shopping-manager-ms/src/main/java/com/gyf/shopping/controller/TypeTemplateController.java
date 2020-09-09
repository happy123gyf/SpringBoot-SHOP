package com.gyf.shopping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gyf.shopping.pojo.ResBean;
import com.gyf.shopping.pojo.ResultPage;
import com.gyf.shopping.pojo.TypeTemplate;
import com.gyf.shopping.service.TypeTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "TypeTemplateController", description = "类型模板后台接口")
public class TypeTemplateController {
    @Autowired
    private TypeTemplateService typeTemplateService;


    @GetMapping("/queryAllTypeTemplate")
    @ApiOperation("查询全部类型模板")
    public List<TypeTemplate> queryAllTypeTemplate() {
        System.out.println("进入TypeTemplateController:/queryAllTypeTemplate");
        return typeTemplateService.queryAllTypeTemplate();
    }

    @GetMapping("/queryTypeTemplateById/{id}")
    @ApiOperation("根据ID查询类型模板")
    public TypeTemplate queryTypeTemplateById(@PathVariable @ApiParam("类型模板id") Long id) {
        System.out.println("进入TypeTemplateController:/queryTypeTemplateById/{id}" + id);
        return typeTemplateService.queryTypeTemplateById(id);
    }


    @ApiOperation("新增类型模板")
    @PostMapping("/createTypeTemplate")
    public ResBean createTypeTemplate(@RequestBody @ApiParam("类型模板对象") TypeTemplate typeTemplate) {
        System.out.println("进入TypeTemplateController:/createTypeTemplate" + typeTemplate.getName());
        try {
            typeTemplateService.createTypeTemplate(typeTemplate);
            return ResBean.ok("新增类型模板成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("新增类型模板错误!");
        }
    }


    @PostMapping("/updateTypeTemplate")
    @ApiOperation("更新类型模板")
    public ResBean updateTypeTemplate(@RequestBody @ApiParam("类型模板对象") TypeTemplate typeTemplate) {
        System.out.println("进入TypeTemplateController:/updateTypeTemplate" + typeTemplate.getId());
        try {
            typeTemplateService.updateTypeTemplate(typeTemplate);
            return ResBean.ok("更新类型模板成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("更新类型模板错误!");
        }
    }

    @GetMapping("/deleteManyTypeTemplate")
    @ApiOperation("根据ids[]批量删除类型模板")
    public ResBean deleteManyTypeTemplate(@ApiParam("类型模板id数组") Long[] ids) {
        System.out.println("进入TypeTemplateController:/deleteManyTypeTemplate" + ids[0]);
        try {
            typeTemplateService.deleteManyTypeTemplate(ids);
            return ResBean.ok("批量删除类型模板成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量删除类型模板错误!!");
        }
    }


    @GetMapping("/findByPageTypeTemplate")
    @ApiOperation("类型模板分页查询")
    public ResultPage findByPageContent(@ApiParam("当前页码") Integer pageNum, @ApiParam("页面大小") Integer pageSize) {
        System.out.println("进入类型模板分页查询");
        PageHelper.startPage(pageNum, pageSize);
        Page<TypeTemplate> pageBean = (Page<TypeTemplate>) typeTemplateService.queryAllTypeTemplate();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(pageBean.getResult());
        resultPage.setTotal(pageBean.getTotal());
        return resultPage;

    }



    @GetMapping("/findBySpecList/{id}")
    @ApiOperation("根据模板id找规格信息")
    public List<Map> findSpecList(@PathVariable @ApiParam("模板id") Long id){
        System.out.println("模板id:"+id);
        return typeTemplateService.findSpecList(id);
    }


}
