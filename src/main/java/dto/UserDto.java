package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private String username;
    private String password;
    private String nameSurname;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNameSurname() {
        return nameSurname;
    }
}
