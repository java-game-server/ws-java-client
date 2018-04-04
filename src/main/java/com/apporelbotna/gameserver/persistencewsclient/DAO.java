package com.apporelbotna.gameserver.persistencewsclient;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.apporelbotna.gameserver.stubs.Game;
import com.apporelbotna.gameserver.stubs.RankingPointsTO;
import com.apporelbotna.gameserver.stubs.RegisterUser;
import com.apporelbotna.gameserver.stubs.Token;
import com.apporelbotna.gameserver.stubs.User;
import com.apporelbotna.gameserver.stubs.UserWrapper;

//TODO los metodos devuelven un HttpStatus que es el code de segun si ha ido bien, mal, etc.
//esto se podria hacer alguna clase que controlase los codigos que se mostraran en la applicacion
//personalizados y en diferentes idiomas

public class DAO
{
	public static final String SERVER_URL = "http://localhost:8082/";
	RestTemplate restTemplate = new RestTemplate();

	public DAO()
	{

	}

	 public boolean isUserLoggeable(String email, String tokenString)
	 {
	 UserWrapper wrapper = new UserWrapper(new User(email), new
	 Token(tokenString));

	 HttpStatus response = restTemplate
	 .getForObject(SERVER_URL + "/auth/" + email + "/" + tokenString,
	 HttpStatus.class);

	 return (response.equals(HttpStatus.OK));
	 }

	public User validateUser(String email, String tokenString)
	{
		//TODO implement
		return null;
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

	public boolean createUser(User user, String password)
	{
		RegisterUser userToRegister = new RegisterUser(user, password);

		ResponseEntity<?> response = restTemplate.postForEntity(SERVER_URL + "/user/",
				userToRegister, null);

		return (response.getStatusCode().equals(HttpStatus.CREATED));
	}

	public User getUserInformation(String email)
	{
		return restTemplate.getForObject(SERVER_URL + "/user/" + email, User.class);
	}

	/**
	 *
	 * @param email
	 * @param gameID
	 * @return time in miliseconds
	 */
	public float gameTimePlayedByGame(String email, int gameID)
	{
		return restTemplate.getForObject(
				SERVER_URL + "/user/" + email + "/game/" + gameID + "/time/", Float.class);
	}

	public List<RankingPointsTO> getRankingPointsByGameAndUser(int gameId)
	{

		ResponseEntity<List<RankingPointsTO>> responseWS = restTemplate.exchange(
				SERVER_URL + "/ranking/" + gameId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<RankingPointsTO>>()
				{
				});

		return responseWS.getBody();
	}



}
