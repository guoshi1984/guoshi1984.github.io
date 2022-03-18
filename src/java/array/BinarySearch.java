import java.util.*;
public class BinarySearch
{
	public static int search( int arr[], int start, int end, int key)
	{
		if ( start <= end ) {
			int mid = ( start + end )/2;
		        if ( key == arr[mid] ) return mid;
 			if ( key < arr[mid] ) 
				return search( arr, start, mid, key );
			else 
				return search( arr, mid+1, end, key );
		} else {
			return -1;
		}

	}	

	public static void main( String[] args ) 
	{
		int[] array = ArrayUtils.array2;
		int index = search( array, 0, 4, 12);
		System.out.println( index );
	}
}         			
            
