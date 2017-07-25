package tk.ladyka.andrei.jpa.repository.cfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import tk.ladyka.andrei.Application;
import tk.ladyka.andrei.jpa.domain.cfg.BodyType;

@SpringApplicationConfiguration(classes = Application.class)
public class BodyTypeRepositoryTests extends AbstractTestNGSpringContextTests {

  @Autowired
  private BodyTypeRepository repo;

  @Test(groups = {
      "jpa", "jpa-cfg"
  })
  public void findAll() {
    List<BodyType> bodyTypes = repo.findAll();
    Assert.assertTrue(bodyTypes != null && !bodyTypes.isEmpty(), "No results returned from the cfg.BodyType repository findAll method");
  }

  @Test(groups = {
      "jpa", "jpa-cfg"
  })
  public void findF55() {
    BodyType bodyType = repo.findOne(1000028L);
    Assert.assertEquals(bodyType.getBodyType(), "Hardtop 4 DR");
  }
}
