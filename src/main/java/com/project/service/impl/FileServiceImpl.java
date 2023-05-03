package com.project.service.impl;

import com.project.mapper.FileMapper;
import com.project.pojo.File;
import com.project.repoistry.FileRepository;
import com.project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    /**
     * 保存文件，默认open_forgetting_curve=1，参数为文件内容
     * @param context
     * @return
     */
    @Override
    public String saveOfcC(String context) {
        File file = new File();
        file.setContext(context);
        file.setOpen_forgetting_curve(1);
        return save(file).getFile_id().toString();
    }
}
