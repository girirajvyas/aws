package io.github.girirajvyas.aws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.girirajvyas.aws.entity.User;
import io.github.girirajvyas.aws.repository.UserRepository;

@Service
public class AwsDynamoDbService {

  @Autowired
  public UserRepository userRepository;
  
  public void save() {
    userRepository.save(new User("Giri", "raj"));
  }
  
}
