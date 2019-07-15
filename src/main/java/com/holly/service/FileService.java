package com.holly.service;

import com.holly.model.FileEntity;

import java.util.List;


public interface FileService {

	public void saveFile(FileEntity entity);

	public FileEntity findByid(long id) ;
	public List<FileEntity> findAll();

    void del(long fileId);
}
