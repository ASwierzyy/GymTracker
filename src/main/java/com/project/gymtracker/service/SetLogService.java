package com.project.gymtracker.service;

import com.project.gymtracker.entity.SetLog;
import com.project.gymtracker.repository.SetLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SetLogService {

    private final SetLogRepository setLogRepository;

    public SetLogService(SetLogRepository setLogRepository) {
        this.setLogRepository = setLogRepository;
    }

    public List<SetLog> getAllSetLogs() {
        return setLogRepository.findAll();
    }

    public Optional<SetLog> getSetLogById(Long id) {
        return setLogRepository.findById(id);
    }

    @Transactional
    public SetLog saveSetLog(SetLog setLog) {
        return setLogRepository.save(setLog);
    }

    @Transactional
    public void deleteSetLog(Long id) {
        setLogRepository.deleteById(id);
    }

    public List<SetLog> getSetLogsByExerciseLog(Long exerciseLogId) {
        return setLogRepository.findByExerciseLogId(exerciseLogId);
    }
}