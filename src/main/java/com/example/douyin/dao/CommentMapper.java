package com.example.douyin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {
    @Select("select count(*) from comments where user_id = #{video_id};")
    int getCommentCountByVideoId(int video_id);
}
