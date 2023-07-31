package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity
@Table(name = "exercises")
public class Exercise{
	
	private Long id;
	private String name;
	private int difficultyLevel;
	private String description;
	
	private GymMachine machine;
	
	@Id
//	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)//targetEntity = GymMachine.class, 
	public GymMachine getMachine() {
		return machine;
	}
	
	public void setMachine(GymMachine machine) {
		this.machine = machine;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
	
	@Override 
	public boolean equals(Object obj) { 
		if (this == obj) 
			return true;
		if (obj == null) 
			return false;
		if (!(obj instanceof Exercise)) 
			return false;
		Exercise otherUser = (Exercise)obj; 
		return id.equals(otherUser.id);
	}
	
}