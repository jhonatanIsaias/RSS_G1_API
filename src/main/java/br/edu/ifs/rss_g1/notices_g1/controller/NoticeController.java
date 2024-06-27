package br.edu.ifs.rss_g1.notices_g1.controller;

import br.edu.ifs.rss_g1.notices_g1.entity.Notice;
import br.edu.ifs.rss_g1.notices_g1.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "notice")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<Notice>> findAllNoticesByCategory(@PathVariable Long userId){
        List<Notice> notices =  noticeService.handlerNoticesByCategory(userId);

        return ResponseEntity.ok().body(notices);
    }
}
