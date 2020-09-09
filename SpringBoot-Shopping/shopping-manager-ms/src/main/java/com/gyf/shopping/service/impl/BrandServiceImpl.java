package com.gyf.shopping.service.impl;

import com.gyf.shopping.mapper.BrandMapper;
import com.gyf.shopping.pojo.Brand;
import com.gyf.shopping.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;


    @Override
    public List<Brand> queryAllBrand() {
        System.out.println("BrandServiceImpl:进行查询全部");
        return brandMapper.selectByExample(null);
    }

    @Override
    public Brand queryBrandById(Long id) {
        System.out.println("BrandServiceImpl:根据id查询品牌");
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void createBrand(Brand brand) {
        System.out.println("BrandServiceImpl:新增品牌");
        brandMapper.insert(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        System.out.println("BrandServiceImpl:更新品牌");
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void deleteManyBrand(Long[] ids) {
        System.out.println("BrandServiceImpl:批量删除品牌");
        for (long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Map> selectOptionList() {


        return brandMapper.selectOptionList();
        
    }
}
