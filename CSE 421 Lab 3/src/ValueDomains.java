



import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map;

public class ValueDomains { 
	private Map<String, List<Object>> valuemap;
	public ValueDomains() { 
		valuemap = new HashMap<String, List<Object>>();
		List<Object> names = new ArrayList<Object>(); 
		names.add("Nawel Boumerdassi"); 
		names.add("Everly Okorji");
		valuemap.put("name", names); 
		List<Object> grades = new ArrayList<Object>(); 
		grades.add("A"); 
		grades.add("A-"); 
		grades.add("B+");
		grades.add("B");
		grades.add("B-");
		grades.add("C+");
		grades.add("C");
		grades.add("C-");
		grades.add("D+");
		grades.add("D");
		grades.add("E");
		valuemap.put("grade", grades); 
		List<Object> ranks = new ArrayList<Object>(); 
		ranks.add("g"); 
		ranks.add("u");  
		valuemap.put("rank", ranks); 
	}
	public void setDomain(String type, List<Object> domain) { 
		valuemap.put(type, domain); 
	}
	public void addValue(String type, Object val) { 
		if (valuemap.containsKey(type)) { 
			valuemap.get(type).add(val);
		}
		else { 
			List<Object> set = new ArrayList<Object>();
			set.add(val); 
			valuemap.put(type, set); 
		}
	}
	public List<Object> getDomain(String type) { 
		return valuemap.get(type); 
	}
}

