package com.atguigu.fruit.service;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/** Add, edit, delete and query in database, Service Layer
 * @author Diyang Li
 * @create 2022-04-13 12:11 PM
 */
public interface FruitService {
    /**
     * Get fruit list information
     *
     * @param pageNo
     * @param keyword
     * @return fruit list
     */
    List<Fruit> getFruitList(Integer pageNo, String keyword);

    /**
     * Get fruit by Fid
     *
     * @param fid
     * @return Fruit
     */
    Fruit getFruitByFid(int fid);

    /**
     * Get fruit list information
     *
     * @param fruit
     * @return if the operation success
     */
    Boolean updateFruitById(Fruit fruit);

    /**
     * Delete fruit by Fid
     *
     * @param fid
     * @return if the operation success
     */
    Boolean deleteFruitById(Integer fid);

    /**
     * Add fruit to the list
     *
     * @param fruit
     */
    void addFruit(Fruit fruit);

    /**
     * Get the count of fruit that include keyword
     *
     * @param keyword
     * @return fruit count
     */
    int getFruitCount(String keyword);

    /**
     * Get the page count that includes keyword
     *
     * @param keyword
     * @return fruit count
     */
    Integer getPageCount(String keyword);
}
