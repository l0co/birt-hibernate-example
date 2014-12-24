package com.blogspot.lifeinide.clibernate.test;

import com.blogspot.lifeinide.clibernate.model.BaseEnity;

import javax.persistence.*;

/**
 * @author l0co@wp.pl
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = BaseEnity.DISCRIMINATOR_COLUMN, discriminatorType = DiscriminatorType.STRING, length = 255)
public class TestEntity extends BaseEnity {

	protected String stringVal;

	protected int intVal;

	public String getStringVal() {
		return stringVal;
	}

	public void setStringVal(String stringVal) {
		this.stringVal = stringVal;
	}

	public int getIntVal() {
		return intVal;
	}

	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}

	@Override
	protected String internalToString() {
		return String.format("stringVal=%s, intVal=%s", stringVal, intVal);
	}

}
