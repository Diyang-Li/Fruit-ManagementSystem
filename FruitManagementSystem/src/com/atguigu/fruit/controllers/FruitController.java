package com.atguigu.fruit.controllers;

import com.atguigu.fruit.service.FruitService;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.fruit.service.impl.FruitServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.atguigu.myssm.util.StringUtil.isEmpty;
import static com.atguigu.myssm.util.StringUtil.isNotEmpty;

/**
 * Use reflect to get all method in this class, then find the method that has the same name with the operate
 *
 * @author Diyang Li
 * @create 2022-04-08 2:47 PM
 */
//@WebServlet("/fruit.do")
public class FruitController {
    private FruitService fruitService = null;

    /**
     * return the fuit list in database and render to the index.html page
     * @param oper
     * @param keyword
     * @param pageNo
     * @param req
     * @return "index"
     */
    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (pageNo == null) {
            pageNo = 1;
        }
        if (isNotEmpty(oper) && "search".equals(oper)) {
            //The request comes from form
            pageNo = 1;
            if (isEmpty(keyword)) {
                keyword = "";
            }

            session.setAttribute("keyword", keyword);

        } else {
            //The request doesn't from form
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }


        session.setAttribute("pateNo", pageNo);


        List<Fruit> fruitList = fruitService.getFruitList(pageNo, keyword);
        //Save to Session
        session.setAttribute("fruitList", fruitList);

        //Use key word to find fruit

        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount", pageCount);

        // Replace super.processTemplate("index", req,resp);
        return "index";
    }

    /**
     * Delete fruit in database by Fid
     * @param fid
     * @return redirect:fruit.do
     */
    private String delete(Integer fid) {
        if (fid != null) {

            Boolean aBoolean = fruitService.deleteFruitById(fid);
            //Replace resp.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        }
        return "error";
    }

    /**
     * Edit fruit information and update to the database
     * @param fid
     * @param req
     * @return "edit"
     */
    private String edit(Integer fid, HttpServletRequest req) {

        if (fid != null) {

            Fruit fruit = fruitService.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            //Replace super.processTemplate("edit", req, resp);
            return "edit";
        }
        return "error";
    }

    /**
     * Treate the add request from index.html and send to add.html
     * @return "add"
     */
    private String treatAdd() {
        //Replace super.processTemplate("add", req, resp);
        return "add";
    }

    /**
     * Add new fruit to database
     * @param fname
     * @param price
     * @param fcount
     * @param remark
     * @return "redirect:fruit.do"
     */
    private String add(String fname, Integer price, Integer fcount, String remark) {
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitService.addFruit(fruit);
        // Replace resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }


    /**
     * Update current fruit list in database
     * @param fid
     * @param fname
     * @param price
     * @param fcount
     * @param remark
     * @return
     */
    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        fruitService.updateFruitById(new Fruit(fid, fname, price, fcount, remark));

        //Replace resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }
}
