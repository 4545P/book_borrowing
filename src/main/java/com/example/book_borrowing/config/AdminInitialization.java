package com.example.book_borrowing.config;

import com.example.book_borrowing.repository.UsersDao;
import com.example.book_borrowing.service.impl.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.book_borrowing.entity.Users;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Configuration
public class AdminInitialization {


  private UsersDao usersDao;

  @Autowired
  public void setUsersDao(UsersDao usersDao) {
    this.usersDao = usersDao;
  }

  private UsersServiceImpl usersServiceImpl;

  @Autowired
  public void setUsersServiceImpl(UsersServiceImpl usersServiceImpl) {
    this.usersServiceImpl = usersServiceImpl;
  }

  @Bean
  public void initializeSuperAdmin() {
    String superAdminName = "admin";

    Optional<List<Users>> existingAdmin = Optional.ofNullable(usersDao.findByName(superAdminName));

    if (existingAdmin.isPresent() && !existingAdmin.get().isEmpty()) {
      System.out.println("超級管理員已存在.");
    } else {
      Users superAdmin = new Users();
      superAdmin.setName(superAdminName);
      superAdmin.setPhone("0900000000");

      String hashedPassword = usersServiceImpl.hashPassword("admin");
      if (hashedPassword != null) {
        superAdmin.setPassword(hashedPassword);
      } else {
        System.out.println("密碼加密失敗，無法創建超級管理員.");
        return;
      }

      superAdmin.setRegistration(LocalDateTime.now());
      superAdmin.setLastLogin(LocalDateTime.now());

      usersDao.save(superAdmin);
      System.out.println("超級管理員建立成功.");
    }
  }
}
