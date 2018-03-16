package com.abebe.demo.sec;

import com.abebe.demo.model.AppRole;
import com.abebe.demo.model.AppUser;
import com.abebe.demo.repo.AppRoleRepository;
import com.abebe.demo.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AppUserRepository userRepository;

    @Autowired
    AppRoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
// add role
        AppRole role = new AppRole();
        role.setRoleName("USER");
        roleRepository.save(role);

        role = new AppRole();
        role.setRoleName("ADMIN");
        roleRepository.save(role);
// addd user
        AppUser user = new AppUser();
        user.setFirstName("user");
        user.setLastName("lastName");
        user.setEmail("user@email.com");
        user.setImage("http://www.nurseryrhymes.org/nursery-rhymes-styles/images/john-jacob-jingleheimer-schmidt.jpg");
        user.setPassword("pass");
        user.setUsername("user");
        user.addRole(roleRepository.findAppRoleByRoleName("USER"));
        userRepository.save(user);
// add admin
        AppUser admin = new AppUser();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@email.com");
        admin.setImage("http://www.nurseryrhymes.org/nursery-rhymes-styles/images/john-jacob-jingleheimer-schmidt.jpg");
        admin.setPassword("pass");
        admin.setUsername("admin");
        admin.addRole(roleRepository.findAppRoleByRoleName("ADMIN"));
        userRepository.save(admin);



    }
}
