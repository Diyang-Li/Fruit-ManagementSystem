package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/** Implementation class of FruitDAO
 * @author Diyang Li
 * @create 2022-04-13 12:22 PM
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(Integer pagenNo, String keyword) {
        return super.executeQuery("select * from t_fruit where fname like ? or remark like ? limit ?, 5", "%"+keyword+"%","%"+keyword+"%",(pagenNo-1)*5);
    }

    @Override
    public Fruit getFruitByFid(int fid) {
        return super.load("SELECT * FROM `t_fruit` WHERE fid = ?", fid);
    }

    @Override
    public Boolean updateFruitById(Fruit fruit) {
        String sql = "UPDATE `t_fruit` SET `fname` = ?, `price` = ?, `fcount` =?, `remark`=? WHERE fid =?";
        int operation = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
        return operation > 0;
    }

    @Override
    public Boolean deleteFruitById(Integer fid) {
        String sql = "DELETE FROM `t_fruit` WHERE `fid` = ?";
        return super.executeUpdate(sql, fid) > 0;
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "INSERT INTO `t_fruit` VALUES(0,?,?,?,?);";
       super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }


    @Override
    public int getFruitCount(String keyword) {
        String sql = "SELECT COUNT(*) FROM `t_fruit` where fname like ? or remark like ?";
        return ((Long) super.executeComplexQuery(sql, "%"+keyword+"%", "%"+keyword+"%")[0]).intValue();
    }
}
