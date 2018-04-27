package com.example.demo.map;

import com.example.demo.entity.Diary;

import java.util.List;

public interface DiaryMapper {
	public int insertDiary(Diary diary) throws Exception;
	public List<Diary> selectShare();
}
