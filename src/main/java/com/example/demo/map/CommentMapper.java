package com.example.demo.map;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Diary;

import java.util.List;

public interface CommentMapper {
	public int insertComment(Comment comment) throws Exception;
	public List<Comment> selectComment(int commentDiaryID);
}
