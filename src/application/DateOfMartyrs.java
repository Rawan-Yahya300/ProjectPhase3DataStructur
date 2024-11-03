package application;

import java.util.Date;

public class DateOfMartyrs implements Comparable<DateOfMartyrs> {
 
	Date date ;
	AVLTree<Martyr> martyrs;
	
	
	public DateOfMartyrs(Date date) {
		
		this.date = date;
		this.martyrs = new AVLTree<>();
	}


	public Date getDate() {
		return date;
	}


	public AVLTree<Martyr> getMartyrs() {
		return martyrs;
	}


	@Override
	public int compareTo(DateOfMartyrs o) {
		// TODO Auto-generated method stub
		return date.compareTo(o.getDate());
	}
	public int numOfMartyrs() {
		return martyrs.size();   
	}
	public int numOfMartyr() {
		return martyrs.size();
	}
	public int numOfMaleMartyrs() { 
		 
		return numOfMaleMartyrs(martyrs.getRoot());
	}
	private int numOfMaleMartyrs(TNode<Martyr> node) {
		if(node == null)
			return 0;
		if(Character.toUpperCase(node.getData().getGender()) == 'M' )
		return 1 + numOfMaleMartyrs(node.getLeft()) + numOfMaleMartyrs(node.getRight());
		return  numOfMaleMartyrs(node.getLeft()) + numOfMaleMartyrs(node.getRight());
		
	}
	
	public int numOfFemaleMartyrs() { 
		 
		return numOfFemaleMartyrs(martyrs.getRoot());
	}
	private int numOfFemaleMartyrs(TNode<Martyr> node) {
		if(node == null)
			return 0;
		if(Character.toUpperCase(node.getData().getGender()) == 'F' )
		return 1 + numOfFemaleMartyrs(node.getLeft()) + numOfFemaleMartyrs(node.getRight());
		return  numOfFemaleMartyrs(node.getLeft()) + numOfFemaleMartyrs(node.getRight());
		
	}
	
	public int sumOfAges() { 
		 
		return sumOfAges(martyrs.getRoot());
	}
	private int sumOfAges(TNode<Martyr> node) {
		if(node == null)
			return 0;
		
		return node.getData().getAge() + sumOfAges(node.getLeft()) + sumOfAges(node.getRight());
		
		
	}
	
	public int avgAge() {
		int size = martyrs.size();
		if(size != 0)
			return sumOfAges()/size;
		return 0;
	}

	 public String toString() {
		  if(date != null) 
				return (date.getMonth()+1)+"/"+(date.getDate())+"/"+(date.getYear()+1900);//+" "+martyrs.toString();//+","+martyrs.toString();
				else
					return "null";
	  }


	public void setDate(Date date) {
		this.date = date;
	}


	public void setMartyrs(AVLTree<Martyr> martyrs) {
		this.martyrs = martyrs;
	}
	public int hashCode() {
		//return date.hashCode();
		return date.getDate()+(date.getMonth()+1)+(date.getYear()+1900);
	}

	public LinkedList<District> districts(){
		LinkedList<District> list = new LinkedList<>();
    	 return districts(list, martyrs.getRoot());
     }
     private LinkedList<District> districts(LinkedList<District> list, TNode<Martyr> node){
    	 if(node != null) {
    		 if(list.find(new District( node.getData().getDistrict())) == null)
    		 list.insert(new District(node.getData().getDistrict()));
    		 District district = list.find(new District( node.getData().getDistrict())).getData();
    		 district.getMartyrs().insert(node.getData());
    		 districts(list,node.getLeft());
    		 districts(list,node.getRight());
    	 }
    		 
    	 return list;
     }
	     public String districtHasMaxNumOfMartyrs() { 

	 		LinkedList<District> list = districts();
	 		Node<District> curr = list.getHead();
	 		int max = 0;
	 		District maxDis = null;
	 		if(curr != null) {
	 			maxDis = curr.getData();
	 			max = maxDis.getMartyrs().length();
	 		}
	 		while(curr != null) {
	 			int num = curr.getData().getMartyrs().length();
	 			if(num > maxDis.getMartyrs().length()) {
	 				maxDis = curr.getData();
	 				max = num;
	 			}
	 			curr = curr.getNext();
	 		}
            if(maxDis != null)
            	return maxDis.getDistrict();
            return null;
	 	}
	     
	     public LinkedList<Location> locations(){
	 		LinkedList<Location> list = new LinkedList<>();
	     	 return locations(list, martyrs.getRoot());
	      }
	      private LinkedList<Location> locations(LinkedList<Location> list, TNode<Martyr> node){
	     	 if(node != null) {
	     		 if(list.find(new Location( node.getData().getLocation())) == null)
	     		 list.insert(new Location(node.getData().getLocation()));
	     		Location location = list.find(new Location( node.getData().getLocation())).getData();
	     		location.getMartyrs().insert(node.getData());
	     		locations(list,node.getLeft());
	     		locations(list,node.getRight());
	     	 }
	     		 
	     	 return list;
	      }
	 	     public String locationHasMaxNumOfMartyrs() { 

	 	 		LinkedList<Location> list = locations();
	 	 		Node<Location> curr = list.getHead();
	 	 		int max = 0;
	 	 		Location maxLoc = null;
	 	 		if(curr != null) {
	 	 			maxLoc = curr.getData();
	 	 			max = maxLoc.getMartyrs().length();
	 	 		}
	 	 		while(curr != null) {
	 	 			int num = curr.getData().getMartyrs().length();
	 	 			if(num > maxLoc.getMartyrs().length()) {
	 	 				maxLoc = curr.getData();
	 	 				max = num;
	 	 			}
	 	 			curr = curr.getNext();
	 	 		}
	             if(maxLoc != null)
	             	return maxLoc.getLocation();
	             return null;
	 	 	}
	 	
}
