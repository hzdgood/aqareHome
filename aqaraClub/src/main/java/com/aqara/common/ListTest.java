package com.aqara.common;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List list = new ArrayList();

        list.add("1111");
        list.add("汪克祥");
        list.add("张凯凯");
        list.add("444");
        list.add("555");

        List list1 = new ArrayList();
        list1.add("1111");
        list1.add("汪克祥");
        list1.add("张凯凯");

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list1.size(); j++) {
                if (list.get(i) == list1.get(j)) {
                    list.remove(i);
                }
            }
        }
        System.out.printf(list.toString());
    }
}
