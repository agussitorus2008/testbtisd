package com.helloword.demo_project.service;
import com.helloword.demo_project.model.Checklist;
import com.helloword.demo_project.model.ChecklistItem;
import com.helloword.demo_project.repository.ChecklistRepository;
import com.helloword.demo_project.repository.ChecklistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private ChecklistItemRepository checklistItemRepository;

    public Checklist createChecklist(Checklist checklist) {
        return checklistRepository.save(checklist);
    }

    public List<Checklist> getAllChecklists() {
        return checklistRepository.findAll();
    }

    public Optional<Checklist> getChecklistById(Long id) {
        return checklistRepository.findById(id);
    }

    public void deleteChecklist(Long id) {
        checklistRepository.deleteById(id);
    }

    public ChecklistItem createChecklistItem(Long checklistId, ChecklistItem item) {
        return checklistRepository.findById(checklistId).map(checklist -> {
            item.setChecklist(checklist);
            return checklistItemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Checklist not found"));
    }

    public List<ChecklistItem> getChecklistItems(Long checklistId) {
        return checklistItemRepository.findByChecklistId(checklistId);
    }

    public Optional<ChecklistItem> getChecklistItemById(Long itemId) {
        return checklistItemRepository.findById(itemId);
    }

    public void deleteChecklistItem(Long itemId) {
        checklistItemRepository.deleteById(itemId);
    }

    public ChecklistItem updateChecklistItemStatus(Long itemId, boolean status) {
        return checklistItemRepository.findById(itemId).map(item -> {
            item.setStatus(status);
            return checklistItemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public ChecklistItem renameChecklistItem(Long itemId, String title) {
        return checklistItemRepository.findById(itemId).map(item -> {
            item.setTitle(title);
            return checklistItemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item not found"));
    }
}