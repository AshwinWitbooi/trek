package za.co.ashtech.trek.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the trail database table.
 * 
 */
@Entity
@Table(name="trail")
@NamedQuery(name="TrailEntity.findAll", query="SELECT t FROM TrailEntity t")
public class TrailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String decription;

	private String length;

	private String level;

	private String location;

	private String name;

	private String status;

	public TrailEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDecription() {
		return this.decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}