package graphs;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0.
 * Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
 * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it,
 * denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
 * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i,
 * return true if you can visit all the rooms, or false otherwise.

 * Example 1:
 * Input: rooms = [[1],[2],[3],[]]
 * Output: true
 * Explanation:
 * We visit room 0 and pick up key 1.
 * We then visit room 1 and pick up key 2.
 * We then visit room 2 and pick up key 3.
 * We then visit room 3.
 * Since we were able to visit every room, we return true.

 * Example 2:
 * Input: rooms = [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
 *
 */
public class LT841_KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int roomAmount = rooms.size();

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[roomAmount];
        int roomCounter = 0;

        q.add(0);
        visited[0] = true;
        roomCounter++;

        while(!q.isEmpty()){
            int currentRoom = q.poll();

            for(int roomKey : rooms.get(currentRoom)){
                if(!visited[roomKey]){
                    q.add(roomKey);
                    visited[roomKey] = true;
                    roomCounter++;
                }
            }
        }

        return roomAmount == roomCounter;
    }



    public boolean canVisitAllRoomsRec(List<List<Integer>> rooms) {
        int roomAmount = rooms.size();

        boolean[] visited = new boolean[roomAmount];

        dfs(0, rooms, visited);

        for(boolean b: visited){
            if(!b){
                return false;
            }
        }
        return true;
    }

    private void dfs(int room, List<List<Integer>> rooms, boolean[] visited){
        if(visited[room]){
            return;
        }
        visited[room] = true;

        for(int roomKey: rooms.get(room)){
            dfs(roomKey, rooms, visited);
        }
    }

}
