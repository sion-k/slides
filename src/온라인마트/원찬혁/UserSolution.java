import java.util.*;

class UserSolution
{
	static TreeSet<Product>[][] p;
	static HashMap<Integer, ProductFull> idPrice;
	static long[][] bias;
	static int[][] delCnt;
    public void init()
    {
    	idPrice = new HashMap<>();
    	bias = new long[5][5];
    	delCnt = new int[5][5];
        p = new TreeSet[5][5];
        for(int i = 0; i < 5; i++) {
        	p[i] = new TreeSet[5];
        	for(int j = 0; j < 5; j++) {
        		p[i][j] = new TreeSet<>();
        	}
        }
    }

    public int sell(int mID, int mCategory, int mCompany, int mPrice)
    {
    	mPrice += bias[mCategory-1][mCompany-1];
    	p[mCategory-1][mCompany-1].add(new Product(mPrice, mID));
    	
    	idPrice.put(mID, new ProductFull(mID, mCategory, mCompany, mPrice));
        return p[mCategory-1][mCompany-1].size() - delCnt[mCategory-1][mCompany-1];
    }

    public int closeSale(int mID)
    {
    	ProductFull pf;
    	int res = -1;
    	if(idPrice.containsKey(mID)) {
    		pf = idPrice.get(mID);
    		delCnt[pf.category - 1][pf.company - 1]++;
    		res = (int)(pf.price - bias[pf.category - 1][pf.company - 1]);
    		idPrice.remove(mID);
    	}
        return res;
    }

    public int discount(int mCategory, int mCompany, int mAmount)
    {
    	bias[mCategory-1][mCompany-1] += mAmount;
		updatePrice(mCategory, mCompany);
    	
        return p[mCategory-1][mCompany-1].size() - delCnt[mCategory-1][mCompany-1];
    }

    Solution.RESULT show(int mHow, int mCode)
    {
        Solution.RESULT res = new Solution.RESULT();

        PriorityQueue<Product> pq = new PriorityQueue<>();
        PriorityQueue<Product> pq2 = new PriorityQueue<>(Collections.reverseOrder());
        if(mHow == 0) {
        	// 모든 상품
        	for(int i = 0; i < 5; i++) {
        		for(int j = 0; j < 5; j++) {
        			updatePrice(i + 1, j + 1);
        			for(Product pr : p[i][j]) {
        				if(idPrice.containsKey(pr.id)) {
        					if(pq.size() < 5 || pq2.peek().price >= pr.price - bias[i][j]) {
        						pq.offer(new Product(pr.price - bias[i][j], pr.id));
        						pq2.offer(new Product(pr.price - bias[i][j], pr.id));
        					}
        					else break;
        				}
        			}
        			
        		}
        	}
        }
        else if(mHow == 1) {
        	// 카테고리 mCode
        	for(int i = 0; i < 5; i++) {
        		updatePrice(mCode, i + 1);
    			for(Product pr : p[mCode - 1][i]) {
    				if(idPrice.containsKey(pr.id)) {
    					if(pq.size() < 5 || pq2.peek().price >= pr.price - bias[mCode - 1][i]) {
    						pq.offer(new Product(pr.price - bias[mCode - 1][i], pr.id));
    						pq2.offer(new Product(pr.price - bias[mCode - 1][i], pr.id));
    					}
    					else break;
    				}
    			}
        	}
        }
        else {
        	// 제조사 mCode
        	for(int i = 0; i < 5; i++) {
        		updatePrice(i + 1, mCode);
    			for(Product pr : p[i][mCode - 1]) {
    				if(idPrice.containsKey(pr.id)) {
    					if(pq.size() < 5 || pq2.peek().price >= pr.price - bias[i][mCode - 1]) {
    						pq.offer(new Product(pr.price - bias[i][mCode - 1], pr.id));
    						pq2.offer(new Product(pr.price - bias[i][mCode - 1], pr.id));
    					}
    					else break;
    				}
    			}
    		}
        }
        int idx = 0;
        while(idx < 5 && !pq.isEmpty()) {
        	Product pr = pq.poll();
    		if(idPrice.containsKey(pr.id)) {
    			res.IDs[idx++] = pr.id;
    		}
        }
        res.cnt = idx;
        return res;
    }
    
    private void updatePrice(int mCategory, int mCompany) {
    	while(!p[mCategory-1][mCompany-1].isEmpty() && p[mCategory-1][mCompany-1].first().price <= bias[mCategory-1][mCompany-1]) {
    		int id = p[mCategory-1][mCompany-1].first().id;
//    		System.out.println("id: " + id + " price: " + p[mCategory-1][mCompany-1].first().price + " bias: " + bias[mCategory-1][mCompany-1]);
    		if(!idPrice.containsKey(id))
    			delCnt[mCategory-1][mCompany-1]--;
    		else
    			idPrice.remove(id);
    		p[mCategory-1][mCompany-1].remove(p[mCategory-1][mCompany-1].first());
//    		System.out.println("res: " + res);
    	}
    }
}

class ProductFull {
	int id;
	int category;
	int company;
	long price;
	public ProductFull(int id, int category, int company, long price) {
		this.id = id;
		this.category = category;
		this.company = company;
		this.price = price;
	}
}

class Product implements Comparable<Product> {
	long price;
	int id;
	public Product(long price, int id) {
		this.price = price;
		this.id = id;
	}
	@Override
	public int compareTo(Product o) {
		int ret = Long.compare(this.price, o.price);
		return (ret != 0) ? ret : Integer.compare(this.id, o.id);
	}	
}
