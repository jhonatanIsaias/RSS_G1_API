package br.edu.ifs.rss_g1.notices_g1.service;

import br.edu.ifs.rss_g1.notices_g1.entity.Category;
import br.edu.ifs.rss_g1.notices_g1.repository.CategoryRepository;;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public Category saveCategory(Category category){
        try{
            return categoryRepository.save(category);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
