package model;

  enum Type{
      BLOQUEADO,
      ADENTRO,
      AFUERA;

  }
public class ClientState {

       private int id;
       private Type type;

    public ClientState(int id, Type type) {
        this.id = id;
        this.type = type;
    }

    public ClientState() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}


