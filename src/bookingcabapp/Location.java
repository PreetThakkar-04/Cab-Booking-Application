/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingcabapp;


public class Location {
            int loc[][] = new int[5][5];
             Location()
            {
                int i;
                for (i=0;i<5;i++)
                {
                    loc[i][i]=0;
                }
                loc[0][1]=3;
                loc[0][2]=3;
                loc[0][3]=2;
                loc[0][4]=3;
                loc[1][0]=3;
                loc[1][2]=7;
                loc[1][3]=5;
                loc[1][4]=4;
                loc[2][0]=4;
                loc[2][1]=5;
                loc[2][3]=7;
                loc[2][4]=9;
                loc[3][0]=8;
                loc[3][1]=4;
                loc[3][2]=4;
                loc[3][4]=6;
                loc[4][0]=2;
                loc[4][1]=1;
                loc[4][2]=4;
                loc[4][3]=8;
            }
            public int getDistance(int a, int b)
            {
                int dist = loc[a][b];
                return dist;
            }
            public int tripDuration(int a,int b)
            {
                int dist = loc[a][b];
                int duration = dist/1;
                return duration;
            }
            public int getfare(int a,int b)
            {
                int dist = loc[a][b];
                int fare = dist*50;
                return fare;
            }
}
