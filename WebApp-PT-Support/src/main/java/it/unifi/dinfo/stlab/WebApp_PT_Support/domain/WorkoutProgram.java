package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import javax.persistence.Column;

// import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "workoutprograms")
public class WorkoutProgram {
	private int id;
	private int difficultyLevel;
	private int EstimatedDuration;
	/*/
	private ArrayList<Exercise> exerciseList;
	/*/
	
	@Id
	@Column(name = "id", nullable = false)
	@NotNull
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
	/*/
	public ArrayList<Exercise> getExerciseList() {
		return exerciseList;
	}
	
	public void setExerciseList(ArrayList<Exercise> exerciseList) {
		this.exerciseList = exerciseList;
	}
	/*/
}
