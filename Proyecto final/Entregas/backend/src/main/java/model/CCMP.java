package model;

// Esta clase se llama así por las iniciales de client-membership-plan, su función es relacionar a cada cliente
// con su membresía, el plan que usa la misma y con su status. Esto con la función de tener accesibilidad completa a la información
// de cada cliente y su afiliación a Buddy Tech.
public class CCMP {

    private Client client;
    private ClientState clientState;
    private Membership membership;
    private Plan plan;

    public CCMP(Client client, ClientState clientState, Membership membership, Plan plan){
        this.client = client;
        this.clientState = clientState;
        this.membership = membership;
        this.plan = plan;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientState getClientState() {
        return clientState;
    }

    public void setClientState(ClientState clientState) {
        this.clientState = clientState;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
