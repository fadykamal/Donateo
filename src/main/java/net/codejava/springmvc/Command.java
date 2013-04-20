package net.codejava.springmvc;

import java.util.Hashtable;

public interface Command {
	public abstract Hashtable execute(Hashtable htblInputParams);
}
