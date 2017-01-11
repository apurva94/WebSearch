# WebSearch

intranet#   breadth  depth   BEST		BEAM
-----------------------------------------------
    1        91/4    58/15    76/6 		46/NSE
    5        88/8    42/10    80/NSE	35/NSE
    7        56/6    12/9     71/10 	67/10
Key: <number of nodes visited> / <solution-path length>
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
For intranet 1:
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
BFS:
in the solution Path :
 >>> page99.html >>> page29.html >>> page18.html >>> page1.html
solution-path length 4
 Visited 91 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet1\\page1.html, using: breadth search.

DFS:
in the solution Path :
 >>> page83.html >>> page2.html >>> page79.html >>> page87.html >>> page93.html >>> page68.html >>> page30.html >>> page84.html >>> page42.html >>> page25.html >>> page78.html >>> page39.html >>> page60.html >>> page23.html >>> page1.html
solution-path length 15
 Visited 58 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet1\\page1.html, using: depth search.

BEST:
in the solution Path :
 >>> page7.html >>> page68.html >>> page30.html >>> page29.html >>> page18.html >>> page1.html
solution-path length 6
 Visited 76 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet1\\page1.html, using: best search.

BEAM:
 Visited 46 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet1\\page1.html, using: beam search.
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
For intranet 5:
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
BFS:
in the solution Path :
 >>> page72.html >>> page95.html >>> page96.html >>> page87.html >>> page89.html >>> page99.html >>> page40.html >>> page1.html
solution-path length 8
 Visited 88 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet5\\page1.html, using: breadth search.

DFS:
in the solution Path :
 >>> page72.html >>> page95.html >>> page7.html >>> page48.html >>> page68.html >>> page97.html >>> page5.html >>> page99.html >>> page40.html >>> page1.html
solution-path length 10
 Visited 42 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet5\\page1.html, using: depth search.

BEST:
 Visited 80 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet5\\page1.html, using: best search.

BEAM:
 Visited 35 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet5\\page1.html, using: beam search.
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
For intranet 7:
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

BFS:
in the solution Path :
 >>> page61.html >>> page62.html >>> page57.html >>> page71.html >>> page48.html >>> page1.html
solution-path length 6
 Visited 56 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet7\\page1.html, using: breadth search.

DFS:
in the solution Path :
 >>> page78.html >>> page11.html >>> page60.html >>> page39.html >>> page90.html >>> page57.html >>> page71.html >>> page48.html >>> page1.html
solution-path length 9
 Visited 12 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet7\\page1.html, using: depth search.

BEST:
in the solution Path :
 >>> page61.html >>> page73.html >>> page23.html >>> page89.html >>> page19.html >>> page8.html >>> page88.html >>> page17.html >>> page99.html >>> page1.html
solution-path length 10
 Visited 71 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet7\\page1.html, using: best search.

BEAM:
in the solution Path :
 >>> page61.html >>> page73.html >>> page23.html >>> page89.html >>> page19.html >>> page8.html >>> page88.html >>> page17.html >>> page99.html >>> page1.html
solution-path length 10
 Visited 67 nodes, starting @ C:\Users\Anubhav\WorkBench\AI_LAB_1\src\intranet7\\page1.html, using: beam search.

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
TASK2 :
2a)
Device a heuristic function :
I have taken three counters in the program which has the occurance of QUERY word into consideration and assigned preference through a number(Highest being in the most important contribution to heuristic) i.e 
a counter-"specialWordInDoc" for the occurance of QUERY words in the HTML documents.
a counter-"specialWordInhyperlink" for the occurance of QUERY words in the HYPERLINKS.
a counter-"successive" for the successive occurance of QUERY words.

Also, the heuristic choosen is not admissible because, I have not calculated the number of QUERY words within document a HYPERLINK is pointing to,therefore, “h” will overestimate.

2c)
It worked for BFS in all three intranets.
Didn't get a solution for BEAM search in Intranets 1 & 5 and Best Search in intranet 5.
 
