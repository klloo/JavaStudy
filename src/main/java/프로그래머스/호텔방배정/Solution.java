package main.java.프로그래머스.호텔방배정;

import java.util.*;

public class Solution {
    Map<Long,Long> emptyRooms = new HashMap<Long,Long>();
    public long findEmptyRoom(long room_number) {
        if(!emptyRooms.containsKey(room_number)) {
            emptyRooms.put(room_number,room_number+1);
            return room_number;
        }
        long nextRoom = emptyRooms.get(room_number);
        long emptyRoom = findEmptyRoom(nextRoom);
        emptyRooms.put(room_number,emptyRoom);
        return emptyRoom;
    }
    public long[] solution(long k, long[] room_number) {
        long[] answer = {};
        answer = new long[room_number.length];
        int i = 0 ;
        for(long room:room_number)
            answer[i++] = findEmptyRoom(room);
        return  answer;
    }
}
