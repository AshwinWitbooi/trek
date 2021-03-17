package za.co.ashtech.trek.service;

import za.co.ashtech.trek.model.Trail;
import za.co.ashtech.trek.util.TrekException;

public interface TrekService {

	public Trail getRandomHikeTrail() throws TrekException;
}
