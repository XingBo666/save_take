package com.ss.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.mapper.GoodsMapper;
import com.ss.mapper.InvenInfoMapper;
import com.ss.mapper.InvenWarnMapper;
import com.ss.pojo.Goods;
import com.ss.pojo.InvenInfo;
import com.ss.pojo.InvenWarn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper GoodsMapper;

    @Autowired
    InvenInfoMapper invenInfoMapper;

    @Autowired
    InvenWarnMapper invenWarnMapper;

    public Goods getGoodsById(Long id) {
        return GoodsMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Goods> getGoodsByPage(Integer page) {
        PageHelper.startPage(page,5);
        List<Goods> Goodss = GoodsMapper.selectAll();
        PageInfo<Goods> info = new PageInfo(Goodss);
        return info;
    }

    public Boolean addGoods(Goods Goods) {
        try{
            Goods.setId(null);
            GoodsMapper.insertSelective(Goods);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public Boolean delGoods(Long id) {
        try {
            //删除商品的时候，根据商品的id将库存的信息，库存的预警信息一并删除

            InvenInfo invenInfo = new InvenInfo();
            invenInfo.setGoodsId(id);
            InvenWarn invenWarn = new InvenWarn();
            invenInfoMapper.delete(invenInfo);
            invenWarn.setGoodsId(id);
            invenWarnMapper.delete(invenWarn);
            GoodsMapper.deleteByPrimaryKey(id);



        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean updateGoods(Goods Goods) {
        try {
            GoodsMapper.updateByPrimaryKeySelective(Goods);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<Goods> searchByPhoneOrName( String keyword,Integer page) {
        if (keyword == "" || keyword == null){
            new PageInfo<>().setList(null);
        }

        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orLike("name","%" + keyword + "%").orEqualTo("id",keyword);
        PageHelper.startPage(page,5);
        List<Goods> Goodss = GoodsMapper.selectByExample(example);
        PageInfo<Goods> info = new PageInfo(Goodss);
        return info;
    }

    public List<Goods> getAllGoods() {
        return GoodsMapper.selectAll();
    }
}
