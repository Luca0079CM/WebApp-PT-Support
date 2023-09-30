package it.unifi.dinfo.stlab.WebApp_PT_Support.dto;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;

public class WorkoutProgramDTO {
	private Long id;
	private String name;
	private String description;
	private int difficultyLevel;
	private int estimatedDuration;
	private String workoutProgramType;
	private String[] exerciseList;
	
	
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
	
	public int getEstimatedDuration() {
		return estimatedDuration;
	}
	
	public void setEstimatedDuration(int estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}
	
	public String getWorkoutProgramType() {
		return workoutProgramType;
	}
	
	public void setWorkoutProgramType(String workoutProgramType) {
		this.workoutProgramType = workoutProgramType;
	}
	
	public String[] getExerciseList() {
		return exerciseList;
	}
	
	public void setExerciseList(String[] exerciseList) {
		this.exerciseList = exerciseList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
