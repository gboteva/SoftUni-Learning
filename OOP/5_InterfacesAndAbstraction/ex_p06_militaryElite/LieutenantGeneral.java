package ex_p06_militaryElite;

import java.util.Map;

public interface LieutenantGeneral {
    Map<Integer, Private> getPrivate();
    void addPrivate(PrivateImpl priv);

}
