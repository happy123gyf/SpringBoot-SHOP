package com.gyf.shopping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gyf.shopping.pojo.ContentCategory;
import com.gyf.shopping.pojo.ResBean;
import com.gyf.shopping.pojo.ResultPage;
import com.gyf.shopping.service.ContentCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "ContentCategoryController", description = "广告分类后台服务接口")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService categoryService;

    @GetMapping("/queryAllContentCategory")
    @ApiOperation("查询全部广告分类")
    public List<ContentCategory> queryAllContentCategory() {
        System.out.println("进入ContentCategoryController:/queryAllContentCategory");
        return categoryService.queryAllContentCategory();
    }

    @GetMapping("/queryContentCategoryById/{id}")
    @ApiOperation("根据ID查询广告分类")
    public ContentCategory queryContentCategoryById(@PathVariable @ApiParam("广告分类id") Long id) {
        System.out.println("进入ContentCategoryController:/queryContentCategoryById/{id}" + id);
        return categoryService.queryContentCategoryById(id);
    }

    @PostMapping("/createContentCategory")
    @ApiOperation("新增广告分类")
    public ResBean createContentCategory(@RequestBody @ApiParam("广告分类对象") ContentCategory contentCategory) {
        System.out.println("进入ContentCategoryController:/createContentCategory" + contentCategory.getId());

        try {
            categoryService.createContentCategory(contentCategory);
            return ResBean.ok("新增广告分类成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("新增广告分类错误!");
        }
    }

    @PostMapping("/updateContentCategory")
    @ApiOperation("更新广告分类")
    public ResBean updateContentCategory(@RequestBody @ApiParam("广告分类对象") ContentCategory contentCategory) {
        System.out.println("进入ContentCategoryController:/updateContentCategory" + contentCategory.getId());
        try {
            categoryService.updateContentCategory(contentCategory);
            return ResBean.ok("更新广告分类成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("更新广告分类错误!");
        }
    }

    @GetMapping("/deleteManyContentCategory")
    @ApiOperation("根据ids[]批量删除广告分类")
    public ResBean deleteManyContentCategory(@ApiParam("广告id数组") Long[] ids) {
        System.out.println("进入ContentCategoryController:/deleteManyContentCategory" + ids[0]);
        try {
            categoryService.deleteManyContentCategory(ids);
            return ResBean.ok("批量删除广告分类成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量删除广告分类错误!!");
        }
    }


    @GetMapping("/findByPageContentCategory")
    @ApiOperation("广告分类分页查询")
    public ResultPage findByPageContent(@ApiParam("当前页码") Integer pageNum, @ApiParam("页面大小") Integer pageSize) {
        System.out.println("进入分页查询");
        PageHelper.startPage(pageNum, pageSize);
        Page<ContentCategory> pageBean = (Page<ContentCategory>) categoryService.queryAllContentCategory();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(pageBean.getResult());
        resultPage.setTotal(pageBean.getTotal());
        return resultPage;

    }


}
