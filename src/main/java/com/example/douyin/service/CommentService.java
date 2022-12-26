package com.example.douyin.service;

import com.example.douyin.dao.CommentMapper;
import com.example.douyin.dao.UserMapper;
import com.example.douyin.dto.Comment;
import com.example.douyin.dto.CommentResponse;
import com.example.douyin.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    public CommentResponse comment(int user_id, int video_id, String content, LocalDateTime create_date, int cancel) {
        if (cancel == 1) {
            commentMapper.insertComment(user_id, content, create_date, video_id, cancel);
            return new CommentResponse(0,"评论成功",new Comment(content,create_date,userMapper.selectById(user_id)));
        }
        commentMapper.updateComment(cancel, user_id, video_id);
        return new CommentResponse(0,"删除成功", new Comment(content,create_date,userMapper.selectById(user_id)));
    }
    public CommentResponse commentList(int video_id) {
        List<Comment> commentList = commentMapper.getCommentListByVideoId(video_id);
        for (Comment comment : commentList) {
            comment.setUser(userMapper.selectById(comment.getUser_id()));
        }
        return new CommentResponse(0,"评论列表", commentList);
    }
}
