package com.aqara.common.service;

import com.aqara.common.entity.Census;
import com.aqara.common.mapper.CensusMapper;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CensusService {
    private CensusMapper CensusMapper;

    @Autowired
    public void setMapper(CensusMapper CensusMapper) {
        this.CensusMapper = CensusMapper;
    }

    public List<Census> select() {
        return CensusMapper.select();
    }

    public String getCensusData() {
        String res = "**今日CRM新增微信** \n";
        List<Census> CensusList = CensusMapper.select();
        return getDepartInfo(res, CensusList);
    }

    public String getDepartInfo(String res, List<Census> CensusList) {
        if (CensusList.size() == 0) {
            return "";
        }
        Map<String, Integer> map = new HashMap<>();
        CensusList.forEach(name -> {
            Integer counts = map.get(name.getDepartment());
            map.put(name.getDepartment(), counts == null ? 1 : ++counts);
        });
        Map<String, Integer> map1 = CommonUtil.sortByValue(map);
        Iterator entries = map1.entrySet().iterator();
        int index = 0;
        while (entries.hasNext()) {
            if (index >= 3) {
                break;
            }
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            Integer value = (Integer) entry.getValue();
            res += ">" + key + ":<font color=\"comment\">" + value + "</font>\n";
            index += 1;
        }
        return res;
    }
}
