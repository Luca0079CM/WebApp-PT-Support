package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "workoutprograms")
public class WorkoutProgram {
	private int id;
	private int difficultyLevel;
	private int EstimatedDuration;
	private WorkoutProgramType workoutProgramType;
	
	private List<Exercise> exerciseList;
	
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
	
	@ManyToMany(fetch=FetchType.LAZY)
	public List<Exercise> getExerciseList() {
		return exerciseList;
	}
	
	public void setExerciseList(List<Exercise> exerciseList) {
		this.exerciseList = exerciseList;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		WorkoutProgram other = (WorkoutProgram) obj;
		if (id != other.getId())
			return false;
		return true;
	}
	
}
