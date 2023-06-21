package com.example.demo.schedule;

import com.example.demo.email.MailService;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class ScheduleEmail {

    @Autowired
    private MailService mailService;
    @Value("${gmail}")
    private String gmail;
    @Autowired
    private UserRepositoty userRepositoty;

    //fixedRate : khoảng thời gian nó thực hiện tác vụ
    //fixedDelay : khoảng thời gian thực hiện tác vụ thêm method đấy hoàn thành xong
    //Cron leen lich
    @Scheduled(cron = "0 30 18 * * *")
    void sentMailSchedule() throws MessagingException {
        List<UserEntity> userEntities = userRepositoty.findAll();
        for(UserEntity userEntity : userEntities){
            // Chuyển đổi giá trị Date thành LocalDateTime
            LocalDateTime birthdayDateTime = LocalDateTime.ofInstant(userEntity.getBirthday().toInstant(), ZoneId.systemDefault());
            // Lấy ngày và giờ hiện tại
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();

            // Tạo LocalDateTime từ ngày và giờ hiện tại
            LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);
            // So sánh thời gian hiện tại với thời gian sinh nhật
            if (currentDateTime.getDayOfMonth() == birthdayDateTime.getDayOfMonth() && currentDateTime.getMonthValue() == birthdayDateTime.getMonthValue()) {
                // Ngày sinh nhật và ngày hiện tại trùng nhau
                // Gửi thông báo chúc mừng sinh nhật qua mail
                mailService.sendMail(gmail, "Chúc mừng sinh nhật!");
            }
        }
    }
}