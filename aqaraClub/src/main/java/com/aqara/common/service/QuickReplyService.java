package com.aqara.common.service;

import com.aqara.common.entity.QuickReply;
import com.aqara.common.mapper.QuickReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuickReplyService {
    private QuickReplyMapper QuickReplyMapper;

    @Autowired
    public void setMapper(QuickReplyMapper QuickReplyMapper) {
        this.QuickReplyMapper = QuickReplyMapper;
    }

    public List<QuickReply> select(QuickReply QuickReply) {
        return QuickReplyMapper.select(QuickReply);
    }

    public void insert(QuickReply QuickReply) {
        QuickReplyMapper.insert(QuickReply);
    }

    public void update(QuickReply QuickReply) {
        QuickReplyMapper.update(QuickReply);
    }

    public void delete(QuickReply QuickReply) {
        QuickReplyMapper.delete(QuickReply);
    }

}
