package tk.ladyka.andrei.jpa.repository.sec;

import java.util.List;

import tk.ladyka.andrei.jpa.domain.sec.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import tk.ladyka.andrei.Application;

@SpringApplicationConfiguration(classes = Application.class)
public class UserRepositoryTests extends AbstractTestNGSpringContextTests {

  @Autowired
  private UserRepository userRepo;

  @Test(groups = {
      "jpa", "jpa-sec"
  })
  public void findAll() {
    List<User> users = userRepo.findAll();
    Assert.assertTrue(users != null && !users.isEmpty(), "No results returned from the sec.User repository findAll method");
  }

}
