package ca.parimal.connectz.model;

import java.util.*;
public class MyClass {
    public static void mainas() {
        int x=10;
        int y=25;
        int z=x+y;


        int n=4;
        List<Integer> from = new ArrayList<>();
        from.add(1);from.add(1);from.add(2);
        List<Integer> to = new ArrayList<>();
        to.add(2);to.add(3);to.add(4);
        List<Integer>wei = new ArrayList<>();
        wei.add(2);wei.add(5);wei.add(3);
        int sig =5;
        System.out.println(n+","+sig);
        System.out.println(getNumPairs(n,from,to,wei,sig).toString());

    }
    public static List<Integer> getNumPairs(int n, List<Integer> from, List<Integer> to, List<Integer> wei, int speed){
        // int[][] adj=new int[n][n];
        // for(int i=0;i<n;i++)for(int j=0;j<n;j++)adj[i][j]=-1;
        // for(int i=0;i<n;i++)adj[from.get(i)-1][to.get(i)-1]=wei.get(i);

        HashMap<Integer,Integer>[] arr=new HashMap[n];
        for(int i=0;i<n;i++)arr[i]=new HashMap<Integer,Integer>();
        for(int i=0;i<n-1;i++)arr[from.get(i)-1].put(to.get(i)-1,wei.get(i));

        List<Integer> ans= new ArrayList<>();
        for(int i=0;i<n;i++){
            helper(i,i,0,new boolean[n],arr);
            int temp=0;
            for (Integer value : arr[i].values()) {
                if(value%speed==0)temp++;
            }
            ans.add(temp);

        }
        return ans;

    }
    public static void helper(int maini, int i, int pre, boolean[] vis, HashMap<Integer,Integer> arr[]){
        if(vis[i])return;
        vis[i]=true;
        if(maini!=i)arr[maini].put(i,pre);
        HashMap<Integer,Integer> map= arr[i];
        int[][] ar=new int [map.size()][2];
        int jk=0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            ar[jk][0]=key; ar[jk][1]=value;
        }
        for(int [] a: ar){
            helper(maini,a[0],(pre+a[1]),vis,arr);
        }
    }
}