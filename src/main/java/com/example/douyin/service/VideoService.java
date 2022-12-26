package com.example.douyin.service;

import com.example.douyin.dao.CommentMapper;
import com.example.douyin.dao.LikeMapper;
import com.example.douyin.dao.VideoMapper;
import com.example.douyin.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class VideoService {
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    LikeMapper likeMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserService userService;
    public List<Video> getVideoList(int selfUserId, int authorId) {
        List<Video> videoList;
        if (authorId == -1) videoList = videoMapper.getVideoList();
        else videoList = videoMapper.getVideoListByUserId(authorId);
        for (Video v : videoList) {
            v.setFavorite_count(likeMapper.getLikeCountByVideoId(v.getId()));
            v.setComment_count(commentMapper.getCommentCountByVideoId(v.getId()));
            v.setIs_favorite(likeMapper.ifLikeByUserIdAndVideoId(selfUserId,v.getId()));
            v.setAuthor(userService.getUserInfoByUserId(v.getAuthor_id(), selfUserId));
        }
        return videoList;
    }
    public Map<String, String> publishVideo(int author_id, MultipartFile data, String title) throws IOException {
        String play_url = "http://192.168.1.3:8080/static/" + author_id + title + ".mp4";
        data.transferTo(Path.of("D:\\BackProject\\douyin\\src\\main\\resources\\static\\", author_id + title + ".mp4"));
        String cover_url = "https://cdn.pixabay.com/photo/2016/03/27/18/10/bear-1283347_1280.jpg";
        Video video = new Video(author_id, play_url, cover_url, LocalDateTime.now(), title);
        if (author_id == -1 || !videoMapper.insertVideo(video) ) return Map.of("status_code", "1", "status_msg","视频上传失败，请重新登录");
        System.out.println("上传视频");
        return Map.of("status_code", "0", "status_msg","视频上传成功");
    }
}
