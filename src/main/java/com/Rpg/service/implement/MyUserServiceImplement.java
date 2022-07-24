package com.Rpg.service.implement;

import com.Rpg.config.exception.myUser.MyUserNotFoundException;
import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.Role;
import com.Rpg.entity.MyUser;
import com.Rpg.repository.MyUserRepository;
import com.Rpg.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
public class MyUserServiceImplement implements MyUserService, UserDetailsService {

    private MyUserRepository myUserRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserServiceImplement(MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private MyUser map(MyUserDTO myUserDTO) {
        MyUser MyUser = new MyUser();
        MyUser.setLogin(myUserDTO.getLogin());
        return MyUser;
    }

    private MyUserDTO map(MyUser MyUser) {
        MyUserDTO myUserDTO = new MyUserDTO();
        myUserDTO.setLogin(MyUser.getLogin());
        return myUserDTO;
    }

    private List<MyUserDTO> map(List<MyUser> MyUsers) {
        List<MyUserDTO> myUserDTOS = new ArrayList<>();
        for (MyUser MyUser : MyUsers) {
            myUserDTOS.add(map(MyUser));
        }
        return myUserDTOS;
    }

    @Override
    public MyUser registration(MyUserDTO myUserDTO) {
        if (!registrationValidation(myUserDTO)) ;
        MyUser MyUser = new MyUser();
        MyUser.setPassword(passwordEncoder.encode(myUserDTO.getPassword()));
        MyUser.setLogin(myUserDTO.getLogin());
        if (myUserDTO.getLogin().equals("admin")) MyUser.setRole(Role.ADMIN);
        else MyUser.setRole(Role.USER);
        return save(MyUser);
    }

    private MyUser save(MyUser MyUser) {
        return myUserRepository.save(MyUser);
    }

    @Override
    public MyUserDTO getByName(String name) {
        return map(findOne(name));
    }

    private MyUser findOne(String name) {
        return myUserRepository.findByLogin(name);
    }

    @Override
    public List<MyUserDTO> getAll() {
        return map(findAll());
    }

    private List<MyUser> findAll() {
        return myUserRepository.findAll();
    }

    @Override
    public void deleteByName(String name) {
        myUserRepository.deleteByLogin(name);
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUser byName = myUserRepository.findByLogin(s);
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + byName.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(
                byName.getLogin(),
                byName.getPassword(),
                simpleGrantedAuthorities
        );
    }

    private Boolean registrationValidation(MyUserDTO myUserDTO) {
        if (!myUserDTO.getPassword().equals(myUserDTO.getPasswordRepeat())) return false;
        if (myUserRepository.countByLogin(myUserDTO.getLogin()) > 0) return false;
        return true;
    }

    @Override
    public MyUser get(String name) {
        return findOne(name);
    }

    @Override
    public MyUserDTO hetOne(String name) {
        Optional<MyUser> optionalMyUser = myUserRepository.findMyUserByLogin(name);
        if (optionalMyUser.isPresent()){
            return map(optionalMyUser.get());
        }
        throw new MyUserNotFoundException("User: "+ name +" not found");
    }

    @Override
    public MyUser getOne(String name) {
        Optional<MyUser> optionalMyUser = myUserRepository.findMyUserByLogin(name);
        if (optionalMyUser.isPresent()){
            return optionalMyUser.get();
        }
        throw new MyUserNotFoundException("User: "+ name +" not found");
    }
}
