package DiGraph_A5;

import java.util.HashMap;
import java.util.HashSet;

public class Vertex {
	int in;
	int out;
	long idNum;
	String label;
	
	HashSet<String> inner;
	HashSet<String> outter;
	HashMap<String, Long> outterid;
	
	boolean here = false;
	
	public void setHere() {
		here = true;
	}
	
	public boolean getHere() {
		return here;
	}
	
	public Vertex (long idNum, String label) {
		inner = new HashSet<String>();
		outter = new HashSet<String>();
		
		this.idNum = idNum;
		this.label = label;
		
		this.outterid = new HashMap<String, Long>();
		
		in = inner.size();
		out = outter.size();
	}
	
	public HashSet<Vertex> outterVert (HashMap<String, Vertex> vert) {
		HashSet<Vertex> outterVert = new HashSet<Vertex>();
		
		for (String outters : outter) {
			Vertex hold = vert.get(outters);
			outterVert.add(hold);
		}
		
		return outterVert;
	}
}
