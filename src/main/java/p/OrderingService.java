package p;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class OrderingService {
	Map<Integer, Ordering> orderings = new HashMap<>();
	public OrderingService() {
		Ordering o = new Ordering(1,1,1,"6000","2020-01-01");
		orderings.put(o.getId(), o);
		
		o = new Ordering(2,1,3,"21000","2020-01-03");
		orderings.put(o.getId(), o);
		
		o = new Ordering(3,2,5,"8000","2020-01-03");
		orderings.put(o.getId(), o);
		
		o = new Ordering(4,3,6,"6000","2020-01-04");
		orderings.put(o.getId(), o);
		
		o = new Ordering(5,4,7,"20000","2020-01-05");
		orderings.put(o.getId(), o);
		
		o = new Ordering(6,1,2,"12000","2020-01-07");
		orderings.put(o.getId(), o);
		
		o = new Ordering(7,4,8,"13000","2020-01-07");
		orderings.put(o.getId(), o);
		
		o = new Ordering(8,3,10,"12000","2020-01-08");
		orderings.put(o.getId(), o);
		
		o = new Ordering(9,2,10,"7000","2020-01-09");
		orderings.put(o.getId(), o);
		
		o = new Ordering(10,3,8,"13000","2020-01-09");
		orderings.put(o.getId(), o);
		
		o = new Ordering(11,1,1,"7000","2020-01-14");
		orderings.put(o.getId(), o);
		
		o = new Ordering(12,1,1,"7000","2020-01-14");
		orderings.put(o.getId(), o);
		
		o = new Ordering(13,1,1,"7000","2020-01-14");
		orderings.put(o.getId(), o);
		
		o = new Ordering(14,1,2,"7000","2020-01-14");
		orderings.put(o.getId(), o);
		
	}
	
	public Map<Integer, Ordering> getOrdering() {
		return orderings;
	}
	public synchronized void add(Ordering ordering) {
		int max = Collections.max(orderings.keySet());
		
		ordering.setId(max+1);
		ordering.getCustomerId();
		ordering.setOrderingDate("2023-07-19");
		orderings.put(ordering.getId(), ordering);
	}
	
	public List<Ordering> findAll(){
		return new ArrayList(orderings.values());
	}
	
}
