package com.gyf.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gyf.shopping.config.JmsConfig;
import com.gyf.shopping.pojo.*;
import com.gyf.shopping.pojoGrouip.Goods;
import com.gyf.shopping.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import java.util.List;

@RestController
@Api(tags = "GoodsController", description = "商品后台服务接口")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/saveGoods")
    @ApiOperation("新增商品组合")
    public ResBean saveGoods(@RequestBody @ApiParam("商品组合对象") Goods goods) {
        System.out.println("进入新增商品组合:" + goods.getGoods().getGoodsName());
        try {
            goodsService.add(goods);
            return ResBean.ok("添加商品成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("添加商品失败!");
        }

    }

    @GetMapping("/findOneGoods/{id}")
    @ApiOperation("根据id找商品组合")
    public Goods findOneGoods(@PathVariable @ApiParam("商品id") Long id) {
        System.out.println("根据id找商品组合" + id);
        return goodsService.findOne(id);
    }


    @GetMapping("/findAllGoods")
    @ApiOperation("查询所有商品")
    public List<TbGoods> findAllGoods() {
        System.out.println("查询所有商品");
        return goodsService.findAll();
    }


    @GetMapping("/findGoodsByPage")
    @ApiOperation("分页查询商品")
    public ResultPage findGoodsByPage(@ApiParam("当前页码") Integer pageNum, @ApiParam("页面大小") Integer pageSize) {
        System.out.println("分页查询商品");
        PageHelper.startPage(pageNum, pageSize);
        Page<TbGoods> pageBean = (Page<TbGoods>) goodsService.findAll();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(pageBean.getResult());
        resultPage.setTotal(pageBean.getTotal());
        return resultPage;

    }






    @GetMapping("/deleteManyGoods/{ids}")
    @ApiOperation("批量删除商品")
    public ResBean deleteManyItemCat(@PathVariable @ApiParam("商品批量ids") Long[] ids) {
        System.out.println("批量删除商品分类" + ids[0]);
        try {
            goodsService.deleteManyGoods(ids);
            return ResBean.ok("批量删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量删除失败");
        }

    }

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/updateGoodsStatus/{ids}/{auditStatus}")
    @ApiOperation("批量更新商品状态")
    public ResBean updateManyGoodsStatus(@PathVariable @ApiParam("批量商品id") Long[] ids, @PathVariable @ApiParam("商品状态") String auditStatus) {
        System.out.println("更新商品状态" + ids[0] + ",status" + auditStatus);
        try {
            //根据ids和传过来的状态进行更新
            goodsService.updateGoodsStatus(ids, auditStatus);

            if ("1".equals(auditStatus)) {// 1表示审核通过
                //得到需要导入的SKU列表    根据spu的id查询对应的sku的商品
                List<Item> itemList = goodsService.findItemListByGoodsIdListAndStatus(ids, auditStatus);
                //转换为JSON对象
                final String jsonString = JSON.toJSONString(itemList);
                System.out.println("jsonString-->" + jsonString);
                Topic topicSolrDestination = new ActiveMQTopic(JmsConfig.TOPIC_SOLR);
                jmsTemplate.send(topicSolrDestination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {

                        return session.createTextMessage(jsonString);
                    }
                });


            }
           /* if("2".equals(auditStatus)){
                System.out.println("进入驳回删除");
                Topic topicSolrDestination = new ActiveMQTopic(JmsConfig.QUEUE_DELETE);
                jmsTemplate.send(topicSolrDestination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {

                        return session.createObjectMessage(ids+"");
                    }
                });



            }*/

            for (final Long id : ids) {
                Topic topicPageDestination = new ActiveMQTopic(JmsConfig.TOPIC_HTML);
                jmsTemplate.send(topicPageDestination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(id + "");
                    }
                });


            }


            return ResBean.ok("批量更新商品状态成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResBean.error("批量更新商品状态失败!");
        }

    }


}
