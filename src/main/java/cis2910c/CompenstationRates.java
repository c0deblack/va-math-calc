package cis2910c;

public class CompenstationRates {

	public static class Basic{
		
		public enum DependentStatus {
			// With a dependent spouse or parent, but no children
		    Alone_No_Depends{
		    	double userRating(int rating) {
		    		if(rating == 10) 		return 152.64;
		    		else if(rating == 20) 	return 301.74;
		    		else if(rating == 30) 	return 467.39;
		    		else if(rating == 40) 	return 673.28;
		    		else if(rating == 50) 	return 958.44;
		    		else if(rating == 60) 	return 1214.03;
		    		else if(rating == 70) 	return 1528.95;
		    		else if(rating == 80) 	return 1778.43;
		    		else if(rating == 90) 	return 1998.52;
		    		else if(rating == 100)	return 3332.06;
		    		else { return 0;}
		    	}
		    },
		    Spouse_No_Depends{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 522.39;
		    		else if(rating == 40) 	return 747.28;
		    		else if(rating == 50) 	return 1050.44;
		    		else if(rating == 60) 	return 1325.03;
		    		else if(rating == 70) 	return 1659.95;
		    		else if(rating == 80) 	return 1926.43;
		    		else if(rating == 90) 	return 2165.52;
		    		else if(rating == 100) 	return 3517.84;
		    		else { return 0;}
		    	}
		    },
		    Spouse_One_Parent{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 566.39;
		    		else if(rating == 40) 	return 806.28;
		    		else if(rating == 50) 	return 1124.44;
		    		else if(rating == 60) 	return 1414.03;
		    		else if(rating == 70) 	return 1763.95;
		    		else if(rating == 80) 	return 2045.43;
		    		else if(rating == 90) 	return 2299.52;
		    		else if(rating == 100) 	return 3666.94;
		    		else { return 0;}
		    	}
		    },
		    Spouse_Two_Parents{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 610.39;
		    		else if(rating == 40) 	return 865.28;
		    		else if(rating == 50) 	return 1198.44;
		    		else if(rating == 60) 	return 1503.03;
		    		else if(rating == 70) 	return 1867.95;
		    		else if(rating == 80) 	return 2164.43;
		    		else if(rating == 90) 	return 2433.52;
		    		else if(rating == 100) 	return 3816.04;
		    		else { return 0;}
		    	}
		    },
		    One_Parent{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 511.39;
		    		else if(rating == 40) 	return 732.28;
		    		else if(rating == 50) 	return 1032.44;
		    		else if(rating == 60) 	return 1303.03;
		    		else if(rating == 70) 	return 1633.95;
		    		else if(rating == 80) 	return 1897.43;
		    		else if(rating == 90) 	return 2132.52;
		    		else if(rating == 100) 	return 3481.16;
		    		else { return 0;}
		    	}
		    },
		    Two_Parents{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 555.39;
		    		else if(rating == 40) 	return 791.28;
		    		else if(rating == 50) 	return 1106.44;
		    		else if(rating == 60) 	return 1392.03;
		    		else if(rating == 70) 	return 1737.95;
		    		else if(rating == 80) 	return 2016.43;
		    		else if(rating == 90) 	return 2266.52;
		    		else if(rating == 100) 	return 3630.26;
		    		else { return 0;}
		    	}
		    },
	
		    // With dependents, including children
		    Child_Only{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 504.39;
		    		else if(rating == 40) 	return 722.28;
		    		else if(rating == 50) 	return 1020.44;
		    		else if(rating == 60) 	return 1288.03;
		    		else if(rating == 70) 	return 1615.95;
		    		else if(rating == 80) 	return 1877.43;
		    		else if(rating == 90) 	return 2109.52;
		    		else if(rating == 100) 	return 3456.30;
		    		else { return 0;}
		    	}
		    },
		    Child_and_Spouse{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 563.39;
		    		else if(rating == 40) 	return 801.28;
		    		else if(rating == 50) 	return 1118.44;
		    		else if(rating == 60) 	return 1407.03;
		    		else if(rating == 70) 	return 1754.95;
		    		else if(rating == 80) 	return 2035.43;
		    		else if(rating == 90) 	return 2287.52;
		    		else if(rating == 100) 	return 3653.89;
		    		else { return 0;}
		    	}
		    },
		    Child_Spouse_One_Parent{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 607.39;
		    		else if(rating == 40) 	return 860.28;
		    		else if(rating == 50) 	return 1192.44;
		    		else if(rating == 60) 	return 1496.03;
		    		else if(rating == 70) 	return 1858.95;
		    		else if(rating == 80) 	return 2154.43;
		    		else if(rating == 90) 	return 2421.52;
		    		else if(rating == 100) 	return 3802.99;
		    		else { return 0;}
		    	}
		    },
		    Child_Spouse_Two_Parents{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 651.39;
		    		else if(rating == 40) 	return 919.28;
		    		else if(rating == 50) 	return 1266.44;
		    		else if(rating == 60) 	return 1585.03;
		    		else if(rating == 70) 	return 1962.95;
		    		else if(rating == 80) 	return 2273.43;
		    		else if(rating == 90) 	return 2555.52;
		    		else if(rating == 100) 	return 3952.09;
		    		else { return 0;}
		    	}
		    },
		    Child_One_Parent{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 548.39;
		    		else if(rating == 40) 	return 781.28;
		    		else if(rating == 50) 	return 1094.44;
		    		else if(rating == 60) 	return 1377.03;
		    		else if(rating == 70) 	return 1719.95;
		    		else if(rating == 80) 	return 1996.43;
		    		else if(rating == 90) 	return 2243.52;
		    		else if(rating == 100) 	return 3605.40;
		    		else { return 0;}
		    	}
		    },
		    Child_Two_Parents{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 592.39;
		    		else if(rating == 40) 	return 840.28;
		    		else if(rating == 50) 	return 1168.44;
		    		else if(rating == 60) 	return 1466.03;
		    		else if(rating == 70) 	return 1823.95;
		    		else if(rating == 80) 	return 2115.43;
		    		else if(rating == 90) 	return 2377.52;
		    		else if(rating == 100) 	return 3754.50;
		    		else { return 0;}
		    	}
		    },
	
		    // Additional amounts
		    Spouse_Aid_Attendance{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 51;
		    		else if(rating == 40) 	return 68;
		    		else if(rating == 50) 	return 86;
		    		else if(rating == 60) 	return 102;
		    		else if(rating == 70) 	return 119;
		    		else if(rating == 80) 	return 136;
		    		else if(rating == 90) 	return 153;
		    		else if(rating == 100) 	return 170.38;
		    		else { return 0;}
		    	}
		    },
		    Additional_Child{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 27;
		    		else if(rating == 40) 	return 36;
		    		else if(rating == 50) 	return 46;
		    		else if(rating == 60) 	return 55;
		    		else if(rating == 70) 	return 64;
		    		else if(rating == 80) 	return 73;
		    		else if(rating == 90) 	return 83;
		    		else if(rating == 100) 	return 92.31;
		    		else { return 0;}
		    	}
		    },
		    Child_Education{
		    	double userRating(int rating) {
		    		if(rating == 30) 		return 89;
		    		else if(rating == 40) 	return 119;
		    		else if(rating == 50) 	return 149;
		    		else if(rating == 60) 	return 178;
		    		else if(rating == 70) 	return 208;
		    		else if(rating == 80) 	return 238;
		    		else if(rating == 90) 	return 268;
		    		else if(rating == 100) 	return 298.18;
		    		else { return 0;}
		    	}
		    }
		    ;
	
			abstract double userRating(int rating);
		}
	}
	
	public static class Special{
		
		public enum DependentStatus {
			// With a dependent spouse or parent, but no children
		    Alone_No_Depends{
		    	double userRating(String rating) {
		    		if(rating == "SMC_L") 			return 4146.13;
		    		else if(rating == "SMC_L_1_2") 	return 4360.47;
		    		else if(rating == "SMC_M") 		return 4575.68;
		    		else if(rating == "SMC_M_1_2") 	return 4890.07;
		    		else if(rating == "SMC_N") 		return 5205.17;
		    		else if(rating == "SMC_N_1_2") 	return 5511.35;
		    		else if(rating == "SMC_O_P") 	return 5818.09;
		    		else if(rating == "SMC_R_1") 	return 8313.61;
		    		else if(rating == "SMC_R_2") 	return 9535.91;
		    		else if(rating == "SMC_S")		return 3729.64;
		    		else { return 0;}
		    	}
		    },
		    Spouse_No_Depends{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4331.91;
		    		else if(rating == "SMC_L_1_2") 	return 4546.25;
		    		else if(rating == "SMC_M") 		return 4761.46;
		    		else if(rating == "SMC_M_1_2") 	return 5075.85;
		    		else if(rating == "SMC_N") 		return 5390.95;
		    		else if(rating == "SMC_N_1_2") 	return 5697.13;
		    		else if(rating == "SMC_O_P") 	return 6003.87;
		    		else if(rating == "SMC_R_1") 	return 8499.39;
		    		else if(rating == "SMC_R_2") 	return 9721.69;
		    		else if(rating == "SMC_S")		return 3915.42;
		    		else { return 0;}
		    	}
		    },
		    Spouse_One_Parent{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4481.01;
		    		else if(rating == "SMC_L_1_2") 	return 4695.35;
		    		else if(rating == "SMC_M") 		return 4910.56;
		    		else if(rating == "SMC_M_1_2") 	return 5224.95;
		    		else if(rating == "SMC_N") 		return 5540.05;
		    		else if(rating == "SMC_N_1_2") 	return 5846.23;
		    		else if(rating == "SMC_O_P") 	return 6152.97;
		    		else if(rating == "SMC_R_1") 	return 8648.49;
		    		else if(rating == "SMC_R_2") 	return 9870.79;
		    		else if(rating == "SMC_S")		return 4064.52;
		    		else { return 0;}
		    	}
		    },
		    Spouse_Two_Parents{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4630.11;
		    		else if(rating == "SMC_L_1_2") 	return 4844.45;
		    		else if(rating == "SMC_M") 		return 5059.66;
		    		else if(rating == "SMC_M_1_2") 	return 5374.05;
		    		else if(rating == "SMC_N") 		return 5689.15;
		    		else if(rating == "SMC_N_1_2") 	return 5995.33;
		    		else if(rating == "SMC_O_P") 	return 6302.07;
		    		else if(rating == "SMC_R_1") 	return 8797.59;
		    		else if(rating == "SMC_R_2") 	return 10019.89;
		    		else if(rating == "SMC_S")		return 4213.62;
		    		else { return 0;}
		    	}
		    },
		    One_Parent{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4295.23;
		    		else if(rating == "SMC_L_1_2") 	return 4509.57;
		    		else if(rating == "SMC_M") 		return 4724.78;
		    		else if(rating == "SMC_M_1_2") 	return 5039.17;
		    		else if(rating == "SMC_N") 		return 5354.27;
		    		else if(rating == "SMC_N_1_2") 	return 5660.45;
		    		else if(rating == "SMC_O_P") 	return 5967.19;
		    		else if(rating == "SMC_R_1") 	return 8462.71;
		    		else if(rating == "SMC_R_2") 	return 9685.01;
		    		else if(rating == "SMC_S")		return 3878.74;
		    		else { return 0;}
		    	}
		    },
		    Two_Parents{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4444.33;
		    		else if(rating == "SMC_L_1_2") 	return 4658.67;
		    		else if(rating == "SMC_M") 		return 4873.88;
		    		else if(rating == "SMC_M_1_2") 	return 5188.27;
		    		else if(rating == "SMC_N") 		return 5503.37;
		    		else if(rating == "SMC_N_1_2") 	return 5809.55;
		    		else if(rating == "SMC_O_P") 	return 6116.29;
		    		else if(rating == "SMC_R_1") 	return 8611.81;
		    		else if(rating == "SMC_R_2") 	return 9834.11;
		    		else if(rating == "SMC_S")		return 4027.84;
		    		else { return 0;}
		    	}
		    },
	
		    // With dependents, including children
		    Child_Only{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4270.37;
		    		else if(rating == "SMC_L_1_2") 	return 4484.71;
		    		else if(rating == "SMC_M") 		return 4699.92;
		    		else if(rating == "SMC_M_1_2") 	return 5014.31;
		    		else if(rating == "SMC_N") 		return 5329.41;
		    		else if(rating == "SMC_N_1_2") 	return 5635.59;
		    		else if(rating == "SMC_O_P") 	return 5942.33;
		    		else if(rating == "SMC_R_1") 	return 8437.85;
		    		else if(rating == "SMC_R_2") 	return 9660.15;
		    		else if(rating == "SMC_S")		return 3853.88;
		    		else { return 0;}
		    	}
		    },
		    Child_and_Spouse{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4467.96;
		    		else if(rating == "SMC_L_1_2") 	return 4682.30;
		    		else if(rating == "SMC_M") 		return 4897.51;
		    		else if(rating == "SMC_M_1_2") 	return 5211.90;
		    		else if(rating == "SMC_N") 		return 5527;
		    		else if(rating == "SMC_N_1_2") 	return 5833.18;
		    		else if(rating == "SMC_O_P") 	return 6139.92;
		    		else if(rating == "SMC_R_1") 	return 8635.44;
		    		else if(rating == "SMC_R_2") 	return 9857.74;
		    		else if(rating == "SMC_S")		return 4051.47;
		    		else { return 0;}
		    	}
		    },
		    Child_Spouse_One_Parent{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4617.06;
		    		else if(rating == "SMC_L_1_2") 	return 4831.40;
		    		else if(rating == "SMC_M") 		return 5046.61;
		    		else if(rating == "SMC_M_1_2") 	return 5361;
		    		else if(rating == "SMC_N") 		return 5676.1;
		    		else if(rating == "SMC_N_1_2") 	return 5982.28;
		    		else if(rating == "SMC_O_P") 	return 6289.02;
		    		else if(rating == "SMC_R_1") 	return 8784.54;
		    		else if(rating == "SMC_R_2") 	return 10006.84;
		    		else if(rating == "SMC_S")		return 4200.57;
		    		else { return 0;}
		    	}
		    },
		    Child_Spouse_Two_Parents{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4766.16;
		    		else if(rating == "SMC_L_1_2") 	return 4980.50;
		    		else if(rating == "SMC_M") 		return 5195.71;
		    		else if(rating == "SMC_M_1_2") 	return 5510.10;
		    		else if(rating == "SMC_N") 		return 5825.20;
		    		else if(rating == "SMC_N_1_2") 	return 6131.38;
		    		else if(rating == "SMC_O_P") 	return 6438.12;
		    		else if(rating == "SMC_R_1") 	return 8933.64;
		    		else if(rating == "SMC_R_2") 	return 10155.94;
		    		else if(rating == "SMC_S")		return 4349.67;
		    		else { return 0;}
		    	}
		    },
		    Child_One_Parent{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4419.47;
		    		else if(rating == "SMC_L_1_2") 	return 4633.81;
		    		else if(rating == "SMC_M") 		return 4849.02;
		    		else if(rating == "SMC_M_1_2") 	return 5163.41;
		    		else if(rating == "SMC_N") 		return 5478.51;
		    		else if(rating == "SMC_N_1_2") 	return 5784.69;
		    		else if(rating == "SMC_O_P") 	return 6091.43;
		    		else if(rating == "SMC_R_1") 	return 8586.95;
		    		else if(rating == "SMC_R_2") 	return 9809.25;
		    		else if(rating == "SMC_S")		return 4002.98;
		    		else { return 0;}
		    	}
		    },
		    Child_Two_Parents{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 4568.57;
		    		else if(rating == "SMC_L_1_2") 	return 4782.91;
		    		else if(rating == "SMC_M") 		return 4998.12;
		    		else if(rating == "SMC_M_1_2") 	return 5312.51;
		    		else if(rating == "SMC_N") 		return 5627.61;
		    		else if(rating == "SMC_N_1_2") 	return 5933.79;
		    		else if(rating == "SMC_O_P") 	return 6240.53;
		    		else if(rating == "SMC_R_1") 	return 8736.05;
		    		else if(rating == "SMC_R_2") 	return 9958.35;
		    		else if(rating == "SMC_S")		return 4152.08;
		    		else { return 0;}
		    	}
		    },
	
		    // Additional amounts
		    Spouse_Aid_Attendance{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 170.38;
		    		else if(rating == "SMC_L_1_2") 	return 170.38;
		    		else if(rating == "SMC_M") 		return 170.38;
		    		else if(rating == "SMC_M_1_2") 	return 170.38;
		    		else if(rating == "SMC_N") 		return 170.38;
		    		else if(rating == "SMC_N_1_2") 	return 170.38;
		    		else if(rating == "SMC_O_P") 	return 170.38;
		    		else if(rating == "SMC_R_1") 	return 170.38;
		    		else if(rating == "SMC_R_2") 	return 170.38;
		    		else if(rating == "SMC_S")		return 170.38;
		    		else { return 0;}
		    	}
		    },
		    Additional_Child{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 92.31;
		    		else if(rating == "SMC_L_1_2") 	return 92.31;
		    		else if(rating == "SMC_M") 		return 92.31;
		    		else if(rating == "SMC_M_1_2") 	return 92.31;
		    		else if(rating == "SMC_N") 		return 92.31;
		    		else if(rating == "SMC_N_1_2") 	return 92.31;
		    		else if(rating == "SMC_O_P") 	return 92.31;
		    		else if(rating == "SMC_R_1") 	return 92.31;
		    		else if(rating == "SMC_R_2") 	return 92.31;
		    		else if(rating == "SMC_S")		return 92.31;
		    		else { return 0;}
		    	}
		    },
		    Child_Education{
		    	double userRating(String rating) {
		    		if(rating == "SML_L") 			return 298.18;
		    		else if(rating == "SMC_L_1_2") 	return 298.18;
		    		else if(rating == "SMC_M") 		return 298.18;
		    		else if(rating == "SMC_M_1_2") 	return 298.18;
		    		else if(rating == "SMC_N") 		return 298.18;
		    		else if(rating == "SMC_N_1_2") 	return 298.18;
		    		else if(rating == "SMC_O_P") 	return 298.18;
		    		else if(rating == "SMC_R_1") 	return 298.18;
		    		else if(rating == "SMC_R_2") 	return 298.18;
		    		else if(rating == "SMC_S")		return 298.18;
		    		else { return 0;}
		    	}
		    }
		    ;
			
			abstract double userRating(String rating);
			
		}
	}
	
}
