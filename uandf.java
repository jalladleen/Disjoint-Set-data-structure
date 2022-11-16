
public class uandf {
	int[] rank;
	int[] parent;
	int n; 
	private boolean finalSet = false;

	public uandf(int n) {
		rank = new int[n];
		parent = new int[n];
		this.n = n;	
	}

	public void make_set(int i) {
		parent[i] = i;
	}
	
	public int find_set(int i) {
		
		if (finalSet == false) {
			if (parent[i] != i) {
				return (parent[i] = find_set(parent[i]));
			} else {
				return i;
			}
		} else {
			return parent[i];
		}
	}
	
	
	public void union_sets(int x, int y) {

		x = find_set(x); y = find_set(y); 
		
		if(x == y)
			return;
		
		else if (rank[x] < rank[y]) 
			parent[x] = parent[y];
		
		if (rank[x] > rank[y]) 
			parent[y] = parent[x];

		 else 
			parent[y] = parent[x];
			rank[x] = rank[x]++;
	}


	public int final_sets() {
		for (int i = 1; i < parent.length; i++) {
			if (parent[i] != 0) {
				// Find the set that i belongs to
				find_set(i);
			}
		}

		int current = 1;

		for (int i = 1; i < parent.length; i++) {
			if (parent[i] == i) {
				parent[i] = current++;
				rank[i] = 1;
			} else {
				rank[i] = 0;
			}
		}

		for (int i = 1; i < parent.length; i++) {
			if (rank[i] == 0 && parent[i] > 0) {
				parent[i] = parent[parent[i]];
			}
		}

		finalSet = true;
		return current - 1;
	}
}

