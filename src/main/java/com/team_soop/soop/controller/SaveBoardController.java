package com.team_soop.soop.controller;

import com.team_soop.soop.security.PrincipalUser;
import com.team_soop.soop.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save")
public class SaveBoardController {

    @Autowired
    private BookMarkService bookMarkService;

    @PostMapping("/{boardId}/{menuId}/board")
    public ResponseEntity<?> saveLunchBoard(@PathVariable int boardId, @PathVariable int menuId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = principalUser.getUserId();
        System.out.println(userId);

        bookMarkService.saveBoard(userId, boardId, menuId);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{boardId}/{menuId}/board")
    public ResponseEntity<?> deleteSaveLunchBoard(@PathVariable int boardId, @PathVariable int menuId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = principalUser.getUserId();

        bookMarkService.deleteSaveBoard(userId, menuId, boardId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{boardId}/{menuId}/board")
    public ResponseEntity<?> getSaveLunchBoard(@PathVariable int boardId, @PathVariable int menuId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = principalUser.getUserId();
        return ResponseEntity.ok(bookMarkService.getSaveBoardStatus(userId, menuId, boardId));
    }

}
