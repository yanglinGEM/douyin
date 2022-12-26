package com.example.douyin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {
    @Select("select count(*) from likes where user_id = #{video_id};")
    int getLikeCountByVideoId(int video_id);
    @Select("select count(*) from likes where user_id = #{user_id} and video_id = #{video_id};")
    boolean ifLikeByUserIdAndVideoId(int user_id, int video_id);
}
