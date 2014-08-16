package com.si.ha;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true)
	protected long id;

	public long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof BaseEntity && obj.getClass() == getClass() && ((BaseEntity) obj).getId() == id) {
			return true;
		}
		return false;
	}
	
	//TODO ennél jobb hashcode is lehetne, ez működő de haszontalan
	@Override
	public int hashCode() {
		if (id != 0) {
			return (int) id;
		}
		return super.hashCode();
	}
}