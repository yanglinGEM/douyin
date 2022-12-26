package com.example.douyin.dao;

import com.example.douyin.dto.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LikeMapper {
    @Select("select count(*) from likes where video_id = #{video_id};")
    int getLikeCountByVideoId(int video_id);
    @Select("select count(*) from likes where user_id = #{user_id} and video_id = #{video_id};")
    boolean ifLikeByUserIdAndVideoId(int user_id, int video_id);
    @Select("select cancel from likes where user_id = #{user_id} and video_id = #{video_id};")
    int getCancelByUserIdAndVideoId(int user_id, int video_id);
    @Update("update likes set cancel = #{action_type} where user_id = #{user_id} and video_id = #{video_id};")
    void setLikeByUserId(int action_type, int user_id, int video_id);
    @Insert("insert into likes(user_id,video_id,cancel) values(#{user_id}, #{video_id}, #{cancel});")
    void insertLikeByUserIdAndVideoId(int user_id, int video_id, int cancel);
    @Select("select videos.* from videos, likes where likes.video_id = videos.id and likes.cancel = 1 and likes.user_id = #{user_id};")
    List<Video> getLikeVideoList(int user_id);
}
