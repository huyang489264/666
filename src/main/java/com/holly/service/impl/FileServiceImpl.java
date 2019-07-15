package com.holly.service.impl;


import com.holly.dao.FileMapper;
import com.holly.model.FileEntity;
import com.holly.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileMapper fileMapper;
	@Override
	public void saveFile(FileEntity entity) {
		fileMapper.saveFile(entity);
		
	}

	@Override
	public FileEntity findByid(long id) {
		
		return fileMapper.findByid(id);
	}

	@Override
	public List<FileEntity> findAll() {
		
		return fileMapper.findAll();
	}

	@Override
	public void del(long fileId) {
		fileMapper.del(fileId);
	}

}
