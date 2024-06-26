package br.edu.ifs.rss_g1.notices_g1.service;

import br.edu.ifs.rss_g1.notices_g1.entity.Category;
import br.edu.ifs.rss_g1.notices_g1.entity.Notice;
import br.edu.ifs.rss_g1.notices_g1.repository.CategoryRepository;
import br.edu.ifs.rss_g1.notices_g1.repository.NoticeRepository;
import br.edu.ifs.rss_g1.notices_g1.utils.ParseDate;
import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.MediaModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@EnableAsync
public class NoticeService {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);


    private final NoticeRepository noticeRepository;
    private final CategoryRepository categoryRepository;
    private final RssFeedService rssFeedService;

    public void save(){

        List<Category> categories = categoryRepository.findAll();
        try{
            for(Category c : categories){
                List<SyndEntry> notices = rssFeedService.readFeed(c.getLink());
                for(SyndEntry n : notices){
                    MediaEntryModule mediaModule = (MediaEntryModule) n.getModule(MediaModule.URI);
                    Notice noticeCreated = new Notice(null,
                            n.getTitle(),
                            n.getDescription().getValue(),
                            getLinkImage(mediaModule),
                            ParseDate.parseDate(n.getPublishedDate().toString(),DATE_FORMAT),
                            c);
                     noticeRepository.save(noticeCreated);
                }

            }
        } catch (FeedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Notice> findAllByCategory(Category category){
        return noticeRepository.findAllByCategory(category);
    }

    public String getLinkImage(MediaEntryModule mediaModule){
        if (mediaModule != null && mediaModule.getMediaContents().length > 0) {
            return String.valueOf(mediaModule.getMediaContents()[0].getReference());
        }
        return "link not found";
    }

}
