package com.example.douyin.dao;

import com.example.douyin.dto.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select count(*) from comments where video_id = #{video_id};")
    int getCommentCountByVideoId(int video_id);

    @Select("select * from comments where video_id = #{video_id};")
    List<Comment> getCommentListByVideoId(int video_id);

    @Insert("insert into comments(user_id, content, create_date, video_id, cancel" +
            ") values (#{user_id}, #{content}, #{create_date}, #{video_id}, #{cancel});")
    void insertComment(int user_id, String content, LocalDateTime create_date, int video_id, int cancel);

    @Update("update comments set cancel = #{cancel} where user_id = #{user_id} and video_id = #{video_id};")
    void updateComment(int cancel, int user_id, int video_id);

    @Select("select id from comments where user_id = #{user_id} and create_date = #{create_date} and video_id = #{video_id};")
    int getCommentId(int user_id, LocalDateTime create_date, int video_id);
}
