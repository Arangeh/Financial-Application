package com.example.financialapp.services;

import com.example.financialapp.domain.dto.AccountDto;
import com.example.financialapp.domain.dto.UserDto;
import com.example.financialapp.domain.entity.Account;
import com.example.financialapp.domain.entity.User;
import com.example.financialapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public UserDto create(UserDto usr){
        Assert.notNull(usr, "The domainObject must not be null!");
        User entity = modelMapper.map(usr, User.class);

        User result = userRepository.save(entity);
        return modelMapper.map(result,UserDto.class);
    }

    @Transactional
    public UserDto addAccount(Long id, AccountDto acc){
        Assert.notNull(id, "The domainObject must not be null!");

        User entity = (userRepository.findById(id)).orElseThrow(NullPointerException::new);
        entity.getAccounts().add(modelMapper.map(acc,Account.class));
        User result = userRepository.save(entity);
        return modelMapper.map(result,UserDto.class);
    }

}


