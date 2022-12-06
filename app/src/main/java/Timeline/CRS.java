package Timeline;

import java.util.HashSet;

public class CRS {
    HashSet<String> prerequisite;
    HashSet<String> sessions;

    public CRS (String[] prerequisites, String[] sessions) {
        this.prerequisite = new HashSet<String>();
        this.sessions = new HashSet<String>();

        for (String i : prerequisites) {
            this.prerequisite.add(i);
        }

        for (String i : sessions) {
            this.sessions.add(i);
        }
    }
}