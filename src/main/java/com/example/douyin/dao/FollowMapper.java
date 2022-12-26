package com.example.douyin.dao;

import com.example.douyin.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FollowMapper {
    @Select("select count(*) from follows where follower_id = #{user_id};")
    int getFollowCountByUserId(int user_id);

    @Select("select count(*) from follows where user_id = #{user_id};")
    int getFollowerCountByUserId(int user_id);

    @Select("select count(*) from follows where follower_id = #{user_id} and user_id = #{to_user_id};")
    boolean ifFollowByUserIdAndAuthorId(int user_id, int to_user_id);
    @Select("select cancel from follows where follower_id = #{user_id} and user_id = #{author_id};")
    int getCancelByUserIdAndAuthorId(int user_id, int author_id);
    @Update("update follows set cancel = #{action_type} where follower_id = #{user_id} and user_id = #{to_user_id};")
    void setFollowByUserIdAndFollowId(int action_type, int user_id, int to_user_id);
    @Insert("insert into follows(user_id,follower_id,cancel) values(#{to_user_id}, #{user_id}, #{cancel});")
    void insertFollowByUserIdAndVideoId(int user_id, int to_user_id, int cancel);

    @Select("select users.* from users, follows where users.id = follows.user_id and #{user_id} = follows.follower_id;")
    List<User> getFollowListByUserId(int user_id);

    @Select("select users.* from users, follows where users.id = follows.follower_id and #{user_id} = follows.user_id;")
    List<User> getFollowerListByUserId(int user_id);
}
