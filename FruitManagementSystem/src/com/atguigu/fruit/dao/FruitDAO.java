package com.atguigu.fruit.dao;


import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/** Add, edit, delete and query in database. DAO layer
 * @author Diyang Li
 * @create 2022-04-13 12:11 PM
 */

public interface FruitDAO {
    /**
     * Get fruit list information
     * @param pageNo
     * @param keyword
     * @return List of fruit
     */
    List<Fruit> getFruitList(Integer pageNo,String keyword);

    /**
     * Get fruit list information
     * @param fid
     * @return Fruit
     */
    Fruit getFruitByFid(int fid);

    /**
     * Get fruit list information
     * @param fruit
     * @return if the operation success
     */
    Boolean updateFruitById(Fruit fruit);

    /**
     * Delete fruit by Fid
     * @param fid
     * @return if the operation success
     */
    Boolean deleteFruitById(Integer fid);

    /**
     * Add fruit to the list
     * @param fruit
     */
    void addFruit(Fruit fruit);

    /**
     * Get the count of fruit that include keyword
     * @param keyword
     * @return fruit count
     */
    int getFruitCount(String keyword);
}
