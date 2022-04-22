package fr.ktheo.back.service;

import fr.ktheo.back.model.Role;
import fr.ktheo.back.model.RoleEnum;
import fr.ktheo.back.model.User;
import fr.ktheo.back.model.UserData;
import fr.ktheo.back.repository.RoleRepository;
import fr.ktheo.back.repository.UserDataRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDataRepository userDataRepository;

    public boolean checkUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public User signup(String username,String mail, String password) {
        Role roleUser = roleRepository.findByName(RoleEnum.ROLE_CUSTOMER);
        List<Role> roleList = Arrays.asList(roleUser);
        Date today = new Date();
        UserData userData = new UserData(today,today,false);
        userDataRepository.save(userData);
        User u = new User(username,mail,passwordEncoder.encode(password),false,roleList,userData);
        return userRepository.save(u);
    }
}
