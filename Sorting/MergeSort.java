// Merge Sort implementation
public class MergeSort{
    public static void conquer(int[] arr, int sidx,int mid,int eidx){
        int x = 0;
        int idx1= sidx;
        int idx2 = mid+1;
        int[] merged = new int [eidx-sidx+1];
        while(idx1<=mid && idx2 <=eidx){
            if(arr[idx1]<arr[idx2]){
                merged[x++]= arr[idx1++];
            }else{
                merged[x++]= arr[idx2++];
            }
        }

        if(idx1<=mid){
            merged[x++]= arr[idx1++];
        }
        if(idx2<=eidx){
            merged[x++]= arr[idx2++];
        }

        for(int i =0, j=sidx; i<merged.length ;i++, j++){
            arr[j]= merged[i];
        }
    }
    public static void divide(int[] arr, int sidx,int eidx){
        if(sidx == eidx || sidx>eidx){
            return;
        }
        int mid = sidx +(eidx-sidx)/2;
        divide(arr,sidx,mid);
        divide(arr, mid+1, eidx);
        conquer(arr,sidx,mid,eidx);
    }
    public static void main(String[] args) {
        int[] arr ={6,3,9,5,2,8};
        divide(arr,0,arr.length-1);

        for(int i =0; i<arr.length ;i++){
            System.out.print(arr[i]+" ");
        }
    }
}