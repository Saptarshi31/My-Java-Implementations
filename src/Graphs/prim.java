// Implementation of Prim's Algoritm

// Saptarshi31

import java.util.*;

class Graph {
	private int v;
	private HashMap <Integer, HashSet <node>> adj;
	private HashSet <node> list, list1;
	Graph(int v) {
		this.v = v;
		this.adj = new HashMap <> ();
	}
	
	private class node {
		private int dest, weight;
		node(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}
	
	private class node1 implements Comparable <node1> {
		private int idx, weight;
		node1(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
		public int compareTo(Graph.node1 n) {
			if(n.weight < this.weight)
				return 1;
			else
				return -1;
	    }
	}
	
	public void addEdge(int src, int dest, int weight) {
		if(!adj.containsKey(src)) {
			list = new HashSet <>();
			list.add(new node(dest, weight));
			adj.put(src, list);
			if(!adj.containsKey(dest)) {
				list1 = new HashSet <>();
				list1.add(new node(src, weight));
			} else {
				list1 = adj.get(dest);
				list1.add(new node(src, weight));
			}
			adj.put(dest, list1);
		} else {
			list = adj.get(src);
			list.add(new node(dest, weight));
			adj.put(src, list);
			if(!adj.containsKey(dest)) {
				list1 = new HashSet <>();
				list1.add(new node(src, weight));
			} else {
				list1 = adj.get(dest);
				list1.add(new node(src, weight));
			}
			adj.put(dest, list1);
		}
		
	}
	
	public int PrimMST() {
		int key[] = new int[this.v];
		for(int i = 0; i < this.v; i++)
			key[i] = Integer.MAX_VALUE;
		
		int parent[] = new int[this.v];
		for(int i = 0; i < this.v; i++)
			parent[i] = -1;
		
		boolean visited[] = new boolean[this.v];
		for(int i = 0; i < this.v; i++)
			visited[i] = false;
		
		int src = 0; key[src] = 0; int sum = 0;
		
		PriorityQueue <node1> pque = new PriorityQueue <> ();
		pque.add(new node1(src, key[src]));
		
		while(pque.size() != 0) {
			int minValueNode = pque.poll().idx;
			visited[minValueNode] = true;
			for(node list : adj.get(minValueNode)) {
				int v = list.dest, weight = list.weight;
				if(key[v] > weight && visited[v] == false) {
					key[v] = weight;
					pque.add(new node1(v, key[v]));
					parent[v] = minValueNode;
				}
			}
		}
		
		for(int i = 0; i < this.v; i++) {
			sum += key[i];
		}
		
		return sum;
	}
}

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		Graph graph = new Graph(n);
		
		for(int i = 0; i < m; i++) {
			int src = sc.nextInt();
			int dest = sc.nextInt();
			int weight = sc.nextInt();
			graph.addEdge(src-1, dest-1, weight);
		}
		
        System.out.println(graph.PrimMST());
        
        sc.close();

	}
}
