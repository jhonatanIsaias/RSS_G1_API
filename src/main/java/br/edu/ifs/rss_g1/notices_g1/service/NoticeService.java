package br.edu.ifs.rss_g1.notices_g1.service;

import br.edu.ifs.rss_g1.notices_g1.entity.Category;
import br.edu.ifs.rss_g1.notices_g1.entity.Notice;
import br.edu.ifs.rss_g1.notices_g1.entity.User;
import br.edu.ifs.rss_g1.notices_g1.repository.CategoryRepository;
import br.edu.ifs.rss_g1.notices_g1.repository.NoticeRepository;
import br.edu.ifs.rss_g1.notices_g1.repository.UserRepository;
import br.edu.ifs.rss_g1.notices_g1.utils.ParseDate;
import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.MediaModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);


    private final NoticeRepository noticeRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RssFeedService rssFeedService;

    @Async
    @Scheduled(fixedDelay = 3600000)
    protected void save(){

        List<Category> categories = categoryRepository.findAll();
        
        try{
            for(Category c : categories){
                List<SyndEntry> notices = rssFeedService.readFeed(c.getLink());
                for(SyndEntry n : notices){
                    Date date =  ParseDate.parseDate(n.getPublishedDate().toString(), DATE_FORMAT);
                    if(noticeRepository.findByTitleAndPubDate(n.getTitle(),date).isEmpty()) {
                        MediaEntryModule mediaModule = (MediaEntryModule) n.getModule(MediaModule.URI);

                        Notice noticeCreated = new Notice(null,
                                n.getTitle(),
                                n.getDescription().getValue(),
                                getLinkImage(mediaModule),
                                date,
                                c);
                        noticeRepository.save(noticeCreated);
                    }
                }

            }
        } catch (FeedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public List<Notice> handlerNoticesByCategory(String authIdentify){

        if(authIdentify != null) authIdentify = authIdentify.replace("Bearer ","");
        String user = TokenService.getSubjectFromToken(authIdentify);
        Long userId =  userRepository.findByLogin(user).getUserId();
        User optionalUser = userRepository.findById(userId).orElse(null);

            if(optionalUser != null){

            List<Category> categories = optionalUser.getCategories();
            if(categories.isEmpty()){
                return noticeRepository.findAll();
            }
            List<Notice> notices = new ArrayList<>();

            for(Category c: categories){
                List<Notice> noticesCategory = noticeRepository.findAllByCategory(c);
                notices.addAll(noticesCategory);
            }
            return notices;
        }

         return null;
    }

    public String getLinkImage(MediaEntryModule mediaModule){
        if (mediaModule != null && mediaModule.getMediaContents().length > 0) {
            return String.valueOf(mediaModule.getMediaContents()[0].getReference());
        }
        return "link not found";
    }

}
