package com.agordillo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.agordillo.GestorMultiple.GestorMultipleApplication;
import com.agordillo.GestorMultiple.models.User;
import com.agordillo.GestorMultiple.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GestorMultipleApplication.class)
class AllTestsTest{

	@Autowired
    private ApplicationContext context;
	
	private UserService service;
	
	@Test
	void test(){
		assertDoesNotThrow(()->addPhotoToUser());
	}
	
	public void testCreate() throws Exception {
		service = context.getBean(UserService.class);
		System.out.println("entra");
		User u = new User();
		u.setId("1");
		u.setEmail("adriangordillo91@gmail.com");
		u.setName("adrian");
		u.setPassword("adri1999");
		u.setUsername("agordillo");
		System.out.println("usuario creado");
		String save = service.save(u);
		System.out.println(save);
	}
	
	public void testReadall() throws Exception {
		service = context.getBean(UserService.class);
		System.out.println("entra");
		List<User> all = service.getAll();
		all.forEach(user->{
			System.out.println(user.getId());
		});
	}
	
	public void deleteOne() throws Exception {
		service = context.getBean(UserService.class);
		System.out.println("entra");
		service.delete("1");
	}
	
	public void getKeys() throws Exception{
		service = context.getBean(UserService.class);
		System.out.println("entra");
		List<String> keys = service.getKeys();
		keys.forEach(k->{
			System.out.println(k);
		});
	}
	
	public void addPhoneToUser() throws Exception{
		service = context.getBean(UserService.class);
		 User u = new User();
		 u.setEmail("adriangordillo91@gmail.com");
		 u.setPhone("+34644719928");
		 service.addPhoneToUser(u);
	}
	
	public void addPhotoToUser() throws Exception{
		service = context.getBean(UserService.class);
		User u = new User();
		u.setEmail("adriangordillo91@gmail.com");
		u.setPhoto("https://img.freepik.com/vector-gratis/perfil-hombre-dibujos-animados_18591-58482.jpg?w=826");
		service.addPhotoToUser(u);
	}

}
