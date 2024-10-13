package my.example.Weather.bot.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Entity
@Data
@Table(name = "users_requests")
@NoArgsConstructor
public class UserRequestModel {

    @Id
    @Column(name = "user_uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userUUID;

    private String telegramId;

    public UserRequestModel(String telegramId, String userRequest, String userResponse, String requestTime) {
        this.telegramId = telegramId;
        this.userRequest = userRequest;
        this.userResponse = userResponse;
        this.requestTime = requestTime;
    }

    private String userRequest;

    private String userResponse;

    private String requestTime;

}
