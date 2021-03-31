package za.co.ashtech.trek.service;

import java.util.List;
import za.co.ashtech.trek.model.Trail;
import za.co.ashtech.trek.model.User;
import za.co.ashtech.trek.util.TrekException;

public interface TrekService {

	public Trail getRandomHikeTrail() throws TrekException;
	public List<Trail> searchTrail(String location) throws TrekException;
	public void addTrail(Trail trail) throws TrekException;
	public void editTrail(String id, String name, String value) throws TrekException;
	public void deleteTrail(String trailId) throws TrekException;
	public void createUser(User user) throws TrekException;
}
