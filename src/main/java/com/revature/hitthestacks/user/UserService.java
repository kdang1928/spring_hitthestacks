package com.revature.hitthestacks.user;

import com.revature.hitthestacks.course.dto.UpdateUserRequest;
import com.revature.hitthestacks.user.dto.NewUserRequest;
import com.revature.hitthestacks.user.dto.UserResponse;
import com.revature.hitthestacks.util.exceptions.InvalidUserInputException;
import com.revature.hitthestacks.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse registerUser(NewUserRequest newUserRequest){
        User newUser = new User(newUserRequest);

        // Checks
        isEmailAvailable(newUser.getEmail());

        return new UserResponse(userRepository.save(newUser));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> readAll(){
        return ((Collection<User>) userRepository.findAll())
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponse findById(String id){
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return new UserResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        return new UserResponse(user);
    }

    @Transactional
    public void update(UpdateUserRequest updateUserRequest, User currentUser) {
        User foundUser = userRepository.findById(currentUser.getId()).orElseThrow(ResourceNotFoundException::new);
        Predicate<String> notNullOrEmpty = (str) -> str!=null && !str.trim().equals("");

        if(notNullOrEmpty.test(updateUserRequest.getEmail())) foundUser.setEmail(updateUserRequest.getEmail());
        if(notNullOrEmpty.test(updateUserRequest.getFname())) foundUser.setFname(updateUserRequest.getFname());
        if(notNullOrEmpty.test(updateUserRequest.getLname())) foundUser.setLname(updateUserRequest.getLname());
        if(notNullOrEmpty.test(updateUserRequest.getDepartment())) foundUser.setDepartment(updateUserRequest.getDepartment());
        if(notNullOrEmpty.test(updateUserRequest.getPassword())) foundUser.setPassword(updateUserRequest.getPassword());

    }

    @Transactional
    public void deactivate(User user){
        User foundUser = userRepository.findById(user.getId()).orElseThrow(ResourceNotFoundException::new);
        userRepository.deactivate(foundUser.getId());
    }

    /*   Verification   */
    @Transactional(readOnly = true)
    public void isEmailAvailable(String email){
        if(userRepository.findByEmail(email).isPresent()){
            throw new InvalidUserInputException("Email: " + email + " is already registered. Please try again.");
        }
    }
}
