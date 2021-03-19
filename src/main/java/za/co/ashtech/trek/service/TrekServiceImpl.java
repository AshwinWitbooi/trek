package za.co.ashtech.trek.service;

import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import za.co.ashtech.trek.db.entity.TrailEntity;
import za.co.ashtech.trek.db.repository.TrailDBRepository;
import za.co.ashtech.trek.model.Trail;
import za.co.ashtech.trek.util.CONSTANTS;
import za.co.ashtech.trek.util.TrekException;
import za.co.ashtech.trek.util.TrekUtil;

@Service
public class TrekServiceImpl implements TrekService {
	
	@Autowired
	private TrailDBRepository dbRepository;

	@Override
	public Trail getRandomHikeTrail() throws TrekException{
		
		try {
			List<TrailEntity> trails = IteratorUtils.toList(dbRepository.findAll().iterator());		
			
			return TrekUtil.trailEntityTyModel(trails.get(RandomUtils.nextInt(0, trails.size()-1)));
			
		} catch (Exception e) {
			throw new TrekException(CONSTANTS.ERC001, CONSTANTS.ERC001_DESC,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Trail> searchTrail() throws TrekException {
		// TODO Auto-generated method stub
		return null;
	}

}
