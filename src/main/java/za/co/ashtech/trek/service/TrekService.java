package za.co.ashtech.trek.service;

import java.util.List;

import za.co.ashtech.trek.model.Trail;
import za.co.ashtech.trek.util.TrekException;

public interface TrekService {

	public Trail getRandomHikeTrail() throws TrekException;
	public List<Trail> searchTrail() throws TrekException;
}
