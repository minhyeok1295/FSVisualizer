package model;

import util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
	
public class FileSystem extends Observable {
	
	public String path;
	public FSNode curr;
	public String tree;
	public List<FSNode> favorites;
	
	
	public FileSystem() {
		this.path = null;
		this.curr = null;
		this.tree = null;
		this.favorites = new ArrayList<FSNode>();
	}
	
	// getter method
	public List<FSNode> getFavorites() {
		return this.favorites;
	}
	
	public FSNode getCurr() {
		return this.curr;
	}

	public String getPath() {
		return this.path;
	}
	
	public String getTree() {
		return this.tree;
	}
	
	//change the current node
	public void setCurr(FSNode curr) {
		this.curr = curr;
		this.notifyObservers();
	}
	
	//add the node to the list of favorites
	public void addFavorite(FSNode fav) {
		this.favorites.add(fav);
		this.notifyObservers();
	}
	
	//remove the node from the favorite list
	public void removeFavorite(FSNode fav) {
		this.favorites.remove(fav);
		this.notifyObservers();
	}
	//Print method
	public void printPath() {
		if (this.path == null) {
			System.out.println("null");
		}
		else {
			System.out.println(this.path);
		}
	}
	
	public void printTree() {
		if (this.tree == null) {
			System.out.println("null");
		}
		else {
			System.out.println(this.tree);
		}
	}
	
	public void printFav() {
		for (int i = 0; i < this.favorites.size(); i++) {
			System.out.println(this.favorites.get(i).name);
		}
	}
	
	/*
	 * split given path into each directory
	 * */
	public String[] splitPath(String path) {
		Path p = Paths.get(path);
		return StreamSupport.stream(p.spliterator(), false).map(Path::toString)
                .toArray(String[]::new);
	}
	
	
	//traverse contents of folder and add to the tree.
	public void traverse(FSNode node) {
		node.path = node.path.concat("\\");
		String contents[] = node.file.list();
		if (contents.length != 0) {
			for (int i = 0; i < contents.length; i++) {
				String subPath = node.path.concat(contents[i]);
				FSNode childNode = new FSNode(contents[i], subPath);
				node.children.add(i, childNode);
				childNode.setParent(node);
				if (childNode.type == 'd') {
					traverse(childNode);
				}
			}
		}
		
	}
	

	/*returns the string representation of file system tree*/
	public String getTree(FSNode node, int depth) {
		if (node == null) {
			return "";
		}
		int d = depth;
		String k = "-";
		String tree = "";
		for (int i = 0; i < d; i++) {
			k = k.concat("-");
		}
		if (node.children.size() == 0) {
			tree = tree.concat(k).concat(String.valueOf(node.type)).concat(":").concat(node.name).concat("\n");
		}
		else {
			tree = tree.concat(k).concat(String.valueOf(node.type)).concat(":").concat(node.name).concat("\n");
			d += 1;
			for (int i = 0; i < node.children.size(); i++) {
				tree = tree.concat(getTree(node.children.get(i), d));
			}
		}
		return tree;
	}
	
	
	//Main method to create tree based on the given path
	public void createTree(String path) {
		this.path = path;
		String[] list = null;
		try {
			list = splitPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String rootName = list[list.length - 1]; //last 
		FSNode rootNode = new FSNode(rootName, path);

		traverse(rootNode);
		this.curr = rootNode;
		this.tree = getTree(rootNode, 0);
		this.notifyObservers();
	}
	
	//search node from the tree if node name equals the given string.
	public FSNode searchTree(FSNode curr, String name) {
		if (curr.name.equals(name)) {
			return curr;
		}
		else {
			for (int i = 0; i < curr.children.size(); i++) {
				FSNode res = searchTree(curr.children.get(i), name);
				if(res != null) {
					return res;
				}
			}
		}
		return null;
	}
}