package it.unifi.dinfo.stlab.WebApp_PT_Support.dto;

public class ExerciseDTO {
	private Long id;
	private String name;
	private int difficultyLevel;
	private String machine;
	private String description;
	
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
	
	public String getMachine() {
		return machine;
	}
	
	public void setMachine(String machine) {
		this.machine = machine;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
