package com.gyf.shopping.controller;

import com.gyf.shopping.pojo.ResBean;
import com.gyf.shopping.pojo.TbItemCat;
import com.gyf.shopping.service.ItemCatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "ItemCatController", description = "商品分类后台接口")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;


    @GetMapping("/findByParentId/{parentId}")
    @ApiOperation("根据PrentId查询分类集合")
    public List<TbItemCat> findByParentId(@PathVariable @ApiParam("商品分类的父id") Long parentId) {
        System.out.println("根据PrentId查询分类集合");
        return itemCatService.findByParentId(parentId);
    }

    @GetMapping("/findOne/{id}")
    @ApiOperation("根据id查询一个分类")
    public TbItemCat findOne(@PathVariable @ApiParam("商品分类id") Long id) {
        System.out.println("根据id查询一个分类" + id);
        return itemCatService.findOne(id);
    }

    @GetMapping("/findAllItemCat")
    @ApiOperation("查询所有分类")
    public List<TbItemCat> findAllItemCat() {
        System.out.println("查询所有分类");
        return itemCatService.findAllItemCat();
    }

    @GetMapping("/deleteManyItemCat/{ids}")
    @ApiOperation("批量删除商品分类")
    public ResBean deleteManyItemCat(@PathVariable @ApiParam("商品分类批量ids")Long[] ids) {
        System.out.println("批量删除商品分类" + ids[0]);
        try {
            itemCatService.deleteManyItemCat(ids);
            return ResBean.ok("批量删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量删除失败");
        }

    }


}
