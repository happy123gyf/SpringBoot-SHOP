package com.gyf.shopping.junit;

import com.gyf.shopping.pojo.Specification;
import com.gyf.shopping.service.SpecificationService;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.gyf.shopping.mapper")
public class TestSpec {

    @Autowired
    private SpecificationService specificationService;


    @Test
    public void testSpec(){
        List<Specification> list = specificationService.findSpecificationByName("尺寸");
        for (Specification specification : list) {
            System.out.println(specification.getId()+specification.getSpecName());
        }

    }



}
