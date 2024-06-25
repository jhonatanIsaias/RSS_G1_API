package br.edu.ifs.rss_g1.notices_g1.dto;



import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO  {




    @NotNull(message = "Name cannot not be null")
    @Size(min = 8,max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
    private String fone;

    @NotNull(message = "Name cannot not be null")
    private String login;

    @Email(message = "this is a invalid email")
    private String email;


    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character (@#$%^&+=)")
    private String password;

    @NotNull(message = "Birth date cannot be null")
    private String birth_date;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

    private String created_at;

    private Set<Long> categories = new HashSet<>();





}
