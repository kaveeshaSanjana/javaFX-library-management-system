package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserChangePassDto {
    private String username;
    private String password;
    private String comfermPassword;
}
