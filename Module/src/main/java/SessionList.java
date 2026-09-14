import java.util.Objects;

public record SessionList(Session first, SessionList rest) {

    public static SessionList replaceSession(SessionList list, Session replace) {
        switch (list) {
            case null:
                return null;
            case SessionList(Session f, SessionList r):
                if (f.id() == replace.id()) {
                    return new SessionList(replace, r);
                } else {
                    return new SessionList(f, replaceSession(r, replace));
                }
        }
    }

    public static SessionList addSession(SessionList list, Session add) {
        switch (list) {
            case null:
                return new SessionList(add, null);
            case SessionList(Session f, SessionList r):
                if (f.id() == add.id()) {
                    throw new IllegalArgumentException();
                }
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

    public static Session searchByIDID(SessionList list, int session_id) {
        switch (list) {
            case null:
                return null;
            case SessionList(Session f, SessionList r):
                if (f.id() == session_id) {
                    return f;
                } else {
                    return searchByIDID(r, session_id);
                }

        }
    }

    public static SessionList searchByIDMentor(SessionList list, String mentor) {
        switch (list) {
            case null:
                return null;
            case SessionList(Session f, SessionList r):
                if ((Objects.equals(mentor, f.mentor()))) {  // figure out how to do null id here
                    return new SessionList(f, searchByIDMentor(r, mentor));
                } else {
                    return searchByIDMentor(r, mentor);
                }
        }
    }

    public static SessionList removeSession(SessionList list, int session_id) {
        switch (list) {
            case null:
                return null;
            case SessionList(Session f, SessionList r):
                if (f.id() == session_id) {
                    return removeSession(r, session_id);
                } else {
                    return new SessionList(f, removeSession(r, session_id));
                }
        }
    }

    public static SessionList registerParticipant(SessionList list, int id) {
        switch (list) {
            case null:
                throw new IllegalArgumentException();
            case SessionList(Session f, SessionList r):
                if (f.id() == id) {
                    int participants = f.currentParticipants() + 1;
                    if (participants > f.maxParticipants()) {
                        throw new IllegalArgumentException();
                    } else {
                        return replaceSession(list, new Session(id, f.title(), f.mentor(), f.date(), f.location(),
                                participants, f.maxParticipants()));
                    }
                }
                else {
                    return registerParticipant(r, id);
                }
        }
    }
}