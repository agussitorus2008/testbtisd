package com.helloword.demo_project.controller;

import com.helloword.demo_project.model.Checklist;
import com.helloword.demo_project.model.ChecklistItem;
import com.helloword.demo_project.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checklists")
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;

    @PostMapping
    public ResponseEntity<Checklist> createChecklist(@RequestBody Checklist checklist) {
        Checklist createdChecklist = checklistService.createChecklist(checklist);
        return ResponseEntity.ok(createdChecklist);
    }

    @GetMapping
    public ResponseEntity<List<Checklist>> getAllChecklists() {
        List<Checklist> checklists = checklistService.getAllChecklists();
        return ResponseEntity.ok(checklists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Checklist>> getChecklistById(@PathVariable Long id) {
        Optional<Checklist> checklist = checklistService.getChecklistById(id);
        return ResponseEntity.ok(checklist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChecklist(@PathVariable Long id) {
        checklistService.deleteChecklist(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{checklistId}/items")
    public ResponseEntity<ChecklistItem> createChecklistItem(@PathVariable Long checklistId, @RequestBody ChecklistItem item) {
        ChecklistItem createdItem = checklistService.createChecklistItem(checklistId, item);
        return ResponseEntity.ok(createdItem);
    }

    @GetMapping("/{checklistId}/items")
    public ResponseEntity<List<ChecklistItem>> getChecklistItems(@PathVariable Long checklistId) {
        List<ChecklistItem> items = checklistService.getChecklistItems(checklistId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{checklistId}/items/{itemId}")
    public ResponseEntity<Optional<ChecklistItem>> getChecklistItemById(@PathVariable Long itemId) {
        Optional<ChecklistItem> item = checklistService.getChecklistItemById(itemId);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{checklistId}/items/{itemId}")
    public ResponseEntity<Void> deleteChecklistItem(@PathVariable Long itemId) {
        checklistService.deleteChecklistItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{checklistId}/items/{itemId}/status")
    public ResponseEntity<ChecklistItem> updateChecklistItemStatus(@PathVariable Long itemId, @RequestBody boolean status) {
        ChecklistItem updatedItem = checklistService.updateChecklistItemStatus(itemId, status);
        return ResponseEntity.ok(updatedItem);
    }

    @PatchMapping("/{checklistId}/items/{itemId}/rename")
    public ResponseEntity<ChecklistItem> renameChecklistItem(@PathVariable Long itemId, @RequestBody String title) {
        ChecklistItem updatedItem = checklistService.renameChecklistItem(itemId, title);
        return ResponseEntity.ok(updatedItem);
    }
}