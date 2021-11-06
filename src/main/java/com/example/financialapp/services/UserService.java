package com.example.financialapp.services;

import com.example.financialapp.domain.Account;
import com.example.financialapp.domain.User;
import com.example.financialapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        setJpaRepository(userRepository);
    }

    public void setJpaRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserById(Long id){
        User u = userRepository.findById(id).orElseThrow(NullPointerException::new);
        return u;
    }

    @Transactional
    public User update(User usr){
        Assert.notNull(usr, "The domainObject must not be null!");
        User entity = modelMapper.map(usr, User.class);

        User result = userRepository.save(entity);
//        log.debug("save/update entity {}", result);
//        return modelMapper.map(result, getDtoClass());
        return modelMapper.map(result,User.class);
    }

    @Transactional
    public User addAccount(Long id, Account acc){
        Assert.notNull(id, "The domainObject must not be null!");

        User entity = (userRepository.findById(id)).orElseThrow(NullPointerException::new);
        entity = modelMapper.map(entity, User.class);
        entity.getAccounts().add(acc);
        User result = userRepository.save(entity);
//        log.debug("save/update entity {}", result);
//        return modelMapper.map(result, getDtoClass());
        return modelMapper.map(result,User.class);
    }





}


