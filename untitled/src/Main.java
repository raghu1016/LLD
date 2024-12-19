public class Main {
    public static void main(String[] args) {

        int[] houses = {0, 2, 1, 2, 0};
        int target = 3;
        int[][] cost = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
        int m = 5;
        int n = 2;
        int prev = 0;
        int count = 0;

        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == 0) continue;
            if (houses[i] != prev) {
                prev = houses[i];
                count++;
            }
        }
        System.out.println(args[0]);


        // System.out.println(count);
//        if(count>target) return -1;
//        System.out.println(fun(0,houses,cost,m,n,target,-1,0));
//    }


//    public static int fun(int ind,int[] houses,int[][] cost, int m ,int n,int target, int prev,int count){
//
//        if(count>target) return (int)Math.pow(10,7);
//        if(ind>=m) return 0;
//        if(houses[ind]!=0) {
//            if(prev!=houses[ind]){
//                count++;
//            }
//            return fun(ind+1,houses,cost,m ,n, target,houses[ind],count);
//
//        }
//
//        int ans = (int)Math.pow(10,7);
//
//        for(int j=0;j<n;j++){
//            if(prev!=j+1){
//                count++;
//            }
//            ans = Math.min(ans,cost[ind][j]+fun(ind+1,houses,cost,m,n,target,j+1,count));
//            count--;
//        }
//        System.out.println(ans);
//
//        return ans;
//    }
    }
}