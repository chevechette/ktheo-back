package fr.ktheo.back;

import fr.ktheo.back.model.Role;
import fr.ktheo.back.model.RoleEnum;
import fr.ktheo.back.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Role role1= new Role(RoleEnum.ROLE_CUSTOMER);
                Role role2 = new Role(RoleEnum.ROLE_ADMIN);
                Role role3 = new Role(RoleEnum.ROLE_ARTIST);
                Role role4 = new Role(RoleEnum.ROLE_GALERY);
                Role role5 = new Role(RoleEnum.ROLE_MODERATOR);
                List<Role> roleList = Arrays.asList(role1,role2,role3,role4,role5);
                roleRepository.saveAll(roleList);
            }
        };
    }

}
