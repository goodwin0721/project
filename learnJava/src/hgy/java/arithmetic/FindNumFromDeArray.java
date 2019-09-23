/*在一个二维数组中（每个一维数组的长度相同），
每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。*/
package hgy.java.arithmetic;
public class FindNumFromDeArray {
	
	public static boolean find(int target, int [][] array) {
		int row = array.length;//行
		int column = array[0].length;//列
//		System.out.println(row+" "+column);
    	//从左下角到右上角查询
    	int i = row-1;
    	int j = 0;
    	System.out.println(i + " " + j);
    	while(i>=0&&j<column){
    		if(array[i][j]==target)
    			return true;
    		else if(array[i][j]>target)
    			i--;
    		else if(array[i][j]<target)
    			j++;
    		System.out.println(i + " " + j);
    	}
        return false;
    }
    
    public static void main(String[] args) {
		int [][]arr = {{1,2,3,5,6},{2,4,5,7,9},{4,5,6,8,9}};
//		System.out.println(Arrays.deepToString(arr));
		for(int[]a:arr){
			for(int aa:a)
				System.out.print(aa+" ");
			System.out.println();
		}
		System.out.println(find(9,arr));
		
	}

}
