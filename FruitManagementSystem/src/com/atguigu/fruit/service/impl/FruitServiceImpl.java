package com.atguigu.fruit.service.impl;

import com.atguigu.fruit.service.FruitService;
import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.basedao.ConnUtil;

import java.util.List;

/** Implementation class of FruitService
 * @author Diyang Li
 * @create 2022-04-13 12:22 PM
 */
public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(Integer pageNo, String keyword) {
        System.out.println("getFruitList --> " + ConnUtil.getConn());
        return fruitDAO.getFruitList(pageNo, keyword);
    }

    @Override
    public Fruit getFruitByFid(int fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public Boolean updateFruitById(Fruit fruit) {
        return fruitDAO.updateFruitById(fruit);
    }

    @Override
    public Boolean deleteFruitById(Integer fid) {
        return fruitDAO.deleteFruitById(fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public int getFruitCount(String keyword) {
        return fruitDAO.getFruitCount(keyword);
    }

    @Override
    public Integer getPageCount(String keyword) {
        System.out.println("getPageCount --> " + ConnUtil.getConn());
        int fruitCount = fruitDAO.getFruitCount(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;
        return pageCount;
    }
}
