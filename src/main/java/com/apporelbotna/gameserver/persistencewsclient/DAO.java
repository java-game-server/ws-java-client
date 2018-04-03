package com.apporelbotna.gameserver.persistencewsclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.apporelbotna.gameserver.stubs.Game;
import com.apporelbotna.gameserver.stubs.Token;
import com.apporelbotna.gameserver.stubs.User;
import com.apporelbotna.gameserver.stubs.UserWrapper;

//TODO los memtodos devuelven un HttpStatus que es el code de segun si ha ido bien, mal, etc.
//esto se podria hacer alguna clase que controlase los codigos que se mostraran en la applicacion
//personalizados y en diferentes idiomas

public class DAO
{
	public static final String SERVER_URL = "http://localhost:8082/";

	public DAO()
	{

	}

	public User getUserInformation(User user)
	{
		// TODO implement
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<User> userResponse = restTemplate.getForEntity(SERVER_URL + "" + user.getId(), User.class);

		return userResponse.getBody();
	}

	//METHOD CheckUserToken

	public String createUser(UserWrapper userWrapper)
	{
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.postForEntity(SERVER_URL + "user",
				userWrapper, String.class);
		return response.getBody();
	}

	public Token login(UserWrapper userWrapper)
	{
		RestTemplate restTemplate = new RestTemplate();

		Token token = restTemplate.postForObject(SERVER_URL + "/login", userWrapper, Token.class);

		return token;
	}

	// create metodo que verifique si el user tiene un token

	// saca todos los juegos de un usuario
	public List<Game> findAllGamesByUser(User user)
	{
		RestTemplate restTemplate = new RestTemplate();

		String userEmail = user.getId();
		ResponseEntity<List> response = restTemplate
				.getForEntity(SERVER_URL + "/user/game/" + userEmail, List.class);
		ArrayList<String> a = (ArrayList<String>) response.getBody();
		for (String iterable_element : a)
		{
			System.out.println(iterable_element);
		}

		// TODO MAKE IT WORKS.
		return null;

	}

	public static void main(String[] args)
	{
		DAO dao = new DAO();

		// Creating User
		/*
		 * User user = new User("janJanitoJwzdf3sanbo@Tronchaco.com", "Jan");
		 * UserWrapper userWrapper = new UserWrapper(user, "1234"); DAO dao = new DAO();
		 * System.out.println(dao.createUser(userWrapper));
		 */

		// Testing log in
		/*
		 * String email = "jan@jan.com"; String password = "1234"; UserWrapper
		 * userWrapper = new UserWrapper(new User(email, "das"), "1234");
		 *
		 * Token token = dao.login(userWrapper);
		 */

		// Find all games by username
		/*
		List<Game> games = dao.findAllGamesByUser(new User("jan@jan.com", "pp"));
		for (Game game : games)
		{
			System.out.println(game);
		}*/

		//TEST USER INFORMATION
		User userInformatiton = dao.getUserInformation(new User("jan@jan.com","jan"));
		System.out.println(userInformatiton);
	}

}
