package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;

@Entity
@Table(name = "workoutprograms")
public class WorkoutProgram {
	private Long id;
	private int difficultyLevel;
	private int estimatedDuration;
	private WorkoutProgramType workoutProgramType;
	
	private List<Exercise> exerciseList = new ArrayList<Exercise>();
	
	
	@Id
//	@GeneratedValue
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
	
	@ManyToMany(fetch=FetchType.LAZY)//cascade = CascadeType.ALL
	public List<Exercise> getExerciseList() {
		return exerciseList;
	}
	
	public void setExerciseList(List<Exercise> exerciseList) {
		this.exerciseList = exerciseList;
	}
	
	public void addExercise(Exercise exercise) {
		this.exerciseList.add(exercise);
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
		if (!(obj instanceof WorkoutProgram)) 
			return false;
		WorkoutProgram otherUser = (WorkoutProgram)obj; 
		return id.equals(otherUser.id);
	}
	
}
