public record SessionList(Session first, SessionList rest) {

    public static SessionList addSession(SessionList list, Session add){
        switch(list){
            case null:
                return new SessionList(add, null);
            case SessionList(Session f, SessionList r):
                if (add.date().compareTo(f.date()) < 0) {
                    return new SessionList(add, new SessionList(f, r));
                } else {
                    return new SessionList(f, addSession(r, add));
                    }

        }
    }




    public static String display(SessionList list) {
        switch (list) {
            case null:
                return "";
            case SessionList(Session f, SessionList r):
                if (r == null) {
                    return f.id() + " " + f.title() + " " + f.mentor() + " " + f.date() + " " + f.location() + " " + f.maxParticipants() + "\n--------------------\n";
                } else {
                    return f.id() + " " + f.title() + " " + f.mentor() + " " + f.date() + " " + f.location() + " " + f.maxParticipants() + "\n--------------------\n"
                            + display(r);
                }

        }
    }


    public static SessionList searchByID(SessionList list, int session_id, String mentor) {
        switch (list) {
            case null:
                return null;
            case SessionList(Session f, SessionList r):
                if (f.id() == session_id) {
                    return new SessionList(f, null);
                } else if ((mentor == f.mentor())) {  // figure out how to do null id here
                    return new SessionList(f, searchByID(r, session_id, mentor));}
                else{
                    return searchByID(r, session_id, mentor);
                }

        }
    }

    public static SessionList removeSession(SessionList list, int session_id){
        switch(list){
            case null:
                return null;
            case SessionList(Session f, SessionList r):
                if (f.id() == session_id){
                    return removeSession(r, session_id);}
                else{
                    return new SessionList(f, removeSession(r, session_id));
                }
        }
    } // NEED TO MAKE SURE THAT THERE ARE NO REPEATS IN LIST

}