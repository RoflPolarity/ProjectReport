package report.pflb.ProjectReport.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.User;
import report.pflb.ProjectReport.Repository.UserRepository;

import java.util.List;

@Service
public class userService{
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public User saveUser(User u) {
        if (existsByLogin(u.getLogin())){
            return userRepository.findByLogin(u.getLogin());
        }else {
            return userRepository.save(u);
        }
    }
    public boolean existsByLogin(String login){return userRepository.existsByLogin(login);}

    public List<User> getAllFromDatabase(){return userRepository.findAll();}

    public User findById(int id){return userRepository.findById(id);}
    public User findByLogin(String login){return userRepository.findByLogin(login);}
}
