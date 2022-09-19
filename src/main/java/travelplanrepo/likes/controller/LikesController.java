package travelplanrepo.likes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import travelplanrepo.likes.dto.PostLikesDto;
import travelplanrepo.likes.entity.Likes;
import travelplanrepo.likes.service.LikesService;

@RestController
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/likes")
    public String postLikes(@RequestBody PostLikesDto postLikesDto) {
        Likes likes = postLikesDto.toLikes();

        return "success likes created";
    }
}
