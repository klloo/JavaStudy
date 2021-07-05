package main.java.프로그래머스.호텔방배정;

import java.util.*;

public class Solution {
    Map<Long,Long> emptyRooms = new HashMap<Long,Long>();
    public long findEmptyRoom(long room_num) {
        if(!emptyRooms.containsKey(room_num)){
            emptyRooms.put(room_num,room_num+1);
            return room_num;
        }
        long nextRoom = emptyRooms.get(room_num);
        long emptyRoom = findEmptyRoom(nextRoom);
        emptyRooms.put(room_num,emptyRoom);
        return emptyRoom;
    }
    public long[] solution(long k, long[] room_number) {
        long[] answer = {};
        answer = new long[room_number.length];
        int i = 0;
        for(long room : room_number)
            answer[i++] = findEmptyRoom(room);
        return answer;
    }
}
