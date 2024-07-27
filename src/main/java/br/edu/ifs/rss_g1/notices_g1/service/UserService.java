package br.edu.ifs.rss_g1.notices_g1.service;

import br.edu.ifs.rss_g1.notices_g1.dto.UserDTO;
import br.edu.ifs.rss_g1.notices_g1.entity.Category;
import br.edu.ifs.rss_g1.notices_g1.entity.Notice;
import br.edu.ifs.rss_g1.notices_g1.entity.User;
import br.edu.ifs.rss_g1.notices_g1.enums.RoleEnum;
import br.edu.ifs.rss_g1.notices_g1.repository.CategoryRepository;
import br.edu.ifs.rss_g1.notices_g1.repository.NoticeRepository;
import br.edu.ifs.rss_g1.notices_g1.repository.UserRepository;
import br.edu.ifs.rss_g1.notices_g1.service.Exceptions.UserException;
import br.edu.ifs.rss_g1.notices_g1.utils.ParseDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.expression.ParseException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public User saveUser(UserDTO user){
        try{
          UserDetails u = userRepository.findByEmail(user.getEmail());
           if(u == null){
               User userCreate = userDtoToUser(user);
               return userRepository.save(userCreate);
           }
          throw new RuntimeException();
        }
        catch (RuntimeException e){
           throw new UserException(e.getMessage());
        }
    }

    private User userDtoToUser(UserDTO userDTO){
        User user =  new User();
        BeanUtils.copyProperties(userDTO,user);
       /*

         user.setName(userDTO.getName());
         user.setEmail(userDTO.getEmail());
         user.setLogin(userDTO.getLogin());
         user.setFone(userDTO.getFone());

         user.setStatus(true);*/
        user.setRole(RoleEnum.valueOf(2));
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        user.setPassword(encryptedPassword);
        user.setBirth_date(ParseDate.parseDate(userDTO.getBirth_date(),DATE_FORMAT));
        user.setCreated_at(new Date());
        setCategoriesUser(userDTO,user);
        return user;

    }


    private void setCategoriesUser(UserDTO userDTO, User user){
        if(!userDTO.getCategories().isEmpty()){
            for(Long id : userDTO.getCategories()){
                Category category = categoryRepository.findByCategoryId(id);
                if(category != null){
                    user.getCategories().add(category);
                }
            }
        }
    }
}
