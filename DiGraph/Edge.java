package DiGraph_A5;

public class Edge {
	long idNum;
	long weight;
	String sLabel;
	String dLabel;
	String eLabel;
	
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum;
		
		if (weight < 0) {
			weight = 1;
		} else {
			this.weight = weight;
		}
		
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		this.eLabel = eLabel;
	}
}
