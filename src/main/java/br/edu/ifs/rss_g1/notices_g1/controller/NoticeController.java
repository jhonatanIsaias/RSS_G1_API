package br.edu.ifs.rss_g1.notices_g1.controller;

import br.edu.ifs.rss_g1.notices_g1.entity.Notice;
import br.edu.ifs.rss_g1.notices_g1.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "notice")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping(value = "/notices-categories")
    public ResponseEntity<List<Notice>> findAllNoticesByCategory( @RequestHeader(HttpHeaders.AUTHORIZATION) String auth){

        List<Notice> notices =  noticeService.handlerNoticesByCategory(auth);

        return ResponseEntity.ok().body(notices);
    }
}
