package my.example.Weather.bot.repository;

import my.example.Weather.bot.Model.UserRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequestModel,Integer> {

}