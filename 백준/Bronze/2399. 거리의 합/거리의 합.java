import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] nums = new int[n];
		long sum = 0;
		
		for(int i=0; i<n; i++) 
			nums[i] = scan.nextInt();
		
		for(int i=0; i<n; i++) 
			for(int j=0; j<n; j++) 
				sum += Math.abs(nums[i]-nums[j]);
		
		System.out.println(sum);
		scan.close();
	}

}