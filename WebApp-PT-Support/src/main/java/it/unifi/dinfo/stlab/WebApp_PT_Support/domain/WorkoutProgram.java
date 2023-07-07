package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;


import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workoutprograms")
public class WorkoutProgram {
	private int id;
	private int difficultyLevel;
	private int EstimatedDuration;
	private WorkoutProgramType workoutProgramType;
	/*/
	private ArrayList<Exercise> exerciseList;
	/*/
	
	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	public int getEstimatedDuration() {
		return EstimatedDuration;
	}
	
	public void setEstimatedDuration(int estimatedDuration) {
		EstimatedDuration = estimatedDuration;
	}

	public WorkoutProgramType getWorkoutProgramType() {
		return workoutProgramType;
	}

	public void setWorkoutProgramType(WorkoutProgramType workoutProgramType) {
		this.workoutProgramType = workoutProgramType;
	}
	
	
	/*/
	public ArrayList<Exercise> getExerciseList() {
		return exerciseList;
	}
	
	public void setExerciseList(ArrayList<Exercise> exerciseList) {
		this.exerciseList = exerciseList;
	}
	/*/
}
