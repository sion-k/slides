package SWEA24995;

import java.util.*;

class UserSolution {
	static class Product implements Comparable<Product> {
		int mID;
		int mCategory;
		int mCompany;
		int mPrice;
		boolean sell;
		public Product(int mID, int mCategory, int mCompany, int mPrice) {
			this.mID = mID;
			this.mCategory = mCategory;
			this.mCompany = mCompany;
			this.mPrice = mPrice;
			this.sell = true;
		}
		@Override
		public int compareTo(Product p) {
			if(this.mPrice != p.mPrice) {
				return this.mPrice - p.mPrice;
			}
			return this.mID - p.mID;
		}
	}
	List<Product>[][] list = new List[6][6];
	HashMap<Integer, Integer> hm = new HashMap<>();
	List<Product> allList = new ArrayList<>();
	int[][] sellCounts;
	
	public void init() {
		for(int i=1; i<=5; i++) {
			for(int j=1; j<=5; j++) {
				list[i][j] = new ArrayList<>();
			}
		}
		hm.clear();
		allList.clear();
		sellCounts = new int[6][6];
		return;
	}

	public int sell(int mID, int mCategory, int mCompany, int mPrice) {
		Product p = new Product(mID, mCategory, mCompany, mPrice);
		list[mCategory][mCompany].add(p);
		allList.add(p);
		sellCounts[mCategory][0]++;
		sellCounts[mCategory][mCompany]++;
		sellCounts[0][mCompany]++;
		hm.put(mID, 10*mCategory + mCompany);
		return sellCounts[mCategory][mCompany];
	}

	public int closeSale(int mID) {
		if(!hm.containsKey(mID)) {
			return -1;
		}
		int temp = hm.get(mID);
		int mCategory = temp / 10;
		int mCompany = temp % 10;
		for(Product p : list[mCategory][mCompany]) {
			if(p.mID == mID && p.sell) {
				p.sell = false;
				sellCounts[p.mCategory][0]--;
				sellCounts[p.mCategory][p.mCompany]--;
				sellCounts[0][p.mCompany]--;
				return p.mPrice;
			}
		}
		return -1;
	}

	public int discount(int mCategory, int mCompany, int mAmount) {
		for(Product p : list[mCategory][mCompany]) {
			if(p.sell){
				p.mPrice -= mAmount;
				if(p.mPrice <= 0) {
					p.sell = false;
					sellCounts[p.mCategory][0]--;
					sellCounts[p.mCategory][p.mCompany]--;
					sellCounts[0][p.mCompany]--;
				}
			}
		}
		return sellCounts[mCategory][mCompany];
	}

	Solution.RESULT show(int mHow, int mCode) {
		Solution.RESULT res = new Solution.RESULT();
		PriorityQueue<Product> pq = new PriorityQueue<>();
		if(mHow == 0) {
			for(int i=1; i<=5; i++) {
				for(int j=1; j<=5; j++) {
					for(Product p : list[i][j]) {
						if(p.sell) {
							pq.offer(p);							
						}
					}
				}
			}
		} else if(mHow == 1) {
			for(int i=1; i<=5; i++){
				for(Product p : list[mCode][i]) {
					if(p.sell) {
						pq.offer(p);
					}
				}
			}
		} else {
			for(int i=1; i<=5; i++) {
				for(Product p : list[i][mCode]) {
					if(p.sell) {
						pq.offer(p);
					}
				}
			}
		}
		int index = 0;		
		while(!pq.isEmpty()) {
			if(index == 5) {
				break;
			}
			res.IDs[index++] = pq.poll().mID; 
		}
		res.cnt = index;
		return res;
	}
}
