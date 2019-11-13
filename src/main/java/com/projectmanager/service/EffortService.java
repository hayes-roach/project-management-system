package com.projectmanager.service;

import com.projectmanager.data.dao.EffortRepository;
import com.projectmanager.data.object.Effort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EffortService {

    @Autowired
    EffortRepository effortRepository;

    public Effort getEffortByDate(Date date) {
        return effortRepository.findEffortByDate(date);
    }

    public Effort getEffortById(String id) {
        return effortRepository.findEffortById(id);
    }


    public Effort updateEffort(Effort effort) {
        effortRepository.save(effort);
        return effort;
    }

    public void deleteEffortById(String id) {
        effortRepository.deleteById(id);
    }

}
