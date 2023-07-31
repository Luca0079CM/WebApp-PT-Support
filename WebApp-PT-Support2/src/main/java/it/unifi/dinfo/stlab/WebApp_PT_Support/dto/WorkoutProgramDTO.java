package it.unifi.dinfo.stlab.WebApp_PT_Support.dto;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;

public class WorkoutProgramDTO {
	private Long id;
	private int difficultyLevel;
	private int estimatedDuration;
	private WorkoutProgramType workoutProgramType;
	private String[] exerciseList;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public WorkoutProgramType getWorkoutProgramType() {
		return workoutProgramType;
	}
	
	public void setWorkoutProgramType(WorkoutProgramType workoutProgramType) {
		this.workoutProgramType = workoutProgramType;
	}
	
	public String[] getExerciseList() {
		return exerciseList;
	}
	
	public void setExerciseList(String[] exerciseList) {
		this.exerciseList = exerciseList;
	}
	
}
