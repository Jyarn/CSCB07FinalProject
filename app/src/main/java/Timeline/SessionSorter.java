package Timeline;

import java.util.Comparator;

public class SessionSorter implements Comparator<String> {
    private double hash (String[] i) {
        double r = 0;

        if (i.length >= 1) {
            if (i[0].equals("Winter")) {
                r += 0.1;
            }

            else if (i[0].equals("Summer")) {
                r += 0.2;
            }

            else if (i[0].equals("Fall")) {
                r += 0.3;
            }
        }

        if (i.length >= 2) {
            r = Double.parseDouble(i[1]);
        }

        return r;
    }

    public int compare (String c1, String c2) {
        if (c1.equals(c2)) { return 0; }

        String[] p_c1 = c1.split(" ");
        String[] p_c2 = c2.split(" ");
        int a = Double.compare(hash(p_c1), hash(p_c2));
        return a;
    }
}
