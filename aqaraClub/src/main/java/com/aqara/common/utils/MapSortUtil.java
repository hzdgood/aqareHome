package com.aqara.common.utils;

import java.util.*;

public class MapSortUtil {
	public static HashMap<String, Integer> sortByValue(Map<String, Integer> map) {
		// HashMap的entry放到List中
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		// 对List按entry的value排序
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		}.reversed() );
		// 将排序后的元素放到LinkedHashMap中
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}
}
