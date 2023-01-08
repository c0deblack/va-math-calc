package cis2910c;

import java.util.*;

import cis2910c.VARates.*;

public class Main{

	public static void main(String[] args){
		// TODO Auto-generated method stub

		ArrayList<DisabilityRating> disabilityList = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		String ratingType = null;
		
		//Testing manual inputs
//		ratingType = "basic";
//		ratingType = "special";
		
		//Testing manual inputs
//		disabilityList.add(new DisabilityRating(20, "left leg"));
//		disabilityList.add(new DisabilityRating(30, "back"));
//		disabilityList.add(new DisabilityRating(10, "neck"));
		
		System.out.println("Basic or Special Rates? "+
							"\n1.Basic" +
							"\n2.Special");
		
		while(ratingType == null) {
			
			int choice = input.nextInt();
			
			switch (choice) {
				case 1:
					ratingType = "basic";
					break;
				case 2:
					ratingType = "special";
					break;
				default:
					System.out.println("Invalid entry:");
					System.out.println("Basic or Special Rates? "+
							"\n1.Basic" +
							"\n2.Special");
					break;
			}
		}
		
		switch (ratingType) {
			case "basic":
				basicRates(disabilityList);
				break;
			case "special":
				specialRates();
				break;
		}
		
	}
	
	public static void basicRates(ArrayList<DisabilityRating> disabilityList) {
		
		Scanner input = new Scanner(System.in);
		boolean finish = false;
		
		/*
		 *  BEGIN
		 *  This section is to simulate the process as if a user were going through the app
		 *   
		 */
		
		//input data to test calculations
		while (!finish) {
			
			input = new Scanner(System.in);
					
			System.out.println("\nEnter description condition:");
			String conditionDisability = input.nextLine();
			
			System.out.println("\nEnter disability rating percent:");
			int percentDisability = input.nextInt();
			
			disabilityList.add(new DisabilityRating(percentDisability, conditionDisability));
			
			System.out.println("\nAdd another disability? "+
								"\n1.Yes" +
								"\n2.No");
			
			if(input.nextInt() == 2) {
				finish = true;
			}
					
		}
		
		/*
		 *  END
		 *   
		 */
		
		//method to check if bilateral
		bilateralFactor(disabilityList);
		
		//method to sort from highest percentage to lowest
		sortDisabilityPercentage(disabilityList);
		
		System.out.println("\nYour disabilities:");
		//output percentage and condition
		for(DisabilityRating d : disabilityList) {
			System.out.printf("%s%% ", d.getPercent());
			System.out.printf("%s\n", d.getCondition());
		}
		
		//output disability rating
		System.out.printf("\nYour Disability Rating: %s%%", calculateDisabilitRating(disabilityList));
		System.out.println("\nYour monthly payment: $" + CompenstationRates.Basic.DependentStatus.Alone_No_Depends.userRating(calculateDisabilitRating(disabilityList)));
	}
	
	public static void specialRates() {
		System.out.println("\n" + CompenstationRates.Special.DependentStatus.Alone_No_Depends.userRating("SMC_N_1_2"));
	}
	
	//method to get disability rating from input
	public static int calculateDisabilitRating(ArrayList<DisabilityRating> disabilityRating) {
		
		double efficiencyRating = 100.00;
		double newEfficiencyRating = efficiencyRating;
		double newDisabilityRating = 0;
		int result = 0;
		
		//loop through disability ratings and run formula to calculate the efficiency rating
		for(int i = 0; i < disabilityRating.size(); i++) {
			
			newDisabilityRating = Math.round(newEfficiencyRating * ((double) disabilityRating.get(i).getPercent() / 100));
			newEfficiencyRating = newEfficiencyRating - newDisabilityRating;
		}
		
		//get final efficiency rating
		newEfficiencyRating = efficiencyRating - newEfficiencyRating;
		
		//VA math has disability rating round to the nearest 10th
		result = roundNearestTenth((int) newEfficiencyRating);
		
		return result;
	}
	
	//method to test the disabilityList if disability is bilateral
	public static void bilateralFactor(ArrayList<DisabilityRating> disabilityList) {
		
		String disability;
		ArrayList <String> disabilityCondition = new ArrayList<>();
		List<ArrayList<DisabilityRating>> bilateral = new ArrayList<ArrayList<DisabilityRating>>();
		ArrayList<DisabilityRating> listToRemove = new ArrayList<>();
		ArrayList<DisabilityRating> tempList = new ArrayList<>();
		
		//loop and extract the condition as a string then add to disabilityCondition list
		for(int i = 0; i < disabilityList.size(); i++) {
			
			disability = disabilityList.get(i).getCondition().substring(disabilityList.get(i).getCondition().lastIndexOf(" ") + 1);
			disabilityCondition.add(disability);
		}
		
		//map to show the indexes that have duplicate disabilities to combine them to new ArrayList for bilateral
		Map<String, List<Integer>> indexes = new HashMap<>();
		
		//loop to extract the indexes and combine the indexes with duplicate conditions
		//to add to new list (i.e. -> {back[0], arm[1], leg[2,3]} )
		for (int i = 0; i < disabilityCondition.size(); i++) {
		    indexes.computeIfAbsent(disabilityCondition.get(i), c -> new ArrayList<>()).add(i);
		}
		
		//add each bilateral to new list
		indexes.forEach((k, v) -> {	
			if(v.size() > 1) {
				
				for(int i = 0; i < v.size(); i++) {
					//add to list to remove
					listToRemove.add(disabilityList.get(v.get(i))); 
					
					/* 
					 * create a new DisabilityRating object and add to tempList. This tempList will use the 
					 * existing percent disability from the disabilityList indexed item, and then add the 
					 * key "k" as the new "condition" that is disabled.
					 * 
					 * The new modified object is to combine the bilateral conditions into one new combined
					 * disability where the percentages have been combined with the bilateral calculations. 
					 * The tempList will be added to the "bilateral" list to calculate the bilateral rating. 
					 *  
					 * Example:------------
					 * 
					 * Original: 
					 * 20% - left leg
					 * 20% - right leg
					 * 
					 * New modified object:
					 * 20% - leg
					 * 20% - leg
					 * 
					 */
					tempList.add(new DisabilityRating(disabilityList.get(v.get(i)).getPercent(), k));
				}
				//add tempList to bilateral list
				bilateral.add(new ArrayList<>(tempList));
				
				//clear the tempList list so it does not add previous added disability lists
				tempList.clear();
			}
		});
		
		//remove all bilateral items from disabilityList
		disabilityList.removeAll(listToRemove);
		
		//do bilateral calculation if duplicate disabilities
		calculateBilateral(bilateral, disabilityList);	
		
	}
	
	private static void calculateBilateral(List<ArrayList<DisabilityRating>> bilateral, ArrayList<DisabilityRating> disabilityList) {
		
		double efficiencyRating = 100.00;
		double newDisabilityRating = 0;
		String condition = null;
		double newEfficiencyRating;
		final double bilateralPercentAmount = .10;
		
		for(int i = 0; i < bilateral.size(); i++) {
			
			//method to sort from highest percentage to lowest
			sortDisabilityPercentage(bilateral.get(i));
			
			newEfficiencyRating = efficiencyRating;
			
			for(int j = 0; j < bilateral.get(i).size(); j++) {
				
				condition = bilateral.get(i).get(j).getCondition();
				newDisabilityRating = Math.round(newEfficiencyRating * ((double) bilateral.get(i).get(j).getPercent() / 100));
				newEfficiencyRating = newEfficiencyRating - newDisabilityRating;
			}
			
			newEfficiencyRating = efficiencyRating - newEfficiencyRating;
			newEfficiencyRating = newEfficiencyRating + (newEfficiencyRating * bilateralPercentAmount);
			newEfficiencyRating = Math.round(newEfficiencyRating);
			
			disabilityList.add(new DisabilityRating((int)newEfficiencyRating, condition));
		}
	}

	//method to sort from highest percentage to lowest
	public static void sortDisabilityPercentage(List<DisabilityRating> disabilityList) {
		
		//Sort the percentage from highest to lowest
		Collections.sort(disabilityList, new Comparator<DisabilityRating>() {

			@Override
			public int compare(DisabilityRating d1, DisabilityRating d2) {
				// TODO Auto-generated method stub
				if (d1.getPercent() < d2.getPercent())
		            return 1;
		        if (d1.getPercent() > d2.getPercent())
		            return -1;
				return 0;
			}
			
		});
	}
	
	//method to round to the nearest 10
	// if 4 or less, round down. if 5 or higher, round up
	static int roundNearestTenth(int n)
    {
        // Smaller multiple
        int a = (n / 10) * 10;
          
        // Larger multiple
        int b = a + 10;
      
        // Return of closest of two
        return (n - a >= b - n)? b : a;
    }
}