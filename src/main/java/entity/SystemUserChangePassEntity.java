package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserChangePassEntity {
    private String username;
    private String password;
    private String comfermPassword;
}
