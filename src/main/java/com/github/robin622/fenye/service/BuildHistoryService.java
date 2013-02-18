package com.github.robin622.fenye.service;

import java.util.ArrayList;
import java.util.List;

import com.github.robin622.fenye.bean.TestBean;

public class BuildHistoryService {

    public List<TestBean> RequestHistory() {
        List<TestBean> histories = null;
        histories = generateBeans();
        return histories;
    }

    // a mock method to generate a list of beans,say 50
    private List<TestBean> generateBeans() {
        List<TestBean> list = new ArrayList<TestBean>();
        for (int i = 0; i < 50; i++) {
            TestBean tb = new TestBean();
            tb.setId(i);
            tb.setName("test" + i);
            list.add(tb);
        }
        return list;
    }

}
