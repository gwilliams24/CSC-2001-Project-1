public record SessionList(Session first, SessionList rest) {

    public static SessionList addSession(Session new_session, SessionList rest){
        switch(rest){
            case null:
                return new SessionList(new_session, null);
            case SessionList(Session f, SessionList r):
                if (r == null) {
                    if (f.date().compareTo(new_session.date()) < 0) {
                        return new SessionList(f, new SessionList(new_session, null));
                    } else {
                        return new SessionList(new_session, new SessionList(f, null));
                    }
                } else {
                    return new SessionList(f, addSession(r));
                }
        }
    }

    public static Session sortSession (SessionList list) {
        switch(list){
            case null:
                return null;
            case SessionList(Session f, SessionList r):

        }
    }


    public static String display(SessionList list) {
        switch (list) {
            case null:
                return "";
            case SessionList(Session f, SessionList r):
                if (r == null) {
                    return f.id() + " " + f.title() + " " + f.mentor() + " " + f.date() + " " + f.location() + " " + f.currentParticipants() + " " + f.maxParticipants() + "\n--------------------\n";
                } else {
                    return f.id() + " " + f.title() + " " + f.mentor() + " " + f.date() + " " + f.location() + " " + f.currentParticipants() + " " + f.maxParticipants() + "\n--------------------\n" + display(r);
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
                } else if ((mentor == f.mentor()) && ) {  // figure out how to do null id here
                    return new SessionList(f, searchByID(r, session_id, mentor));}
                else{
                    return searchByID(r, session_id, mentor);
                }

        }
    }

}