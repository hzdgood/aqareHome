package com.aqara.common.mapper;

import com.aqara.common.entity.QuickReply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuickReplyMapper {
    List<QuickReply> select(QuickReply quickReply);

    void insert(QuickReply quickReply);

    void update(QuickReply quickReply);

    void delete(QuickReply quickReply);
}
