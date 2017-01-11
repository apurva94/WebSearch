import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class WebSearch {

	static LinkedList<SearchNode> OPEN; // Feel free to choose your own data structures for searching,
	static HashSet<String> CLOSED;      // and be sure to read documentation about them.

	static final boolean DEBUGGING = false; // When set, report what's happening.

	static int beamWidth = 10; // If searchStrategy = "beam",

	static final String START_NODE     = "page1.html";

	static final String GOAL_PATTERN   = "QUERY1 QUERY2 QUERY3 QUERY4";
		
	static void performSearch(String startNode, String directoryName, String searchStrategy)
	{
		int nodesVisited = 0;

		OPEN   = new LinkedList<SearchNode>();	//Frontier 
		CLOSED = new HashSet<String>();	//explored set

		OPEN.add(new SearchNode(startNode,null));

		while (!OPEN.isEmpty())
		{
			
			
			SearchNode currentNode = pop(OPEN);
			String currentURL = currentNode.getNodeName();

			nodesVisited++;

			// Go and fetch the contents of this file.
			String contents = Utilities.getFileContents(directoryName
					+ File.separator
					+ currentURL);

			if (isaGoalNode(contents))
			{
				// Report the solution path found
				// (You might also wish to write a method that
				// counts the solution-path's length, and then print that
				// number here.)
				currentNode.reportSolutionPath(currentNode);
				break;
			}

			// Remember this node was visited.
			CLOSED.add(currentURL);

			addNewChildrenToOPEN(currentNode, contents, searchStrategy);

			// Provide a status report.
			if (DEBUGGING) System.out.println("Nodes visited = " + nodesVisited
					+ " |OPEN| = " + OPEN.size());
			
			if (searchStrategy.equalsIgnoreCase("beam")) {
        		if (OPEN.size() > beamWidth) {
            			LinkedList<SearchNode> beamSearch = new LinkedList<SearchNode>();
           	 		for(int i = 0;i<beamWidth;i++) beamSearch.add(OPEN.get(i));
            			OPEN.clear();
            			OPEN.addAll(beamSearch);
        		}
    		}
		}

		System.out.println(" Visited " + nodesVisited + " nodes, starting @" +
				" " + directoryName + File.separator + startNode +
				", using: " + searchStrategy + " search.");
	}

	// This method reads the page's contents and
	// collects the 'children' nodes (ie, the hyperlinks on this page).
	// The prev node is also passed in so that 'backpointers' can be
	// created (in order to later extract solution paths).
	static void addNewChildrenToOPEN(SearchNode prev, String contents, String searchStrategy)
	{
		int counter=0;
		StringTokenizer st = new StringTokenizer(contents);
				
		while (st.hasMoreTokens())
		{
			String token = st.nextToken(" ");
			if(token.matches("QUERY1")||token.matches("QUERY2") ||token.matches("QUERY3") ||token.matches("QUERY4")) {
				 counter++;
				}
			
			// Look for the hyperlinks on the current page.

			// (Lots a print statments and error checks are in here,
			// both as a form of documentation and as a debugging tool should you
			// create your own intranets.)

			// At the start of some hypertext?  Otherwise, ignore this token.
			if (token.equalsIgnoreCase("<A"))
			{
				String hyperlink; // The name of the child node.

				if (DEBUGGING) System.out.println("Encountered a HYPERLINK");

				// Read: HREF = page#.html >

				token = st.nextToken(" ");
				if (!token.equalsIgnoreCase("HREF"))
				{
					System.out.println("Expecting 'HREF' and got: " + token);
				}

				token = st.nextToken(" ");
				if (!token.equalsIgnoreCase("="))
				{
					System.out.println("Expecting '=' and got: " + token);
				}

				// Now we should be at the name of file being linked to.
				hyperlink = st.nextToken(" ");
				if (!hyperlink.startsWith("page"))
				{
					System.out.println("Expecting 'page#.html' and got: " + hyperlink);
				}

				token = st.nextToken(" ");
				if (!token.equalsIgnoreCase(">"))
				{
					System.out.println("Expecting '>' and got: " + token);
				}

				if (DEBUGGING) System.out.println(" - found a link to " + hyperlink);

				//////////////////////////////////////////////////////////////////////
				// Have collected a child node; now have to decide what to do with it.
				//////////////////////////////////////////////////////////////////////

				if (alreadyInOpen(hyperlink))
				{ // If already in OPEN, we'll ignore this hyperlink
					// (Be sure to read the "Technical Note" below.)
					if (DEBUGGING) System.out.println(" - this node is in the OPEN list.");
				}
				else if (CLOSED.contains(hyperlink))
				{ // If already in CLOSED, we'll also ignore this hyperlink.
					if (DEBUGGING) System.out.println(" - this node is in the CLOSED list.");
				}
				else 
				{ // Collect the hypertext if this is a previously unvisited node.
					// (This is only needed for HEURISTIC SEARCH, but collect in
					// all cases for simplicity.)
					String hypertext = ""; // The text associated with this hyperlink.

					do
					{
						token = st.nextToken();
						if (!token.equalsIgnoreCase("</A>")) hypertext += " " + token;
					}
					while (!token.equalsIgnoreCase("</A>"));

					if (DEBUGGING) System.out.println("   with hypertext: " + hypertext);

					//////////////////////////////////////////////////////////////////////
					// At this point, you have a new child (hyperlink) and you have to
					// insert it into OPEN according to the search strategy being used.
					// Your heuristic function for best-first search should accept as 
					// arguments both "hypertext" (ie, the text associated with this 
					// hyperlink) and "contents" (ie, the full text of the current page).
					//////////////////////////////////////////////////////////////////////

					// Technical note: in best-first search,
					// if a page contains TWO (or more) links to the SAME page,
					// it is acceptable if only the FIRST one is inserted into OPEN,
					// rather than the better-scoring one.  For simplicity, once a node
					// has been placed in OPEN or CLOSED, we won't worry about the
					// possibility of later finding of higher score for it.
					// Since we are scoring the hypertext POINTING to a page,
					// rather than the web page itself, we are likely to get
					// different scores for given web page.  Ideally, we'd
					// take this into account when sorting OPEN, but you are
					// NOT required to do so (though you certainly are welcome
					// to handle this issue).

					// HINT: read about the insertElementAt() and addElement()
					// methods in the Vector class.
					if(searchStrategy.equalsIgnoreCase("breadth")){
						OPEN.addLast(new SearchNode(hyperlink,prev));
					}
					else if(searchStrategy.equalsIgnoreCase("depth")){
						
						OPEN.addFirst(new SearchNode(hyperlink,prev));
						
					}
					else if(searchStrategy.equalsIgnoreCase("best") || searchStrategy.equalsIgnoreCase("beam")) {
						int heuristics = heuristic_func(contents, hypertext);
						SearchNode node = new SearchNode(hyperlink, prev, heuristics);

						if(OPEN.isEmpty()) {
							OPEN.add(node);
						} else {for(int i=0; i<OPEN.size(); i++) {
								int currentHeuristic = OPEN.get(i).heuristicValue();
								if(currentHeuristic > heuristics) {
									OPEN.add(i,node); 
									break;
								}
								else {
									if(i == OPEN.size()-1 && currentHeuristic < heuristics)
										OPEN.add(node); 
								} 
							}
						}
				}
			}
		}
					
	}
	}
	// A GOAL is a page that contains the goalPattern set above.
	static boolean isaGoalNode(String contents)
	{
		return (contents != null && contents.indexOf(GOAL_PATTERN) >= 0);
	}

	// Is this hyperlink already in the OPEN list?
	// This isn't a very efficient way to do a lookup,
	// but its fast enough for this homework.
	// Also, this for-loop structure can be
	// be adapted for use when inserting nodes into OPEN
	// according to their heuristic score.
	static boolean alreadyInOpen(String hyperlink)
	{
		int length = OPEN.size();

		for(int i = 0; i < length; i++)
		{
			SearchNode node = OPEN.get(i);
			String lastLink = node.getNodeName();

			if (hyperlink.equalsIgnoreCase(lastLink)) return true; 
		}

		return false;   
	}

	// You can use this to remove the first element from OPEN.
	static SearchNode pop(LinkedList<SearchNode> list)
	{
		SearchNode result = list.removeFirst();
		return result;
	}
	static int heuristic_func(String contents,String hyperLink)
	{
		StringTokenizer st = new StringTokenizer(contents);
		String token;
		int heuristics = 0;
		boolean lnkPresent = false;
		int specialWordInDoc = 0;
		int specialWordInhyperlink = 0;
	//collect all QUERY words in doc	
		while(st.hasMoreTokens()){
			token = st.nextToken();
			if(token.equalsIgnoreCase("<A"))	lnkPresent = true;
			if(token.equalsIgnoreCase("</A>"))	lnkPresent = false;
			if(token.contains("QUERY") && !lnkPresent)	specialWordInDoc++;
		}
		
		st = new StringTokenizer(hyperLink);
		//collect all QUERY words in hyperlink
		while(st.hasMoreTokens()){
			token = st.nextToken();
			if(token.contains("QUERY"))	specialWordInhyperlink++;
		}
		
		
		int ct = 0;
		int successive = 0;
		boolean ispattern;
		
		while(st.hasMoreTokens()){
			token = st.nextToken();
			if(token.contains("QUERY")) {
				ct++;
				ispattern = true;
				
			} else {ispattern = false;}
			
			if(ct!=0 && !ispattern) {
				if(ct == 1)
					successive = successive + ct;
				else
					successive = successive + 2*ct;
				ct = 0;
			}
		}
		
		heuristics = (specialWordInDoc*1)+(specialWordInhyperlink*5)+(successive*10);

		return heuristics;
	}

	public static void main(String[] args) {
		if (args.length != 2)
		{
			System.out.println("You must provide the directoryName and searchStrategyName.  Please try again.");
		}
		else
		{
			String directoryName = args[0]; // Specify the intranet-1,2,3 to search.
			String searchStrategyName = args[1]; // Read the search strategy to use.

			if (searchStrategyName.equalsIgnoreCase("breadth") ||
					searchStrategyName.equalsIgnoreCase("depth")   ||
					searchStrategyName.equalsIgnoreCase("best")    ||
					searchStrategyName.equalsIgnoreCase("beam"))		{
				performSearch(START_NODE, directoryName, searchStrategyName);
			}
			else
			{
				System.out.println("The valid search strategies are:");
				System.out.println("  BREADTH DEPTH BEST BEAM");
			}
		}

		Utilities.waitHere("Press ENTER to exit.");

	}

}
