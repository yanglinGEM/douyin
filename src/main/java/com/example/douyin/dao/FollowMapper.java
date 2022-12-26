package com.example.douyin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FollowMapper {
    @Select("select count(*) from follows where user_id = #{user_id};")
    int getFollowCountByUserId(int user_id);

    @Select("select count(*) from follows where follower_id = #{user_id};")
    int getFollowerCountByUserId(int user_id);

    @Select("select count(*) from follows where follower_id = #{user_id} and user_id = #{author_id};")
    boolean ifFollowByUserIdAndAuthorId(int user_id, int author_id);
}
