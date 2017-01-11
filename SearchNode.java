public class SearchNode {
	final String nodeName;
	SearchNode prev;
	int heuristic = 0,ct = 0;
	public SearchNode(String name,SearchNode previous) {
		nodeName = name;
		prev = previous;
		ct++;
	}
	public SearchNode(String name, SearchNode previous, int heuristic) {
		nodeName = name;
		prev = previous;
		this.heuristic = heuristic;
	}
	public int heuristicValue() {
		return this.heuristic;
	}
	public void reportSolutionPath(SearchNode s) {
		int pathLength = 0;
		System.out.println("in the solution Path :");
		while(s.prev!=null)
		{	
			System.out.print(" >>> "+s.prev.getNodeName());
			s = s.prev;
			pathLength++;
		}
		System.out.println();
		System.out.println("solution-path length "+pathLength);
	
	}

	public String getNodeName() {
		return nodeName;
	} 
}
