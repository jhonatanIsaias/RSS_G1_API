package br.edu.ifs.rss_g1.notices_g1.controller;

import br.edu.ifs.rss_g1.notices_g1.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<Void> save(){
        noticeService.save();
        return ResponseEntity.ok().build();
    }
}
