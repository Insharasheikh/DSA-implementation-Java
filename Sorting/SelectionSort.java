// Selection Sort implementation
public class SelectionSort{
    public static void main(String[] args){
        int[] arr = {7,8,3,1,2};
        for(int i =0; i<arr.length-1;i++){
            int smallest = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j]<arr[smallest]){
                    smallest = j;
                }
            }
            
            int temp = arr[i];
            arr[i]=arr[smallest];
            arr[smallest]= temp;
        }
        for (int i =0; i<arr.length; i++){
        System.out.print(arr[i]+ " ");
        }
    }
}