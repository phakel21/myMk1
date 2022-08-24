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

    //CRUD methods work with repository

    private MyUser save(MyUser MyUser) {
        return myUserRepository.save(MyUser);
    }

    private MyUser findOne(String name) {
        return myUserRepository.findByLogin(name);
    }

    private List<MyUser> findAll() {
        return myUserRepository.findAll();
    }

    void delete(String name){ myUserRepository.deleteByLogin(name);}

    //mappers

    private MyUser map(MyUserDTO myUserDTO) {
        MyUser MyUser = new MyUser();
        MyUser.setLogin(myUserDTO.getLogin());
        return MyUser;
    }

    private MyUserDTO map(MyUser MyUser) {
        if(MyUser == null) return null;
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

    //my methods

    @Override
    public MyUser registration(MyUserDTO myUserDTO) {
        if (!registrationValidation(myUserDTO));
        MyUser MyUser = new MyUser();
        MyUser.setPassword(passwordEncoder.encode(myUserDTO.getPassword()));
        MyUser.setLogin(myUserDTO.getLogin());
        if (myUserDTO.getLogin().equals("admin")) MyUser.setRole(Role.ADMIN);
        else MyUser.setRole(Role.USER);
        return save(MyUser);
    }

    @Override
    public MyUser getMyUserByName(String name) {
        Optional<MyUser> optionalMyUser = myUserRepository.findMyUserByLogin(name);

        if (optionalMyUser.isPresent()) {
            return optionalMyUser.get();
        }

        throw new MyUserNotFoundException("User: " + name + " not found");
    }

    @Override
    public List<MyUserDTO> getAll() {
        return map(findAll());
    }

    @Override
    public void deleteByName(String name) {
        Optional<MyUser> optionalMyUser = myUserRepository.findMyUserByLogin(name);
        if (!optionalMyUser.isPresent()) {
            throw new MyUserNotFoundException("User with name: " + name + " not found");
        }
        delete(name);
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
    public MyUserDTO getMyUserDTOByName(String name) {
        return map(getMyUserByName(name));
    }

    @Override
    public MyUserDTO getMyUserDTOforUpdate(String updateUser) {
        Optional<MyUser> myUserOptional = myUserRepository.findMyUserByLogin(updateUser);
        return myUserOptional.map(this::map).orElse(null);
    }
}
