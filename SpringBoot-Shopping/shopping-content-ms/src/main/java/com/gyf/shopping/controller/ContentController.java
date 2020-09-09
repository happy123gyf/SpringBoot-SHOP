package com.gyf.shopping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gyf.shopping.pojo.Content;
import com.gyf.shopping.pojo.ResBean;
import com.gyf.shopping.pojo.ResultPage;
import com.gyf.shopping.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "ContentController", description = "广告后台服务接口")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @GetMapping("/queryAllContent")
    @ApiOperation("查询全部广告")
    public List<Content> queryAllContent() {
        System.out.println("进入ContentController:/queryAllContent");
        return contentService.queryAllContent();
    }

    @GetMapping("/queryContentById/{id}")
    @ApiOperation("根据ID查询广告")
    public Content queryContentById(@PathVariable @ApiParam("广告id") Long id) {
        System.out.println("进入ContentController:/queryContentById/{id}" + id);
        return contentService.queryContentById(id);
    }

    @PostMapping("/createContent")
    @ApiOperation("新增广告")
    public ResBean createContent(@RequestBody @ApiParam("广告对象") Content content) {
        System.out.println("进入ContentController:/createContent" + content.getId());
        System.out.println(content.getStatus());

        try {
            contentService.createContent(content);
            return ResBean.ok("新增广告成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("新增广告错误!");
        }
    }

    @PostMapping("/updateContent")
    @ApiOperation("更新广告")
    public ResBean updateContent(@RequestBody @ApiParam("广告对象") Content content) {
        System.out.println("进入ContentController:/updateContent" + content.getId());
        try {
            contentService.updateContent(content);
            return ResBean.ok("更新广告成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("更新广告错误!");
        }
    }

    @GetMapping("/deleteManyContent")
    @ApiOperation("根据ids[]批量删除广告")
    public ResBean deleteManyContent(@ApiParam("广告id数组") Long[] ids) {
        System.out.println("进入ContentController:/deleteManyContent" + ids[0]);
        try {
            contentService.deleteManyContent(ids);
            return ResBean.ok("批量删除广告成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量删除广告错误!!");
        }
    }

    @GetMapping("/updataStartContentStatus")
    @ApiOperation("根据ids[]更新开启广告状态")
    public ResBean updataStartContentStatus(@ApiParam("广告id数组") Long[] ids) {
        System.out.println("进入ContentController:/updataManyContentStatus" + ids[0]);
        try {
            contentService.updataStartContentStatus(ids);
            return ResBean.ok("批量更新广告状态成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量更新广告状态错误!!");
        }
    }

    @GetMapping("/updataShieldContentStatus")
    @ApiOperation("根据ids[]更新屏蔽广告状态")
    public ResBean updataShieldContentStatus(@ApiParam("广告id数组") Long[] ids) {
        System.out.println("进入ContentController:/updataShieldContentStatus" + ids[0]);
        try {
            contentService.updataShieldContentStatus(ids);
            return ResBean.ok("批量更新屏蔽广告状态成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量更新屏蔽广告状态错误!!");
        }
    }


    @GetMapping("/findByPageContent")
    @ApiOperation("广告分页查询")
    public ResultPage findByPageContent(@ApiParam("当前页码") Integer pageNum, @ApiParam("页面大小") Integer pageSize) {
        System.out.println("进入广告分页查询");
        PageHelper.startPage(pageNum, pageSize);
        Page<Content> pageBean = (Page<Content>) contentService.queryAllContent();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(pageBean.getResult());
        resultPage.setTotal(pageBean.getTotal());
        return resultPage;

    }

    @GetMapping("/findContentsByCategoryId/{categoryId}")
    public List<Content> findContentsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        System.out.println("进入按广告类型查询全部广告" + categoryId);
        List<Content> contentList = contentService.findContentsByCategoryId(categoryId);


        return contentList;
    }


}
