package com.blogspot.lifeinide.clibernate.model;

import javax.persistence.*;

/**
 * Base model class.
 *
 * @author l0co@wp.pl
 */
@MappedSuperclass
public class BaseEnity {

	public static final String DISCRIMINATOR_COLUMN = "classname";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected long id;

	@Version
	private long version;

	@Column(updatable = false, insertable = false)
	protected String classname;

	public long getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	public String getClassname() {
		return classname;
	}

	protected String internalToString() {
		return "";
	}

	@Override
	public String toString() {
		String internalToString = internalToString();
		return String.format("%s [id=%d%s]", getClass().getSimpleName(), getId(),
			"".equals(internalToString) ? "" : ", "+internalToString);
	}

}
