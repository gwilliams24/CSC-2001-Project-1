import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionListTest {

    // Session and SessionLists to Test!
    SessionList a = new SessionList(new Session(101, "Business", "Arden", "2026-09-01", "03-302", 0, 5),
            new SessionList(new Session(102, "Math", "Wetzel", "2026-09-04", "38-220", 0, 12),
                    new SessionList(new Session(103, "Biology", "Taylor", "2026-09-08", "180-216", 0, 10),
                            new SessionList(new Session(104, "Statistics", "Chance", "2026-09-10", "25-205", 0, 3),
                                    new SessionList(new Session(105, "Journalism", "Riley", "2026-09-11", "26-210", 0, 1), null)))));

    Session b = new Session(106, "Econ", "Tinsley", "2026-09-09", "Baker", 0, 2);
    Session c = new Session(107, "Math", "Tinsley", "2026-01-29", "Frost", 0, 3);
    Session d = new Session(109, "Sociology", "Peters", "2027-02-24", "Science North", 0, 16);


    //Tests for addSession button method
    @Test
    void addSession1() {
        assertEquals(new SessionList(new Session(101, "Business", "Arden", "2026-09-01", "03-302", 0, 5),
                new SessionList(new Session(102, "Math", "Wetzel", "2026-09-04", "38-220", 0, 12),
                        new SessionList(new Session(103, "Biology", "Taylor", "2026-09-08", "180-216", 0, 10),
                                new SessionList(new Session(106, "Econ", "Tinsley", "2026-09-09", "Baker", 0, 2),
                                        new SessionList(new Session(104, "Statistics", "Chance", "2026-09-10", "25-205", 0, 3),
                                                new SessionList(new Session(105, "Journalism", "Riley", "2026-09-11", "26-210", 0, 1), null)))))), SessionList.addSession(a, b));
    }

    @Test
    void addSession2() {
        assertEquals(new SessionList(new Session(106, "Econ", "Tinsley", "2026-09-09", "Baker", 0, 2), null), SessionList.addSession(null, b));
    }


    @Test
    void addSession3() {
        assertThrows(IllegalArgumentException.class, () -> SessionList.addSession(new SessionList(b, null), b));
    }


    //Tests for display method:
     @Test
     void display1() {
         assertEquals("106 Econ Tinsley 2026-09-09 Baker 2\n--------------------\n",
                 SessionList.display(new SessionList(b, null)));
     }

     @Test
     void display2(){
        assertEquals("106 Econ Tinsley 2026-09-09 Baker 2\n--------------------\n" +
                "107 Math Tinsley 2026-01-29 Frost 3\n--------------------\n",
                SessionList.display(new SessionList(b, new SessionList(c, null))));
     }

     @Test
     void display3(){
        assertEquals("", SessionList.display(null));
     }


    //Tests for searchBy (both ID and mentor)
    @Test
    void searchByID1(){
        assertEquals(new Session(104, "Statistics", "Chance", "2026-09-10", "25-205", 0, 3), SessionList.searchByID(a, 104));
    }

    @Test
    void searchbyID2(){
        assertEquals(null, SessionList.searchByID(a, 201));
    }

    @Test
    void searchbyID3(){
        assertEquals(null, SessionList.searchByID(null, 302));
    }

    @Test
    void searchbyMentor1(){
        assertEquals(new SessionList(b, new SessionList(c, null)), SessionList.searchByMentor(new SessionList(d, new SessionList(b, new SessionList(c, null))), "Tinsley"));
    }

    @Test
    void searchbyMentor2(){
        assertEquals(null, SessionList.searchByMentor(a, "Tinsley"));
    }

    @Test
    void searchbyMentor3(){
        assertEquals(null, SessionList.searchByMentor(null, "Gin"));
    }

    // Tests for remove session method:
    @Test
    void removeSession1(){
        assertEquals(new SessionList(new Session(101, "Business", "Arden", "2026-09-01", "03-302", 0, 5),
                new SessionList(new Session(102, "Math", "Wetzel", "2026-09-04", "38-220", 0, 12),
                        new SessionList(new Session(104, "Statistics", "Chance", "2026-09-10", "25-205", 0, 3),
                                new SessionList(new Session(105, "Journalism", "Riley", "2026-09-11", "26-210", 0, 1), null)))),
                SessionList.removeSession(a, 103));
    }

    @Test
    void removeSession2(){
        assertEquals(a, SessionList.removeSession(a, 107));
    }

    @Test
    void removeSession3(){
        assertEquals(null, SessionList.removeSession(null, 101));
    }


    // Tests for replaceSession method
    @Test
    void replaceSession1() {
        assertEquals(new SessionList(new Session(101, "Business", "Arden", "2026-09-01", "03-302", 0, 5),
                        new SessionList(new Session(102, "Math", "Wetzel", "2026-09-04", "38-220", 0, 12),
                                new SessionList(new Session(103, "Biology", "Taylor", "2026-09-08", "180-216", 0, 10),
                                        new SessionList(new Session(104, "Statistics", "Robinson", "2026-09-11", "25-104", 1, 4),
                                                new SessionList(new Session(105, "Journalism", "Riley", "2026-09-11", "26-210", 0, 1), null))))),
                SessionList.replaceSession(a, new Session(104, "Statistics", "Robinson", "2026-09-11", "25-104", 1, 4)));

    }

    @Test
    void replaceSession2(){
        assertEquals(new SessionList(b, new SessionList(c, null)), SessionList.replaceSession(new SessionList(b, new SessionList(c, null)), d));
    }

    @Test
    void replaceSession3(){
        assertEquals(null, SessionList.replaceSession(null, c));
    }

    // Tests for registerParticipant method
    @Test
    void registerParticipant1(){
        assertEquals(new SessionList(new Session(106, "Econ", "Tinsley", "2026-09-09", "Baker", 1, 2), null),
                (SessionList.registerParticipant(new SessionList(b, null), 106)));
    }

    @Test
    void registerParticipant2(){
        assertThrows(IllegalArgumentException.class,
                () -> SessionList.registerParticipant(new SessionList(new Session(106, "Econ", "Tinsley", "2026-09-09", "Baker", 2, 2), null), 106));
    }

    @Test
    void registerParticipant3(){
        assertThrows(IllegalArgumentException.class,
                () -> SessionList.registerParticipant(new SessionList(new Session(106, "Econ", "Tinsley", "2026-09-09", "Baker", 2, 2), null), 107));
    }

    @Test
    void registerParticipant4(){
        assertEquals(new SessionList(new Session(106, "Econ", "Tinsley", "2026-09-09", "Baker", 1, 2), a),
                (SessionList.registerParticipant(new SessionList(b, a), 106)));
    }

}



