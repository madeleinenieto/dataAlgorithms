package DiGraph_A5;

import java.util.HashMap;
import java.util.HashSet;

public class DiGraph implements DiGraphInterface {
	HashMap<String, Vertex> verts;
	HashMap<Long, Edge> edges;
	
	HashMap<String, Long> length;
	HashMap<String, String> prev;
	HashSet <Long> vertid;
	
	MinBinHeap pQ;
	
	long max = Long.MAX_VALUE;
	int num;
	int edg;
	
	public DiGraph() {
		verts = new HashMap<String, Vertex>();
		edges = new HashMap<Long, Edge>();
		vertid = new HashSet<Long>();
		num = 0;
		edg = 0;
	}
	
	@Override
	public boolean addNode(long idNum, String label) {
		if (verts.containsKey(label)) {
			return false;
		} else if (label == null) {
			return false;
		} else if (vertid.contains(idNum)) {
			return false;
		} else if (idNum < 0) {
			return false;
		}
		
		vertid.add(idNum);
		
		Vertex neve = new Vertex(idNum, label);
		
		verts.put(label, neve);
		
		num++;
		
		return true;
	}
	
	@Override
	public boolean addEdge (long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (edges.containsKey(idNum)) {
			return false;
		} else if (idNum < 0) {
			return false;
		} else if (!verts.containsKey(sLabel)) {
			return false;
		} else if (!verts.containsKey(dLabel)) {
			return false;
		} else if (verts.get(sLabel).outter.contains(dLabel)) {
			return false;
		}
		
		verts.get(sLabel).outterid.put(dLabel, idNum);
		verts.get(sLabel).outter.add(dLabel);
		verts.get(sLabel).out++;
		
		verts.get(dLabel).inner.add(sLabel);
		verts.get(dLabel).in++;
		
		Edge need = new Edge(idNum, sLabel, dLabel, weight, eLabel);
		edges.put(idNum, need);
		
		edg++;
		
		return true;
	}
	
	@Override
	public boolean delNode (String label) {
		if (!verts.containsKey(label)) {
			return false;
		}
		
		for (String inners : verts.get(label).inner) {
			this.delEdge(inners, label);
		}
		
		for (String outters : verts.get(label).outter) {
			this.delEdge(label, outters);
		}
		
		verts.get(label).inner = null;
		verts.get(label).in = 0;
		
		verts.get(label).outter = null;
		verts.get(label).out = 0;
		
		long x = verts.get(label).idNum;
		vertid.remove(x);
		
		verts.remove(label);
		
		num--;
		
		return true;
	}
	
	@Override
	public boolean delEdge (String sLabel, String dLabel) {
		if ((!verts.containsKey(sLabel)) || (!verts.containsKey(dLabel))) {
			return false;
		}
		
		if (!verts.get(sLabel).outter.contains(dLabel)) {
			return false;
		}
		
		verts.get(sLabel).outter.remove(dLabel);
		verts.get(sLabel).out--;
		verts.get(dLabel).outter.remove(sLabel);
		verts.get(dLabel).in--;
		
		for (Long curr : edges.keySet()) {
			if ((edges.get(curr).sLabel == sLabel) && (edges.get(curr).dLabel == dLabel)) {
				edges.remove(curr);
				
				edg--;
				
				break;
			}
		}
		
		return true;
	}
	
	@Override
	public long numNodes() {
		return num;
	}
	
	@Override
	public long numEdges() {
		return edg;
	}
	
	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		length = new HashMap<String, Long>();
		prev = new HashMap<String, String>();
		
		ShortestPathInfo[] pathList = new ShortestPathInfo[verts.size()];
		
		pQ = new MinBinHeap();		
		pQ.insert(new EntryPair(label, 0));
		
		for (String random : verts.keySet()) {
			length.put(random, max);
		}
		
		length.put(label, (long) 0);
		
		while (pQ.size() != 0) {
			if (pQ.getMin() != null) {
				Vertex v = verts.get(pQ.getMin().getValue());
				pQ.delMin();
				
				if (!v.getHere()) {
					v.setHere();
					
					for (Vertex x : v.outterVert(verts)) {
						if (!x.getHere()) {
							long aPriority = length.get(v.label) + edges.get(v.outterid.get(x.label)).weight;
							
							if (aPriority < length.get(x.label)) {
								length.put(x.label, aPriority);
							}
							
							pQ.insert(new EntryPair(x.label, aPriority));
						}
					}
				}
			}
		}
		
		int i = 0;
		
		for (String v : verts.keySet()) {
			long lengths = length.get(v);
			
			if (lengths < max) {
				pathList[i] = new ShortestPathInfo(v, lengths);
			} else {
				pathList[i] = new ShortestPathInfo(v, -1);
			}
			i++;
		}
		
		return pathList;
	}
}
