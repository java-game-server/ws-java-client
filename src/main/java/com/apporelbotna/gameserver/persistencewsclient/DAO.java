package com.apporelbotna.gameserver.persistencewsclient;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.apporelbotna.gameserver.stubs.Game;
import com.apporelbotna.gameserver.stubs.Token;
import com.apporelbotna.gameserver.stubs.User;
import com.apporelbotna.gameserver.stubs.UserWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//TODO los memtodos devuelven un HttpStatus que es el code de segun si ha ido bien, mal, etc.
//esto se podria hacer alguna clase que controlase los codigos que se mostraran en la applicacion
//personalizados y en diferentes idiomas

public class DAO
{
	public static final String SERVER_URL = "http://localhost:8082/";

	public DAO()
	{

	}

	public User getUserInformation(User user, Token token)
	{
		// TODO implement
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<User> userResponse = restTemplate
				.getForEntity(SERVER_URL + "" + user.getId(), User.class);

		return userResponse.getBody();
	}

	// METHOD CheckUserToken
	// create metodo que verifique si el user tiene un token
	public boolean isUserLoggeable(UserWrapper userWrapper)
	{

		return true;
	}

	public UserWrapper login(String email, String password)
	{

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(SERVER_URL + "/login/" + email + "/" + password,
				UserWrapper.class);
	}

	// saca todos los juegos de un usuario
	public List<Game> findAllGamesByUser(User user)
	{
		String userEmail = user.getId();

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Game>> responseWS = restTemplate.exchange(
				SERVER_URL + "/user/game/" + userEmail, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Game>>()
				{
				});

		return responseWS.getBody();
	}

	public String createUser(User user, String password)
	{
		RestTemplate restTemplate = new RestTemplate();

		ObjectMapper mapper = new ObjectMapper();
		try
		{
//			System.out.println(mapper.writeValueAsString(user));
//			System.out.println(mapper.writeValueAsString(password));

			// Create the node factory that gives us nodes.
			Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String,Object>>(){});



		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// objMapper.

//		ResponseEntity<String> response = restTemplate.postForEntity(SERVER_URL + "user",
//				userWrapper, null);

		return null;
	}

	public static void main(String[] args)
	{
		DAO dao = new DAO();

		// Creating User
		 User user = new User("janJanitoJwzdf3sssanbo@Tronchaco.com", "Jan");
		 String password = "1234";

		 System.out.println(dao.createUser(user, password));

		// Testing log in
		//
		// String email = "jan@jan.com";
		// String password = "1234";
		// Token token = dao.login(email, password);
		// System.out.println(token);

		// Find all games by username
		// List<Game> games = dao.findAllGamesByUser(new User("jan@jan.com", "pp"));
		// for (Game game : games)
		// {
		// System.out.println(game);
		// }

		// TEST USER INFORMATION
		/*
		 * User userInformatiton = dao.getUserInformation(new
		 * User("jan@jan.com","jan")); System.out.println(userInformatiton);
		 */
	}

}
