package com.holly.dao;

import com.holly.model.FileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FileMapper {
	void saveFile(FileEntity entity);
	FileEntity findByid(long id);
	List<FileEntity> findAll();

    void del(long fileId);
}
