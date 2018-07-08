package com.example.demo.map;

import com.example.demo.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//说明这是个一个mapper
public interface NoticeMapper {
    public int insertNotice(Notice notice) throws Exception;

    public List<Notice> selectNotice();
}
