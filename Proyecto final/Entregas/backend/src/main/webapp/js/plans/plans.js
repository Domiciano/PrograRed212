var clientsContainer = document.getElementById("clientsContainer")

const getCardInfoPlans = async () =>{
    let response1 = await fetch("http://localhost:8080/backend/api/cls/getclients")
    let clients = await response1.json();
    for(let i in clients){
        let client = clients[i];
        let response2 = await fetch("http://localhost:8080/backend/api/cls/cardInfo/" + client.natId)
        let card = await response2.json();
        let cardView = new Card(card[0]);
        cardView.render(clientsContainer);
        
    }
}

getCardInfoPlans();

const create = async ()=>{

}