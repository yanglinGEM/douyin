package com.example.douyin.dao;

import com.example.douyin.dto.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VideoMapper {
    @Select("select * from videos;")
    List<Video> getVideoList();

    @Select("select * from videos where author_id = #{user_id};")
    List<Video> getVideoListByUserId(int user_id);

    @Insert("insert into videos(author_id, play_url, cover_url, publish_time, title) values (#{author_id}, #{play_url}, #{cover_url}, #{publish_time}, #{title});")
    boolean insertVideo(Video video);
}
