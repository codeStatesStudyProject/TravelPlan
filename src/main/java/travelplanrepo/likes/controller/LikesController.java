package travelplanrepo.likes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import travelplanrepo.likes.service.LikesService;
import travelplanrepo.security.argumentresolver.LoginAccountId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/{boardId}")
    public String postLikes(@LoginAccountId Long accountId, @PathVariable Long boardId) {
        likesService.createLikes(accountId, boardId);

        return "success likes created";
    }

    @DeleteMapping("/{boardId}")
    public String deleteLikes(@LoginAccountId Long accountId, @PathVariable Long boardId) {
        likesService.deleteLikes(accountId, boardId);

        return "success likes deleted";
    }
}
