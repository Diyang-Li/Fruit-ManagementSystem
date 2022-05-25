package com.atguigu.fruit.pojo;

/** java bean for fruit_db
 * @author Diyang Li
 * @create 2022-04-13 12:22 PM
 */
public class Fruit {
    private Integer fid;
    private String fname;
    private Integer price;
    private Integer fcount;
    private String remark;

    public Fruit() {
    }

    public Fruit(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        this.fid = fid;
        this.fname = fname;
        this.price = price;
        this.fcount = fcount;
        this.remark = remark;
    }

    /**
     * getFid
     *
     * @return fid
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * set Fid
     *
     * @param fid
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * getFname
     *
     * @return fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * set Fname
     *
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    public Integer getPrice() {
        return price;
    }

    /**
     * setPric
     *
     * @param price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * getFcount()
     *
     * @return fcount
     */
    public Integer getFcount() {
        return fcount;
    }

    /**
     * setFcount
     *
     * @param fcount
     */
    public void setFcount(Integer fcount) {
        this.fcount = fcount;
    }

    /**
     * getRemark()
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * setRemark
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return fid + "\t\t" + fname + "\t\t" + price + "\t\t" + fcount + "\t\t" + remark;
    }
}
