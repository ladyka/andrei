package tk.ladyka.andrei.security;

import tk.ladyka.andrei.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = Application.class)
public class SecurityTests extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private PasswordEncoder passwordEncoder;
	// @Autowired
	// private AuthorityRepository authRepo;
	// @Autowired
	// private UserRepository userRepo;
	// @Autowired
	// private UserAuthorityRepository userAuthRepo;
	// @Autowired
	// private GroupRepository groupRepo;
	// @Autowired
	// private GroupAuthorityRepository groupAuthRepo;
	// @Autowired
	// private GroupUserRepository groupUserRepo;

	@Autowired
	private JPAUserDetailsService userDetailsService;

	// @Test(groups = {
	// "security"
	// })
	// public void testPasswordEncoder() {
	// log.info("Generated password for b32ml2nd: {}", passwordEncoder.encode("b32ml2nd"));
	// Assert.assertTrue(true);
	// }

	// @Test(groups = {
	// "security"
	// })
	// @Rollback(value = false)
	// public void createSecurityBase() {
	// // Authority adminAuth = new Authority();
	// // adminAuth.setCode("role_admin");
	// // adminAuth.setName("Administrator Role");
	// // adminAuth.setDescription("Administrator Role");
	// // adminAuth = authRepo.saveAndFlush(adminAuth);
	//
	// User user = new User();
	// user.setUsername("dev");
	// user.setPassword(passwordEncoder.encode("dev"));
	// user.setEnabled(true);
	// user.setLocked(false);
	// user = userRepo.saveAndFlush(user);
	//
	// // Group group = new Group();
	// // group.setCode("admin");
	// // group.setName("Administrators");
	// // group.setDescription("Administrator Group");
	// // group = groupRepo.saveAndFlush(group);
	//
	// Group group = groupRepo.findByCodeIgnoreCase("admin");
	//
	// GroupUser grpUser = new GroupUser();
	// grpUser.setUser(user);
	// grpUser.setGroup(group);
	// groupUserRepo.saveAndFlush(grpUser);
	//
	// // GroupAuthority ga = new GroupAuthority();
	// // ga.setGroup(group);
	// // ga.setAuthority(adminAuth);
	// // groupAuthRepo.saveAndFlush(ga);
	// }

	@Test(groups = {
			"security"
	})
	public void testUserDetailsService() {
		UserDetails userDetails = userDetailsService.loadUserByUsername("adam.crane@beamland.com");
		Assert.assertNotNull(userDetails);
		Assert.assertTrue(passwordEncoder.matches("My2Girls!@", userDetails.getPassword()));
		Assert.assertTrue(userDetails.isEnabled());
		Assert.assertTrue(userDetails.isAccountNonExpired());
		Assert.assertTrue(userDetails.isAccountNonLocked());
		Assert.assertTrue(userDetails.isCredentialsNonExpired());
		Assert.assertTrue(!userDetails.getAuthorities().isEmpty());
		boolean foundAdminAuth = false;
		for (GrantedAuthority ga : userDetails.getAuthorities()) {
			if ("ROLE_ADMIN".equalsIgnoreCase(ga.getAuthority())) {
				foundAdminAuth = true;
			}
		}
		Assert.assertTrue(foundAdminAuth);
	}
}
