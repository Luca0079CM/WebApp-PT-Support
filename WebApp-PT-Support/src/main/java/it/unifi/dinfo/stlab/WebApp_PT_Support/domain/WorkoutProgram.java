package it.unifi.dinfo.stlab.WebApp_PT_Support.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "workoutprograms")
public class WorkoutProgram {
	private Long id;
	private String name;
	private int difficultyLevel;
	private int estimatedDuration;
	private WorkoutProgramType workoutProgramType;
	private String description;

	private List<Exercise> exerciseList = new ArrayList<>();


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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		if ((obj == null) || !(obj instanceof WorkoutProgram))
			return false;
		WorkoutProgram otherUser = (WorkoutProgram)obj;
		return id.equals(otherUser.id);
	}

}
