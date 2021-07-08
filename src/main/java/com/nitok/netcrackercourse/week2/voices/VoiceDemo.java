package com.nitok.netcrackercourse.week2.voices;

import java.util.ArrayList;
import java.util.List;

public class VoiceDemo {
    public static void main(String[] args) {
        List<Voice> voiceList = new ArrayList<Voice>() {{
            add(new Cat());
            add(new Cow());
            add(new Dog());
        }};
        voiceList.forEach(Voice::voice);
    }
}
