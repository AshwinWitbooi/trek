package za.co.ashtech.trek.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import za.co.ashtech.trek.db.entity.TrailEntity;
import za.co.ashtech.trek.db.entity.UserEntity;
import za.co.ashtech.trek.db.entity.UserRoleEntity;
import za.co.ashtech.trek.db.repository.TrailDBRepository;
import za.co.ashtech.trek.db.repository.UserDBRepository;
import za.co.ashtech.trek.model.Trail;
import za.co.ashtech.trek.model.User;
import za.co.ashtech.trek.util.CONSTANTS;
import za.co.ashtech.trek.util.TrekException;
import za.co.ashtech.trek.util.TrekUtil;

@Service
public class TrekServiceImpl implements TrekService {
	
	@Autowired
	private TrailDBRepository dbRepository;
	@Autowired
	private UserDBRepository userDBRepository;

	@Override
	public Trail getRandomHikeTrail() throws TrekException{
		
		try {
			List<TrailEntity> trails = IteratorUtils.toList(dbRepository.findAll().iterator());		
			
			return TrekUtil.trailEntityToModel(trails.get(RandomUtils.nextInt(0, trails.size()-1)));
			
		} catch (Exception e) {
			throw new TrekException(CONSTANTS.ERC001, CONSTANTS.ERC001_DESC,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Trail> searchTrail(String location) throws TrekException {
		List<Trail> trails = null;
		try {
			
			List<TrailEntity> te = dbRepository.findByLocation(location);
			
			if(te != null && !te.isEmpty()) {
				trails = new ArrayList<>();
				
				for(TrailEntity i:te) {
					trails.add(TrekUtil.trailEntityToModel(i));
				}
				
				return trails;
				
			}else {
				throw new TrekException(CONSTANTS.ERC003, CONSTANTS.ERC003_DESC, HttpStatus.BAD_REQUEST);
			}
			
		} catch (TrekException e) {
			throw e;
		}catch (Exception e) {
			throw new TrekException(CONSTANTS.ERC003, "Error retrieving hike trails for location",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void addTrail(Trail trail) throws TrekException {
		
		try {
			dbRepository.save(TrekUtil.trailModelToEntity(trail));
		} catch (Exception e) {
			throw new TrekException(CONSTANTS.ERC004, "Error adding hike trail",HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}

	@Override
	public void editTrail(String id, String name, String value) throws TrekException {
		
		try {
			Optional<TrailEntity> trailEntity = dbRepository.findById(new Long(id));
			
			if(trailEntity.isPresent()) {
				switch(name.toUpperCase()) {
				
				  case "NAME":
					  trailEntity.get().setName(value);
					  this.updateTrail(trailEntity.get());
					  break;
				  case "LOCATION":	  
					  trailEntity.get().setLocation(value);
					  this.updateTrail(trailEntity.get());
					  break;
				  case "LENGTH":
					  trailEntity.get().setLength(value);
					  this.updateTrail(trailEntity.get());
					  break;
				  case "LEVEL":
					  trailEntity.get().setLevel(value);
					  this.updateTrail(trailEntity.get());
					  break;
				  case "DESCRIPTION":	
					  trailEntity.get().setDecription(value);
					  this.updateTrail(trailEntity.get());
					  break;
				  case "STATUS":
					  trailEntity.get().setStatus(value);
					  this.updateTrail(trailEntity.get());
					  break;
				  default:
					  throw new TrekException(CONSTANTS.ERC005, "Invalid id action",HttpStatus.BAD_REQUEST);
				}
				
			}else {
				throw new TrekException(CONSTANTS.ERC005, "No trail found",HttpStatus.BAD_REQUEST);	
			}
			
		} catch (NullPointerException e) {
			throw new TrekException(CONSTANTS.ERC005, CONSTANTS.UPDATE_MSG,HttpStatus.BAD_REQUEST);	
		}catch (NumberFormatException e) {
			throw new TrekException(CONSTANTS.ERC005, CONSTANTS.INVALID_ID_MSG,HttpStatus.BAD_REQUEST);	
		}catch (Exception e) {
			throw new TrekException(CONSTANTS.ERC005, CONSTANTS.UPDATE_MSG,HttpStatus.BAD_REQUEST);	
		}

	}
	

	@Override
	public void deleteTrail(String trailId) throws TrekException {
		
		try {
			dbRepository.delete(dbRepository.findById(new Long(trailId)).get());
		} catch (NumberFormatException e) {
			throw new TrekException(CONSTANTS.ERC006, CONSTANTS.INVALID_ID_MSG,HttpStatus.BAD_REQUEST);
		} catch (NullPointerException e) {
			throw new TrekException(CONSTANTS.ERC006, CONSTANTS.DELETE_MSG,HttpStatus.BAD_REQUEST);	
		}catch (Exception e) {
			throw new TrekException(CONSTANTS.ERC006, CONSTANTS.DELETE_MSG,HttpStatus.BAD_REQUEST);	
		}
		
	}
	
	/* Method to update trail db record */
	private void updateTrail(TrailEntity trailEntity) {
		dbRepository.save(trailEntity);
	}

	@Override
	public void createUser(User user) throws TrekException {
		UserEntity userEntity = new UserEntity();
		
		if(user.getPassword().equals(user.getConfirmPassword())) {
			
			userEntity.setUsername(user.getUsername());
			userEntity.setPassword(user.getPassword());
			userEntity.setEnabled(new Byte("1"));
			
			userEntity.setTrekRoles(new ArrayList<>());
			UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setTrekUser(userEntity);
			userRoleEntity.setAuthority("USER");
			userEntity.getTrekRoles().add(userRoleEntity);
			
			try {
				userDBRepository.save(userEntity);
			} catch (Exception e) {
				throw new TrekException(CONSTANTS.ERC007, CONSTANTS.USER_CREATE_MSG, HttpStatus.INTERNAL_SERVER_ERROR);			
			}
			
		}else{
			throw new TrekException(CONSTANTS.ERC007, CONSTANTS.PASSOWRDS_MISMATCH, HttpStatus.BAD_REQUEST);
		}
	}


}
