import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionListTest {

    SessionList a = new SessionList(new Session(101, "Business", "Arden", "2026-09-01", "03-302", 0, 5),
            new SessionList(new Session(102, "Math", "Wetzel", "2026-09-04", "38-220", 0, 12),
                    new SessionList(new Session(103, "Biology", "Taylor", "2026-09-08", "180-216", 0, 10),
                            new SessionList(new Session(104, "Statistics", "Chance", "2026-09-10", "25-205", 0, 3),
                                    new SessionList(new Session(105, "Journalism", "Riley", "2026-09-11", "26-210", 0, 1), null)))));

    @Test
    void replaceSession1() {
        assertEquals(new SessionList(new Session(101, "Business", "Arden", "2026-09-01", "03-302", 0, 5),
                new SessionList(new Session(102, "Math", "Wetzel", "2026-09-04", "38-220", 0, 12),
                        new SessionList(new Session(103, "Biology", "Taylor", "2026-09-08", "180-216", 0, 10),
                                new SessionList(new Session(104, "Statistics", "Robinson", "2026-09-11", "25-104", 0, 4),
                                        new SessionList(new Session(105, "Journalism", "Riley", "2026-09-11", "26-210", 0, 1), null))))),
                                SessionList.replaceSession(a, new Session(104, "Statistics", "Robinson", "2026-09-11", "25-104", 0, 4)));
    }
}
