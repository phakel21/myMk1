package com.Rpg.service.implement;

import com.Rpg.config.exception.myUser.MyUserLoginBusyException;
import com.Rpg.config.exception.myUser.MyUserNotFoundException;
import com.Rpg.config.exception.myUser.PasswordDontMatchException;
import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.MyUser;
import com.Rpg.entity.Role;
import com.Rpg.repository.MyUserRepository;
import com.Rpg.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Transactional
public class MyUserServiceImplement implements MyUserService{

    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;


    //mappers

//    private MyUser map(MyUserDTO myUserDTO) {
//        MyUser MyUser = new MyUser();
//        MyUser.setLogin(myUserDTO.getLogin());
//        return MyUser;
//    }
//
    private MyUserDTO map(MyUser MyUser) {
        if (MyUser == null) return null;
        MyUserDTO myUserDTO = new MyUserDTO();
        myUserDTO.setLogin(MyUser.getLogin());
        return myUserDTO;
    }
//
//    private List<MyUserDTO> map(List<MyUser> MyUsers) {
//        List<MyUserDTO> myUserDTOS = new ArrayList<>();
//        for (MyUser MyUser : MyUsers) {
//            myUserDTOS.add(map(MyUser));
//        }
//        return myUserDTOS;
//    }

    //my methods

    //@Override
//    public MyUser registration(MyUserDTO myUserDTO) {
//        if (!registrationValidation(myUserDTO)) ;
//        MyUser MyUser = new MyUser();
//        MyUser.setPassword(passwordEncoder.encode(myUserDTO.getPassword()));
//        MyUser.setLogin(myUserDTO.getLogin());
//        if (myUserDTO.getLogin().equals("admin")) MyUser.setRole(Role.ADMIN);
//        else MyUser.setRole(Role.USER);
//        return save(MyUser);
//    }

    private MyUser userExist(String username){

        Optional<MyUser> optionalMyUser = myUserRepository.findMyUserByLogin(username);

        if (!optionalMyUser.isPresent()) {
            throw new MyUserNotFoundException("User with username: " + username + " not found");
        }

        return optionalMyUser.get();
    }

    @Override
    public MyUser create(MyUserDTO myUserDTO) {

        Optional<MyUser> myUserByLogin = myUserRepository.findMyUserByLogin(myUserDTO.getLogin());

        if (myUserByLogin.isPresent()) {
            throw new MyUserLoginBusyException("Login" + myUserDTO.getLogin() + "busy");
        }

        if (!myUserDTO.getPassword().equals(myUserDTO.getPasswordRepeat())) {
            throw new PasswordDontMatchException("Password dont match");
        }

        MyUser myUser = new MyUser();
        myUser.setLogin(myUserDTO.getLogin());
        String encode = passwordEncoder.encode(myUserDTO.getPassword());
        myUser.setPassword(encode);
        myUser.setRole(Role.ROLE_USER);
        return myUserRepository.save(myUser);

    }

    @Override
    public MyUser get(String username) {

        return userExist(username);
    }

    @Override
    public List<MyUser> getAll() {
        return myUserRepository.findAll();
    }

    @Override
    public void updateLogin(String username, String newUsername) {
        MyUser myUser = userExist(username);
        myUser.setLogin(newUsername);
        myUserRepository.save(myUser);
    }

    @Override
    public void updatePassword(String username, String newPassword){
        MyUser myUser = userExist(username);
        String encodeNewPassword = passwordEncoder.encode(newPassword);
        myUser.setPassword(encodeNewPassword);
        myUserRepository.save(myUser);

    }
    @Override
    public void delete(String username) {

        MyUser myUser = userExist(username);

        myUserRepository.delete(myUser);
    }



//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        MyUser byName = myUserRepository.findByLogin(s);
//        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + byName.getRole().toString()));
//        return new org.springframework.security.core.userdetails.User(
//                byName.getLogin(),
//                byName.getPassword(),
//                simpleGrantedAuthorities
//        );
//    }
//
//    private Boolean registrationValidation(MyUserDTO myUserDTO) {
//        if (!myUserDTO.getPassword().equals(myUserDTO.getPasswordRepeat())) return false;
//        if (myUserRepository.countByLogin(myUserDTO.getLogin()) > 0) return false;
//        return true;
//    }
//

//
//    @Override
//    public MyUserDTO getMyUserDTOforUpdate(String updateUser) {
//        Optional<MyUser> myUserOptional = myUserRepository.findMyUserByLogin(updateUser);
//        return myUserOptional.map(this::map).orElse(null);
//    }





//    @Override
//    public MyUser getByLoginAndPassword(String login, String password){
//        MyUser myUser = getMyUserByLogin(login);
//        if(myUser == null){
//            throw new NotFoundException("User with login "+ login + " not found");
//        }
//        boolean matches = passwordEncoder.matches(password, myUser.getPassword());
//
//        if(matches){
//            return myUser;
//        }
//        throw new WrongPasswordException("Wrong password");
//    }
//
//    @Override
//    public MyUserDTO getCurrentMyUserDTO() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        MyUser myUser = (MyUser) principal;
//        String currentMyUserLogin = myUser.getLogin();
//        return map(get(currentMyUserLogin));
//    }

    @Override
    public MyUser getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        MyUser myUser = (MyUser) principal;
        String currentMyUserLogin = myUser.getLogin();
        return get(currentMyUserLogin);
    }
}