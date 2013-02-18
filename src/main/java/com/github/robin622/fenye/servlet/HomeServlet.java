package com.github.robin622.fenye.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.robin622.fenye.bean.TestBean;
import com.github.robin622.fenye.service.BuildHistoryService;

/**
 * @author wezhao Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    @Inject
    private BuildHistoryService historyservice;

    /**
     * Default constructor.
     */
    public HomeServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPage = request.getParameter("cp");
        if (currentPage == null) {
            currentPage = "1";
        }
        request.setAttribute("cp", currentPage);
        request.setAttribute("rp", "/HomeServlet");
        // I get all items out at one time, and display them by pages, you can also get one-page data at once from the database
        List<TestBean> histories = historyservice.RequestHistory();

        int spp = 5; // items per page
        int allData = histories.size();
        int sum = allData / spp + (allData % spp != 0 ? 1 : 0);
        if (sum == 0) {
            sum = 1;
        }
        int lastindex = (Integer.valueOf(currentPage)) * spp;
        if (lastindex > allData) {
            lastindex = allData;
        }
        Collection<TestBean> sub_histories = ((ArrayList<TestBean>) histories).subList(
                (Integer.valueOf(currentPage) - 1) * spp, lastindex);

        request.setAttribute("allpage", sum);
        request.setAttribute("histories", sub_histories);
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

}
