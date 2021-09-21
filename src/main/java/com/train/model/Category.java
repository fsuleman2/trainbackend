package com.train.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "category")
public class Category {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long catId;

	    private String type;

	    private String description;
	    
	    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	    @JsonIgnore
	    private Set<TrainDetails> trains = new LinkedHashSet<>();

	    public Category() {
	    }

	    public Category(String type, String description) {
	        this.type = type;
	        this.description = description;
	    }

		public Long getCatId() {
			return catId;
		}

		public void setCatId(Long catId) {
			this.catId = catId;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Set<TrainDetails> getTrains() {
			return trains;
		}

		public void setTrains(Set<TrainDetails> trains) {
			this.trains = trains;
		}
	    

}
