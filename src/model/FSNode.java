package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FSNode {
	
	public List<FSNode> children;
	public FSNode parent;
	public String name;
	public char type;
	public String path;
	public File file;
	
	public FSNode(String name, String path) {
		this.parent = null;
		this.file = new File(path);
		this.name = name;
		this.children = new ArrayList<FSNode>();
		if (this.file.isFile()) {
			this.type = 'f';
		}
		else if (this.file.isDirectory()) {
			this.type = 'd';
		}
		this.path = path;
	}
	
	//getter method
	public FSNode getParent() {
		return this.parent;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<FSNode> getChildren() {
		return this.children;
	}
	
	//setter method
	public void setParent(FSNode parent) {
		this.parent = parent;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void printChildren() {
		List<FSNode> children = this.children;
		for(int i = 0; i < children.size(); i++) {
			 FSNode child = children.get(i);
			 System.out.println(child.getName());
		 }
	}
	
}
