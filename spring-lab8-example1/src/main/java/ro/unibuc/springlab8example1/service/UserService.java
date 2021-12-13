package ro.unibuc.springlab8example1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.springlab8example1.domain.User;
import ro.unibuc.springlab8example1.domain.UserType;
import ro.unibuc.springlab8example1.dto.UserDto;
import ro.unibuc.springlab8example1.mapper.UserMapper;
import ro.unibuc.springlab8example1.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto create(UserDto userDto, UserType type) {
        User user = userMapper.mapToEntity(userDto);
        user.setUserType(type);
        User savedUser = userRepository.save(user);

        return userMapper.mapToDto(savedUser);
    }

    public UserDto getOne(String username) {
        return userMapper.mapToDto(userRepository.get(username));
    }

    public UserDto updateOne(String username, UserDto userDto) {
        User userToUpdate = userRepository.get(username);

        if(userToUpdate == null) {
            return create(userDto, UserType.STUDENT);
        }

        User newUser = userMapper.mapToEntity(userDto);
        userToUpdate.setUsername(newUser.getUsername());
        userToUpdate.setFullName(newUser.getFullName());
        userToUpdate.setUserDetails(newUser.getUserDetails());
        userToUpdate.setUserType(newUser.getUserType());
        userToUpdate.setAccountCreated(newUser.getAccountCreated());

        User savedUser = userRepository.save(userToUpdate);
        return userMapper.mapToDto(savedUser);
    }

    public Long deleteOne(Long id) {
        return userRepository.deleteUser(id);
    }

    public List<UserDto> getUsersByType(UserType type) {
        List<UserDto> users = new ArrayList<UserDto>();
        userRepository.getUsersByType(type).forEach(user -> users.add(userMapper.mapToDto(user)));
        return users;
    }
}
