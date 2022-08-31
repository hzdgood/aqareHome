package com.aqara.common.controller;

import java.util.*;

import com.aqara.common.utils.HttpUtil;

public class Demo {

//  hashmap按值排序
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
		// HashMap的entry放到List中
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
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

	public static void main(String[] args) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		// 填充测试数据
		hm.put("Math", 98);
		hm.put("Data Structure", 85);
		hm.put("Database", 91);
		hm.put("Java", 95);
		hm.put("Operating System", 79);
		hm.put("Networking", 80);
		Map<String, Integer> hm1 = sortByValue(hm);
		// 打印按值排序后的数据
		for (Map.Entry<String, Integer> en : hm1.entrySet()) {
			System.out.println("Key = " + en.getKey() + ", Value = " + en.getValue());
		}
	}

	public static void cece() {
		Map<String, String> stu = new TreeMap<>();// 用TreeMap储存
		// Map<String,String> stu=new HashMap<>();//用HashMap储存
		stu.put("apple", "55");
		stu.put("boy", "32");
		stu.put("cat", "22");
		stu.put("dog", "12");
		stu.put("egg", "11");
		// 1：把map转换成entryset，再转换成保存Entry对象的list。
		List<Map.Entry<String, String>> entrys = new ArrayList<>(stu.entrySet());
		// 2：调用Collections.sort(list,comparator)方法把Entry-list排序
		Collections.sort(entrys, new MyComparator());
		// 3：遍历排好序的Entry-list，可得到按顺序输出的结果
		for (Map.Entry<String, String> entry : entrys) {
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
	}

	public static void mapIterator() {
		List<String> nameLists = Arrays.asList("Lvshen", "Lvshen", "Zhouzhou", "Huamulan", "Huamulan", "Huamulan");
		Map<String, Integer> map = new HashMap<>();
		nameLists.forEach(name -> {
			Integer counts = map.get(name);
			map.put(name, counts == null ? 1 : ++counts);
		});
		System.out.println(map);
		Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			String key = (String) entry.getKey();
			Integer value = (Integer) entry.getValue();
			System.out.println("Key = " + key + ", Value = " + value);
		}
	}

	public static void sendData() {
		HttpUtil.workRequset("**今日CRM新增客户TOP** \n" + ">徐金兰:<font color=\"comment\">10</font>\n"
				+ ">汪正:<font color=\"comment\">10</font>\n" + ">徐浩:<font color=\"comment\">10</font>\n\n"
				+ "**今日CRM新增定金TOP** \n" + ">徐金兰:<font color=\"comment\">10</font>\n"
				+ ">汪正:<font color=\"comment\">10</font>\n" + ">徐浩:<font color=\"comment\">10</font>\n\n"
				+ "**今日CRM新增工勘TOP** \n" + ">徐金兰:<font color=\"comment\">10</font>\n"
				+ ">汪正:<font color=\"comment\">10</font>\n" + ">徐浩:<font color=\"comment\">10</font>\n"
				+ "[查看详情](http://work.weixin.qq.com/api/doc)");
	}
}

class MyComparator implements Comparator<Map.Entry> {
	public int compare(Map.Entry o1, Map.Entry o2) {
		return ((String) o1.getValue()).compareTo((String) o2.getValue());
	}
}
