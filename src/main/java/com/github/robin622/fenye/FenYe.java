package com.github.robin622.fenye;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jboss.logging.Logger;

public class FenYe extends TagSupport {

    protected static final Logger log = Logger.getLogger(FenYe.class);

    String currentpage;
    String sum;
    String myrequest;

    public void setSum(String sum) {
        this.sum = sum;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpServletRequest hsr = (HttpServletRequest) pageContext.getRequest();
        String path = hsr.getContextPath();
        // attention: display_page should larger than 2
        int display_page = 5;
        int sum_page = Integer.parseInt(sum);
        int current_page = Integer.parseInt(currentpage);
        try {
            if (current_page == 1) {
                out.println("<li class='disabled'><a href='#'>«</a></li>");
            } else {
                out.println("<li><a href='" + path + myrequest + "?cp=1'>«</a></li>");
            }
            if (sum_page > display_page) {
                int init_page = 1;
                // the left is not enough for dividing up
                if (sum_page - current_page < 2) {
                    init_page = sum_page - (display_page - 1);
                } else {
                    // the distance of moving
                    int move_page = display_page / 2 + init_page;
                    // decide if moving or not
                    if (current_page > move_page) {
                        init_page += current_page - move_page;
                    }
                }
                // solve the ending page
                int last_page = init_page + display_page;
                if (last_page > sum_page + 1) {
                    last_page = sum_page + 1;
                    init_page = init_page - 1;
                }
                for (int i = init_page; i < last_page; i++) {
                    output_allpages(out, path, current_page, i);
                }
            } else {
                for (int i = 1; i < sum_page + 1; i++) {
                    output_allpages(out, path, current_page, i);
                }
            }
            if (current_page == sum_page) {
                out.println("<li class='disabled'><a href='#'>»</a></li>");
            } else {
                out.println("<li><a href='" + path + myrequest + "?cp=" + sum_page + "'>»</a></li>");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return super.doStartTag();
    }

    private void output_allpages(JspWriter out, String path, int current_page, int i) throws IOException {
        if (current_page == i) {
            out.println("<li class='active'><a href='#'>" + i + "</a></li>");
        } else {
            out.println("<li><a href='" + path + myrequest + "?cp=" + i + "'>" + i + "</a></li>");
        }
    }

    public void setMyrequest(String myrequest) {
        this.myrequest = myrequest;
    }

}
